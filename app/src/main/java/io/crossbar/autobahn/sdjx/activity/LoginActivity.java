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

package io.crossbar.autobahn.sdjx.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crossbar.autobahn.sdjx.MyApplication;
import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.bean.LoginBean;
import io.crossbar.autobahn.sdjx.bean.MessageBean;
import io.crossbar.autobahn.sdjx.bean.TokenBean;
import io.crossbar.autobahn.sdjx.manager.HttpManager;
import io.crossbar.autobahn.sdjx.utils.Code;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;

import static io.crossbar.autobahn.sdjx.manager.HttpManager.BaseUrl;


public class LoginActivity extends BaseActivity {
    //    private static String BaseUrl = "http://192.168.1.199";
    static final String TAG = "io.crossbar.autobahn.echo";
    private static final String PREFS_NAME = "AutobahnAndroidEcho";

    public EditText password;
    public EditText username;
    public EditText ed_yzm;
    public Button login;
    public LinearLayout ll_main;
    public List<MessageBean> mlist;
    public Gson Gson = new Gson();
    private CheckBox cb_xx;
    //产生的验证码
    private String realCode = "";
    private RotateLoading rotateloading;
    @Override
    public void initListener() {
        //登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
//                } else if (ed_yzm.getText().toString().equals("")) {
//                    showToast("验证码不能为空");
//                } else if (!ed_yzm.getText().toString().equals(realCode)) {
//                    showToast("请核对验证码");
                } else if (!cb_xx.isChecked()) {
                    showToast("请阅读服务协议");
                } else {
//                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    //调用登录接口
                    getToken();
                }
            }
        });
        ll_main.setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        rotateloading=findViewById(R.id.rotateloading);
        login = findViewById(R.id.login);
        login.setClickable(false);
        username = findViewById(R.id.ed_username);
        setdrawable(username, R.mipmap.icon_username);
        password = findViewById(R.id.ed_pwd);
        setdrawable(password, R.mipmap.icon_pwd);
        ed_yzm = findViewById(R.id.ed_yzm);
        Drawable drawable3 = getResources().getDrawable(R.mipmap.icon_yzm);
        drawable3.setBounds(0, 0, 70, 70);
        Drawable drawable4 = new BitmapDrawable(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
        drawable4.setBounds(0, 0, 130, 70);
        ed_yzm.setCompoundDrawablePadding(10);
        ed_yzm.setCompoundDrawables(drawable3, null, drawable4, null);//只放左边
        ed_yzm.setOnTouchListener((v, event) -> {
            // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
            Drawable drawable = ed_yzm.getCompoundDrawables()[2];
            //如果右边没有图片，不再处理
            if (drawable == null)
                return false;
            //如果不是按下事件，不再处理
            if (event.getAction() != MotionEvent.ACTION_UP)
                return false;
            if (event.getX() > ed_yzm.getWidth()
                    - ed_yzm.getPaddingRight()
                    - drawable.getIntrinsicWidth()) {
                Drawable drawable41 = new BitmapDrawable(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                drawable41.setBounds(0, 0, 130, 70);
                ed_yzm.setCompoundDrawablePadding(10);
                ed_yzm.setCompoundDrawables(drawable3, null, drawable41, null);//只放左边
                showToast(realCode);
            }
            return false;
        });
        ll_main = findViewById(R.id.ll_main);
        cb_xx = findViewById(R.id.cb_xx);
        mlist = new ArrayList<>();
    }

    private void setdrawable(EditText v, int drawableid) {
        Drawable drawable = getResources().getDrawable(drawableid);
        drawable.setBounds(0, 0, 70, 70);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        v.setCompoundDrawablePadding(10);
        v.setCompoundDrawables(drawable, null, null, null);//只放左边
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 登录
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
                        showToast("请检查网络");
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        rotateloading.stop();
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.d("登录login", response.toString());
                        try {
                            LoginBean loginBean = gson.fromJson(response, LoginBean.class);
                            if (loginBean.org.size() > 0) {
                                PreferenceUtil.commitString("uid", loginBean.uid + "");
                                PreferenceUtil.commitString("branch", loginBean.org.get(0).organ_company);
                                PreferenceUtil.commitString("organ_tel", loginBean.org.get(0).organ_tel);
                                PreferenceUtil.commitString("department_id", loginBean.org.get(0).organ_department);
                                PreferenceUtil.commitString("username", username.getText().toString().trim());
                                PreferenceUtil.commitString("password", password.getText().toString().trim());
                                PreferenceUtil.commitString("company_id", loginBean.org.get(0).organ_company);
                                PreferenceUtil.commitString("tdepartmentid", loginBean.org.get(0).organ_tdepartment);
                                PreferenceUtil.commitString("rankid", loginBean.org.get(0).rankid + "");
                                PreferenceUtil.commitString("position", loginBean.org.get(0).organ_id + "");
                                PreferenceUtil.commitString("pname", loginBean.org.get(0).organ_name);
                                PreferenceUtil.commitString("sex", loginBean.org.get(0).organ_famale);
                                if (loginBean.code != null && "1".equals(loginBean.code)) {
                                    Map map = new HashMap();
                                    map.put("type", "loginphone");
                                    map.put("ntel", loginBean.org.get(0).organ_tel);
                                    String mapToJson = Gson.toJson(map);
                                    MyApplication.mConnection.sendMessage(mapToJson);
                                    showToast(loginBean.msg);
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else if (loginBean.errcode != null && "10014".equals(loginBean.errcode)) {
                                    Map map = new HashMap();
                                    map.put("type", "phoneLogon");
                                    map.put("ntel", loginBean.org.get(0).organ_tel);
                                    String mapToJson = Gson.toJson(map);
                                    MyApplication.mConnection.sendMessage(mapToJson);
                                    showToast(loginBean.errmsg);
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    showToast("请去完善个人信息");
                                }
                            } else {
                                showToast("请去相关部门完善个人信息");
                            }
                        } catch (Exception e) {
                            showToast("服务器异常");
                        }
                    }
                });
    }
    private void getToken() {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("token", "sendking");
        parmas.put("index", "0");
        HttpManager.postAsync(BaseUrl + "/oainterface/vali/create_access_token_inter.html?", parmas,
                new HttpManager.ResultCallback<TokenBean>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        rotateloading.start();
                    }

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
}
