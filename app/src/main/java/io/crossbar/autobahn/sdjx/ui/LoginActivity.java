///////////////////////////////////////////////////////////////////////////////
//
//   AutobahnJava - http://crossbar.io/autobahn
//
//   Copyright (c) Crossbar.io Technologies GmbH and contributors
//
//   Licensed under the MIT License.
//   http://www.opensource.org/licenses/mit-license.php
//
///////////////////////////////////////////////////////////////////////////////

package io.crossbar.autobahn.sdjx.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crossbar.autobahn.sdjx.MyApplication;
import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.bean.IMmessageBean;
import io.crossbar.autobahn.sdjx.bean.LoginBean;
import io.crossbar.autobahn.sdjx.bean.LoginBeanError;
import io.crossbar.autobahn.sdjx.bean.MessageBean;
import io.crossbar.autobahn.sdjx.bean.TokenBean;
import io.crossbar.autobahn.sdjx.manager.HttpManager;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;
import io.crossbar.autobahn.websocket.WebSocketConnection;
import io.crossbar.autobahn.websocket.WebSocketConnectionHandler;
import io.crossbar.autobahn.websocket.exceptions.WebSocketException;
import io.crossbar.autobahn.websocket.interfaces.IWebSocket;
import io.crossbar.autobahn.websocket.types.WebSocketOptions;

import static io.crossbar.autobahn.sdjx.manager.HttpManager.BaseUrl;


public class LoginActivity extends BaseActivity {
//    private static String BaseUrl = "http://192.168.1.199";
    static final String TAG = "io.crossbar.autobahn.echo";
    private static final String PREFS_NAME = "AutobahnAndroidEcho";

    public EditText mHostname;
    public EditText mPort;
    public TextView mStatusline;
    public Button mStart;
    public EditText mMessage;
    public EditText sendto;
    public EditText password;
    public EditText username;
    public Button mSendMessage;
    public Button login;
    public Button exit;
    public TextView tv_msg;
    public LinearLayout ll_title;
    public List<MessageBean> mlist;
    public Gson Gson = new Gson();
    private SharedPreferences mSettings;
    public static Gson gson;

    @Override
    public void initListener() {
        //发送消息
        mSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map map2 = new HashMap();
                map2.put("type", "talk");
                map2.put("val", mMessage.getText().toString());
                map2.put("to_ntel", sendto.getText().toString().trim());//15116193179
                String mapToJson2 = gson.toJson(map2);
                mConnection.sendMessage(mapToJson2);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    getToken();
                }
            }
        });
        //退出
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                logout();
            }
        });
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        gson = new Gson();
        mHostname = findViewById(R.id.hostname);
        mPort = findViewById(R.id.port);
        mStatusline = findViewById(R.id.statusline);
        mStart = findViewById(R.id.start);
        mMessage = findViewById(R.id.msg);
        mSendMessage = findViewById(R.id.sendMsg);
        login = findViewById(R.id.login);
        exit = findViewById(R.id.exit);
        sendto = findViewById(R.id.sendto);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        tv_msg = findViewById(R.id.tv_msg);
        ll_title = findViewById(R.id.ll_title);
        mSettings = getSharedPreferences(PREFS_NAME, 0);
        mlist = new ArrayList<>();
        mSendMessage.setEnabled(false);
        mMessage.setEnabled(false);
        loadPrefs();
        setButtonConnect();
    }

    private void alert(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    private void loadPrefs() {
        mHostname.setText(mSettings.getString("hostname", "192.168.1.199"));
        mPort.setText(mSettings.getString("port", "8282"));
    }

    private void savePrefs() {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("hostname", mHostname.getText().toString());
        editor.putString("port", mPort.getText().toString());
        editor.commit();
    }

    private void setButtonConnect() {
        mHostname.setEnabled(true);
        mPort.setEnabled(true);
        mStart.setText("Connect");
        mStart.setOnClickListener(v -> start());
    }

    private void setButtonDisconnect() {
        mHostname.setEnabled(false);
        mPort.setEnabled(false);
        mStart.setText("Disconnect");
        mStart.setOnClickListener(v -> mConnection.sendClose());
    }

    public static final IWebSocket mConnection = new WebSocketConnection();

    @SuppressLint("LongLogTag")
    private void start() {
        String hostname = mHostname.getText().toString();
        if (!hostname.startsWith("ws://") && !hostname.startsWith("wss://")) {
            hostname = "ws://" + hostname;
        }
        String port = mPort.getText().toString();

        String wsuri;
        if (!port.isEmpty()) {
            wsuri = hostname + ":" + port;
        } else {
            wsuri = hostname;
        }

        mStatusline.setText("Status: Connecting to " + wsuri + " ..");


        setButtonDisconnect();
        WebSocketOptions connectOptions = new WebSocketOptions();
        connectOptions.setReconnectInterval(5000);

        try {
            mConnection.connect(wsuri, new WebSocketConnectionHandler() {
                @Override
                public void onOpen() {
                    mStatusline.setText("Status: Connected to " + wsuri);
                    savePrefs();
                    mSendMessage.setEnabled(true);
                    mMessage.setEnabled(true);
                }

                @Override
                public void onMessage(String payload) {
                    alert("Got echo: " + payload);
                    Log.d("Got", payload);
                    if (payload.startsWith("{\"type\":\"talk\"")) {
                        IMmessageBean iMmessageBean = Gson.fromJson(payload, IMmessageBean.class);
                        tv_msg.setText("及时消息:    " + iMmessageBean.getVal());
                    } else if (payload.startsWith("{\"type\":\"storeMsg\"")) {
                        StringBuffer asd = new StringBuffer();
                        MessageBean messageBean = Gson.fromJson(payload, MessageBean.class);
                        for (int i = 0; i < messageBean.getInfo().size(); i++) {
                            asd = asd.append(messageBean.getInfo().get(i).getVal() + " : ");
                        }
                        tv_msg.setText("历史消息:    " + asd.toString());
                    } else {
                    }
                }

                @Override
                public void onClose(int code, String reason) {
                    alert("Connection lost.");
                    mStatusline.setText("Status: Ready.");
                    setButtonConnect();
                    mSendMessage.setEnabled(false);
                    mMessage.setEnabled(false);
                }
            }, connectOptions);
        } catch (WebSocketException e) {
            Log.d(TAG, e.toString());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mConnection.isConnected()) {
//            mConnection.sendClose();
//        }
    }

    //    /测试登录
    private void login() {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("username", username.getText().toString().trim());//18898730808
        parmas.put("password", password.getText().toString().trim());//a123456
        parmas.put("dev_type", MyApplication.LOGINTYPE);//设备类型
        parmas.put("captcha", "1234");
        parmas.put("v_code", "1234");
        HttpManager.postAsync(BaseUrl + "/oainterface/login/login.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("登录login", response.toString());
                        try {
                            LoginBean loginBean = gson.fromJson(response, LoginBean.class);
                            if (loginBean.getCode().equals("1")) {
                                Map map = new HashMap();
                                map.put("type", "loginphone");
                                map.put("ntel", username.getText().toString().trim());
                                String mapToJson = Gson.toJson(map);
                                mConnection.sendMessage(mapToJson);
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(LoginActivity.this,ChatActivity.class));
                            }
                        } catch (Exception e) {
                            LoginBeanError loginBeanError = gson.fromJson(response, LoginBeanError.class);
                            if (loginBeanError.getErrcode().equals("10014")) {
                                Map map = new HashMap();
                                map.put("type", "phoneLogon");
                                map.put("ntel", username.getText().toString().trim());
                                String mapToJson = Gson.toJson(map);
                                mConnection.sendMessage(mapToJson);
                                Toast.makeText(LoginActivity.this, loginBeanError.getErrmsg(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, loginBeanError.getErrmsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void getToken() {
//        start();
        Map<String, String> parmas = new HashMap<>();
        parmas.put("token", "sendking");
        parmas.put("index", "0");
        HttpManager.postAsync(BaseUrl + "/oainterface/vali/create_access_token_inter.html?", parmas,
                new HttpManager.ResultCallback<TokenBean>() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(TokenBean response) {
                        Log.d("登录getToken", response.toString());
                        PreferenceUtil.commitString("access_token", response.getAccess_token());
                        PreferenceUtil.commitString("ack", response.getAck());
                        login();
                    }
                });
    }

    private void logout() {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("username", username.getText().toString().trim());
        parmas.put("dev_type", MyApplication.LOGINTYPE);
        HttpManager.postAsync(BaseUrl + "oainterface/login/logout.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("logout", response);
                        if (mConnection.isConnected()) {
                            mConnection.sendClose();
                        }
                    }
                });
    }

}
