package com.example.kaola.myapplication.designpattern.decorator.java;

import com.google.android.exoplayer2.util.Log;

/**
 * @author zhangchao on 2019-06-12.
 */

public class Pancake extends APancake {

    @Override
    public void decorator() {
        Log.e(APancake.TAG, "这是一个煎饼");
    }

    @Override
    public int price() {
        return 888;
    }
}
