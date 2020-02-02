package com.example.kaola.myapplication.rxjava.observable.backpressure;

/**
 * @ClassName TelephonerWithUpstream
 * @Description TODO
 * @Author zhangchao
 * @Date 2020-02-02 09:34
 * @Version 1.0
 */
public abstract class TelephonerWithUpstream<T, R> extends Telephoner<R> implements TelephonerSource<T> {
    protected final Telephoner<T> source;

    public TelephonerWithUpstream(Telephoner<T> source) {
        this.source = source;
    }

    @Override
    public Telephoner<T> source() {
        return source;
    }
}
