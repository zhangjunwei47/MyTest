package com.example.kaola.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AppInitTaskConstants
 * @Description TODO
 * @Author zhangchao
 * @Date 2020-05-09 11:45
 * @Version 1.0
 */
public class AppInitTaskConstants {
    public List<AppInitTask> appInitTaskList;
    public List<AppInitTask> asynAppInitTaskList;

    public AppInitTaskConstants() {
        appInitTaskList = new ArrayList<>();
        asynAppInitTaskList = new ArrayList<>();
    }

    public void addTask(AppInitTask appInitTask) {
        appInitTaskList.add(appInitTask);
        if (appInitTask.isAsync) {
            asynAppInitTaskList.add(appInitTask);
        }
    }
}
