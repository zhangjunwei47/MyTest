package com.kaolafm.module.update.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaolafm.module.update.R;
import com.kaolafm.module.update.UpdateManager;
import com.kaolafm.module.update.listener.IDownloadListener;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 强制升级view
 */
public class MandatoryUpdateView extends ConstraintLayout {
    public static final int SHOW_TYPE_LOADING = 1;
    public static final int SHOW_TYPE_DOWNLOADING = 2;
    public static final float MIN_PROGRESS_STEP_VALUE = 0.01F;
    public static final float PROGRESS_DEFAULT_VALUE = 0.0F;
    private DownloadListener mDownloadListener;
    private ImageView loadingView;
    private ImageView downloadProgressingBg;
    private ImageView carProgress;
    private ConstraintLayout mainLayout;
    private TextView messageTextView;
    private static int MAX_PROGRESS = 1000;
    private int mOldProgress;
    private ConstraintSet mConstraintSet;
    private int mShowType = SHOW_TYPE_LOADING;
    private float progress = PROGRESS_DEFAULT_VALUE;

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
        setMessageText();
    }

    private void initView() {
        inflate(getContext(), R.layout.view_mandatory_update, this);
        carProgress = findViewById(R.id.downloadCar);
        mainLayout = findViewById(R.id.mainLayout);
        mConstraintSet = new ConstraintSet();
        mDownloadListener = new DownloadListener(this);
        loadingView = findViewById(R.id.downloadProgressing);
        downloadProgressingBg = findViewById(R.id.downloadProgressingBg);
        messageTextView = findViewById(R.id.messageTextView);
        UpdateManager.getInstance(getContext()).addDownloadStatusListener(mDownloadListener);
        loading();
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

    public void setMessageText() {
        if (mShowType == SHOW_TYPE_LOADING) {
            messageTextView.setText(getResources().getString(R.string.update_loading_plugin));
        } else {
            messageTextView.setText(getResources().getString(R.string.update_downloading_plugin));
        }
    }

    private void getDownloadIngProgress(float progress) {
        int progressInt = (int) (progress * 100);
        messageTextView.setText(getResources().getString(R.string.update_downloading_plugin) + progressInt + "%");
    }


    public void updateNew(float progress) {
        int currentProgress = (int) (progress * MAX_PROGRESS);
        if (mOldProgress == currentProgress || mOldProgress > MAX_PROGRESS) {
            return;
        }
        setViewVisibility(loadingView, View.VISIBLE);
        mOldProgress = currentProgress;
        float progressPercent = progress;
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) loadingView.getLayoutParams();
        params.width = 0;
        params.height = LayoutParams.MATCH_PARENT;
        params.matchConstraintPercentWidth = progressPercent;
        params.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        loadingView.setLayoutParams(params);
        ConstraintLayout.LayoutParams paramCar = (ConstraintLayout.LayoutParams) carProgress.getLayoutParams();
        paramCar.leftMargin = loadingView.getWidth();
        carProgress.setLayoutParams(paramCar);
    }

    private void setViewVisibility(View view, int visibility) {
        if (view.getVisibility() == visibility) {
            return;
        }
        view.setVisibility(visibility);
    }

    private void timer() {
        progress = progress + MIN_PROGRESS_STEP_VALUE;
        int progressInt = (int) (progress * MAX_PROGRESS);
        if (progressInt > MAX_PROGRESS) {
            progress = PROGRESS_DEFAULT_VALUE;
            mOldProgress = 0;
            return;
        }
        updateNew(progress);
        getDownloadIngProgress(progress);
    }


    private void loading() {
        Observable.interval(100, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        timer();
                    }
                });
    }
}
