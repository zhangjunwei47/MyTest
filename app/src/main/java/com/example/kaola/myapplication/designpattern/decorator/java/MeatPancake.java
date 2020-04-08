package com.example.kaola.myapplication.designpattern.decorator.java;


import android.util.Log;

/**
 * @author zhangchao on 2019-06-12.
 */

public class MeatPancake extends APancakeDecorator {
    public MeatPancake(APancake aPancake) {
        super(aPancake);
    }

    @Override
    public void decorator() {
        Log.e(APancake.TAG, "加肉");
    }

    @Override
    public int price() {
        return super.price()+ 2;
    }
}
