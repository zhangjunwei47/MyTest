package com.example.kaola.myapplication.network;

import com.example.kaola.myapplication.network.model.TokenRequestData;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author zhangchao on 2019/3/5.
 */

public class XRequest {

    public RequestBody getToken(TokenRequestData requestData) {
        String json = new Gson().toJson(requestData);
        return RequestBody.create(MediaType.parse("application/json"), json);
    }

}
