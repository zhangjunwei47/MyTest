package com.example.kaola.myapplication.network.api;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author zhangchao on 2019/3/5.
 */

public interface RequestService {
    @POST("/thirdParty/login")
    Call<String> requestToken(@Body RequestBody body);


}
