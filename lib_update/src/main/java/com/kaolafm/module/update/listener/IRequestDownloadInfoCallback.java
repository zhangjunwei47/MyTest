package com.kaolafm.module.update.listener;

public interface IRequestDownloadInfoCallback {
    /**
     * 是否强制升级
     *
     * @param isNeedShowToast
     * @param isMandatory
     */
    void pluginNeedDownload(boolean isMandatory, boolean isNeedShowToast, String toastMessage);

    /**
     * 没有插件需要升级
     */
    void noPluginNeedDownload();
}
