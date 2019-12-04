package com.kaolafm.module.update;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.kaolafm.module.update.listener.IRequestDownloadInfoCallback;
import com.kaolafm.module.update.modle.PluginInfo;
import com.kaolafm.module.update.utils.UpdateConstant;
import com.kaolafm.module.update.utils.UpdateLog;
import com.kaolafm.module.update.view.MandatoryUpdateView;
import com.kaolafm.module.update.view.RecommendUpdateView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class UpdateActivity extends FragmentActivity {
    public static Context mContext;
    private MandatoryUpdateView progressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.update_activity);
        //   start(getApplicationContext());
//        findViewById(R.id.updateBtn).setOnClickListener(v -> {
//            showDialog();
//        });
//        progressView = findViewById(R.id.progressView);
//        testBegin();
        startPluginCheck(mContext);
    }

    public void startPluginCheck(Context context) {
        boolean isHasDown = UpdateManager.getInstance(context).isHasDownloadedPlugin();
        if (isHasDown) {
            String path = UpdateManager.getInstance(context).getDownloadedPluginPath();
            UpdateLog.d("加载路径 = " + path);
            // TODO: 2019-12-04 加载插件
            UpdateManager.getInstance(context).loadPluginSuccess();
            //加载插件
        } else {
            if (UpdateManager.getInstance(context).isHasConditionRequestUpdateInfo()) {
                UpdateManager.getInstance(context).requestUpdateInfo(new IRequestDownloadInfoCallback() {
                    @Override
                    public void pluginNeedDownload(boolean isMandatory, boolean isNeedShowToast, String toastMessage) {
                        if (isMandatory) {
                            UpdateLog.d("强制下载插件...");
                            // TODO: 2019-12-04 显示进度条
                            return;
                        }
                        //进入首页
                        if (isNeedShowToast) {
                            sendBroadcastToPlugin();
                            UpdateLog.d("需要弹出提示框下载...");
                        } else {
                            UpdateLog.d("不需要弹出提示框下载...");
                        }
                    }

                    @Override
                    public void noPluginNeedDownload() {
                        startPlugin();
                        UpdateLog.d("没有需要下载的插件...");
                    }
                });
            } else {
                startPlugin();
                UpdateLog.d("没有条件下载插件...");
            }
        }
    }

    /**
     * 开始进入首页
     */
    private void startPlugin() {

    }

    /**
     * 发送升级信息到客户端
     */
    private void sendBroadcastToPlugin() {
        PluginInfo pluginInfo = UpdateManager.getInstance(mContext).getCurrentPluginInfo();
        if (pluginInfo == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(UpdateConstant.MESSAGE_KEY_PLUGIN_INFO_VERSION, pluginInfo.getVersionNum());
        intent.putExtra(UpdateConstant.MESSAGE_KEY_PLUGIN_INFO_SIZE, pluginInfo.getFileSize());
        intent.putExtra(UpdateConstant.MESSAGE_KEY_PLUGIN_INFO_UPDATE_INFO, pluginInfo.getUpgradeNotes());
        sendBroadcast(intent);
    }

    float progress = 0.0f;

    private void testBegin() {

        Observable.interval(100, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        progress = progress + 0.01f;

                        progressView.update(progress);

                    }
                });
    }

    private void showDialog() {
        // RecommendUpdateView recommendUpdateView = RecommendUpdateView.getInstance(UpdateManager.getInstance(UpdateActivity.this).getCurrentPluginInfo());
        RecommendUpdateView.getInstance(test()).show(getFragmentManager(), "haha");
    }

    private PluginInfo test() {
        PluginInfo pluginInfo = new PluginInfo();
        pluginInfo.setVersionNum("10101010");
        pluginInfo.setUpgradeNotes("新科技馆斯柯达解放路卡视角的弗兰克撒旦教上来的会计法是路口附近按时螺丝刀解放路快速的减肥螺丝刀");
        return pluginInfo;
    }
}
