package io.crossbar.autobahn.sdjx.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 主界面中ViewPager的适配器
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public MainPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
