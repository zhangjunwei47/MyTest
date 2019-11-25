package com.example.kaola.myapplication.dinatortablayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kaola.myapplication.daggertest.CoordinatorTabLayout;
import com.example.kaola.myapplication.dinatortablayout.adapter.AllFragmentPageAdapter;
import com.zc.test.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhangchao on 2018/9/6.
 * 自动折叠 fragment 测试
 */

public class AllFragment extends Fragment {
    // CoordinatorTabLayout coordinatorTabLayout;
    @BindView(R.id.coordinatortablayout)
    CoordinatorTabLayout coordinatortablayout;
    @BindView(R.id.vp)
    ViewPager viewPager;

    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_viewpager, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment();
        initLayout();
    }

    private void initFragment() {
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(firstFragment);
        arrayList.add(secondFragment);
        arrayList.add(thirdFragment);
        AllFragmentPageAdapter allFragmentPageAdapter = new AllFragmentPageAdapter(getChildFragmentManager(), arrayList);
        viewPager.setAdapter(allFragmentPageAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);
        coordinatortablayout.setupWithViewPager(viewPager);
    }

    private void initLayout() {
        int[] mImageArray = new int[]{
                R.mipmap.timg,
                R.mipmap.timg,
                R.mipmap.timg,};
        //coordinatorTabLayout.setTitle("哈哈哈哈").setImageArray(mImageArray).setupWithViewPager(viewPager);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
