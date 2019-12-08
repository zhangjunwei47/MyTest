package com.kaolafm.module.update.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
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
    private static int MAX_PROGRESS = 1000;

    private static final float MIN_PROGRESS_STEP_VALUE = 0.01F;
    private static final float PROGRESS_DEFAULT_VALUE = 0.0F;

    private DownloadListener mDownloadListener;

    private ImageView loadingView;
    private ImageView carProgress;
    private TextView messageTextView;

    private int mOldProgress;
    private int mShowType = SHOW_TYPE_LOADING;
    private float progress = PROGRESS_DEFAULT_VALUE;

    public MandatoryUpdateView(Context context) {
        super(context);
        initView(null);
    }

    public MandatoryUpdateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public MandatoryUpdateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MandatoryUpdateView, 0, 0);
        try {
            mShowType = ta.getInt(R.styleable.MandatoryUpdateView_view_type, 0);
        } finally {
            ta.recycle();
        }
        inflate(getContext(), R.layout.view_mandatory_update, this);
        carProgress = findViewById(R.id.downloadCar);

        mDownloadListener = new DownloadListener(this);
        loadingView = findViewById(R.id.downloadProgressing);
        messageTextView = findViewById(R.id.messageTextView);
        UpdateManager.getInstance(getContext()).addDownloadStatusListener(mDownloadListener);
        setMessageText();
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
                float tempFloat = ((float) progress / (float) totalSize);
                mandatoryUpdateView.updateAnimation(tempFloat);
                mandatoryUpdateView.setDownloadIngProgressText(tempFloat);
            }
        }

        @Override
        public void complete() {
            MandatoryUpdateView mandatoryUpdateView = viewWeakReference.get();
            if (mandatoryUpdateView != null) {
                mandatoryUpdateView.resetLoadingView();
                mandatoryUpdateView.startLoadingPlugin();
            }
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

    /**
     * @param progress
     */
    private void setDownloadIngProgressText(float progress) {
        int currentProgress = (int) (progress * MAX_PROGRESS);
        if (mOldProgress == currentProgress || mOldProgress > MAX_PROGRESS) {
            return;
        }
        int progressInt = (int) (progress * 100);
        messageTextView.setText(getResources().getString(R.string.update_downloading_plugin) + progressInt + "%");
    }


    /**
     * 更新进度动画
     *
     * @param progress
     */
    public void updateAnimation(float progress) {
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
        updateAnimation(progress);
    }


    private void startLoadingPlugin() {
        Observable.interval(100, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        timer();
                    }
                });
        setMessageText();
    }

    /**
     * 回复动画ui
     */
    private void resetLoadingView() {
        progress = MIN_PROGRESS_STEP_VALUE;
        updateAnimation(progress);
    }

}
