package com.kaolafm.module.update.utils;


import java.security.MessageDigest;


/******************************************
 * 类描述： MD5工具类 类名称：MD5
 *
 * @version: 1.0
 * @author: shaoningYang
 * @time: 2016-7-19 15:04
 ******************************************/

public class MD5Util {
    private static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private MD5Util() {
    }

    public static String hexdigest(String string) {
        String s = null;

        try {
            s = hexdigest(string.getBytes());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return s;
    }

    public static String hexdigest(byte[] bytes) {
        String s = null;

        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(bytes);
            byte[] tmp = e.digest();
            char[] str = new char[32];
            int k = 0;

            for (int i = 0; i < 16; ++i) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            s = new String(str);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return s;
    }

    public static void main(String[] args) {
        System.out.println(hexdigest("c"));
    }

    /**
     * MD5加密
     *
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        if (str == null) {
            return UpdateConstant.BLANK_STR;
        }
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] byteArray = messageDigest.digest();
            StringBuffer md5StrBuff = new StringBuffer();
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(
                            Integer.toHexString(0xFF & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
            }
            return md5StrBuff.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
