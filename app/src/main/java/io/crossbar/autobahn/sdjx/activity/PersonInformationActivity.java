package io.crossbar.autobahn.sdjx.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.bean.Person;
import io.crossbar.autobahn.sdjx.bean.PersondataBean;
import io.crossbar.autobahn.sdjx.manager.HttpManager;
import io.crossbar.autobahn.sdjx.receive.MyReceiver;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;

import static io.crossbar.autobahn.sdjx.manager.HttpManager.BaseUrl;


/**
 * Created by Administrator on 2018/2/6 0006.
 * 个人信息详细资料界面
 */

public class PersonInformationActivity extends BaseActivity {
    private TextView content_tv_title;
    private TextView tv_personinfo_icon;//logo
    private TextView tv_personinfo_name;//名字
    private TextView tv_personinfo_sex;//性别
    private TextView tv_personinfo_phone;//手机号
    private TextView tv_personinfo_department;//部门
    private TextView tv_personinfo_position;//职位
    private TextView tv_personinfo_state;//在线状态
    private TextView tv_personinfo_email;//邮箱
    private ImageView imgExit;
    private ImageView content_title_img;
    //广播收到的消息
    private String broadcastmsg;
    MyReceiver myReceiver = new MyReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            broadcastmsg = intent.getStringExtra("msgsdjx");
            if (broadcastmsg.equals("{\"type\":\"whoislogintype\"}")) {
                initdata();
            }
        }
    };

    @Override
    public void initView() {
        setContentView(R.layout.activity_personinformation);
//        Person person = (Person) getIntent().getSerializableExtra("PersonInformationActivity");
        content_tv_title = findViewById(R.id.content_tv_title);
        content_title_img = findViewById(R.id.content_title_img);
        tv_personinfo_icon = findViewById(R.id.tv_personinfo_icon);
        tv_personinfo_name = findViewById(R.id.tv_personinfo_name);
        tv_personinfo_sex = findViewById(R.id.tv_personinfo_sex);
        tv_personinfo_phone = findViewById(R.id.tv_personinfo_phone);
        tv_personinfo_department = findViewById(R.id.tv_personinfo_department);
        tv_personinfo_position = findViewById(R.id.tv_personinfo_position);
        tv_personinfo_state = findViewById(R.id.tv_personinfo_state);
        tv_personinfo_email = findViewById(R.id.tv_personinfo_email);
        content_title_img.setImageResource(R.mipmap.ic_persondetal_right);
        imgExit = findViewById(R.id.imgExit);
        imgExit.setImageResource(R.mipmap.ic_back);
        initdata();
    }

    public void initdata() {
        Person person = (Person) getIntent().getSerializableExtra("PersonInformationActivity");
        if (person != null) {
            content_tv_title.setText(person.getOrgan_name());
            tv_personinfo_icon.setText(person.getOrgan_name().substring(0, 1));
            tv_personinfo_name.setText(person.getOrgan_name());
            tv_personinfo_sex.setText(person.getOrgan_name().equals("0") ? "女" : "男");
            tv_personinfo_phone.setText(person.getOrgan_tel());
            if (person.getOrgan_tdepartmentname() != null) {
                tv_personinfo_department.setText(person.getDepartmentname() + "-" + person.getOrgan_tdepartmentname());
            } else {
                tv_personinfo_department.setText(person.getDepartmentname());
            }
            tv_personinfo_position.setText(person.getPosition_name());
            if (person.getPad_isonline() == 1 || person.getPhone_isonline() == 1) {
                tv_personinfo_state.setText("在线");
            } else {
                tv_personinfo_state.setText("离线");
            }
            tv_personinfo_email.setText(person.getOrgan_email());
        } else {
            getPersondata(getIntent().getStringExtra("PERSONIF"));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgExit:
                finish();
                break;
            //修改备注信息
            case R.id.tv_personinfo_icon:
                Intent intent1 = new Intent(PersonInformationActivity.this, PersonInforSetActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void initListener() {
        super.initListener();
        imgExit.setOnClickListener(this);
        tv_personinfo_icon.setOnClickListener(this);
    }

    private void getPersondata(String id) {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("dev_type", "1");
        parmas.put("username", PreferenceUtil.getString("username", ""));
        parmas.put("password", PreferenceUtil.getString("password", ""));
        parmas.put("uid", id);//个人登录ID(返回本人所在公司的所有部门)
        HttpManager.postAsync(BaseUrl + "/oainterface/Interfe/personal_data.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.d("personal_data", response.toString());
                        PersondataBean person = gson.fromJson(response, PersondataBean.class);
                        if (person.code==1) {
                            content_tv_title.setText(person.msg.staff.organ_name);
                            tv_personinfo_icon.setText(person.msg.staff.organ_name.substring(0, 1));
                            tv_personinfo_name.setText(person.msg.staff.organ_name);
                            tv_personinfo_sex.setText(person.msg.staff.organ_famale.equals("0") ? "女" : "男");
                            tv_personinfo_phone.setText(person.msg.staff.organ_tel);
//                        if (person.getOrgan_tdepartmentname() != null) {
//                            tv_personinfo_department.setText(person.getDepartmentname() + "-" + person.getOrgan_tdepartmentname());
//                        } else {
//                            tv_personinfo_department.setText(person.getDepartmentname());
//                        }
                            tv_personinfo_department.setText(person.msg.staff.dment_name);
                            tv_personinfo_position.setText(person.msg.staff.position_name);
                            if (person.msg.staff.pad_isonline == 1 || person.msg.staff.phone_isonline == 1) {
                                tv_personinfo_state.setText("在线");
                            } else {
                                tv_personinfo_state.setText("离线");
                            }
                            tv_personinfo_email.setText(person.msg.staff.organ_email);
                        }else {
                            showToast(person.errmsg);
                        }
                    }
                });
    }

    //拨号界面
    public void callpeople(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tv_personinfo_phone.getText().toString()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
