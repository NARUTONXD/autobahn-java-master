package io.crossbar.autobahn.sdjx.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.adapter.MyMessageDetailAdapter;


/**
 * Created by Administrator on 2018/2/6 0006.
 * 消息详细界面
 */

public class MessageDetailActivity extends BaseActivity implements View.OnClickListener{
    private TextView content_tv_title;
    private ImageView imgExit;
    private LinearLayout ll_back;
    private RecyclerView myrecycle_messagedetail;
    List<String> demo=new ArrayList<>();
    @Override
    public void initView() {
        setContentView(R.layout.activity_messagedetail);
        content_tv_title = findViewById(R.id.content_tv_title);

        for (int i=0;i<10;i++){
            demo.add("测试"+i);
        }

        myrecycle_messagedetail = findViewById(R.id.myrecycle_messagedetail);
        ll_back = findViewById(R.id.ll_back);
        content_tv_title.setText(getIntent().getStringExtra("messagetitle"));
        imgExit = findViewById(R.id.imgExit);
        imgExit.setImageResource(R.mipmap.ic_back);
        myrecycle_messagedetail.setLayoutManager(new LinearLayoutManager(this));
        myrecycle_messagedetail.setAdapter(new MyMessageDetailAdapter(this,demo));
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgExit:
                finish();
                break;
            case R.id.ll_back:
                finish();
                break;
        }
    }
    @Override
    public void initListener() {
        super.initListener();
        imgExit.setOnClickListener(this);
        ll_back.setOnClickListener(this);
    }
}
