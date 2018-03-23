package io.crossbar.autobahn.sdjx.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.activity.MessageDetailActivity;
import io.crossbar.autobahn.sdjx.db.MessageDB;
import io.crossbar.autobahn.sdjx.utils.DateUtils;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;
import io.crossbar.autobahn.sdjx.utils.ToastUtil;
import io.crossbar.autobahn.sdjx.widget.SideslipView;

/**
 * Created by Administrator on 2018/1/31 0031.
 */

public class MessageFragmentAdapter extends BaseAdapter {
    Context context;
    List<MessageDB> messageDBList;
    List< SideslipView> mylist=new ArrayList<>();
    public MessageFragmentAdapter(Context context,List<MessageDB> messageDBList) {
        super();
        this.context = context;
        this.messageDBList = messageDBList;
    }
    @Override
    public int getCount() {
        return messageDBList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        int loc=i;//记录旧点
        if (view == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(context, R.layout.item_slideslip, null);
//            view = View.inflate(context, R.layout.item_messagefragment, null);
            viewHolder.tv =  view.findViewById(R.id.tv_messagefragment_name);
            viewHolder.tv_item_messagefragment_contain =  view.findViewById(R.id.tv_item_messagefragment_contain);
            viewHolder.ll_slideslip =  view.findViewById(R.id.ll_slideslip);
            viewHolder.sideslipView =  view.findViewById(R.id.sideslipView);
            mylist.add(viewHolder.sideslipView );
            viewHolder.tv_item_messagefragment_name =  view.findViewById(R.id.tv_item_messagefragment_name);
            viewHolder.tv_item_messagefragment_time =  view.findViewById(R.id.tv_item_messagefragment_time);
            viewHolder.iv_mes_icon =  view.findViewById(R.id.iv_mes_icon);
            viewHolder.btn_delete =  view.findViewById(R.id.btn_delete);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //设置每个空间属性
        if (messageDBList.get(i).getStore_msg_leixing().equals("1")){
            viewHolder.tv.setText("申请通知：盛大金禧");
            viewHolder.iv_mes_icon.setImageResource(R.mipmap.message_apply);
        }else if (messageDBList.get(i).getStore_msg_leixing().equals("2")){
            viewHolder.tv.setText("审批");
        }else if (messageDBList.get(i).getStore_msg_leixing().equals("3")){
            viewHolder.tv.setText("抄送");
        }else {
            viewHolder.tv.setText("其他");
        }
        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast("删除"+i);
                messageDBList.remove(i);
                notifyDataSetChanged();
                for (int i=0;i<mylist.size();i++){
                    mylist.get(i).close();
                }
            }
        });
        viewHolder.ll_slideslip .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<mylist.size();i++){
                    mylist.get(i).close();
                }
                Intent intent = new Intent(context, MessageDetailActivity.class);
                intent.putExtra("messagetitle", messageDBList.get(i).getCurr_approve_name());
                context.startActivity(intent);
            }
        });
        viewHolder.tv_item_messagefragment_contain.setText(messageDBList.get(i).getVal());
        viewHolder.tv_item_messagefragment_name.setText(PreferenceUtil.getString("pname","无"));
        viewHolder.tv_item_messagefragment_time.setText(DateUtils.timedate(messageDBList.get(i).getMsg_sendtime()+""));
        return view;
    }
    static class ViewHolder {
        private TextView tv;
        private TextView tv_item_messagefragment_contain;
        private TextView tv_item_messagefragment_name;
        private TextView tv_item_messagefragment_time;
        private ImageView iv_mes_icon;
        private Button btn_delete;
        private LinearLayout ll_slideslip;
        private SideslipView sideslipView;
    }
}
