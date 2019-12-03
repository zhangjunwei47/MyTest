package com.kaolafm.module.update.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kaolafm.module.update.R;
import com.kaolafm.module.update.modle.PluginInfo;

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

    //@Override
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
        // TODO: 2019-12-02  确定大小
        String sizeStr = getString(R.string.update_text_size_str);
        sizeStr += " " + "303003" + "M";
        return sizeStr;
    }

    private String getUpdateInfoString() {
        return "这是一个流口水的房间辣可视对讲放辣椒水电费拉卡机是东风路;氨基酸对伐啦手机端风口浪尖萨迪克了附近阿里斯顿就发了啥;快递费就";
    }

}
