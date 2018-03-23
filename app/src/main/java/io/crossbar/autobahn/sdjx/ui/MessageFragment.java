package io.crossbar.autobahn.sdjx.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import io.crossbar.autobahn.sdjx.MyApplication;
import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.manager.HttpManager;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;

import static io.crossbar.autobahn.sdjx.manager.HttpManager.BaseUrl;


/**
 * @创建者 CSDN_LQR
 * @描述 微信
 */
public class MessageFragment extends BaseFragment {



    MainActivity activity;

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
        View view = View.inflate(getActivity(), R.layout.fragment_message1, null);

//        mHeaderView = View.inflate(getActivity(), R.layout.header_message_rv, null);
//        mHeaderView.setVisibility(View.GONE);


        return view;
    }

    @Override
    public void initData() {

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        setAdapter();
//    }

    //测试
    private void ceshi() {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("dev_type", MyApplication.LOGINTYPE);//设备类型
        parmas.put("username", "18229863760");//设备类型
        parmas.put("password", "123456");//设备类型
        parmas.put("rankid", "1");
        HttpManager.postAsync(BaseUrl + "/oainterface/apply/findInfoByTel_inter.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("登录login", response.toString());
                    }
                });
    }


}
