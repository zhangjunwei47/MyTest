package com.example.kaola.myapplication;

import android.content.Context;
import androidx.multidex.MultiDexApplication;

import com.example.kaola.myapplication.util.LogToFile;


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
    }



}
