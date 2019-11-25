package com.kaolafm.module.update;

import android.content.Context;
import android.text.TextUtils;

import com.kaolafm.module.update.download.DownloadManager;
import com.kaolafm.module.update.listener.IDownloadListener;
import com.kaolafm.module.update.listener.IRequestDownloadInfoCallback;
import com.kaolafm.module.update.listener.RequestCallback;
import com.kaolafm.module.update.modle.PluginInfo;
import com.kaolafm.module.update.network.RequestManager;
import com.kaolafm.module.update.utils.DownloadCacheInfoUtil;
import com.kaolafm.module.update.utils.ThreadUtil;
import com.kaolafm.module.update.utils.UpdateConditionUtil;
import com.kaolafm.module.update.utils.UpdateConstant;
import com.kaolafm.module.update.utils.UpdateLog;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 升级管理类
 */
public class UpdateManager {
    public static Context mContext;
    private static volatile UpdateManager mInstance;
    /**
     * 下载管理
     */
    private DownloadManager mDownloadManager;
    /**
     * 网络请求管理
     */
    private RequestManager mRequestManager;

    private ArrayList<IDownloadListener> mDownloadArrayList;

    /**
     * 缓存当前plugin信息
     */
    private PluginInfo mPluginInfo;

    private UpdateManager(Context context) {
        mContext = context;
        mDownloadManager = new DownloadManager();
        mDownloadManager.setDownloadListener(iDownloadListener);
        mRequestManager = new RequestManager();
        mDownloadArrayList = new ArrayList<>();
    }

    public static UpdateManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UpdateManager.class) {
                if (mInstance == null) {
                    mInstance = new UpdateManager(context);
                }
            }
        }
        return mInstance;
    }


    /**
     * 是否有下载好的插件
     *
     * @return
     */
    public boolean isHasDownloadedPlugin() {
        int downloadState = DownloadCacheInfoUtil.getDownloadState(mContext);
        return downloadState == UpdateConstant.DOWNLOAD_STATE_COMPLETE;
    }

    /**
     * 获取已下载的数据
     *
     * @return
     */
    public String getDownloadedPluginPath() {
        return DownloadCacheInfoUtil.getDownloadPath(mContext);
    }

    /**
     * 是否有足够的条件下载数据, 检查是否有网络, sd卡是否满足条件
     *
     * @return
     */
    public boolean isHasConditionDownload() {
        return UpdateConditionUtil.isNetworkAvailable(mContext) && UpdateConditionUtil.isSDCardAvailable();
    }

    /**
     * 是否有条件请求下载信息
     *
     * @return
     */
    public boolean isHasConditionRequestUpdateInfo() {
        return UpdateConditionUtil.isNetworkAvailable(mContext) && UpdateConditionUtil.isHasRequestInfoAvailable(mContext);
    }

    /**
     * 请求升级信息
     */
    public void requestUpdateInfo(IRequestDownloadInfoCallback iRequestDownloadInfoCallback) {
        mRequestManager.requestUpdateInfo(new RequestCallback() {
            @Override
            public void result(Object object) {
                if (object instanceof PluginInfo) {
                    analysisPluginInfo((PluginInfo) object, iRequestDownloadInfoCallback);
                } else {
                    if (iRequestDownloadInfoCallback != null) {
                        iRequestDownloadInfoCallback.noPluginNeedDownload();
                    }
                }
            }

            @Override
            public void requestFailure() {
                if (iRequestDownloadInfoCallback != null) {
                    iRequestDownloadInfoCallback.noPluginNeedDownload();
                }
            }
        });
    }


    /**
     * 解析结果
     *
     * @param pluginInfo
     * @param iRequestDownloadInfoCallback
     */
    private void analysisPluginInfo(PluginInfo pluginInfo, IRequestDownloadInfoCallback iRequestDownloadInfoCallback) {
        if (pluginInfo == null) {
            if (iRequestDownloadInfoCallback != null) {
                iRequestDownloadInfoCallback.noPluginNeedDownload();
            }
            return;
        }
        if (TextUtils.isEmpty(pluginInfo.getApkUrl())) {
            if (iRequestDownloadInfoCallback != null) {
                iRequestDownloadInfoCallback.noPluginNeedDownload();
            }
            return;
        }

        if (!isHasConditionDownload()) {
            // TODO: 2019-11-21 这里添加错误原因
            reportUpdateError("111111111111");
            if (iRequestDownloadInfoCallback != null) {
                iRequestDownloadInfoCallback.noPluginNeedDownload();
            }
            return;
        }

        if (hasCacheDownloadingFile()) {
            cacheDownload(pluginInfo, iRequestDownloadInfoCallback);
            return;
        }

        noCacheDownload(pluginInfo, iRequestDownloadInfoCallback);
    }

    /**
     * 本地是否缓存正在下载的file
     *
     * @return
     */
    private boolean hasCacheDownloadingFile() {
        int downloadState = DownloadCacheInfoUtil.getDownloadState(mContext);
        return downloadState == UpdateConstant.DOWNLOAD_STATE_DOWNLOADING;
    }

    /**
     * 下载是否需要用户同意
     *
     * @return
     */
    private boolean isDownloadNeedUserAgree(PluginInfo pluginInfo) {
        if (pluginInfo.isMandatoryUpdate()) {
            return false;
        }
        return pluginInfo.isShowToast();
    }

    /**
     * 无缓存下载升级流程
     *
     * @param pluginInfo
     */
    private void noCacheDownload(PluginInfo pluginInfo, IRequestDownloadInfoCallback iRequestDownloadInfoCallback) {
        UpdateLog.d("没有缓存信息,开始新的下载");
        uiCallback(pluginInfo, iRequestDownloadInfoCallback);
        if (isDownloadNeedUserAgree(pluginInfo)) {
            mPluginInfo = pluginInfo;
            UpdateLog.d("需要用户同意后, 下载");
            return;
        }
        startDownloadNewVersion(pluginInfo);
    }

    /**
     * 有缓存下载升级流程
     *
     * @param pluginInfo
     */
    private void cacheDownload(PluginInfo pluginInfo, IRequestDownloadInfoCallback iRequestDownloadInfoCallback) {
        PluginInfo pluginInfoOld = DownloadCacheInfoUtil.getPluginInfo(mContext);
        if (pluginInfoOld == null || TextUtils.isEmpty(pluginInfoOld.getVersionNum())) {
            UpdateLog.d("原来缓存的数据有问题, 重新下载");
            uiCallback(pluginInfo, iRequestDownloadInfoCallback);
            if (isDownloadNeedUserAgree(pluginInfo)) {
                mPluginInfo = pluginInfo;
                UpdateLog.d("需要用户同意后, 下载");
                return;
            }
            startDownloadNewVersion(pluginInfo);
            return;
        }

        String oldVersion = pluginInfoOld.getVersionNum();
        String newVersion = pluginInfo.getVersionNum();
        UpdateLog.d("旧版本: " + oldVersion + " 新版本: " + newVersion);
        if (oldVersion.equals(newVersion)) {
            UpdateLog.d("升级信息版本相同, 继续下载");
            mDownloadManager.startDownload(false);
        } else {
            UpdateLog.d("升级信息版本不相同, 清空数据, 开始新的下载");
            uiCallback(pluginInfo, iRequestDownloadInfoCallback);
            if (isDownloadNeedUserAgree(pluginInfo)) {
                UpdateLog.d("需要用户同意后, 下载");
                mPluginInfo = pluginInfo;
                return;
            }
            startDownloadNewVersion(pluginInfo);
        }
    }

    /**
     * 请求结果返回
     *
     * @param pluginInfo
     * @param callback
     */
    private void uiCallback(PluginInfo pluginInfo, IRequestDownloadInfoCallback callback) {
        if (callback == null) {
            return;
        }
        callback.pluginNeedDownload(pluginInfo.isMandatoryUpdate(), pluginInfo.isShowToast(), pluginInfo.getUpgradeNotes());
    }

    /**
     * 开始一个新的版本下载
     *
     * @param pluginInfo
     */
    private void startDownloadNewVersion(PluginInfo pluginInfo) {
        UpdateLog.d("开始新下载");
        mDownloadManager.deleteOldFile(DownloadCacheInfoUtil.getDownloadPath(mContext));
        DownloadCacheInfoUtil.clearCacheData(mContext);
        DownloadCacheInfoUtil.setPluginInfo(mContext, pluginInfo);
        mDownloadManager.startDownload(true);
    }

    /**
     * 开始下载
     */
    public void startDownloadNewVersion() {
        UpdateLog.d("用户同意开始新下载");
        startDownloadNewVersion(mPluginInfo);
    }

    /**
     * 上报升级成功
     */
    public void reportUpdateSuccess() {
        HashMap<String, String> tempHashMap = new HashMap<>();
        tempHashMap.put(UpdateConstant.KEY_UPDATE_STATUS, UpdateConstant.UPDATE_CODE_SUCCESS);
        mRequestManager.reportUpdateResultState(tempHashMap, new RequestCallback() {
            @Override
            public void result(Object object) {

            }

            @Override
            public void requestFailure() {

            }
        });
    }

    /**
     * 上报升级失败
     *
     * @param errorCode
     */
    public void reportUpdateError(String errorCode) {
        HashMap<String, String> tempHashMap = new HashMap<>();
        tempHashMap.put(UpdateConstant.KEY_UPDATE_STATUS, UpdateConstant.UPDATE_CODE_ERROR);
        tempHashMap.put(UpdateConstant.KEY_ERROR_EVENT_CODE, errorCode);
        mRequestManager.reportUpdateResultState(tempHashMap, new RequestCallback() {
            @Override
            public void result(Object object) {

            }

            @Override
            public void requestFailure() {

            }
        });
    }

    /**
     * 添加下载状态监听
     *
     * @param downloadListener
     */
    public void addDownloadStatusListener(IDownloadListener downloadListener) {
        if (!mDownloadArrayList.contains(downloadListener)) {
            mDownloadArrayList.add(downloadListener);
        }
    }

    /**
     * 删除下载状态监听
     *
     * @param downloadListener
     */
    public void removeDownloadStatusListener(IDownloadListener downloadListener) {
        if (mDownloadArrayList.contains(downloadListener)) {
            mDownloadArrayList.remove(downloadListener);
        }
    }

    private void notifyDownloadStatus(int state, Object param1) {
        ArrayList<IDownloadListener> tempDownloadList = (ArrayList<IDownloadListener>) mDownloadArrayList.clone();
        int size = tempDownloadList.size();
        for (int i = 0; i < size; i++) {
            IDownloadListener downloadListener = tempDownloadList.get(i);
            if (downloadListener == null) {
                continue;
            }
            notifyStatusChange(downloadListener, state, param1);
        }
    }

    private void notifyStatusChange(IDownloadListener iDownloadListener, int state, Object param1) {
        switch (state) {
            case UpdateConstant.DOWNLOAD_STATE_INVALID: {

            }
            break;
            case UpdateConstant.DOWNLOAD_STATE_BEGIN: {
                ThreadUtil.runUIThread(() -> iDownloadListener.start());
            }
            break;
            case UpdateConstant.DOWNLOAD_STATE_DOWNLOADING: {
                ThreadUtil.runUIThread(() -> iDownloadListener.loading((int) param1));
            }
            break;
            case UpdateConstant.DOWNLOAD_STATE_COMPLETE: {
                ThreadUtil.runUIThread(() -> iDownloadListener.complete());
            }
            break;
            case UpdateConstant.DOWNLOAD_STATE_FAIL: {
                ThreadUtil.runUIThread(() -> iDownloadListener.fail(0, (String) param1));
            }
            break;
            default:
                break;
        }
    }

    private IDownloadListener iDownloadListener = new IDownloadListener() {
        @Override
        public void start() {
            UpdateLog.d("start download...: ");
            DownloadCacheInfoUtil.setDownloadState(UpdateManager.mContext, UpdateConstant.DOWNLOAD_STATE_BEGIN);
            notifyDownloadStatus(UpdateConstant.DOWNLOAD_STATE_BEGIN, 0);
        }

        @Override
        public void loading(int progress) {
            UpdateLog.i("loading download..." + progress);
            DownloadCacheInfoUtil.setDownloadState(UpdateManager.mContext, UpdateConstant.DOWNLOAD_STATE_DOWNLOADING);
            notifyDownloadStatus(UpdateConstant.DOWNLOAD_STATE_DOWNLOADING, progress);
        }

        @Override
        public void complete() {
            UpdateLog.d("complete download...");
            DownloadCacheInfoUtil.setDownloadState(UpdateManager.mContext, UpdateConstant.DOWNLOAD_STATE_COMPLETE);
            notifyDownloadStatus(UpdateConstant.DOWNLOAD_STATE_COMPLETE, 0);
        }

        @Override
        public void fail(int code, String message) {
            UpdateLog.d("fail download...");
            DownloadCacheInfoUtil.setDownloadState(UpdateManager.mContext, UpdateConstant.DOWNLOAD_STATE_FAIL);
            notifyDownloadStatus(UpdateConstant.DOWNLOAD_STATE_FAIL, message);
        }
    };

}
