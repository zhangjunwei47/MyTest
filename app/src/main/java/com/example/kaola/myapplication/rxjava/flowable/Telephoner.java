package com.example.kaola.myapplication.rxjava.observable.backpressure;

/**
 * @ClassName Telephoner
 * @Description 1. 打电话的人
 * @Author zhangchao
 * @Date 2020-02-02 09:31
 * @Version 1.0
 */
public abstract class Telephoner<T> {
    public static <T> Telephoner<T> create(TelephonerOnCall<T> telephonerOnCall) {
        return new TelephonerCreate<>(telephonerOnCall);
    }

    public void call(Receiver<T> receiver) {
        callActual(receiver);
    }

    protected  abstract  void callActual(Receiver<T> receiver);

}
