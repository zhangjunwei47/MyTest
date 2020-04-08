package com.example.kaola.myapplication.designpattern.decorator.java;


import android.util.Log;

/**
 * @author zhangchao on 2019-06-12.
 */

public class EggsPancake extends APancakeDecorator {

    public EggsPancake(APancake aPancake) {
        super(aPancake);
    }

    @Override
    public void decorator() {
        Log.e(APancake.TAG, "添加一个鸡蛋");
    }

    @Override
    public int price() {
        return super.price() + 1;
    }
}
