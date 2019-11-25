package com.kaolafm.module.update.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.kaolafm.module.update.modle.PluginInfo;

/**
 * 缓存下载文件信息
 */
public class DownloadCacheInfoUtil {

    private static final String PREFERENCE_NAME = "DownloadInfo.sp";
    private static final String INFO = "plugin_info";
    private static final String SIZE = "plugin_size";
    /**
     * 下载状态
     */
    private static final String DOWNLOAD_STATE = "plugin_state";
    /**
     * 下载路径
     */
    private static final String DOWNLOAD_PATH = "plugin_path";

    /**
     * 插件信息
     */
    private static PluginInfo mPluginInfo;

    /**
     * 下载状态
     */
    private static int mDownloadState = -1;

    /**
     * 获取SharedPreferences
     *
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    /**
     * 设置plugin信息
     *
     * @param pluginInfo
     */
    public static void setPluginInfo(Context context, PluginInfo pluginInfo) {
        if (pluginInfo == null) {
            return;
        }
        String infoStr = new Gson().toJson(pluginInfo);

        if (TextUtils.isEmpty(infoStr)) {
            return;
        }
        mPluginInfo = pluginInfo;
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(INFO, infoStr);
        editor.apply();
    }

    /**
     * 获取插件信息
     *
     * @param context
     * @return
     */
    public static PluginInfo getPluginInfo(Context context) {
        String infoStr = getSharedPreferences(context).getString(INFO, UpdateConstant.BLANK_STR);
        if (TextUtils.isEmpty(infoStr)) {
            return null;
        }
        try {
            mPluginInfo = new Gson().fromJson(infoStr, PluginInfo.class);
            return mPluginInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置下载状态
     *
     * @param context
     * @param state
     */
    public static void setDownloadState(Context context, int state) {
        if (mDownloadState == state) {
            return;
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(DOWNLOAD_STATE, state);
        editor.apply();
        mDownloadState = state;
    }

    /**
     * 获取下载状态
     *
     * @param context
     */
    public static int getDownloadState(Context context) {
        if (mDownloadState != -1) {
            return mDownloadState;
        }
        mDownloadState = getSharedPreferences(context).getInt(DOWNLOAD_STATE, UpdateConstant.DOWNLOAD_STATE_INVALID);
        return mDownloadState;
    }

    /**
     * 获取保持文件的路径
     *
     * @param context
     * @return
     */
    public static String getDownloadPath(Context context) {
        return getSharedPreferences(context).getString(DOWNLOAD_PATH, UpdateConstant.BLANK_STR);
    }

    /**
     * 设置下载路径
     *
     * @param context
     * @param path
     */
    public static void setDownloadPath(Context context, String path) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DOWNLOAD_PATH, path);
        editor.apply();
    }

    /**
     * 获取下载的url
     *
     * @param context
     * @return
     */
    public static String getDownloadUrl(Context context) {
        if (mPluginInfo == null) {
            getPluginInfo(context);
        }
        if (mPluginInfo != null) {
            return mPluginInfo.getApkUrl();
        }
        return UpdateConstant.BLANK_STR;
    }

    /**
     * 清楚缓存数据
     */
    public static void clearCacheData(Context context) {
        mPluginInfo = null;
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(DOWNLOAD_STATE, UpdateConstant.DOWNLOAD_STATE_INVALID);
        editor.putString(DOWNLOAD_PATH, UpdateConstant.BLANK_STR);
        editor.putString(INFO, UpdateConstant.BLANK_STR);
        editor.apply();
    }

}
