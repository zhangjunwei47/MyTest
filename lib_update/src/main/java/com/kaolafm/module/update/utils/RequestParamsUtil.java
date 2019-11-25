package com.kaolafm.module.update.utils;

import android.content.Context;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 服务器请求公共参数拼接工具
 */
public final class RequestParamsUtil {

    /**
     * 获取公共参数
     *
     * @return
     */
    public static HashMap<String, String> getCommonParams(Context context) {
        HashMap<String, String> data = new HashMap<>();

        String appId = RequestCacheInfoUtil.getAppId(context);
        // TODO: 2019-11-22 测试环境需要屏蔽代码
        //String pkgName = context.getPackageName();
        String pkgName = "com.edog.car.ceshizhuanyong_kradio";
        String openId = RequestCacheInfoUtil.getOpenid(context);
        String deviceId = RequestCacheInfoUtil.getUdid(context);

        data.put(UpdateConstant.KEY_APP_ID, appId);
        data.put(UpdateConstant.KEY_PACKAGE, pkgName);
        if (!TextUtils.isEmpty(openId)) {
            data.put(UpdateConstant.KEY_OPEN_ID, openId);
        }
        data.put(UpdateConstant.KEY_DEVICE_ID, deviceId);
        data.put(UpdateConstant.KEY_OS, UpdateConstant.OS_NAME);

        String contentSub = data.toString().replace("{", UpdateConstant.BLANK_STR).replace("}", UpdateConstant.BLANK_STR).replace(" ", UpdateConstant.BLANK_STR);
        String[] totalValues = contentSub.split(",");

        data.put(UpdateConstant.KEY_SIGN, MD5Util.getMD5Str(madeUrlSign(totalValues, RequestCacheInfoUtil.getAppKey(context))));
        data.put(UpdateConstant.KEY_VERSION, RequestCacheInfoUtil.getAppVersion(context));
        return data;
    }

    /**
     * 生成url加密sign数组
     *
     * @param params
     * @return
     */
    private static String madeUrlSign(String[] params, String appKey) {
        Arrays.sort(params);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(appKey);
        int index = 0;
        String preValue = null;
        for (String value : params) {
            if (TextUtils.isEmpty(value) || value.equals(preValue)) {
                continue;
            }
            String tempValue = value.trim();
            preValue = tempValue;
            stringBuilder.append(index++);
            stringBuilder.append(tempValue);
        }
        stringBuilder.append(appKey);
        return stringBuilder.toString();
    }
}
