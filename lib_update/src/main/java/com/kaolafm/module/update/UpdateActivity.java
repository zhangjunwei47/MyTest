package com.kaolafm.module.update;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kaolafm.module.update.listener.IRequestDownloadInfoCallback;
import com.kaolafm.module.update.utils.UpdateLog;
import com.kaolafm.module.update.view.RecommendUpdateView;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);
        //   start(getApplicationContext());
        findViewById(R.id.updateBtn).setOnClickListener();
    }

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
                            UpdateLog.d("强制下载插件...");

                            return;
                        }
                        //进入首页
                        if (isNeedShowToast) {
                            showDialog();
                            UpdateLog.d("需要弹出提示框下载...");
                        } else {
                            UpdateLog.d("不需要弹出提示框下载...");
                        }
                    }

                    @Override
                    public void noPluginNeedDownload() {
                        //进入首页
                        UpdateLog.d("没有需要下载的插件...");
                    }
                });
            } else {
                //进入首页
                UpdateLog.d("没有条件下载插件...");
            }
        }

    }

    private void showDialog() {
        RecommendUpdateView recommendUpdateView = RecommendUpdateView.getInstance(UpdateManager.getInstance(UpdateActivity.this).getCurrentPluginInfo());
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, recommendUpdateView)
                .commit();
    }
}
