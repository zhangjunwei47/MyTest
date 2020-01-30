package com.example.kaola.myapplication.rxjava.observable;

/**
 * @ClassName Caller
 * @Description 对应 Observable 无背压, 打电话的人
 * @Author zhangchao
 * @Date 2020-01-30 17:31
 * @Version 1.0
 */
public abstract class Caller<T> {

    public static <T> Caller<T> create(CallerOnCall<T> callerOnCall) {
        return new CallerCreate<>(callerOnCall);
    }

    public void call(Callee<T> callee) {
        callActual(callee);
    }

    protected abstract void callActual(Callee<T> callee);
}
