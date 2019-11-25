package com.example.kaola.myapplication.network;

import android.util.Log;

import com.example.kaola.myapplication.network.api.RequestService;
import com.example.kaola.myapplication.network.model.TokenData;
import com.example.kaola.myapplication.network.model.TokenRequestData;
import com.google.gson.Gson;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhangchao on 2019/3/5.
 */

public class RequestHelper {
    private static RequestHelper requestHelper;
    private Retrofit mRetrofit;
    private RequestService requestService;

    private RequestHelper() {

    }

    public static RequestHelper getInstance() {
        if (requestHelper == null) {
            synchronized (RequestHelper.class) {
                if (requestHelper == null) {
                    requestHelper = new RequestHelper();
                }
            }
        }
        return requestHelper;
    }

    public void init() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(RequestConstants.BASE_URL_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        requestService = mRetrofit.create(RequestService.class);
    }

    public void addRequest(TokenRequestData tokenRequestData) {
        RequestBody requestBody = new XRequest().getToken(tokenRequestData);
        Call<String> call = requestService.requestToken(requestBody);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("logx", "xxxx 请求返回结果");
                if (response.isSuccessful()) {
                    Log.e("logx", "xxxx 请求返回正确结果: " + response.body());
                    TokenData tokenData = new Gson().fromJson(response.body(), TokenData.class);
                    if (tokenData != null && tokenData.getData() != null) {
                        Log.e("logx", "xxxx 请求的token=  " + tokenData.getData().getToken());
                    }
                } else {
                    Log.e("logx", "xxxx 请求返回错误");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("logx", "xxxx 请求返回失败");
            }
        });
    }

}
