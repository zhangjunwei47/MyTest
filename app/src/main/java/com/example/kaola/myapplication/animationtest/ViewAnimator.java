package com.example.kaola.myapplication.animationtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import com.zc.test.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @author zhangchao on 2018/10/22.
 */

public class ViewAnimator extends View {
    Paint mPaint, mPaint2;
    private RectF mRectF, mRectF2;

    private int width;
    int[] colors = {getResources().getColor(R.color.endhahahaha, null), getResources().getColor(R.color.hahahaha, null), getResources().getColor(R.color.endhahahaha, null)};

    public ViewAnimator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }


    private void initPaint() {
        mPaint = new Paint();
        LinearGradient mGradient = new LinearGradient(0, 0, 100, 0, colors,
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(mGradient);
        mRectF = new RectF();
        width = 10;
        timer();
    }

    private void initPaint2() {
        mPaint2 = new Paint();
        mRectF = new RectF();
        width = 10;
        timer();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            mRectF.left = 100 - xxx;
            mRectF.top = 0;
            mRectF.right = 200 - xxx;
            mRectF.bottom = 2;
        }
    }

    private void changeRect() {
        mRectF.left = 100 - xxx;
        mRectF.top = 0;
        mRectF.right = 200 - xxx;
        mRectF.bottom = 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("logx", "xxxxxxxxxx onDraw");
        canvas.drawRect(mRectF, mPaint);
    }

    float xxx = 0.2f;

    private void timer() {
        Observable.interval(0, 50, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                xxx = xxx + 0.5f;
                changeRect();
                invalidate();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
