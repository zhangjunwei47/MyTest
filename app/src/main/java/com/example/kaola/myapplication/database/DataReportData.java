package com.example.kaola.myapplication.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author zhangchao on 2019/1/7.
 */

@Entity
public class DataReportData {
    int type;
    String value;
    @Generated(hash = 1277208195)
    public DataReportData(int type, String value) {
        this.type = type;
        this.value = value;
    }
    @Generated(hash = 403935201)
    public DataReportData() {
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
