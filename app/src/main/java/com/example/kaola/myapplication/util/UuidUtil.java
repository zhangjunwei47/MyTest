package com.example.kaola.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.example.kaola.myapplication.MyApplication;
import com.google.android.exoplayer2.util.Log;

import java.io.*;
import java.nio.file.Files;

/**
 * @author zhangchao on 2019-08-20.
 */

public class UuidUtil {
    public static final String KAOLA_AUTO_BASE = "kaolafm_auto";

    public static final String UDID_PATH_KAOLA_API = "kaolaapi";

    public static final String UDID_PATH_UDID_FILE = "udid.dat";

    public static final String UDID_PREFERENCE_NAME = "udid_information_pf";

    public static final String UDID_VALUE = "udid_value";

    public static SharedPreferences share(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(UDID_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static void init() {
        String udid = share(MyApplication.context).getString(UDID_VALUE, "");
    }

    /**
     * uuid file
     */
    private static final String UUID_FILE = "/kaolafm_auto/kaolaapi/udid.dat";

    public static void initPath() {
        File file = Environment.getExternalStorageDirectory();
        if (file != null) {
            String path = file.getAbsolutePath();
            String dataPath = path + UUID_FILE;
            String uuid = readDataFromFile(dataPath);
        }
    }

    public static void deleteFile(String path) {
        try {
          //  Files.delete(path);
        } catch (Exception e) {
            Log.e("logx", "xxxxxxx 删除文件错误: " + e.getMessage());
        }
    }


    public static String readDataFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            Log.e("logx", "xxxxxxx 文件不存在");
            return null;
        }
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeIoSilence(bufferedReader);
            closeIoSilence(inputStreamReader);
            closeIoSilence(fileInputStream);
        }
        return null;
    }

    private static void closeIoSilence(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
