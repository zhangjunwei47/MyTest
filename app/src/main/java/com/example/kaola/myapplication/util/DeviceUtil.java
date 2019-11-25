package com.example.kaola.myapplication.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author zhangchao on 2018/10/10.
 */

public class DeviceUtil {

    /**
     * 获得设备屏幕宽度
     */
    private static int mScreenWidth;

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources()
                .getDisplayMetrics();
        mScreenWidth = metrics.widthPixels;
        return mScreenWidth;
    }


}
