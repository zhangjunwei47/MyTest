package com.example.kaola.myapplication.rxjava.observable;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName CallerCreate
 * @Description 实际的Caller
 * @Author zhangchao
 * @Date 2020-01-30 19:29
 * @Version 1.0
 */
public final class CallerCreate<T> extends Caller<T> {

    private CallerOnCall<T> mCallerOnCall;

    CallerCreate(CallerOnCall<T> callerOnCall) {
        mCallerOnCall = callerOnCall;
    }

    @Override
    protected void callActual(Callee<T> callee) {

    }

    public static final class  CreateEmitter<T> extends AtomicReference<Release> implements 
}
