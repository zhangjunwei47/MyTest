package com.example.kaola.myapplication.rxjava.observable;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName ReleaseHelper
 * @Description 挂电话帮助类
 * @Author zhangchao
 * @Date 2020-01-31 08:47
 * @Version 1.0
 */
public enum ReleaseHelper implements Release {

    /**
     * 已经挂断电话
     */
    RELEASED;

    public static boolean isReleased(Release release) {
        return release == RELEASED;
    }


    public static boolean release(AtomicReference<Release> releaseAtomicReference) {
        Release current = releaseAtomicReference.get();
        Release d = RELEASED;
        if (current != d) {
            current = releaseAtomicReference.getAndSet(d);
            if (current != d) {
                if (current != null) {
                    current.release();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isReleased() {
        return true;
    }

    @Override
    public void release() {

    }

}
