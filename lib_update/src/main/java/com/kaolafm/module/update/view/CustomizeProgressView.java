package com.kaolafm.module.update.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.kaolafm.module.update.R;

/**
 * 自定义进度条
 */
public class CustomizeProgressView extends ConstraintLayout {

    ImageView progressView;

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
        inflate(getContext(), R.layout.view_customize_progress, this);
        progressView = findViewById(R.id.downloadProgressing);
        setBackgroundColor(getResources().getColor(R.color.update_layout_bg_color, null));
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(float progress) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        constraintSet.constrainPercentWidth(progressView.getId(), progress);
        constraintSet.applyTo(this);
    }


}
