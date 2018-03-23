/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.crossbar.autobahn.sdjx.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import io.crossbar.autobahn.sdjx.MyApplication;
import io.crossbar.autobahn.sdjx.manager.HttpManager;
import io.crossbar.autobahn.sdjx.receive.MyReceiver;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;
import io.crossbar.autobahn.sdjx.utils.ToastUtil;

import static android.Manifest.permission.READ_CONTACTS;
import static io.crossbar.autobahn.sdjx.manager.HttpManager.BaseUrl;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected InputMethodManager inputMethodManager;
    public static Gson gson;
    //广播收到的消息
    private String broadcastmsg;
    MyReceiver myReceiver = new MyReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            broadcastmsg = intent.getStringExtra("msgsdjx");
//            ToastUtil.showToast(intent.getStringExtra("msgsdjx"));
            if (broadcastmsg.equals("{\"type\":\"oldphone\",\"val\":\"\\u60a8\\u7684\\u8d26\\u53f7\\u5728\\u53e6\\u5916\\u4e00\\u53f0\\u8bbe\\u5907\\u4e0a\\u767b\\u9646\\uff0c\\u60a8\\u88ab\\u8feb\\u4e0b\\u7ebf\"}")) {
                ToastUtil.showToast("您的账号在另外一台设备上登陆，您被迫下线");
                Intent intent1=new Intent(BaseActivity.this,LoginActivity.class);
                startActivity(intent1);
                finish();
//            }else if (broadcastmsg.startsWith("{\"type\":\"phoneLogon\",\"ntel\"")){
//                Intent intent1=new Intent(BaseActivity.this,MainActivity.class);
//                startActivity(intent1);
//                finish();
            }
        }
    };
    private long clickTime = 0; //记录第一次点击的时间
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        //注册广播接收
        IntentFilter filter = new IntentFilter(Intent.ACTION_EDIT);
        registerReceiver(myReceiver, filter);
        gson = new Gson();
        //检查是否有读取联系人权限
        int checkPermissionResult = ActivityCompat.checkSelfPermission(this, READ_CONTACTS);
        if (checkPermissionResult != PackageManager.PERMISSION_GRANTED) {
            //动态申请读取联系人权限
            ActivityCompat.requestPermissions(this, new String[]{READ_CONTACTS}, 666);
        }
        //http://stackoverflow.com/questions/4341600/how-to-prevent-multiple-instances-of-an-activity-when-it-is-launched-with-differ/
        // should be in launcher activity, but all app use this can avoid the problem
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initView();
        initListener();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * back
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }

    public void showToast(String content) {
        ToastUtil.showToast(content);
    }

    public void initView() {
    }

    public void initListener() {
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onDestroy() {
//        logout();
        unregisterReceiver(myReceiver);
        super.onDestroy();

    }

    private void logout() {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("username", PreferenceUtil.getString("username", ""));
        parmas.put("dev_type", MyApplication.LOGINTYPE);
        HttpManager.postAsync(BaseUrl + "/oainterface/login/logout.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.d("logout", response);
//                        if (mConnection.isConnected()) {
//                            mConnection.sendClose();
//                        }
                    }
                });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序",
                    Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
//            logout();
            this.finish();
        }
    }
}