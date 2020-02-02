package com.example.kaola.myapplication.rxjava.observable.backpressure;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName TelephonerCreate
 * @Description 1. 实际的Telephoner
 * @Author zhangchao
 * @Date 2020-02-02 09:46
 * @Version 1.0
 */
public final class TelephonerCreate<T> extends Telephoner<T> {
    private TelephonerOnCall<T> mTelephonerOnCall;

    public TelephonerCreate(TelephonerOnCall<T> telephonerOnCall) {
        mTelephonerOnCall = telephonerOnCall;
    }

    @Override
    protected void callActual(Receiver<T> receiver) {
        DropEmitter<T> dropEmitter = new DropEmitter<>(receiver);
        receiver.onCall(dropEmitter);
        mTelephonerOnCall.call(dropEmitter);
    }

    private static class DropEmitter<T> extends AtomicLong implements TelephonerEmitter<T>, Drop {
        private Receiver<T> mReceiver;
        private volatile boolean mIsDropped;

        private DropEmitter(Receiver<T> receiver) {
            mReceiver = receiver;
        }

        @Override
        public void onReceive(T t) {
            if (get() != 0) {
                mReceiver.onReceiver(t);
                BackpressureHelper.produced(this, 1);
            }
        }

        @Override
        public void onCompleted() {
            mReceiver.onCompleted();
        }

        @Override
        public void onError(Throwable t) {
            mReceiver.onError(t);
        }

        @Override
        public void request(long n) {
            BackpressureHelper.add(this, n);
        }

        @Override
        public void drop() {
            mIsDropped = true;
        }
    }
}
