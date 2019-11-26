package com.kaolafm.module.update;

import android.content.Context;
import android.widget.Toast;

import com.kaolafm.module.update.listener.IRequestDownloadInfoCallback;
import com.kaolafm.module.update.utils.UpdateLog;

public class TestDemo {

    public void start(Context context) {
        boolean isHasDown = UpdateManager.getInstance(context).isHasDownloadedPlugin();
        if (isHasDown) {
            String path = UpdateManager.getInstance(context).getDownloadedPluginPath();
            UpdateLog.d("加载路径 = " + path);
            UpdateManager.getInstance(context).loadPluginSuccess();
            //加载插件
        } else {
            if (UpdateManager.getInstance(context).isHasConditionRequestUpdateInfo()) {
                UpdateManager.getInstance(context).requestUpdateInfo(new IRequestDownloadInfoCallback() {
                    @Override
                    public void pluginNeedDownload(boolean isMandatory, boolean isNeedShowToast, String toastMessage) {
                        if (isMandatory) {
                            //强制升级, 显示进度条
                            return;
                        }
                        //进入首页
                        if (isNeedShowToast) {
                            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void noPluginNeedDownload() {
                        //进入首页
                    }
                });
            } else {
                //进入首页
            }
        }

    }
}
