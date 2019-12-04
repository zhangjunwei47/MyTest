package com.kaolafm.module.update.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 升级版本缓存信息工具
 */
public class UpdateVersionCacheInfoUtil {
    private static final String PREFERENCE_NAME = "UpdateInfo.sp";
    private static final String KEY_OLD_VERSION = "old_version";
    private static final String KEY_UPDATE_TYPE = "update_type";

    private static SharedPreferences mSharedPreferences;

    /**
     * 获取SharedPreferences
     *
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences;
    }

    public static void setUpdateType(Context context, String type) {
        getSharedPreferences(context).edit().putString(KEY_UPDATE_TYPE, type).apply();
    }

    public static String getUpdateType(Context context) {
        String updateType = getSharedPreferences(context).getString(KEY_UPDATE_TYPE, UpdateConstant.BLANK_STR);
        return updateType;
    }

    public static void setOldVersion(Context context, String oldVersion) {
        getSharedPreferences(context).edit().putString(KEY_OLD_VERSION, oldVersion).apply();
    }

    public static String getOldVersion(Context context) {
        String oldVersion = getSharedPreferences(context).getString(KEY_OLD_VERSION, UpdateConstant.BLANK_STR);
        return oldVersion;
    }

    public static void clearAlldata(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_UPDATE_TYPE, UpdateConstant.BLANK_STR);
        editor.putString(KEY_OLD_VERSION, UpdateConstant.BLANK_STR);
        editor.apply();
    }

}
