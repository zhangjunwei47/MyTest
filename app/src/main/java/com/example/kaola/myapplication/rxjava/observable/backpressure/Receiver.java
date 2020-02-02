package com.example.kaola.myapplication.rxjava.observable.backpressure;

/**
 * @ClassName Receiver
 * @Description
 * 1. 接电话的人
 * @Author zhangchao
 * @Date 2020-02-02 09:50
 * @Version 1.0
 */
public interface Receiver<T> {

    void onCall(Drop d);

    void onReceiver(T t);

    void onError(Throwable t);

    void onCompleted();
}
