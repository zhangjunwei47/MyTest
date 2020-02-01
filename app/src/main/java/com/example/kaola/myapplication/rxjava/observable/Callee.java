package com.example.kaola.myapplication.rxjava.observable;

/**
 * @ClassName Callee
 * @Description 对应 Observer
 * 1. 接电话的人(观察者) --- 接收数据的一方
 * 2. 作为caller的call方法的参数
 * @Author zhangchao
 * @Date 2020-01-30 17:31
 * @Version 1.0
 */
public interface Callee<T> {
    void onCall(Release release);

    void onReceive(T t);

    void onCompleted();

    void onError(Throwable t);
}
