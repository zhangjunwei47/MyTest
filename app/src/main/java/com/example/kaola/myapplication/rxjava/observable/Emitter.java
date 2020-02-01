package com.example.kaola.myapplication.rxjava.observable;

/**
 * @ClassName Emitter
 * @Description
 * 1. 发射数据的接口
 * 2. 对Callee的包装
 * @Author zhangchao
 * @Date 2020-01-31 09:06
 * @Version 1.0
 */
public interface Emitter<T> {

    void onReceive(T t);

    void onCompleted();

    void onError(Throwable t);
}
