package com.example.kaola.myapplication.rxjava.observable.backpressure;

/**
 * @ClassName TelephonerSource
 * @Description 1. 返回原Telephoner
 * @Author zhangchao
 * @Date 2020-02-02 09:35
 * @Version 1.0
 */
public interface TelephonerSource<T> {
    Telephoner<T> source();
}
