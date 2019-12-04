package com.kaolafm.module.update.utils;

import android.content.Context;
import android.os.Build;

import com.google.gson.Gson;
import com.kaolafm.module.update.UpdateManager;
import com.kaolafm.module.update.modle.PluginInfo;
import com.kaolafm.module.update.modle.ReportEvent;

public class ReportUtil {

    /**
     * 添加上报事件
     */
    public static String getEvent(Context context, int errorCode, String message) {
        ReportEvent reportEvent = new ReportEvent();
        reportEvent.setEventcode(UpdateConstant.UPDATE_ERROR_REPORT_EVENT_CODE);
        reportEvent.setAppid(RequestParamsCacheInfoUtil.getAppId(UpdateManager.mContext));
        reportEvent.setUdid(RequestParamsCacheInfoUtil.getUdid(UpdateManager.mContext));
        reportEvent.setOpenid(RequestParamsCacheInfoUtil.getOpenid(UpdateManager.mContext));
        reportEvent.setApp_version(RequestParamsCacheInfoUtil.getAppVersion(UpdateManager.mContext));
        reportEvent.setTimestamp(String.valueOf(System.currentTimeMillis()));
        reportEvent.setManufacturer(Build.MANUFACTURER);
        reportEvent.setChannel(getPackageName(UpdateManager.mContext));

        PluginInfo pluginInfo = DownloadCacheInfoUtil.getPluginInfo(context);
        reportEvent.setType(getUpdateType(pluginInfo));

        reportEvent.setResult(String.valueOf(errorCode));
        reportEvent.setRemarks1(RequestParamsCacheInfoUtil.getAppVersion(context));
        if (pluginInfo != null) {
            reportEvent.setRemarks2(pluginInfo.getVersionNum());
        }
        reportEvent.setRemarks3("2");
        reportEvent.setMessage(message);
        String tempStr = new Gson().toJson(reportEvent);
        UpdateLog.d("上报升级失败结果 json = " + tempStr);
        return tempStr;
    }

    private static String getUpdateType(PluginInfo pluginInfo) {
        if (pluginInfo == null) {
            return UpdateConstant.BLANK_STR;
        }
        String updateType = UpdateVersionCacheInfoUtil.TYPE_UPDATE_BACKGROUND;
        if (pluginInfo.isMandatoryUpdate()) {
            updateType = UpdateVersionCacheInfoUtil.TYPE_UPDATE_FORCE;
        } else if (pluginInfo.isShowToast()) {
            updateType = UpdateVersionCacheInfoUtil.TYPE_UPDATE_RECOMMEND;
        }
        return updateType;
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
