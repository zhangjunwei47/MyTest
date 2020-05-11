package com.example.kaola.lib;

import android.support.annotation.IntRange;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName AppInit
 * @Description TODO
 * @Author zhangchao
 * @Date 2020-05-09 10:50
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AppInit {

    /**
     * 优先级
     * @return
     */
    @IntRange(from = 0, to = 100)
    int priority() default 50;

    /**
     * 是否需要同步
     * @return
     */
    boolean isAsync() default false;

    /**
     * 在什么线程初始化
     * @return
     */
    @Process.ProcessValue
    int process() default Process.MAIN;

    /**
     *
     * @return
     */
    String desc();
}
