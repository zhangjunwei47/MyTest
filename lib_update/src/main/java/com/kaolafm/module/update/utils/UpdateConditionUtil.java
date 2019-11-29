package com.kaolafm.module.update.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

/**
 * 升级条件检查工具类
 */
public class UpdateConditionUtil {

    /**
     * 检查是否有网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
            } else {
                NetworkInfo infos = connectivity.getActiveNetworkInfo();
                if (infos != null && infos.isConnected()) {
                    return true;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 检查SDCard空间是否满足下载的需求
     *
     * @return
     */
    public static boolean isSDCardAvailable() {
        long SDFreeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
        return SDFreeSpace > UpdateConstant.MINIMUM_SPACE;
    }

    /**
     * 检查本地是否有请求接口的条件
     *
     * @return
     */
    public static boolean isHasRequestInfoAvailable(Context context) {
        return !RequestParamsCacheInfoUtil.isNoHasConditionInfo(context);
    }

}
