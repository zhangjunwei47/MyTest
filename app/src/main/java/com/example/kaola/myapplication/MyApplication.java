package com.example.kaola.myapplication;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.example.kaola.myapplication.util.LogToFile;
import com.kaolafm.opensdk.OpenSDK;
import com.kaolafm.opensdk.http.core.HttpCallback;
import com.kaolafm.opensdk.http.error.ApiException;


/**
 * @author zhangchao on 2018/6/20.
 */

public class MyApplication extends MultiDexApplication {

    public static Context context;


    public static boolean SDKInit = false;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //  attachBaseContext(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LogToFile.init();
        initSdk();
    }

    private void initSdk() {
        OpenSDK.getInstance().initSDK(MyApplication.this, new HttpCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isSuccess) {
                Log.e("logx", "xxxxxxx init 成功");
                //showToast(isSuccess ? "初始化SDK成功" : "初始化SDK失败");
                if (OpenSDK.getInstance().isActivate()) {
                    Log.e("logx", "xxxxxxx 已经激活成功..");
                    return;
                }
                OpenSDK.getInstance().activate(new HttpCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        Log.e("logx", "xxxxxxx 激活 成功");
                    }

                    @Override
                    public void onError(ApiException e) {
                        Log.e("logx", "xxxxxxx 激活 失败");
                    }
                });
            }

            @Override
            public void onError(ApiException exception) {
                //showToast("初始化SDK失败，错误码=" + exception.getCode() + ",错误信息=" + exception.getMessage());
                Log.e("logx", "xxxxxxx init 失败");
            }
        });
    }

}
