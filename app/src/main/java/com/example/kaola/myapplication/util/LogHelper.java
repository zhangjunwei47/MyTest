package com.example.kaola.myapplication.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

/**
 * @author zhangchao on 2018/3/27.
 * 把log输出到本地
 */


public class LogHelper {
    private static LogHelper instance = null;
    private String dirPath;//保存路径
    private int appid;//应用pid
    private Thread logThread;

    /**
     * @param mContext
     * @param path     log日志保存根目录
     * @return
     */
    public static LogHelper getInstance(Context mContext, String path) {
        if (instance == null) {
            instance = new LogHelper(mContext, path);
        }
        return instance;
    }

    private LogHelper(Context mContext, String path) {
        appid = android.os.Process.myPid();
        if (TextUtils.isEmpty(path)) {
            dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + "kradiolog1";
        } else {
            dirPath = path;
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 启动log日志保存
     */
    public void start() {
        if (logThread == null) {
            logThread = new Thread(new LogRunnable(appid, dirPath));
            logThread.start();
        }
    }

    private static class LogRunnable implements Runnable {

        private Process mProcess;
        private FileOutputStream fos;
        private BufferedReader mReader;
        private String cmds;
        private String mPid;

        public LogRunnable(int pid, String dirPath) {
            this.mPid = "" + pid;
            try {
                File file = new File(dirPath, FormatDate.getFormatDate() + ".log");
                if (!file.exists()) {
                    file.createNewFile();
                }
                fos = new FileOutputStream(file, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //cmds = "logcat *:v | grep \"(" + mPid + ")\"";
            cmds = "logcat *:v";
        }

        @Override
        public void run() {
            try {
                mProcess = Runtime.getRuntime().exec(cmds);
                mReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()), 1024);
                String line;
                while ((line = mReader.readLine()) != null) {
                    if (line.length() == 0) {
                        continue;
                    }
                    if (fos != null && line.contains(mPid)) {
                        fos.write((FormatDate.getFormatTime() + " " + line + "\r\n").getBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mProcess != null) {
                    mProcess.destroy();
                    mProcess = null;
                }
                try {
                    if (mReader != null) {
                        mReader.close();
                        mReader = null;
                    }
                    if (fos != null) {
                        fos.close();
                        fos = null;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private static class FormatDate {

        public static String getFormatDate() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
            return sdf.format(System.currentTimeMillis());
        }

        public static String getFormatTime() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(System.currentTimeMillis());
        }

        public static String getFormatTimeFileName() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            return sdf.format(System.currentTimeMillis());
        }
    }
}
