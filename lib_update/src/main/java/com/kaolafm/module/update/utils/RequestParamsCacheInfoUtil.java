package com.kaolafm.module.update.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * 请求服务器需要的信息缓存工具
 */
public class RequestParamsCacheInfoUtil {

    private static final String PREFERENCE_NAME = "RequestParameterInfo.sp";
    private static final String UDID = "udId";
    private static final String APP_ID = "appId";
    private static final String OPEN_ID = "openId";
    private static final String APP_KEY = "appKey";
    private static final String APP_VERSION = "appVersion";

    private static SharedPreferences mSharedPreferences;

    private static String mUdid = "91bcc4fc97207be76c16cddc9d905b87";
    private static String mAppId = "ye8192";
    private static String mOpenId = "ye81922019111310000001";
    private static String mAppKey = "f6dff42133bf06810a52a1d392b9906b";
    private static String mAppVersion = "1.0.0";

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

    /**
     * 初始化缓存信息
     *
     * @param context
     */
    private static void initCacheInfo(Context context) {
        mUdid = getSharedPreferences(context).getString(UDID, UpdateConstant.BLANK_STR);
        mAppId = getSharedPreferences(context).getString(APP_ID, UpdateConstant.BLANK_STR);
        mOpenId = getSharedPreferences(context).getString(OPEN_ID, UpdateConstant.BLANK_STR);
        mAppKey = getSharedPreferences(context).getString(APP_KEY, UpdateConstant.BLANK_STR);
        mAppVersion = getSharedPreferences(context).getString(APP_VERSION, UpdateConstant.BLANK_STR);
    }

    /**
     * 设置网络请求参数
     *
     * @param context
     * @param udid
     * @param openid
     * @param appid
     */
    public static void setReqeustParameterInfo(Context context, String udid, String openId, String appId, String appKey) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(UDID, udid);
        editor.putString(OPEN_ID, openId);
        editor.putString(APP_ID, appId);
        editor.putString(APP_KEY, appKey);
        editor.apply();
    }

    public static String getOpenid(Context context) {
        if (TextUtils.isEmpty(mOpenId)) {
            initCacheInfo(context);
        }
        return mOpenId;
    }

    public static String getUdid(Context context) {
        if (TextUtils.isEmpty(mUdid)) {
            initCacheInfo(context);
        }
        return mUdid;
    }

    public static String getAppId(Context context) {
        if (TextUtils.isEmpty(mAppId)) {
            initCacheInfo(context);
        }
        return mAppId;
    }

    public static String getAppKey(Context context) {
        if (TextUtils.isEmpty(mAppKey)) {
            initCacheInfo(context);
        }
        return mAppKey;
    }

    public static String getAppVersion(Context context) {
        if (TextUtils.isEmpty(mAppVersion)) {
            initCacheInfo(context);
        }
        return mAppVersion;
    }

    public static boolean isNoHasConditionInfo(Context context) {
        // TODO: 2019-11-22 测试环境,暂时关闭
//        if (TextUtils.isEmpty(mOpenId)) {
//            initCacheInfo(context);
//        }

        return TextUtils.isEmpty(mOpenId)
                || TextUtils.isEmpty(mUdid)
                || TextUtils.isEmpty(mAppVersion)
                || TextUtils.isEmpty(mAppKey)
                || TextUtils.isEmpty(mAppId);
    }
}
