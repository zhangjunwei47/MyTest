package com.example.kaola.myapplication.rxjava.observable;

/**
 * @ClassName CallerOnCall
 * @Description 无背压  当打电话时.
 * @Author zhangchao
 * @Date 2020-01-30 19:27
 * @Version 1.0
 */
public interface CallerOnCall<T> {
    void call(CallerEmitter<T> callerEmitter);
}
