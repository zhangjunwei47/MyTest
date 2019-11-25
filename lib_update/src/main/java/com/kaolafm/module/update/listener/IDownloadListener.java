package com.kaolafm.module.update.listener;

/**
 * 下载状态listener
 */
public interface IDownloadListener {
    /**
     * 开始下载
     */
    void start();

    /**
     * 正在下载进度
     */
    void loading(int progress);

    /**
     * 下载完成
     */
    void complete();

    /**
     * 请求失败
     */
    void fail(int code, String message);
}
