package io.crossbar.autobahn.sdjx.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import io.crossbar.autobahn.sdjx.MyApplication;
import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.activity.MainActivity;
import io.crossbar.autobahn.sdjx.adapter.MessageFragmentAdapter;
import io.crossbar.autobahn.sdjx.bean.MessageSocketBean;
import io.crossbar.autobahn.sdjx.db.MessageDB;
import io.crossbar.autobahn.sdjx.db.MessageDB_;
import io.crossbar.autobahn.sdjx.receive.MyReceiver;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;


/**
 * 消息首页
 */
public class MessageFragment extends BaseFragment {

    MainActivity activity;
    private ListView lv_messagefragment_contant;
    private ImageView imgExit;
    private TextView content_title_img;
    private TextView content_tv_title;
    private SmartRefreshLayout SmartRefreshLayout;
    //广播收到的消息
    private String broadcastmsg;
    //消息数据库
    private Box<MessageDB> notesBox;
    private Query<MessageDB> notesQuery;
    private List<MessageDB> mymessagelist = new ArrayList<>();
    Gson gson;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void init() {
    }

    MyReceiver myReceiver = new MyReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            broadcastmsg = intent.getStringExtra("msgsdjx");
            if (broadcastmsg.equals("{\"type\":\"whoislogintype\"}")) {
//                ToastUtil.showToast("有人下线");
            } else if (broadcastmsg.startsWith("{\"type\":\"approvefrist_app\"")) {
                MessageSocketBean messageSocketBean = gson.fromJson(broadcastmsg, MessageSocketBean.class);
                //请假申请提交
                MessageDB messageDB = new MessageDB();
                messageDB.setApply_name(messageSocketBean.apply_name);
                messageDB.setApply_no(messageSocketBean.apply_no);
                messageDB.setApprove_rea(messageSocketBean.approve_rea);
                messageDB.setType(messageSocketBean.type);
                messageDB.setVal(messageSocketBean.val);
                messageDB.setNtel(messageSocketBean.ntel);
                messageDB.setTo_ntel(messageSocketBean.to_ntel);
                messageDB.setCurr_approve_name(messageSocketBean.curr_approve_name);
                messageDB.setStore_msg_leixing(messageSocketBean.store_msg_leixing);
                messageDB.setMsg_sendtime(messageSocketBean.msg_sendtime);
                messageDB.setFlow_ptype(messageSocketBean.flow_ptype);
                messageDB.setCurr_note_id(messageSocketBean.curr_note_id);
                messageDB.setNext_note_id(messageSocketBean.next_note_id);
                messageDB.setApprove_res(messageSocketBean.approve_res);
                messageDB.setStore_msg_id(messageSocketBean.store_msg_id);
                notesBox.put(messageDB);
                mymessagelist.clear();
                for (int i = notesQuery.find().size() - 1; i >= 0; i--) {
                    mymessagelist.add(notesQuery.find().get(i));
                }
                lv_messagefragment_contant.setAdapter(new MessageFragmentAdapter(activity, mymessagelist));
            }
        }
    };
    @Override
    public View initView() {
        //获取数据库对象
        BoxStore boxStore = MyApplication.getInstance().getBoxStore();
        notesBox = boxStore.boxFor(MessageDB.class);
        notesQuery = notesBox.query().order(MessageDB_.msg_sendtime).build();
        View view = View.inflate(getActivity(), R.layout.fragment_message, null);
        gson = new Gson();
        //注册广播接收
        IntentFilter filter = new IntentFilter(Intent.ACTION_EDIT);
        getActivity().registerReceiver(myReceiver, filter);
        imgExit = view.findViewById(R.id.imgExit);
        SmartRefreshLayout = view.findViewById(R.id.refreshLayout);
        imgExit.setVisibility(View.GONE);
        content_tv_title = view.findViewById(R.id.content_tv_title);
        content_tv_title.setText("消息");
        content_title_img = view.findViewById(R.id.content_title_img);
        lv_messagefragment_contant = view.findViewById(R.id.lv_messagefragment_contant);
        for (int i = notesQuery.find().size() - 1; i >= 0; i--) {
            mymessagelist.add(notesQuery.find().get(i));
        }
        lv_messagefragment_contant.setAdapter(new MessageFragmentAdapter(activity, mymessagelist));
        return view;
    }

    @Override
    public void initData() {
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initListener() {
        super.initListener();
        SmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                mymessagelist.clear();
                for (int i = notesQuery.find().size() - 1; i >= 0; i--) {
                    mymessagelist.add(notesQuery.find().get(i));
                }
                lv_messagefragment_contant.setAdapter(new MessageFragmentAdapter(activity, mymessagelist));
            }
        });
        SmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
            }
        });
        SmartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()).setAccentColor(R.color.white));
////设置 Footer 为 球脉冲
//        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(myReceiver);
        super.onDestroy();
    }
}
