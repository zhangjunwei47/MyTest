package com.example.kaola.myapplication.designpattern.singleton.java;

import java.io.Serializable;

/**
 * @author zhangchao on 2019-06-12.
 * 懒汉设计模式
 */

public class LazySingleton implements Serializable {
    private static class InnerClass {
        private static LazySingleton lazySingleton = new LazySingleton();
    }

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        return InnerClass.lazySingleton;
    }

    private Object readResolve() {
        return InnerClass.lazySingleton;
    }

}
