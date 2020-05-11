package com.example.kaola.lib;

/**
 * @ClassName AppInitTask
 * @Description TODO
 * @Author zhangchao
 * @Date 2020-05-09 11:09
 * @Version 1.0
 */
public class AppInitTask {
    public int priority;

    public boolean isAsync;

    public int process;

    public String desc;

    public AppInitializable appInitializable;

    public AppInitTask(int priority, boolean isAsync, int process, String desc, AppInitializable appInitializable) {
        this.priority = priority;
        this.isAsync = isAsync;
        this.process = process;
        this.desc = desc;
        this.appInitializable = appInitializable;
    }

    public boolean inAllProcess() {
        return process == Process.ALL;
    }

    public boolean inMainProcess() {
        return process == Process.MAIN || inAllProcess();
    }

    public boolean inOtherProcess() {
        return process == Process.OTHER || inAllProcess();
    }

}
