package com.example.kaola.myapplication.rxjava.observable.backpressure;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName BackpressureHelper
 * @Description 1.处理背压的帮助类
 * @Author zhangchao
 * @Date 2020-02-02 10:00
 * @Version 1.0
 */
public class BackpressureHelper {
    public static void add(AtomicLong requested, long n) {
        long r = requested.get();
        if (r == Long.MAX_VALUE) {
            return;
        }
        long u = r + n;
        if (u < 0L) {
            u = Long.MAX_VALUE;
        }
        requested.compareAndSet(r, u);
    }

    public static void produced(AtomicLong requested, long n) {
        long current = requested.get();
        if (current == Long.MAX_VALUE) {
            return;
        }

        long update = current = n;
        if (update < 0L) {
            update = 0L;
        }

        requested.compareAndSet(current, update);
    }
}
