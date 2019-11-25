package com.example.kaola.myapplication.designpattern.singleton.java;

/**
 * @author zhangchao on 2019-06-12.
 * 双次检验
 */

public class DoubleCheckSingleton {
    /**
     * 这个volatile 为了解决多线程调用导致的问题.
     */
    private static volatile DoubleCheckSingleton doubleCheckSingleton;

    private DoubleCheckSingleton() {

    }

    public static DoubleCheckSingleton getInstance() {
        if (doubleCheckSingleton == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (doubleCheckSingleton == null) {
                    doubleCheckSingleton = new DoubleCheckSingleton();
                }
            }
        }
        return doubleCheckSingleton;
    }

    /**
     * 防止序列化和反序列化对单利模式的破坏
     *
     * @return
     */
    private Object readResolve() {
        return doubleCheckSingleton;
    }

}
