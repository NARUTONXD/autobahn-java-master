package io.crossbar.autobahn.sdjx.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.crossbar.autobahn.sdjx.R;


/**
 * Created by Administrator on 2018/2/6 0006.
 * 采购
 */

public class PurchaseActivity extends BaseActivity implements View.OnClickListener{
    private TextView content_tv_title;
    private ImageView imgExit;
    private LinearLayout ll_back;
    @Override
    public void initView() {
        setContentView(R.layout.activity_purchase);
        content_tv_title = findViewById(R.id.content_tv_title);
        ll_back = findViewById(R.id.ll_back);
        content_tv_title.setText("采购申请");
        imgExit = findViewById(R.id.imgExit);
        imgExit.setImageResource(R.mipmap.ic_back);

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
