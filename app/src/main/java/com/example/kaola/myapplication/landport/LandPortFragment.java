package com.example.kaola.myapplication.landport;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


import com.zc.test.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


/**
 * @author zhangchao on 2019-07-26.
 */

public class LandPortFragment extends Fragment {
    View rootView;
    ConstraintLayout mRootView;
    ConstraintLayout mAnimation;
    ImageView showLineImageView;
    ImageView topIv;
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getContext(), R.layout.fragment_land_layout, null);
        mRootView = rootView.findViewById(R.id.root_layout);
        mAnimation = rootView.findViewById(R.id.cl_live);
        showLineImageView = rootView.findViewById(R.id.imagexxx);
        topIv = rootView.findViewById(R.id.imagetop);
        button = rootView.findViewById(R.id.beginbtn);
        button.setOnClickListener(v -> {
            if (mAnimation.getVisibility() == GONE) {
                show();
            } else {
                hide();
            }
        });
        return rootView;
    }


    ConstraintSet set = new ConstraintSet();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        set.clone(mRootView);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            changeLand(set);
        } else {
            changePort(set);
        }

        set.applyTo(mRootView);
    }

    private void changeLand(ConstraintSet set) {
        set.clear(mAnimation.getId(), ConstraintSet.TOP);
        set.connect(mAnimation.getId(), ConstraintSet.TOP, topIv.getId(), ConstraintSet.BOTTOM);

        set.clear(showLineImageView.getId(), ConstraintSet.TOP);
        set.connect(showLineImageView.getId(), ConstraintSet.TOP, mAnimation.getId(), ConstraintSet.BOTTOM);
        //下面这句代码失灵不知道为什么
        set.setGoneMargin(showLineImageView.getId(), ConstraintSet.TOP, 20);
        // 下面的代码可以
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams)(showLineImageView.getLayoutParams());
        lp.goneTopMargin = 20;
        showLineImageView.setLayoutParams(lp);
    }

    private void changePort(ConstraintSet set) {
        set.clear(mAnimation.getId(), ConstraintSet.TOP);
        set.connect(mAnimation.getId(), ConstraintSet.TOP, button.getId(), ConstraintSet.BOTTOM);

        set.clear(showLineImageView.getId(), ConstraintSet.TOP);
        set.connect(showLineImageView.getId(), ConstraintSet.TOP, mAnimation.getId(), ConstraintSet.BOTTOM);
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams)(showLineImageView.getLayoutParams());
        lp.goneTopMargin = 400;
        showLineImageView.setLayoutParams(lp);
    }

    private void hide() {
        if (mAnimation.getVisibility() == VISIBLE) {
            Animation animation = AnimationUtils.loadAnimation(mRootView.getContext()
                    , R.anim.item_animation_from_left);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mAnimation.setVisibility(GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mAnimation.startAnimation(animation);
        }
    }

    private void show() {
        if (mAnimation.getVisibility() == View.GONE) {
            Animation animation = AnimationUtils.loadAnimation(mRootView.getContext(), R.anim.item_animation_from_right);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mAnimation.setVisibility(VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {


                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            mAnimation.startAnimation(animation);
        }
    }

}
