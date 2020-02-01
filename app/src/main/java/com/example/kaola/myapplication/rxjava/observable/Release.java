package com.example.kaola.myapplication.rxjava.observable;

/**
 * @ClassName Release
 * @Description
 * 1. 描述打电话这件事
 * 2. 用于取消挂掉电话和获取当前是否在打电话
 * @Author zhangchao
 * @Date 2020-01-31 08:50
 * @Version 1.0
 */
public interface Release {
    boolean isReleased();

    void release();
}

