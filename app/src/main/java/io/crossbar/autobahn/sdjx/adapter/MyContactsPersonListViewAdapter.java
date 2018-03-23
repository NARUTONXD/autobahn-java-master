package io.crossbar.autobahn.sdjx.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.crossbar.autobahn.sdjx.R;

/**
 * Created by Administrator on 2018/2/6 0006.
 */

public class MyContactsPersonListViewAdapter extends BaseAdapter {
    private List<String> groups=new ArrayList<>();
    private Context context;
    TextView tv_group;
    TextView tv_contacts_count;
    public MyContactsPersonListViewAdapter(List<String> groups, Context context) {
        super();
        this.groups=groups;
        this.context=context;
    }
    @Override
    public int getCount() {
        return groups.size();

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=View.inflate(context,R.layout.item_group_contacts,null);
        tv_group=view1.findViewById(R.id.tv_group);
        tv_contacts_count =view1.findViewById(R.id.tv_contacts_count);
        tv_group.setText(groups.get(i));
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
