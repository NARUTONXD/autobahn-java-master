///////////////////////////////////////////////////////////////////////////////
//
//   AutobahnJava - http://crossbar.io/autobahn
//
//   Copyright (c) Crossbar.io Technologies GmbH and contributors
//
//   Licensed under the MIT License.
//   http://www.opensource.org/licenses/mit-license.php
//
///////////////////////////////////////////////////////////////////////////////

package io.crossbar.autobahn.sdjx.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.adapter.MainPagerAdapter;
import io.crossbar.autobahn.sdjx.utils.UIUtils;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static String BaseUrl = "http://192.168.1.199";
    @Bind(R.id.vpContent)
    ViewPager vpContent;
    @Bind(R.id.tvMessageNormal)
    TextView tvMessageNormal;
    @Bind(R.id.tvMessagePress)
    TextView tvMessagePress;
    @Bind(R.id.rlMessage)
    RelativeLayout rlMessage;
    @Bind(R.id.tvMessageCount)
    TextView tvMessageCount;
    @Bind(R.id.tvMessageTextNormal)
    TextView tvMessageTextNormal;
    @Bind(R.id.tvMessageTextPress)
    TextView tvMessageTextPress;
    @Bind(R.id.llMessage)
    LinearLayout llMessage;
    @Bind(R.id.tvContactsNormal)
    TextView tvContactsNormal;
    @Bind(R.id.tvContactsPress)
    TextView tvContactsPress;
    @Bind(R.id.rlContacts)
    RelativeLayout rlContacts;
    @Bind(R.id.tvContactCount)
    TextView tvContactCount;
    @Bind(R.id.tvContactsTextNormal)
    TextView tvContactsTextNormal;
    @Bind(R.id.tvContactsTextPress)
    TextView tvContactsTextPress;
    @Bind(R.id.llContacts)
    LinearLayout llContacts;
    @Bind(R.id.tvDiscoveryNormal)
    TextView tvDiscoveryNormal;
    @Bind(R.id.tvDiscoveryPress)
    TextView tvDiscoveryPress;
    @Bind(R.id.rlDiscovery)
    RelativeLayout rlDiscovery;
    @Bind(R.id.tvDiscoveryCount)
    TextView tvDiscoveryCount;
    @Bind(R.id.tvDiscoveryTextNormal)
    TextView tvDiscoveryTextNormal;
    @Bind(R.id.tvDiscoveryTextPress)
    TextView tvDiscoveryTextPress;
    @Bind(R.id.llDiscovery)
    LinearLayout llDiscovery;
    @Bind(R.id.tvMeNormal)
    TextView tvMeNormal;
    @Bind(R.id.tvMePress)
    TextView tvMePress;
    @Bind(R.id.rlMe)
    RelativeLayout rlMe;
    @Bind(R.id.tvMeCount)
    TextView tvMeCount;
    @Bind(R.id.tvMeTextNormal)
    TextView tvMeTextNormal;
    @Bind(R.id.tvMeTextPress)
    TextView tvMeTextPress;
    @Bind(R.id.llMe)
    LinearLayout llMe;
    @Bind(R.id.llButtom)
    LinearLayout llButtom;
    @Bind(R.id.etSearch)
    EditText etSearch;
    @Bind(R.id.btnOk)
    Button btnOk;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private List<Fragment> mFragments;
    private MessageFragment mMessageFragment;
    private MessageFragment1 mMessageFragment1;
    private MessageFragment2 mMessageFragment2;
    private MessageFragment3 mMessageFragment3;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTransparency();
        tvMessagePress.getBackground().setAlpha(255);
        tvMessageTextPress.setTextColor(Color.argb(255, 69, 192, 26));
        llMessage.setOnClickListener(this);
        llContacts.setOnClickListener(this);
        llDiscovery.setOnClickListener(this);
        llMe.setOnClickListener(this);
        mFragments = new ArrayList<>();
        mMessageFragment = new MessageFragment();
        mMessageFragment1 = new MessageFragment1();
        mMessageFragment2 = new MessageFragment2();
        mMessageFragment3 = new MessageFragment3();
        mFragments.add(mMessageFragment);
        mFragments.add(mMessageFragment1);
        mFragments.add(mMessageFragment2);
        mFragments.add(mMessageFragment3);
        vpContent.setOffscreenPageLimit(4);
        //设置中间内容vp适配器
        vpContent.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), mFragments));
        vpContent.setCurrentItem(0);

        //设置ToolBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("OA");
        toolbar.setTitleTextColor(UIUtils.getColor(R.color.white));

        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
            }
        });
        refreshLayout.setRefreshHeader(new ClassicsHeader(this).setAccentColor(R.color.white));
////设置 Footer 为 球脉冲
//        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
    }

    @Override
    public void initListener() {
        //设置vp的滑动监听事件，控制底部图标渐变
        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //根据ViewPager滑动位置更改透明度
                int diaphaneity_one = (int) (255 * positionOffset);
                int diaphaneity_two = (int) (255 * (1 - positionOffset));
                switch (position) {
                    case 0:
                        tvMessageNormal.getBackground().setAlpha(diaphaneity_one);
                        tvMessagePress.getBackground().setAlpha(diaphaneity_two);
                        tvContactsNormal.getBackground().setAlpha(diaphaneity_two);
                        tvContactsPress.getBackground().setAlpha(diaphaneity_one);
                        tvMessageTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        tvMessageTextPress.setTextColor(Color.argb(diaphaneity_two, 69, 192, 26));
                        tvContactsTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                        tvContactsTextPress.setTextColor(Color.argb(diaphaneity_one, 69, 192, 26));
                        break;
                    case 1:
                        tvContactsNormal.getBackground().setAlpha(diaphaneity_one);
                        tvContactsPress.getBackground().setAlpha(diaphaneity_two);
                        tvDiscoveryNormal.getBackground().setAlpha(diaphaneity_two);
                        tvDiscoveryPress.getBackground().setAlpha(diaphaneity_one);
                        tvContactsTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        tvContactsTextPress.setTextColor(Color.argb(diaphaneity_two, 69, 192, 26));
                        tvDiscoveryTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                        tvDiscoveryTextPress.setTextColor(Color.argb(diaphaneity_one, 69, 192, 26));
                        break;
                    case 2:
                        tvDiscoveryNormal.getBackground().setAlpha(diaphaneity_one);
                        tvDiscoveryPress.getBackground().setAlpha(diaphaneity_two);
                        tvMeNormal.getBackground().setAlpha(diaphaneity_two);
                        tvMePress.getBackground().setAlpha(diaphaneity_one);
                        tvDiscoveryTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        tvDiscoveryTextPress.setTextColor(Color.argb(diaphaneity_two, 69, 192, 26));
                        tvMeTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                        tvMeTextPress.setTextColor(Color.argb(diaphaneity_one, 69, 192, 26));
                        break;
                }

            }

            @Override
            public void onPageSelected(int position) {
                //如果是“通讯录”页被选中，则显示快速导航条
//                if (position == 1) {
//                    mContactsFragment.showQuickIndexBar(true);
//                } else {
//                    mContactsFragment.showQuickIndexBar(false);
//                }

//                //根据position刷新对应Fragment的数据
//                mFragments.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                if (state != ViewPager.SCROLL_STATE_IDLE) {
//                    //滚动过程中隐藏快速导航条
//                    mContactsFragment.showQuickIndexBar(false);
//                } else {
//                    mContactsFragment.showQuickIndexBar(true);
//                }
            }
        });
    }


    /**
     * 把press图片、文字全部隐藏(设置透明度)
     */
    private void setTransparency() {
        tvMessageNormal.getBackground().setAlpha(255);
        tvContactsNormal.getBackground().setAlpha(255);
        tvDiscoveryNormal.getBackground().setAlpha(255);
        tvMeNormal.getBackground().setAlpha(255);
        tvMessagePress.getBackground().setAlpha(1);
        tvContactsPress.getBackground().setAlpha(1);
        tvDiscoveryPress.getBackground().setAlpha(1);
        tvMePress.getBackground().setAlpha(1);
        tvMessageTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvContactsTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvDiscoveryTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvMeTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvMessageTextPress.setTextColor(Color.argb(0, 69, 192, 26));
        tvContactsTextPress.setTextColor(Color.argb(0, 69, 192, 26));
        tvDiscoveryTextPress.setTextColor(Color.argb(0, 69, 192, 26));
        tvMeTextPress.setTextColor(Color.argb(0, 69, 192, 26));
    }

    @Override
    public void onClick(View v) {
        setTransparency();
        switch (v.getId()) {
            case R.id.llMessage:
                vpContent.setCurrentItem(0, false);
                tvMessagePress.getBackground().setAlpha(255);
                tvMessageTextPress.setTextColor(Color.argb(255, 69, 192, 26));
                break;
            case R.id.llContacts:
                vpContent.setCurrentItem(1, false);
                tvContactsPress.getBackground().setAlpha(255);
                tvContactsTextPress.setTextColor(Color.argb(255, 69, 192, 26));
                break;
            case R.id.llDiscovery:
                vpContent.setCurrentItem(2, false);
                tvDiscoveryPress.getBackground().setAlpha(255);
                tvDiscoveryTextPress.setTextColor(Color.argb(255, 69, 192, 26));
                break;
            case R.id.llMe:
                vpContent.setCurrentItem(3, false);
                tvMePress.getBackground().setAlpha(255);
                tvMeTextPress.setTextColor(Color.argb(255, 69, 192, 26));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
