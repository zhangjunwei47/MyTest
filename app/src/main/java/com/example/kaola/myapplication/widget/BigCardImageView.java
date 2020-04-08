package com.example.kaola.myapplication.widget;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

public class BigCardImageView extends AppCompatImageView {
    private boolean isPlaying = false;

    public BigCardImageView(Context context) {
        super(context);
    }

    public BigCardImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BigCardImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPlayState(boolean isPlaying) {
        this.isPlaying = isPlaying;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isPlaying) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        }
    }
}
