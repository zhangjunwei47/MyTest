package com.example.kaola.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;


import com.example.kaola.myapplication.util.AnimUtil;

import skin.support.widget.SkinCompatRelativeLayout;

/******************************************
 * 类描述:
 *
 * @version: V1.0
 * @author: yangshaoning
 * @time: 2018-04-18 15:47
 *
 * 备注如果其子控件添加了点击事件可能会影响动画效果
 *
 ******************************************/

public class CScaleRelativeLayout extends RelativeLayout implements CScaleWidgetInter {
    private boolean canMakeAnimation = true;

    private MakeAnimationImpl mMakeAnimationImpl;

    public CScaleRelativeLayout(Context context) {
        super(context);
    }

    public CScaleRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CScaleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @TargetApi(21)
//    public CScaleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (canMakeAnimation) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if (mMakeAnimationImpl != null) {
                        mMakeAnimationImpl.makeAnimationPress(this);
                    } else {
                        AnimUtil.startScalePress(this);
                    }
                    break;
                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    if (mMakeAnimationImpl != null) {
                        mMakeAnimationImpl.makeAnimationRelease(this);
                    } else {
                        AnimUtil.startScaleRelease(this);
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    @Override
    public void setMakeAnimationImpl(MakeAnimationImpl makeAnimationImpl) {
        this.mMakeAnimationImpl = makeAnimationImpl;
    }
}
