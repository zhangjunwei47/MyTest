package com.kaolafm.module.update.view;

import android.app.DialogFragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.kaolafm.module.update.R;
import com.kaolafm.module.update.modle.PluginInfo;
import com.kaolafm.module.update.utils.ScreenUtil;

public class RecommendUpdateView extends DialogFragment {
    protected PluginInfo pluginInfo;

    private TextView titleTextView;
    private TextView versionTextView;
    private TextView sizeTextView;
    private TextView infoTextView;
    private TextView infoContentTextView;

    private Button updateBtn, cancelBtn;

    private View mRootView;

    /**
     * @param title
     * @return
     */
    public static RecommendUpdateView getInstance(PluginInfo pluginInfo) {
        RecommendUpdateView recommendUpdateView = new RecommendUpdateView();
        recommendUpdateView.pluginInfo = pluginInfo;
        return recommendUpdateView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE, R.style.BaseDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return getContentView(inflater, container);
    }

    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.customize_dialog, container);
        }
        initView(mRootView);
        initDataView();
        return mRootView;
    }


    private void initView(View view) {
        titleTextView = view.findViewById(R.id.titleTextView);
        versionTextView = view.findViewById(R.id.contentVersion);
        sizeTextView = view.findViewById(R.id.contentSize);
        infoTextView = view.findViewById(R.id.contentInfo);
        infoContentTextView = view.findViewById(R.id.contentUpdateInfo);
        updateBtn = view.findViewById(R.id.updateBtn);
        cancelBtn = view.findViewById(R.id.cancelBtn);
    }


    private void initDataView() {
        titleTextView.setText(getString(R.string.update_dialog_title_str));
        versionTextView.setText(getVersionString());
        sizeTextView.setText(getSizeString());
        infoTextView.setText(getString(R.string.update_text_content_str));
        infoContentTextView.setText(getUpdateInfoString());
        updateBtn.setOnClickListener(v -> {
            this.dismiss();
        });
        cancelBtn.setOnClickListener(v -> {
            this.dismiss();
        });
    }

    private String getVersionString() {
        String versionStr = getString(R.string.update_text_version_str);
        versionStr += " " + pluginInfo.getVersionNum();
        return versionStr;
    }

    private String getSizeString() {
        String sizeStr = getString(R.string.update_text_size_str);
        float size = pluginInfo.getFileSize() / (1024 * 1024);
        sizeStr += " " + size + "M";
        return sizeStr;
    }

    private String getUpdateInfoString() {
        return pluginInfo.getUpgradeNotes();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int mCurrentOrientation = newConfig.orientation;
        if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            portraitScreen();
        } else {
            horizontalScreen();
        }
    }

    private void setupWindowSize(int height, int width) {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.height = height;
            lp.width = width;
            window.setAttributes(lp);
        }
    }

    /**
     * 横屏
     */
    private void horizontalScreen() {
        int height = (int)(ScreenUtil.getScreenHeight(getContext())* 0.8);
        int width =  (int)(ScreenUtil.getScreenWidth(getContext())*0.75);
        setupWindowSize(height, width);
    }

    /**
     * 竖屏
     */
    private void portraitScreen() {
        int height = (int)(ScreenUtil.getScreenHeight(getContext())* 0.6);
        int width =  (int)(ScreenUtil.getScreenWidth(getContext())*0.8);
        setupWindowSize(height, width);
    }

}
