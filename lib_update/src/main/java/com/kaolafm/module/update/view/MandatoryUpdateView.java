package com.kaolafm.module.update.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.kaolafm.module.update.R;
import com.kaolafm.module.update.UpdateManager;
import com.kaolafm.module.update.listener.IDownloadListener;

import java.lang.ref.WeakReference;

/**
 * 强制升级view
 */
public class MandatoryUpdateView extends ConstraintLayout {
    DownloadListener mDownloadListener;
    CustomizeProgressView customizeProgressView;

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

    private void initView() {
        inflate(getContext(), R.layout.view_mandatory_update, this);
        customizeProgressView = findViewById(R.id.downloadProgressView);
        mDownloadListener = new DownloadListener(this);
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
            if (mandatoryUpdateView != null) {
                mandatoryUpdateView.customizeProgressView.setProgress((float) progress / (float) totalSize);
            }
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

    public void update(float progress)
    {
        customizeProgressView.setProgress(progress);
    }
}
