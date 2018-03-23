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
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.adapter.MyContactsSecondListViewAdapter;
import io.crossbar.autobahn.sdjx.bean.ContactInfo;
import io.crossbar.autobahn.sdjx.bean.SecondContactsBean;
import io.crossbar.autobahn.sdjx.manager.HttpManager;
import io.crossbar.autobahn.sdjx.receive.MyReceiver;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;

import static io.crossbar.autobahn.sdjx.manager.HttpManager.BaseUrl;

/**
 * 通讯录二级页面部门列表
 */

public class MyContactsDetalActivity extends BaseActivity {
    private TextView content_tv_title;
    private ImageView imgExit;
    private ImageView content_title_img;
    private LinearLayout iv_mycontact_nouser;
    private ListView lv_mycontactdetalsactivity;
    //Model：部门的数据
    private List<ContactInfo> groups = new ArrayList<>();
    public static Gson gson = new Gson();

    @Override
    public void initListener() {
        imgExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //广播收到的消息
    private String broadcastmsg;
    MyReceiver myReceiver=new MyReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            broadcastmsg=intent.getStringExtra("msgsdjx");
            if (broadcastmsg.equals("{\"type\":\"whoislogintype\"}")){
                getSecondContact(getIntent().getStringExtra("mycontactstitleid"));
            }
        }
    };
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {
        setContentView(R.layout.activity_mycontactsdetal);
        //注册广播接收
        IntentFilter filter = new IntentFilter(Intent.ACTION_EDIT);
        registerReceiver(myReceiver, filter);
        content_tv_title = findViewById(R.id.content_tv_title);
        content_title_img = findViewById(R.id.content_title_img);
        lv_mycontactdetalsactivity = findViewById(R.id.lv_mycontactdetalsactivity);
        iv_mycontact_nouser = findViewById(R.id.iv_mycontact_nouser);
        getSecondContact(getIntent().getStringExtra("mycontactstitleid"));
        imgExit = findViewById(R.id.imgExit);
        content_title_img.setVisibility(View.GONE);
        imgExit.setImageResource(R.mipmap.ic_back);
        content_tv_title.setText(getIntent().getStringExtra("mycontactstitle"));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }


    public void getthread(String id) {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("dev_type", "1");
        parmas.put("username", PreferenceUtil.getString("username", ""));
        parmas.put("password", PreferenceUtil.getString("password", ""));
        parmas.put("uid", PreferenceUtil.getString("uid", ""));//个人登录ID(返回本人所在公司的所有部门)
        parmas.put("department_id", id);//个人登录ID(返回本人所在公司的所有部门)
        HttpManager.postAsync(BaseUrl + "/oainterface/Interfe/mail_department.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(MyContactsDetalActivity.this, MyContactsPersonDetalActivity.class);
                        intent.putExtra("personinfo", response);
                        startActivity(intent);
                    }
                });
    }
    int count1=0;
    int count2=0;
    //获取通讯录二级
    private void getSecondContact( String id) {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("dev_type", "1");
        parmas.put("username", PreferenceUtil.getString("username", ""));
        parmas.put("password", PreferenceUtil.getString("password", ""));
        parmas.put("uid", PreferenceUtil.getString("uid", ""));//个人登录ID(返回本人所在公司的所有部门)
        parmas.put("department_id", id);//个人登录ID(返回本人所在公司的所有部门)
        HttpManager.postAsync(BaseUrl + "/oainterface/Interfe/mail_department.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.d("getSecondContact1", response.toString());
                        groups.clear();
                        SecondContactsBean secondContactBean = gson.fromJson(response, SecondContactsBean.class);
                        if (secondContactBean.code == 1&&secondContactBean.msg.department!=null) {
                            groups.add(new ContactInfo(secondContactBean.msg.department_info.dment_name,secondContactBean.msg.department_info.dment_lofficial,secondContactBean.msg.department_info.all_count, secondContactBean.msg.department_info.all_on_line, secondContactBean.msg.department_info.dment_id));
                            for (int i = 0; i < secondContactBean.msg.department.size(); i++) {
                                groups.add(new ContactInfo(secondContactBean.msg.department.get(i).dment_name, secondContactBean.msg.department.get(i).dment_lofficial,secondContactBean.msg.department.get(i).count, secondContactBean.msg.department.get(i).count_on_line, secondContactBean.msg.department.get(i).dment_id));
                            }
                            lv_mycontactdetalsactivity.setAdapter(new MyContactsSecondListViewAdapter(groups, MyContactsDetalActivity.this));
                            lv_mycontactdetalsactivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    if (i==0){
                                        return;
                                    }
                                    Intent intent = new Intent(MyContactsDetalActivity.this, MyContactsPersonDetalActivity.class);
                                    intent.putExtra("personinfoid", groups.get(i).id + "");
                                    startActivity(intent);
                                }
                            });
                            if (groups.size() > 0) {
                                iv_mycontact_nouser.setVisibility(View.GONE);
                            } else {
                                iv_mycontact_nouser.setVisibility(View.VISIBLE);
                            }
                        }else if (secondContactBean.code == 1&&secondContactBean.msg.staff!=null){
                            groups.add(new ContactInfo(secondContactBean.msg.department_info.dment_name,secondContactBean.msg.department_info.dment_lofficial, secondContactBean.msg.department_info.all_count, secondContactBean.msg.department_info.all_on_line, secondContactBean.msg.department_info.dment_id));
                            lv_mycontactdetalsactivity.setAdapter(new MyContactsSecondListViewAdapter(groups, MyContactsDetalActivity.this));
                            lv_mycontactdetalsactivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(MyContactsDetalActivity.this, MyContactsPersonDetalActivity.class);
                                    intent.putExtra("personinfoid", groups.get(i).id + "");
                                    startActivity(intent);
                                }
                            });
                            if (groups.size() > 0) {
                                iv_mycontact_nouser.setVisibility(View.GONE);
                            } else {
                                iv_mycontact_nouser.setVisibility(View.VISIBLE);
                            }
                        }else {
                            showToast(secondContactBean.errmsg);
                            Intent intent=new Intent(MyContactsDetalActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}
