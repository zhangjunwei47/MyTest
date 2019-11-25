package com.example.kaola.myapplication.designpattern.decorator.java;

/**
 * @author zhangchao on 2019-06-12.
 */

public abstract class APancake {

    public static final String TAG = "DecoratorTAG";

    /**
     * 装饰
     */
    public abstract void decorator();

    /**
     * 价钱
     */
    public abstract int price();
}
