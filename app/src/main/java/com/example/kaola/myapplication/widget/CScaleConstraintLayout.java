package com.example.kaola.myapplication.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.kaola.myapplication.util.AnimUtil;

/**
 * @author zhangchao on 2018/6/6.
 */

public class CScaleConstraintLayout extends ConstraintLayout implements CScaleWidgetInter {
    private boolean canMakeAnimation = true;

    private MakeAnimationImpl mMakeAnimationImpl =  new MakeAnimationImpl() {
        @Override
        public void makeAnimationPress(View view) {
            AnimUtil.startScalePress(view);
        }

        @Override
        public void makeAnimationRelease(View view) {
            AnimUtil.startScaleRelease(view);
        }
    };

    public CScaleConstraintLayout(Context context) {
        super(context);
    }

    public CScaleConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CScaleConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setMakeAnimationImpl(MakeAnimationImpl makeAnimationImpl) {
        this.mMakeAnimationImpl = makeAnimationImpl;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("logx","xxxxxxx touch = "+ event.getAction());
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
}
