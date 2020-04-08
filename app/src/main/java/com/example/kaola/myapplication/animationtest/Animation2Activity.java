package com.example.kaola.myapplication.animationtest;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zc.test.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author zhangchao on 2019-08-26.
 */

public class Animation2Activity extends AppCompatActivity {

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animationtwo);
        findViewById(R.id.beginbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (constraintLayout.getVisibility() == VISIBLE) {
                    hide();
                } else {
                    show();
                }
            }
        });
        constraintLayout = findViewById(R.id.cl_live);
    }

    private void hide() {
        if (constraintLayout.getVisibility() == VISIBLE) {
            Animation animation = AnimationUtils.loadAnimation(this
                    , R.anim.item_animation_from_left);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.e("logx", "xxxxx设置为gone" );
                    constraintLayout.setVisibility(GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            constraintLayout.startAnimation(animation);
        }
    }

    private void show() {
        if (constraintLayout.getVisibility() == View.GONE) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.item_animation_from_right);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Log.e("logx", "xxxxxxxxxxx showLiveItemWithAnimation2222");

                    constraintLayout.setVisibility(VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.e("logx", "xxxxxxxxxxx showLiveItemWithAnimation233333");


                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    Log.e("logx", "xxxxxxxxxxx showLiveItemWithAnimation4444");

                }
            });
            constraintLayout.startAnimation(animation);
        }
    }




}
