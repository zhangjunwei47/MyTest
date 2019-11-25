package com.kaolafm.module.update.listener;

public interface RequestCallback {
    void result(Object object);

    void requestFailure();
}
