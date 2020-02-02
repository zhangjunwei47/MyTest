package com.example.kaola.myapplication.rxjava.observable.backpressure;

/**
 * @ClassName TelephonerOnCall
 * @Description TODO
 * @Author zhangchao
 * @Date 2020-02-02 09:33
 * @Version 1.0
 */
public interface TelephonerOnCall<T> {
    void call(TelephonerEmitter<T> tTelephonerEmitter);
}
