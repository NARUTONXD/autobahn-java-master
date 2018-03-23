package io.crossbar.autobahn.sdjx.ui;

import android.content.Context;
import android.view.View;

import io.crossbar.autobahn.sdjx.R;


/**
 * @创建者 CSDN_LQR
 * @描述 微信
 */
public class MessageFragment2 extends BaseFragment {



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
        View view = View.inflate(getActivity(), R.layout.fragment_message2, null);

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

    private void setAdapter() {
    }



}
