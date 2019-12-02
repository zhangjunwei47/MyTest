package com.kaolafm.module.update.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaolafm.module.update.R;
import com.kaolafm.module.update.modle.PluginInfo;
import com.kaolafm.module.update.utils.UpdateConstant;

public class RecommendUpdateView extends CustomizeDialogView {
    protected String mTitleStr = UpdateConstant.BLANK_STR;
    protected PluginInfo pluginInfo;

    private TextView titleTextView;
    private TextView versionTextView;
    private TextView sizeTextView;
    private TextView infoTextView;

    /**
     * @param title
     * @return
     */
    public static RecommendUpdateView getInstance(PluginInfo pluginInfo) {
        RecommendUpdateView recommendUpdateView = new RecommendUpdateView();
        recommendUpdateView.pluginInfo = pluginInfo;
        return recommendUpdateView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initDataView();
    }

    private void initView(View view) {
        titleTextView = view.findViewById(R.id.titleTextView);
        versionTextView = view.findViewById(R.id.contentVersion);
        sizeTextView = view.findViewById(R.id.contentSize);
        infoTextView = view.findViewById(R.id.contentUpdateInfo);
    }

    private void initDataView() {
        titleTextView.setText(getString(R.string.update_dialog_title_str));
        versionTextView.setText(getVersionString());
        sizeTextView.setText(getSizeString());
        infoTextView.setText(getString(R.string.update_text_content_str));
    }

    private String getVersionString() {
        return "";
    }

    private String getSizeString() {
        return "";

    }

    private String getUpdateString() {
        return "";
    }

}
