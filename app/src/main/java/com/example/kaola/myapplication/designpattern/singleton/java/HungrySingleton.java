package com.example.kaola.myapplication.designpattern.singleton.java;

/**
 * @author zhangchao on 2019-06-12.
 * 饿汉设计模式
 */

public class HungrySingleton {
    /**
     * 第一种
     */
    private static HungrySingleton hungrySingleton;

    /**
     * 第二种
     */
    static {
        hungrySingleton = new HungrySingleton();
    }

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}
