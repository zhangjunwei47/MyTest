package com.example.kaola.myapplication.animationtest;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.zc.test.R;


/**
 * @author zhangchao on 2018/5/2.
 * 动画实现 --- 属相动画
 */

public class AnimationByAnimator extends Fragment {

    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animation, container, false);
        imageView = view.findViewById(R.id.hahahaha1);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        start();
    }

    private void start() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationX", 0.0f, 300, -300, 0f);
        animator.setDuration(2000);//动画时间
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);//设置动画重复次数
        animator.start();//启动动
    }
}
