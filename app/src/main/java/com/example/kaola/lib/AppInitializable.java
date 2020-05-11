package com.example.kaola.lib;

import android.app.Application;

/**
 * @ClassName AppInitializable
 * @Description TODO
 * @Author zhangchao
 * @Date 2020-05-09 11:12
 * @Version 1.0
 */
public interface AppInitializable {

    void onCreate(Application application);

    void asyncCreate(Application application);
}
