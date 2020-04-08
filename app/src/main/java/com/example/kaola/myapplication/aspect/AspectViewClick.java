package com.example.kaola.myapplication.aspect;

import com.example.kaola.myapplication.util.DoubleClickUtil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author zhangchao on 2019-09-05.
 */

@Aspect //表示这是一个切面类
public class AspectViewClick {
    private static final String TAG = "AspectViewClick";


    boolean isCanDoubleClick = false;

    @Before("execution (@com.example.kaola.myapplication.aspect.IDoubleClick * *(..))")
    public void beforeOnClickListener(JoinPoint joinPoint) throws Throwable {
        isCanDoubleClick = true;
    }


    /**
     * 环绕通知,会拦截原方法内容的执行，也就是说会拦截Activity中的onclick(),只执行AspectTest的onClickLitener()
     *
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("execution (* android.view.View.OnClickListener.onClick(..))")
    public void onClickListener(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (isCanDoubleClick || !DoubleClickUtil.isDoubleClick()) {
            /**
             * 表示这个方法执行完毕了，可以放开拦截了，接下来就会执行Activity中的onclick()。
             */
            isCanDoubleClick = false;
            proceedingJoinPoint.proceed();
        }
    }


}
