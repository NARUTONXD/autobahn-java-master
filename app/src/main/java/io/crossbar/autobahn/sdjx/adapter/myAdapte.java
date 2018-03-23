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
 * Created by Administrator on 2018/1/31 0031.
 */

public class myAdapte extends BaseAdapter {
    List<String> mlist=new ArrayList<>();
    Context context;
    TextView tv;
    public myAdapte(List<String> mlist,Context context) {
        super();
        mlist=mlist;
        context=context;
    }

    @Override
    public int getCount() {
        return mlist.size();
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
        view = View.inflate(context, R.layout.item_chat, null);
        tv=view.findViewById(R.id.tv);
        tv.setText(mlist.get(i));
        return view;
    }
}
