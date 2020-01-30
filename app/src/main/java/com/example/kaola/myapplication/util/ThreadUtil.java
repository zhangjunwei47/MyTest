package com.example.kaola.myapplication.util;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 测试listener, 多线程调用.崩溃
 */
public class ThreadUtil {

    private CopyOnWriteArrayList<IThreadUtil> mIThreadUtils;
    IThreadUtil iThreadUtil, iThreadUtil2;

    private ThreadUtil() {
        mIThreadUtils = new CopyOnWriteArrayList<>();
    }

    private static class THREAD_UTIL {
        private static ThreadUtil threadUtil = new ThreadUtil();
    }

    public static ThreadUtil getInstance() {
        return THREAD_UTIL.threadUtil;
    }


    public void addListener(IThreadUtil iThreadUtil) {
        mIThreadUtils.add(iThreadUtil);
    }

    public void removeListener(IThreadUtil iThreadUtil) {
        mIThreadUtils.remove(iThreadUtil);
    }


    public void notifyListener() {
      //  ArrayList<IThreadUtil> tempArrayList = (ArrayList<IThreadUtil>) mIThreadUtils.clone();
        for (int i = 0; i < mIThreadUtils.size(); i++) {
            IThreadUtil iThreadUtil = mIThreadUtils.get(i);
            iThreadUtil.change();
        }
    }


    public void addListenerTest() {
        iThreadUtil = new IThreadUtil() {
            @Override
            public void change() {
            }
        };
        iThreadUtil2 = new IThreadUtil() {
            @Override
            public void change() {
            }
        };

        addListener(iThreadUtil);
        addListener(iThreadUtil2);
        test();
    }


    private void test() {
        Single.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                removeListener(iThreadUtil);
                return 1L;
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {

                    }
                });

        Single.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                notifyListener();
                return 1L;
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {

                    }
                });
    }


    public interface IThreadUtil {
        void change();
    }


}
