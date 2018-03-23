package io.crossbar.autobahn.sdjx.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.activity.MainActivity;
import io.crossbar.autobahn.sdjx.activity.PersonInforSetActivity;
import io.crossbar.autobahn.sdjx.bean.PersondataBean;
import io.crossbar.autobahn.sdjx.manager.HttpManager;
import io.crossbar.autobahn.sdjx.utils.DateUtils;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;

import static io.crossbar.autobahn.sdjx.manager.HttpManager.BaseUrl;


/**
 * 我的主页
 */
public class MeFragment extends BaseFragment {

    MainActivity activity;
    private ImageView content_title_img;
    private LinearLayout ll_fragmentme_set;
    private TextView tv_mefragment_name;
    private TextView tv_mefragment_depart;
    private TextView tv_mefragment_position;
    private TextView tv_mefragment_birthday;
    private TextView tv_mefragment_tel;
    private TextView tv_mefragment_email;
    Gson gson;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void init() {

    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_me, null);
        gson = new Gson();
        content_title_img = view.findViewById(R.id.content_title_me_img);
        ll_fragmentme_set = view.findViewById(R.id.ll_fragmentme_set);
        tv_mefragment_depart = view.findViewById(R.id.tv_mefragment_depart);
        tv_mefragment_position = view.findViewById(R.id.tv_mefragment_position);
        tv_mefragment_birthday = view.findViewById(R.id.tv_mefragment_birthday);
        tv_mefragment_tel = view.findViewById(R.id.tv_mefragment_tel);
        tv_mefragment_name = view.findViewById(R.id.tv_mefragment_name);
        tv_mefragment_email = view.findViewById(R.id.tv_mefragment_email);
        ll_fragmentme_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PersonInforSetActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        getpersondata(PreferenceUtil.getString("position", ""));
    }

    //查询个人信息
    private void getpersondata(String id) {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("dev_type", "1");
        parmas.put("username", PreferenceUtil.getString("username", ""));
        parmas.put("password", PreferenceUtil.getString("password", ""));
        parmas.put("uid", id);
        HttpManager.postAsync(BaseUrl + "/oainterface/Interfe/personal_data.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.d("个人信息", response.toString());
                        PersondataBean persondataBean = gson.fromJson(response, PersondataBean.class);
                        String sex = persondataBean.msg.staff.organ_famale.equals("1") ? "男" : "女";
                        tv_mefragment_name.setText(PreferenceUtil.getString("pname", "") + "   " + sex);
                        tv_mefragment_depart.setText(persondataBean.msg.staff.dment_name);
                        tv_mefragment_position.setText(persondataBean.msg.staff.position_name);
                        tv_mefragment_birthday.setText(DateUtils.timedate(persondataBean.msg.staff.organ_newtime+"").substring(0,10));
                        tv_mefragment_tel.setText(persondataBean.msg.staff.organ_tel);
                        tv_mefragment_email.setText(persondataBean.msg.staff.organ_email);
                    }
                });
    }
}
