package com.example.kaola.lib;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ClassName Process
 * @Description TODO
 * @Author zhangchao
 * @Date 2020/5/11 10:45
 * @Version 1.0
 */
public class Process {
    /**
     * 主进程
     */
    public static final int MAIN = 1;
    /**
     * 子进程
     */
    public static final int OTHER = 2;
    /**
     * 所有进程
     */
    public static final int ALL = 3;

    @IntDef({MAIN, OTHER, ALL})
    @Retention(RetentionPolicy.RUNTIME)
    @interface ProcessValue {
    }
}
