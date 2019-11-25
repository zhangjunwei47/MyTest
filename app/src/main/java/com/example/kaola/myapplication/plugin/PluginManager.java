package com.example.kaola.myapplication.plugin;

import android.content.Context;

import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @author zhangchao on 2019-09-20.
 */

public class PluginManager {

    private void test(Context context, String apk) {
        String dexDir = context.getDir("/plugindex", Context.MODE_PRIVATE).getAbsolutePath();
        DexClassLoader classLoader = new DexClassLoader(apk, dexDir, null, null);
    }

    private void test1()
    {

    }
}
