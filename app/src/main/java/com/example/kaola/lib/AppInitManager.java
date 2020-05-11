package com.example.kaola.lib;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AppInitManager
 * @Description TODO
 * @Author zhangchao
 * @Date 2020-05-09 11:09
 * @Version 1.0
 */

public class AppInitManager {
    private List<AppInitTask> appInitTaskList = new ArrayList();
    private static BlockingQueue<AppInitTask> mAsyncTaskQueue;
    private static boolean isMainProcess;

    public void registerInitializable(AppInitTaskConstants container) {
        appInitTaskList.addAll(container.appInitTaskList);
        if (container.asynAppInitTaskList != null) {
            if (mAsyncTaskQueue == null) {
                mAsyncTaskQueue = new ArrayBlockingQueue<>(container.asynAppInitTaskList.size());
            } else {
                ArrayBlockingQueue<AppInitTask> appInitTasks = new ArrayBlockingQueue<>(appInitTaskList.size() + container.asynAppInitTaskList.size());
                appInitTasks.addAll(container.asynAppInitTaskList);
                mAsyncTaskQueue = appInitTasks;
            }
        }
    }

    public void onCreate(Application application) {
        if (appInitTaskList == null) {
            return;
        }
        asyncCreate(application);
        syncCreate(application);
    }

    private void asyncCreate(final Application application) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> asyncCreateInner(application));
    }

    private void syncCreate(Application application) {

    }

    private void asyncCreateInner(Application application) {
        AppInitTask appInitTask = null;
        for (; ; ) {
            try {
                appInitTask = mAsyncTaskQueue.poll(100, TimeUnit.MICROSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (appInitTask == null) {
                continue;
            }
            if (isNotIgnore(appInitTask)) {
                AppInitTask finalAppInitTask = appInitTask;
                time(appInitTask.desc, () -> finalAppInitTask.appInitializable.asyncCreate(application));
            }
            if (mAsyncTaskQueue.isEmpty()) {
                return;
            }
        }
    }

    private long time(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }

    private void time(String desc, Runnable runnable) {
        long longTime = time(runnable);
        Log.d(Constants.TAG, desc + "执行时间: " + longTime);
    }

    private static boolean isNotIgnore(AppInitTask appInitTask) {
        return (isMainProcess && appInitTask.inMainProcess()) || (!isMainProcess && appInitTask.inOtherProcess());
    }
}
