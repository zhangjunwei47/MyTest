package com.kaolafm.module.update.utils;

import android.content.Context;
import android.text.TextUtils;

import com.kaolafm.opensdk.log.Logging;
import com.kaolafm.report.ReportHelper;
import com.kaolafm.report.event.UpdateReportEvent;
import com.kaolafm.report.event.VersionChangeReportEvent;
import com.kaolafm.report.util.ReportConstants;

/**
 * 用于考拉升级
 */
public class ReportUtilx {

    /**
     * 上报升级
     */
    public static void reportUpdate(Context context) {
        String updateType = UpdateVersionCacheInfoUtil.getUpdateType(context);
        String oldVersion = UpdateVersionCacheInfoUtil.getOldVersion(context);
        if (!TextUtils.isEmpty(updateType)) {
            Logging.d(ReportConstants.REPORT_TAG, "有插件升级");
            reportVersion(context, updateType, oldVersion);
            return;
        }
        ReportHelper.getInstance().initUpdateEvent(getUpdateEventType(updateType));
    }

    /**
     * 获取升级上报 类型
     * 因为升级上报类型与版本变更上报类型定义值不一样需要转换
     *
     * @param type
     * @return
     */
    private static String getUpdateEventType(String type) {
        if (TextUtils.isEmpty(type)) {
            return UpdateReportEvent.TYPE_UPDATE_BY_SELF;
        }
        if (type.equals(VersionChangeReportEvent.TYPE_UPDATE_RECOMMEND)) {
            return UpdateReportEvent.TYPE_UPDATE_RECOMMEND;
        }
        if (type.equals(VersionChangeReportEvent.TYPE_UPDATE_BACKGROUND)) {
            return UpdateReportEvent.TYPE_UPDATE_BACKGROUND;
        }
        if (type.equals(VersionChangeReportEvent.TYPE_UPDATE_FORCE)) {
            return UpdateReportEvent.TYPE_UPDATE_FORCE;
        }
        return UpdateReportEvent.TYPE_UPDATE_BY_SELF;
    }

    /**
     * 升级成功上报
     */
    public static void reportVersion(Context context, String type, String oldVersion) {
        VersionChangeReportEvent changeReportEvent = new VersionChangeReportEvent();
        changeReportEvent.setType(type);
        changeReportEvent.setRemarks1(RequestParamsCacheInfoUtil.getAppVersion(context));
        changeReportEvent.setRemarks2(oldVersion);
        ReportHelper.getInstance().addEvent(changeReportEvent);
        UpdateVersionCacheInfoUtil.clearAlldata(context);
    }
}
