package com.kaolafm.module.update.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.google.gson.Gson;
import com.kaolafm.module.update.UpdateManager;
import com.kaolafm.module.update.modle.ReportEvent;

public class ReportUtil {

    /**
     * 添加上报事件
     */
    public static String getEvent(int errorCode) {
        ReportEvent reportEvent = new ReportEvent();
        reportEvent.setEventcode(UpdateConstant.UPDATE_ERROR_REPORT_EVENT_CODE);
        reportEvent.setAppid(RequestParamsCacheInfoUtil.getAppId(UpdateManager.mContext));
        reportEvent.setUdid(RequestParamsCacheInfoUtil.getUdid(UpdateManager.mContext));
        reportEvent.setOpenid(RequestParamsCacheInfoUtil.getOpenid(UpdateManager.mContext));
        reportEvent.setApp_version(RequestParamsCacheInfoUtil.getAppVersion(UpdateManager.mContext));
        reportEvent.setTimestamp(String.valueOf(System.currentTimeMillis()));
        reportEvent.setManufacturer(Build.MANUFACTURER);
        reportEvent.setChannel(getPackageName(UpdateManager.mContext));
        String tempStr = new Gson().toJson(reportEvent);
        UpdateLog.d("上报升级结果 json = " + tempStr);
        return tempStr;
    }

    /**
     * 获取 PackageName
     *
     * @return
     */
    private static String getPackageName(Context context) {
        if (context == null) {
            return null;
        }
        return context.getPackageName();
    }

}
