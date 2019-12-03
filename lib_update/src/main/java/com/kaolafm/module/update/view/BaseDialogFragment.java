package com.kaolafm.module.update.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.kaolafm.module.update.R;
import com.kaolafm.module.update.UpdateActivity;
import com.kaolafm.module.update.UpdateManager;

/**
 * @author Donald Yan
 * @date 2018/5/20
 * 子类只有重写getContentView()即可.
 * 默认dialog在屏幕中央显示.
 */

public class BaseDialogFragment extends DialogFragment  {

    //左右边距
    private int margin;

    //宽度
    private int width = 0;

    //高度
    private int height = getScreenHeight() / 2;

    //灰度深浅
    private float dimAmount = 0.5f;


    //是否点击外部取消
    private boolean outCancel = true;

    @StyleRes
    private int animStyle;
    private int gravity;


    public void setMargin(int margin) {
        this.margin = margin;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
    }

    public void setAnimStyle(int animStyle) {
        this.animStyle = animStyle;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public void setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.dialog_fragment_content, container, false);
        View contentView = getContentView();
        if (contentView != null) {
            view.addView(contentView);
        }
       // showAccordingToScreen(ResUtil.getOrientation());
        view.setOnClickListener(v -> dismiss());
        return view;
    }

    protected View getContentView() {
        return null;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BaseDialogTheme);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    private void initParams() {
        setupWindowDimAmount();
        setupWindowGravity();
        setupWindowAnim();
        setupWindowSize();
        setCancelable(outCancel);
    }

    private void setupWindowDimAmount() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount;
        }
    }

    private void setupWindowGravity() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            //是否在底部显示
            lp.gravity = gravity;// Gravity.BOTTOM;
        }
    }

    private void setupWindowAnim() {
//        Window window = getDialog().getWindow();
//        if (window != null) {
//            if (animStyle == 0) {
//                animStyle = R.style.DefaultAnimation;
//            }
//            //设置dialog进入、退出的动画
//            window.setWindowAnimations(animStyle);
//        }
    }

    /**
     * 设置window大小,会影响content view的显示,所以去掉;
     */
    private void setupWindowSize() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            //设置dialog宽度
            //必须减去状态栏的高度,否则,dialog弹出时,状态栏会出现;
            lp.height = height;// ScreenUtil.getScreenHeightWithoutStatus();
            lp.width = width;// WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(lp);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * @param orientation Configuration.ORIENTATION_LANDSCAPE(横屏) or Configuration.ORIENTATION_PORTRAIT(竖屏)
     */
    protected void showAccordingToScreen(int orientation) {
        setupWindowSize();
    }

    /**
     * 获取屏幕高度
     */
    public  int getScreenHeight() {
        // 解决横竖屏切换时，偶现的 获取屏幕高度 错乱问题,在横版情况下，高度低于宽度,返回 宽高度中最小值
        WindowManager wm = (WindowManager) UpdateActivity.mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
