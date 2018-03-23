package io.crossbar.autobahn.sdjx.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crossbar.autobahn.sdjx.MyApplication;
import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.adapter.myAdapte;

/**
 * Created by Administrator on 2018/1/31 0031.
 */

public class ChatActivity extends LoginActivity {
private ListView listView;
private Button btn_send;
private EditText et_text;
private List<String> mlist;
    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_chat);
        initData();
        initEvent();
    }

    private void initEvent() {
        btn_send.setOnClickListener(v->
                sendMsg(et_text.getText().toString().trim(),"18229863760"));
    }

    private void initData() {
        mlist.add("ceshi");
        listView.setAdapter(new myAdapte(mlist,this));
    }

    public void initView() {
    mlist=new ArrayList<String>();
        listView=findViewById(R.id.lv_chat);
        btn_send=findViewById(R.id.btn_send);
        et_text=findViewById(R.id.et_text);
    }
    public void sendMsg(String msg,String tosb){
        mlist.add(msg);
        Map map2 = new HashMap();
        map2.put("type", "talk");
        map2.put("val",msg);
        map2.put("to_ntel", tosb);//15116193179
        String mapToJson2 = gson.toJson(map2);
        MyApplication.mConnection.sendMessage(mapToJson2);
    }
}