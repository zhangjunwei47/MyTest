package com.example.kaola.myapplication.util;

/**
 * @author zhangchao on 2019-09-05.
 */

public class DoubleClickUtil {

    private static final int SPACE_TIME = 500;
    private static long mLastClickTime;

    /**
     * 是不是第二次点击
     *
     * @return
     */
    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick;
        if (currentTime - mLastClickTime > SPACE_TIME) {
            isClick = false;
        } else {
            isClick = true;
        }
        mLastClickTime = currentTime;
        return isClick;
    }
}
