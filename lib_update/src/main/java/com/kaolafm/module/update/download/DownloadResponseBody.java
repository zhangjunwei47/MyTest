package com.kaolafm.module.update.download;

import com.kaolafm.module.update.listener.IDownloadListener;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

/**
 * 重写ResponseBody, 主要功能: 监听下载进度
 */
public class DownloadResponseBody extends ResponseBody {
    private Response originalResponse;
    private IDownloadListener mDownloadListener;
    private long oldPoint = 0;

    public DownloadResponseBody(Response originalResponse, long startsPoint, IDownloadListener iDownloadListener) {
        this.originalResponse = originalResponse;
        this.mDownloadListener = iDownloadListener;
        this.oldPoint = startsPoint;
    }

    @Override
    public MediaType contentType() {
        ResponseBody responseBody = originalResponse.body();
        if (responseBody == null) {
            return null;
        }

        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        ResponseBody responseBody = originalResponse.body();
        if (responseBody == null) {
            return -1;
        }
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        return Okio.buffer(new ForwardingSource(originalResponse.body().source()) {
            private long alreadyBytesRead = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                alreadyBytesRead += bytesRead == -1 ? 0 : bytesRead;
                long total = (contentLength() + oldPoint) / 1024;
                long progress = (alreadyBytesRead + oldPoint) / 1024;
                if (mDownloadListener != null) {
                    mDownloadListener.loading((int) progress, (int) total);
                }
                return bytesRead;
            }
        });
    }
}
