package com.kaolafm.module.update.download;

import android.util.Log;

import com.kaolafm.module.update.UpdateManager;
import com.kaolafm.module.update.listener.IDownloadListener;
import com.kaolafm.module.update.utils.DownloadCacheInfoUtil;
import com.kaolafm.module.update.utils.UpdateConstant;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下载管理
 */
public class DownloadManager {
    private long mStartPoint = 0;
    /**
     * 下载url
     */
    private String mDownloadUrl = UpdateConstant.BLANK_STR;

    /**
     * 下载监听
     */
    private IDownloadListener mIDownloadListener;

    public DownloadManager() {
        mStartPoint = getFileStart();
        mDownloadUrl = getDownloadUrl();
    }

    /**
     * 设置现在监听
     *
     * @param iDownloadListener
     */
    public void setDownloadListener(IDownloadListener iDownloadListener) {
        mIDownloadListener = iDownloadListener;
    }

    /**
     * 开始一个新的任务
     *
     * @param url
     */
    public void startNewDownload(String url) {
        mDownloadUrl = url;
        mStartPoint = 0;
        startDownload();
    }

    /**
     * 开始一个下载任务
     */
    public void startDownload() {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                notifyDownloadFail(-1, e.getMessage());
                Log.d(UpdateConstant.TAG, "callback failure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                downloadToFile(response);
            }
        };

        requestDownload(callback);
    }

    /**
     * 下载流信息到文件
     *
     * @param response
     */
    private void downloadToFile(Response response) {
        long length = response.body().contentLength();
        Log.d(UpdateConstant.TAG, "on Response read length = " + length);
        if (length == 0) {
            notifyDownloadComplete();
            return;
        }
        notifyStart();
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        BufferedInputStream bufferedInputStream = null;

        byte[] bytes = new byte[2048];
        int len = 0;
        try {
            inputStream = response.body().byteStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            File file = getFile();
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(mStartPoint);
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                randomAccessFile.write(bytes, 0, len);
            }
            notifyDownloadComplete();
        } catch (Exception e) {
            notifyDownloadFail(-1, e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Exception e) {
                Log.e(UpdateConstant.TAG, "download close file error = " + e.getMessage());
            }
        }
    }

    /**
     * 获取文件
     *
     * @return
     */
    private File getFile() {
        File file = new File(UpdateConstant.FILE_PATH, "updatePlugin" + UpdateConstant.FILE_APK_NAME);
        return file;
    }

    /**
     * 获取文件断点位置
     *
     * @return
     */
    private long getFileStart() {
        File file = new File(UpdateConstant.FILE_PATH, "updatePlugin" + UpdateConstant.FILE_APK_NAME);
        return file.length();
    }

    /**
     * 获取下载url
     *
     * @return
     */
    private String getDownloadUrl() {
        return DownloadCacheInfoUtil.getDownloadUrl(UpdateManager.mContext);
    }

    /**
     * 请求执行下载任务
     *
     * @param callback
     */
    private void requestDownload(Callback callback) {
        Request request = new Request.Builder()
                .url(mDownloadUrl)
                .header("RANGE", "bytes=" + mStartPoint + "-")
                .build();

        Interceptor interceptor = chain -> {
            Response response = chain.proceed(chain.request());
            return response.newBuilder()
                    .body(new DownloadResponseBody(response, mStartPoint, mIDownloadListener)).build();
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder().addNetworkInterceptor(interceptor);

        Call call = builder.build().newCall(request);
        call.enqueue(callback);
    }

    private void notifyStart() {
        if (mIDownloadListener != null) {
            mIDownloadListener.start();
        }
    }

    private void notifyDownloading(int progress) {
        if (mIDownloadListener != null) {
            mIDownloadListener.loading(progress);
        }
    }

    private void notifyDownloadComplete() {
        if (mIDownloadListener != null) {
            mIDownloadListener.complete();
        }
    }

    private void notifyDownloadFail(int code, String errorMsg) {
        if (mIDownloadListener != null) {
            mIDownloadListener.fail(code, errorMsg);
        }
    }

    /**
     * 删除本地文件
     */
    public void deleteOldFile(String path) {
        // TODO: 2019-11-21 删除就文件
    }
}
