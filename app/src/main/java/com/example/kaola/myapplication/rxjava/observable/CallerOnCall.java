package com.example.kaola.myapplication.rxjava.observable;

/**
 * @ClassName CallerOnCall
 * @Description 无背压
 * 1. 当打电话时会触发此接口调用
 * 2. 作用于Caller, 向接电话的人发送通话内容.
 * @Author zhangchao
 * @Date 2020-01-30 19:27
 * @Version 1.0
 */
public interface CallerOnCall<T> {
    void call(CallerEmitter<T> callerEmitter);
}
