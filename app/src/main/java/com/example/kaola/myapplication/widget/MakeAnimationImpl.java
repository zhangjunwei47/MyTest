package com.example.kaola.myapplication.widget;

import android.view.View;

/******************************************
 * 类描述:
 *
 * @version: V1.0
 * @author: yangshaoning
 * @time: 2018-04-18 14:55
 ******************************************/
public interface MakeAnimationImpl {
    /**
     * 按压动画效果
     */
    void makeAnimationPress(View view);

    /**
     * 松开动画效果
     */
    void makeAnimationRelease(View view);
}