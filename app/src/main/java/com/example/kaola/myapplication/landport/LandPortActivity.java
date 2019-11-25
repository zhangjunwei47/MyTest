package com.example.kaola.myapplication.landport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.zc.test.R;


/**
 * @author zhangchao on 2019-07-26.
 * 横竖屏切换时,如果是activity走生命周期, 那么只有最上层的Activity才会走.ondestroy
 */

public class LandPortActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landport);
        relativeLayout = findViewById(R.id.fragment);
        initView();
    }

    private void initView() {
        LandPortFragment fragment = new LandPortFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
