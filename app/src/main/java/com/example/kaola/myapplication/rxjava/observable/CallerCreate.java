package com.example.kaola.myapplication.rxjava.observable;

import com.google.android.exoplayer2.C;

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
        CreateEmitter<T> createEmitter = new CreateEmitter<>(callee);
        callee.onCall(createEmitter);
        mCallerOnCall.call(createEmitter);
    }

    public static final class CreateEmitter<T> extends AtomicReference<Release> implements CallerEmitter<T>, Release {
        private Callee<T> mCallee;

        public CreateEmitter(Callee<T> caller) {
            mCallee = caller;
        }

        @Override
        public boolean isReleased() {
            return ReleaseHelper.isReleased(get());
        }

        @Override
        public void release() {
            ReleaseHelper.release(this);
        }


        @Override
        public void onReceive(T t) {
            if (!isReleased()) {
                mCallee.onReceive(t);
            }
        }

        @Override
        public void onCompleted() {
            if (!isReleased()) {
                mCallee.onCompleted();
            }
        }

        @Override
        public void onError(Throwable error) {
            if (!isReleased()) {
                mCallee.onError(error);
            }
        }

    }
}
