package com.kaolafm.module.update.download;

import com.kaolafm.module.update.UpdateManager;
import com.kaolafm.module.update.listener.IDownloadListener;
import com.kaolafm.module.update.utils.DownloadCacheInfoUtil;
import com.kaolafm.module.update.utils.UpdateConstant;
import com.kaolafm.module.update.utils.UpdateLog;

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

    public String mPluginPath = UpdateConstant.FILE_PATH + File.separator + UpdateConstant.PATH_NAME_KRADIO + File.separator + UpdateConstant.PATH_NAME_PLUGIN;

    public DownloadManager() {
        initDownloadPath();
    }

    /**
     * 设置下载监听
     *
     * @param iDownloadListener
     */
    public void setDownloadListener(IDownloadListener iDownloadListener) {
        mIDownloadListener = iDownloadListener;
    }

    /**
     * 开始任务
     *
     * @param url
     */
    public void startDownload(boolean isDownloadNew) {
        mDownloadUrl = getDownloadUrl();
        if (isDownloadNew) {
            mStartPoint = 0;
            saveDownloadPath();
        } else {
            mStartPoint = getFileStart();
        }
        startDownload();
    }

    /**
     * 开始下载
     */
    private void startDownload() {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                notifyDownloadFail(-1, e.getMessage());
                UpdateLog.d("callback failure: " + e.getMessage());
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
        UpdateLog.d("on Response read length = " + length);
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
        boolean isDownloadSuccess = false;
        try {
            inputStream = response.body().byteStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            File file = getFile();
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(mStartPoint);
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                randomAccessFile.write(bytes, 0, len);
            }
            isDownloadSuccess = true;
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
                UpdateLog.e("download close file error = " + e.getMessage());
            }
            if (isDownloadSuccess) {
                notifyDownloadComplete();
            }
        }
    }

    /**
     * 获取文件
     *
     * @return
     */
    private File getFile() {
        File file = new File(mPluginPath, UpdateConstant.FILE_TEMP_NAME);
        return file;
    }

    /**
     * 获取文件断点位置
     *
     * @return
     */
    private long getFileStart() {
        File file = new File(mPluginPath, UpdateConstant.FILE_TEMP_NAME);
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

    private void saveDownloadPath() {
        String path = mPluginPath + File.separator + UpdateConstant.FILE_TEMP_NAME;
        DownloadCacheInfoUtil.setDownloadPath(UpdateManager.mContext, path);
        UpdateLog.d("下载插件路径为: " + path);
    }


    /**
     * 初始化下载路径
     */
    private void initDownloadPath() {
        String sdPath = UpdateConstant.FILE_PATH;
        String kradioPath = sdPath + File.separator + UpdateConstant.PATH_NAME_KRADIO;
        File file = new File(kradioPath);
        if (!file.exists()) {
            file.mkdir();
        }

        String pluginPath = kradioPath + File.separator + UpdateConstant.PATH_NAME_PLUGIN;
        File file1 = new File(pluginPath);
        if (!file1.exists()) {
            file1.mkdir();
        }
    }

    /**
     * 删除本地文件
     */
    public void deleteOldFile(String path) {
        UpdateLog.d("要删除的文件路径为: " + path);
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    /**
     * 重命名file
     */
    public String renameDownloadFile() {
        UpdateLog.d("重命名文件");
        String path = DownloadCacheInfoUtil.getDownloadPath(UpdateManager.mContext);
        String pluginName = mPluginPath + File.separator + UpdateConstant.FILE_NAME;
        File file = new File(path);
        File pluginFile = new File(pluginName);
        if (file.exists()) {
            file.renameTo(pluginFile);
        }
        return pluginName;
    }
}
