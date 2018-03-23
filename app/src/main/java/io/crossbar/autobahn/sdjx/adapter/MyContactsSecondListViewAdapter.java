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
import io.crossbar.autobahn.sdjx.bean.ContactInfo;

/**
 * Created by Administrator on 2018/2/6 0006.
 */

public class MyContactsSecondListViewAdapter extends BaseAdapter {
    private List<ContactInfo> groups=new ArrayList<>();
    private Context context;
    TextView tv_group;
    TextView tv_contacts_count;
    TextView tv_group_name;
    public MyContactsSecondListViewAdapter(List<ContactInfo> groups, Context context) {
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
        View view1=View.inflate(context,R.layout.item_group_contacts,null);
        tv_group=view1.findViewById(R.id.tv_group);
        tv_contacts_count =view1.findViewById(R.id.tv_contacts_count);
        tv_group_name =view1.findViewById(R.id.tv_group_name);
        tv_group.setText(groups.get(i).getName());
        tv_contacts_count.setText(groups.get(i).getCount_onlint()+"/"+groups.get(i).getCount());
        tv_group_name.setText("(负责人"+groups.get(i).getDment_lofficial()+")");
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
