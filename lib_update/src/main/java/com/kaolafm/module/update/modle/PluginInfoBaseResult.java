package com.kaolafm.module.update.modle;

public class PluginInfoBaseResult {
    private PluginInfo result;

    private String serverTime;

    private String requestId;

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public PluginInfo getResult() {
        return result;
    }

    public void setResult(PluginInfo result) {
        this.result = result;
    }
}
