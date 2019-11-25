package com.example.kaola.myapplication.network.model;

/**
 * @author zhangchao on 2019/3/5.
 */

public class TokenRequestData {
    private int modelName;
    private  String vin;
    private String packageName;

    public int getModelName() {
        return modelName;
    }

    public void setModelName(int modelName) {
        this.modelName = modelName;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
