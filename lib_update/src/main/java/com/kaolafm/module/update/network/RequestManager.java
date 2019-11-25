package com.kaolafm.module.update.network;

import com.google.gson.Gson;
import com.kaolafm.module.update.UpdateManager;
import com.kaolafm.module.update.listener.RequestCallback;
import com.kaolafm.module.update.modle.PluginInfoBaseResult;
import com.kaolafm.module.update.utils.RequestParamsUtil;
import com.kaolafm.module.update.utils.UpdateConstant;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络请求管理
 */
public class RequestManager {

    public RequestManager() {
    }

    /**
     * 请求升级信息
     */
    public void requestUpdateInfo(RequestCallback requestCallback) {
        new OkHttpClient()
                .newBuilder()
                .addInterceptor(createGetInterceptor(RequestParamsUtil.getCommonParams(UpdateManager.mContext)))
                .build()
                .newCall(createGetRequest(UpdateConstant.REQUEST_UPDATE_INFO_URL))
                .enqueue(createRequestUpdateInfoCallback(requestCallback));
    }

    /**
     * 上报升级结果状态
     */
    public void reportUpdateResultState(HashMap<String, String> parameter, RequestCallback requestCallback) {
        new OkHttpClient()
                .newBuilder()
                .addInterceptor(createPostInterceptor(RequestParamsUtil.getCommonParams(UpdateManager.mContext)))
                .build()
                .newCall(createPostRequest(parameter))
                .enqueue(createReportUpdateResultStateCallback(requestCallback));
    }

    private BasicParamsInterceptor createPostInterceptor(HashMap parameterMap) {
        return new BasicParamsInterceptor.Builder().addQueryParamsMap(parameterMap).build();
    }

    private BasicParamsInterceptor createGetInterceptor(HashMap parameterMap) {
        return new BasicParamsInterceptor.Builder().addQueryParamsMap(parameterMap).build();
    }

    private Request createPostRequest(HashMap hashMap) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String json = new Gson().toJson(hashMap);
        RequestBody requestBody = RequestBody.create(mediaType, json);
        return new Request.Builder().url(UpdateConstant.REPORT_UPDATE_RESULT_URL)
                .post(requestBody)
                .build();
    }

    private Request createGetRequest(String url) {
        return new Request.Builder().url(url).build();
    }

    private Callback createRequestUpdateInfoCallback(RequestCallback requestCallback) {
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback != null) {
                    requestCallback.requestFailure();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PluginInfoBaseResult pluginInfoBaseResult = null;
                if (response.isSuccessful() && response.body() != null) {
                    Gson gson = new Gson();
                    pluginInfoBaseResult = gson.fromJson(response.body().charStream(), PluginInfoBaseResult.class);
                }
                if (requestCallback == null) {
                    return;
                }
                if (pluginInfoBaseResult != null && pluginInfoBaseResult.getResult() != null) {
                    requestCallback.result(pluginInfoBaseResult.getResult());
                } else {
                    requestCallback.requestFailure();
                }
            }
        };
    }

    private Callback createReportUpdateResultStateCallback(RequestCallback requestCallback) {
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback != null) {
                    requestCallback.requestFailure();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallback != null) {
                    requestCallback.result(response);
                }
            }
        };
    }
}
