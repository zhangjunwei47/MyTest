package com.kaolafm.module.update.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.kaolafm.module.update.R;

/**
 * 自定义进度条
 */
public class CustomizeProgressView extends ConstraintLayout {
    /**
     * 最大进度
     */
    private static int MAX_PROGRESS = 1000;
    private int mOldProgress;
    private ImageView mProgressView;
    private ConstraintSet mConstraintSet;

    public CustomizeProgressView(Context context) {
        super(context);
        initView();
    }

    public CustomizeProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomizeProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mConstraintSet = new ConstraintSet();
        inflate(getContext(), R.layout.view_customize_progress, this);
        mProgressView = findViewById(R.id.downloadProgressing);
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(float progress) {
        int currentProgress = (int) (progress * MAX_PROGRESS);
        if (mOldProgress == currentProgress || mOldProgress > MAX_PROGRESS) {
            return;
        }
        setViewVisibility(mProgressView, View.VISIBLE);
        mOldProgress = currentProgress;
        mConstraintSet.clone(this);
        mConstraintSet.constrainPercentWidth(mProgressView.getId(), progress);
        mConstraintSet.applyTo(this);
    }

    private void setViewVisibility(View view, int visibility) {
        if (view.getVisibility() == visibility) {
            return;
        }
        view.setVisibility(visibility);
    }
}
