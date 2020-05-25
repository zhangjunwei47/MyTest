package com.example.kaola.myapplication.init;

import android.app.Application;
import android.util.Log;

import com.example.kaola.lib.AppInit;
import com.example.kaola.lib.AppInitializable;
import com.example.kaola.lib.Constants;

/**
 * @ClassName PlayerInitializable
 * @Description TODO
 * @Author zhangchao
 * @Date 2020-05-09 11:20
 * @Version 1.0
 */
//@AppInit(priority = 40)
public class PlayerInitializable implements AppInitializable {
    @Override
    public void onCreate(Application application) {
        Log.d(Constants.TAG, "播放器初始化");
    }

    @Override
    public void asyncCreate(Application application) {
        Log.d(Constants.TAG, "异步播放器初始化");
    }
}
