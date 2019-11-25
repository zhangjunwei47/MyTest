package com.kaolafm.module.update.utils;

import android.util.Log;

public class UpdateLog {
    public static boolean CAN_LOG = true;

    public static void i(String log) {
        if (CAN_LOG) {
            Log.i(UpdateConstant.TAG, log);
        }
    }

    public static void d(String log) {
        if (CAN_LOG) {
            Log.d(UpdateConstant.TAG, log);
        }
    }

    public static void w(String log) {
        if (CAN_LOG) {
            Log.w(UpdateConstant.TAG, log);
        }
    }

    public static void e(String log) {
        if (CAN_LOG) {
            Log.e(UpdateConstant.TAG, log);
        }
    }
}
