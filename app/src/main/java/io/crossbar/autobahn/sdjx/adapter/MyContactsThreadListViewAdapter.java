package io.crossbar.autobahn.sdjx.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.bean.ThreadContactBean;

/**
 * Created by Administrator on 2018/2/6 0006.
 */

public class MyContactsThreadListViewAdapter extends BaseAdapter {
    private List<ThreadContactBean.MsgBean.StaffBean> groups=new ArrayList<>();
    private Context context;
    TextView tv_group;
    TextView tv_contacts_count;
    public MyContactsThreadListViewAdapter(List<ThreadContactBean.MsgBean.StaffBean> groups, Context context) {
        super();
        this.groups=groups;
        this.context=context;
    }
    @Override
    public int getCount() {
        return groups.size();

    }
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=View.inflate(context,R.layout.list_item,null);
        TextView tv_contact_name = (TextView) view1
                .findViewById(R.id.tv_contact_name);
        TextView tv_contactperson_item = (TextView) view1
                .findViewById(R.id.tv_contactperson_item);
        TextView tv_contact_state = (TextView) view1
                .findViewById(R.id.tv_contact_state);
        TextView tv_contact_position = (TextView) view1
                .findViewById(R.id.tv_contact_position);
        tv_contactperson_item.setText(groups.get(i).organ_name.substring(0,1));
        tv_contact_name.setText(groups.get(i).organ_name);
        tv_contact_position.setText(groups.get(i).rank_name);
        if (groups.get(i).pad_isonline==1||groups.get(i).phone_isonline==1){
            tv_contact_state.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            tv_contact_state.setText("在线");
        }else {
            tv_contact_state.setTextColor(context.getResources().getColor(R.color.gray1));
            tv_contact_state.setText("离线");
        }
        return view1;

    }
    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
