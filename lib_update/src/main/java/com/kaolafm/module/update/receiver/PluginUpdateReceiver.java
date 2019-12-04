package com.kaolafm.module.update.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.kaolafm.module.update.UpdateManager;
import com.kaolafm.module.update.utils.UpdateConstant;

/**
 * 插件升级广播收取
 */
public class PluginUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if (action.equals(UpdateConstant.MESSAGE_USER_CHOOSE_UPDATE_RESULT)) {
            boolean isUpdate = intent.getBooleanExtra(UpdateConstant.MESSAGE_KEY_CHOOSE_RESULT, false);
            if (isUpdate) {
                UpdateManager.getInstance(context).startDownloadNewVersion();
            } else {
                UpdateManager.getInstance(context).destroyService();
            }
        }
    }
}
