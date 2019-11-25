package com.kaolafm.module.update.listener;

/**
 * 网络请求回调
 */
public interface RequestCallback {

    /**
     * 请求结果返回
     *
     * @param object
     */
    void result(Object object);

    /**
     * 请求失败回调
     */
    void requestFailure();
}
