package com.example.kaola.myapplication.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * 动画相关工具类
 * @author donald
 * @date 2018/5/7
 */

public class AnimUtil {

    public static void startScale(View view, float scaleValue, float defaultScale, int duration) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animationScaleX = ObjectAnimator.ofFloat(view, "scaleX", scaleValue, defaultScale);
        ObjectAnimator animationScaleY = ObjectAnimator.ofFloat(view, "scaleY", scaleValue, defaultScale);
        animationScaleX.setDuration(duration);
        animationScaleY.setDuration(duration);
        animatorSet.playTogether(animationScaleX, animationScaleY);

        animatorSet.start();
    }
    public static void startScalePress(View view){
        startScale(view, 1F, 0.88F, 500);
    }
    public static void startScaleRelease(View view){
        startScale(view, 0.88F, 1F, 500);
    }
}
