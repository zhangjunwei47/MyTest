package com.example.kaola.myapplication.dinatortablayout.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author zhangchao on 2018/9/6.
 */

public class AllFragmentPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentArrayList;

    private String[] mTitleArray = {"第一页", "第二页", "第三页"};

    public AllFragmentPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        fragmentArrayList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleArray[position];
    }
}
