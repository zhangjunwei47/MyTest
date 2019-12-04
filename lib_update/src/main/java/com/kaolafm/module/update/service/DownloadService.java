package com.kaolafm.module.update.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.kaolafm.module.update.download.DownloadManager;
import com.kaolafm.module.update.listener.IDownloadListener;

/**
 * 下载service
 */
public class DownloadService extends Service {

    private DownloadManager mDownloadManager;

    private IDownloadListener mDownloadListener;

    @Override
    public void onCreate() {
        super.onCreate();
        initDownload();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new DownloadServiceBinder();
    }

    private void initDownload() {
        mDownloadManager = new DownloadManager();
        mDownloadManager.setDownloadListener(iDownloadListener);
    }

    private void startDownloadInner(boolean isNewDownload) {
        mDownloadManager.startDownload(isNewDownload);
    }

    public class DownloadServiceBinder extends Binder {
        public void startDownload(boolean isNewDownload) {
            startDownloadInner(isNewDownload);
        }

        public void setDownloadListener(IDownloadListener iDownloadListener) {
            mDownloadListener = iDownloadListener;
        }

        public void deleteOldFile(String filePath) {
            mDownloadManager.deleteOldFile(filePath);
        }

        public String renameDownloadFile() {
            return mDownloadManager.renameDownloadFile();
        }
    }

    private IDownloadListener iDownloadListener = new IDownloadListener() {

        @Override
        public void start() {
            if (mDownloadListener == null) {
                return;
            }
            mDownloadListener.start();
        }

        @Override
        public void loading(int progress, int totalSize) {
            if (mDownloadListener == null) {
                return;
            }
            mDownloadListener.loading(progress, totalSize);
        }

        @Override
        public void complete() {
            if (mDownloadListener == null) {
                return;
            }
            mDownloadListener.complete();
        }

        @Override
        public void fail(int code, String message) {
            if (mDownloadListener == null) {
                return;
            }
            mDownloadListener.fail(code, message);
        }
    };
}
