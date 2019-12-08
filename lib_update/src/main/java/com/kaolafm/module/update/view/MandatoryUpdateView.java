package com.kaolafm.module.update.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.kaolafm.module.update.R;
import com.kaolafm.module.update.UpdateManager;
import com.kaolafm.module.update.listener.IDownloadListener;

import java.lang.ref.WeakReference;

/**
 * 强制升级view
 */
public class MandatoryUpdateView extends ConstraintLayout {
    public static final int SHOW_TYPE_LOADING = 1;
    public static final int SHOW_TYPE_DOWNLOAING = 2;

    DownloadListener mDownloadListener;
    ImageView loadingView;
    ImageView downloadProgressingBg;
    ImageView carProgress;
    ConstraintLayout mainLayout;
    private static int MAX_PROGRESS = 1000;
    private int mOldProgress;
    private ConstraintSet mConstraintSet;
    private int mShowType;

    public MandatoryUpdateView(Context context) {
        super(context);
        initView();
    }

    public MandatoryUpdateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MandatoryUpdateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setShowType(int type) {
        mShowType = type;
        if (mShowType == SHOW_TYPE_LOADING) {

        } else {

        }
    }

    private void initView() {
        inflate(getContext(), R.layout.view_mandatory_update, this);
        carProgress = findViewById(R.id.downloadCar);
        mainLayout = findViewById(R.id.mainLayout);
        mConstraintSet = new ConstraintSet();
        mDownloadListener = new DownloadListener(this);
        loadingView = findViewById(R.id.downloadProgressing);
        downloadProgressingBg = findViewById(R.id.downloadProgressingBg);
        UpdateManager.getInstance(getContext()).addDownloadStatusListener(mDownloadListener);
    }

    private static class DownloadListener implements IDownloadListener {
        WeakReference<MandatoryUpdateView> viewWeakReference;

        DownloadListener(MandatoryUpdateView view) {
            viewWeakReference = new WeakReference<>(view);
        }

        @Override
        public void start() {

        }

        @Override
        public void loading(int progress, int totalSize) {
            MandatoryUpdateView mandatoryUpdateView = viewWeakReference.get();
//            if (mandatoryUpdateView != null) {
//                mandatoryUpdateView.customizeProgressView.setProgress((float) progress / (float) totalSize);
//            }
        }

        @Override
        public void complete() {

        }

        @Override
        public void fail(int code, String message) {

        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        UpdateManager.getInstance(getContext()).removeDownloadStatusListener(mDownloadListener);
    }

    /**
     * 横竖屏切换
     */
    public void onConfigurationChanged() {

    }

    public void update(float progress) {

        int currentProgress = (int) (progress * MAX_PROGRESS);
        if (mOldProgress == currentProgress || mOldProgress > MAX_PROGRESS) {
            return;
        }
        Log.e("logx", "xxxxxx 进度= " + mOldProgress + "xxxx = " + progress);
        setViewVisibility(loadingView, View.VISIBLE);
        mOldProgress = currentProgress;
        mConstraintSet.clone(mainLayout);
        mConstraintSet.constrainPercentWidth(loadingView.getId(), progress);
        mConstraintSet.applyTo(mainLayout);
    }

    public void updateNew(float progress) {
        int currentProgress = (int) (progress * MAX_PROGRESS);
        if (mOldProgress == currentProgress || mOldProgress > MAX_PROGRESS) {
            return;
        }
        Log.e("logx", "xxxxxx 进度= " + mOldProgress);
        setViewVisibility(loadingView, View.VISIBLE);
        mOldProgress = currentProgress;

        float progressPercent = progress;
        ConstraintLayout.LayoutParams lparams = (ConstraintLayout.LayoutParams) loadingView.getLayoutParams();
        lparams.width = 0;
        lparams.height = LayoutParams.MATCH_PARENT;
        lparams.matchConstraintPercentWidth = progressPercent;
        lparams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        lparams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        lparams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        loadingView.setLayoutParams(lparams);
        ConstraintLayout.LayoutParams lparamsx = (ConstraintLayout.LayoutParams) carProgress.getLayoutParams();
        lparamsx.leftMargin = loadingView.getWidth();
        carProgress.setLayoutParams(lparamsx);
    }

    private void setViewVisibility(View view, int visibility) {
        if (view.getVisibility() == visibility) {
            return;
        }
        view.setVisibility(visibility);
    }
}
