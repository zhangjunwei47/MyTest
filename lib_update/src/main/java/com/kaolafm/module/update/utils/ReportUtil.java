package com.kaolafm.module.update.utils;

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
        reportEvent.setAppid(RequestCacheInfoUtil.getAppId(UpdateManager.mContext));
        reportEvent.setUdid(RequestCacheInfoUtil.getUdid(UpdateManager.mContext));
        reportEvent.setOpenid(RequestCacheInfoUtil.getOpenid(UpdateManager.mContext));
        reportEvent.setApp_version(RequestCacheInfoUtil.getAppVersion(UpdateManager.mContext));
        String tempStr = new Gson().toJson(reportEvent);
        UpdateLog.d("上报升级结果 json = " + tempStr);
        return tempStr;
    }
}
