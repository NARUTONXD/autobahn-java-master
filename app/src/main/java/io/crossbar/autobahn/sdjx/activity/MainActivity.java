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

package io.crossbar.autobahn.sdjx.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.adapter.MainPagerAdapter;
import io.crossbar.autobahn.sdjx.fragment.ContactsFragment;
import io.crossbar.autobahn.sdjx.fragment.MeFragment;
import io.crossbar.autobahn.sdjx.fragment.MessageFragment;
import io.crossbar.autobahn.sdjx.fragment.ProgressFragment;
import io.crossbar.autobahn.sdjx.fragment.WorkFragment;
import io.crossbar.autobahn.sdjx.widget.CustomViewPager;


public class MainActivity extends BaseActivity  {
    private static String BaseUrl = "http://192.168.1.199";
    @Bind(R.id.vpContent)
    CustomViewPager vpContent;
    @Bind(R.id.tvWorkNormal)
    TextView tvWorkNormal;
    @Bind(R.id.tvWorkPress)
    TextView tvWorkPress;
    @Bind(R.id.rlWork)
    RelativeLayout rlWork;
    @Bind(R.id.tvWorkCount)
    TextView tvWorkCount;
    @Bind(R.id.tvWorkTextNormal)
    TextView tvWorkTextNormal;
    @Bind(R.id.tvWorkTextPress)
    TextView tvWorkTextPress;
    @Bind(R.id.llWork)
    LinearLayout llWork;
    @Bind(R.id.tvMessagesNormal)
    TextView tvMessagesNormal;
    @Bind(R.id.tvMessagesPress)
    TextView tvMessagesPress;
    @Bind(R.id.rlMessages)
    RelativeLayout rlMessages;
    @Bind(R.id.tvContactCount)
    TextView tvContactCount;
    @Bind(R.id.tvMessagesTextNormal)
    TextView tvMessagesTextNormal;
    @Bind(R.id.tvMessagesTextPress)
    TextView tvMessagesTextPress;
    @Bind(R.id.llMessages)
    LinearLayout llMessages;
    @Bind(R.id.tvContactsNormal)
    TextView tvContactsNormal;
    @Bind(R.id.tvContactsPress)
    TextView tvContactsPress;
    @Bind(R.id.rlContacts)
    RelativeLayout rlContacts;
    @Bind(R.id.tvContactsCount)
    TextView tvContactsCount;
    @Bind(R.id.tvContactsTextNormal)
    TextView tvContactsTextNormal;
    @Bind(R.id.tvContactsTextPress)
    TextView tvContactsTextPress;
    @Bind(R.id.llContacts)
    LinearLayout llContacts;
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
//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
//    @Bind(R.id.refreshLayout)
//    SmartRefreshLayout refreshLayout;
    @Bind(R.id.tvProgressNormal)
    TextView tvProgressNormal;
    @Bind(R.id.tvProgressPress)
    TextView tvProgressPress;
    @Bind(R.id.rlProgress)
    RelativeLayout rlProgress;
    @Bind(R.id.tvProgressCount)
    TextView tvProgressCount;
    @Bind(R.id.tvProgressTextNormal)
    TextView tvProgressTextNormal;
    @Bind(R.id.tvProgressTextPress)
    TextView tvProgressTextPress;
    @Bind(R.id.llProgress)
    LinearLayout llProgress;
    private List<Fragment> mFragments;
    private WorkFragment mMessageFragment;
    private MessageFragment mMessageFragment1;
    private ContactsFragment contactsfragment;
    private ProgressFragment mMessageFragment3;
    private MeFragment mMessageFragment4;
    @SuppressLint("ResourceAsColor")
    @Override
    public void initView() {
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setTransparency();
        tvWorkPress.getBackground().setAlpha(255);
        tvWorkTextPress.setTextColor(Color.argb(255, 4, 82, 154));
        llWork.setOnClickListener(this);
        llMessages.setOnClickListener(this);
        llContacts.setOnClickListener(this);
        llProgress.setOnClickListener(this);
        llMe.setOnClickListener(this);
        mFragments = new ArrayList<>();
        mMessageFragment = new WorkFragment();
        mMessageFragment1 = new MessageFragment();
        contactsfragment = new ContactsFragment();
        mMessageFragment3 = new ProgressFragment();
        mMessageFragment4 = new MeFragment();
        mFragments.add(mMessageFragment);
        mFragments.add(mMessageFragment1);
        mFragments.add(contactsfragment);
        mFragments.add(mMessageFragment3);
        mFragments.add(mMessageFragment4);
        vpContent.setOffscreenPageLimit(5);
        vpContent.setScanScroll(false);
        //设置中间内容vp适配器
        vpContent.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), mFragments));
        vpContent.setCurrentItem(0);

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
                        tvWorkNormal.getBackground().setAlpha(diaphaneity_one);
                        tvWorkPress.getBackground().setAlpha(diaphaneity_two);
                        tvMessagesNormal.getBackground().setAlpha(diaphaneity_two);
                        tvMessagesPress.getBackground().setAlpha(diaphaneity_one);
                        tvWorkTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        tvWorkTextPress.setTextColor(Color.argb(diaphaneity_two, 4, 82, 154));
                        tvMessagesTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                        tvMessagesTextPress.setTextColor(Color.argb(diaphaneity_one, 4, 82, 154));
                        break;
                    case 1:
                        tvMessagesNormal.getBackground().setAlpha(diaphaneity_one);
                        tvMessagesPress.getBackground().setAlpha(diaphaneity_two);
                        tvContactsNormal.getBackground().setAlpha(diaphaneity_two);
                        tvContactsPress.getBackground().setAlpha(diaphaneity_one);
                        tvMessagesTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        tvMessagesTextPress.setTextColor(Color.argb(diaphaneity_two, 4, 82, 154));
                        tvContactsTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                        tvContactsTextPress.setTextColor(Color.argb(diaphaneity_one, 4, 82, 154));
                        break;
                    case 2:
                        tvContactsNormal.getBackground().setAlpha(diaphaneity_one);
                        tvContactsPress.getBackground().setAlpha(diaphaneity_two);
                        tvProgressNormal.getBackground().setAlpha(diaphaneity_two);
                        tvProgressPress.getBackground().setAlpha(diaphaneity_one);
                        tvContactsTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        tvContactsTextPress.setTextColor(Color.argb(diaphaneity_two, 4, 82, 154));
                        tvProgressTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                        tvProgressTextPress.setTextColor(Color.argb(diaphaneity_one, 4, 82, 154));
                        break;
                    case 3:
                        tvProgressNormal.getBackground().setAlpha(diaphaneity_one);
                        tvProgressPress.getBackground().setAlpha(diaphaneity_two);
                        tvMeNormal.getBackground().setAlpha(diaphaneity_two);
                        tvMePress.getBackground().setAlpha(diaphaneity_one);
                        tvProgressTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        tvProgressTextPress.setTextColor(Color.argb(diaphaneity_two, 4, 82, 154));
                        tvMeTextNormal.setTextColor(Color.argb(diaphaneity_two, 153, 153, 153));
                        tvMeTextPress.setTextColor(Color.argb(diaphaneity_one, 4, 82, 154));
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
        tvWorkNormal.getBackground().setAlpha(255);
        tvMessagesNormal.getBackground().setAlpha(255);
        tvContactsNormal.getBackground().setAlpha(255);
        tvMeNormal.getBackground().setAlpha(255);
        tvProgressNormal.getBackground().setAlpha(255);
        tvWorkPress.getBackground().setAlpha(1);
        tvMessagesPress.getBackground().setAlpha(1);
        tvContactsPress.getBackground().setAlpha(1);
        tvMePress.getBackground().setAlpha(1);
        tvProgressPress.getBackground().setAlpha(1);
        tvWorkTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvMessagesTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvContactsTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvMeTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvProgressTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvWorkTextPress.setTextColor(Color.argb(0, 4, 82, 154));
        tvMessagesTextPress.setTextColor(Color.argb(0, 4, 82, 154));
        tvContactsTextPress.setTextColor(Color.argb(0, 4, 82, 154));
        tvMeTextPress.setTextColor(Color.argb(0, 4, 82, 154));
        tvProgressTextPress.setTextColor(Color.argb(0, 4, 82, 154));
    }

    @Override
    public void onClick(View v) {
        setTransparency();
        switch (v.getId()) {
            case R.id.llWork:
                vpContent.setCurrentItem(0, false);
                tvWorkPress.getBackground().setAlpha(255);
                tvWorkTextPress.setTextColor(Color.argb(255, 4, 82, 154));
                break;
            case R.id.llMessages:
                vpContent.setCurrentItem(1, false);
                tvMessagesPress.getBackground().setAlpha(255);
                tvMessagesTextPress.setTextColor(Color.argb(255, 4, 82, 154));
                break;
            case R.id.llContacts:
                vpContent.setCurrentItem(2, false);
                tvContactsPress.getBackground().setAlpha(255);
                tvContactsTextPress.setTextColor(Color.argb(255, 4, 82, 154));
                break;
            case R.id.llProgress:
                vpContent.setCurrentItem(3, false);
                tvProgressPress.getBackground().setAlpha(255);
                tvProgressTextPress.setTextColor(Color.argb(255, 4, 82, 154));
                break;
            case R.id.llMe:
                vpContent.setCurrentItem(4, false);
                tvMePress.getBackground().setAlpha(255);
                tvMeTextPress.setTextColor(Color.argb(255, 4, 82, 154));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
