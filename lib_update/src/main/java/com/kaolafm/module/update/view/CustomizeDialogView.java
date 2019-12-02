package com.kaolafm.module.update.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaolafm.module.update.R;

public class CustomizeDialogView extends DialogFragment {

    private View mRootView;
    protected View mContentView;

    public static CustomizeDialogView getInstance() {
        CustomizeDialogView customizeDialogView = new CustomizeDialogView();
        return customizeDialogView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.customize_dialog, container, false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setContentView()
    {

    }





}
