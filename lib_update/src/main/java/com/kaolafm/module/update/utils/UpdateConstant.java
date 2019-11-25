package com.kaolafm.module.update.utils;

import android.os.Environment;

/**
 * 升级需要的常量
 */
public class UpdateConstant {
    public static final String TAG = "UpdateTag";

    /**
     * 下载文件的根目录
     */
    public static final String FILE_PATH = Environment.getExternalStorageDirectory().getPath();

    public static final String FILE_NAME_KRADIO = "kradio";

    public static final String FILE_NAME_PLUGIN = "plugin";

    public static final String FILE_NAME = "kradio_plugin.apk";

    public static final String BLANK_STR = "";

    public static final String BASE_URL = "http://open.kaolafm.com";

    /**
     * 请求升级信息get
     */
    public static final String REQUEST_UPDATE_INFO_URL = BASE_URL + "/v2/kradio/upgrade?";

    /**
     * 上报结果 post
     */
    public static final String REPORT_UPDATE_RESULT_URL = BASE_URL + "/v2/kradio/reportUpgrade?";

    /**
     * 网络请求公共参数key
     */
    public static final String KEY_SIGN = "sign";
    public static final String KEY_OPEN_ID = "openid";
    public static final String KEY_APP_ID = "appid";
    public static final String KEY_DEVICE_ID = "deviceid";
    public static final String KEY_OS = "os";
    public static final String KEY_PACKAGE = "packagename";
    public static final String OS_NAME = "android";
    public static final String KEY_VERSION = "version";
    /**
     * 升级状态
     */
    public static final String KEY_UPDATE_STATUS = "codeStatus";

    /**
     * 升级失败code
     */
    public static final String KEY_ERROR_EVENT_CODE = "errorEventCode";

    public static final String UPDATE_CODE_SUCCESS = "1";
    public static final String UPDATE_CODE_ERROR = "0";

    /**
     * 下载状态
     */
    public static final int DOWNLOAD_STATE_INVALID = 0;
    public static final int DOWNLOAD_STATE_BEGIN = 1;
    public static final int DOWNLOAD_STATE_DOWNLOADING = 2;
    public static final int DOWNLOAD_STATE_COMPLETE = 3;
    public static final int DOWNLOAD_STATE_FAIL = 4;

    /**
     * 可下载插件最小支持空间 50M
     */
    public static final long MINIMUM_SPACE = 50 * 1024 * 1024;

    /**
     * 错误类型
     */
    public static final String UPDATE_ERROR_ = "";

}
