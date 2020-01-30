package com.example.kaola.myapplication.rxjava.observable;

/**
 * @ClassName Callee
 * @Description 对应 Observer 接电话的人
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
