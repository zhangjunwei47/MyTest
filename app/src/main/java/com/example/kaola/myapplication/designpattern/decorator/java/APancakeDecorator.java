package com.example.kaola.myapplication.designpattern.decorator.java;

/**
 * @author zhangchao on 2019-06-12.
 */

public class APancakeDecorator extends APancake {
    APancake mAPancake;

    public APancakeDecorator(APancake aPancake) {
        mAPancake = aPancake;
    }

    @Override
    public void decorator() {
        mAPancake.decorator();
    }

    @Override
    public int price() {
        return mAPancake.price();
    }
}
