package com.example.kaola.myapplication.init;

import android.app.Application;
import android.util.Log;

import com.example.kaola.lib.AppInit;
import com.example.kaola.lib.AppInitializable;
import com.example.kaola.lib.Constants;

/**
 * @ClassName SdkInitializable
 * @Description TODO
 * @Author zhangchao
 * @Date 2020-05-09 11:14
 * @Version 1.0
 */
@AppInit(priority = 10, isAsync = true)
public class SdkInitializable implements AppInitializable {
    @Override
    public void onCreate(Application application) {
        Log.d(Constants.TAG, "sdk初始化");
    }

    @Override
    public void asyncCreate(Application application) {

    }

    //    private void initSdk() {
//        OpenSDK.getInstance().initSDK(MyApplication.this, new HttpCallback<Boolean>() {
//            @Override
//            public void onSuccess(Boolean isSuccess) {
//                Log.e("logx", "xxxxxxx init 成功");
//                //showToast(isSuccess ? "初始化SDK成功" : "初始化SDK失败");
//                if (OpenSDK.getInstance().isActivate()) {
//                    Log.e("logx", "xxxxxxx 已经激活成功..");
//                    return;
//                }
//                OpenSDK.getInstance().activate(new HttpCallback<Boolean>() {
//                    @Override
//                    public void onSuccess(Boolean aBoolean) {
//                        Log.e("logx", "xxxxxxx 激活 成功");
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//                        Log.e("logx", "xxxxxxx 激活 失败");
//                    }
//                });
//            }
//
//            @Override
//            public void onError(ApiException exception) {
//                //showToast("初始化SDK失败，错误码=" + exception.getCode() + ",错误信息=" + exception.getMessage());
//                Log.e("logx", "xxxxxxx init 失败");
//            }
//        });
//    }
}
