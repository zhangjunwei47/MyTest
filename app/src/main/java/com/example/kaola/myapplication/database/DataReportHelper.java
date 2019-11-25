package com.example.kaola.myapplication.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kaola.myapplication.MyApplication;
import com.example.kaola.myapplication.database.greendao.DaoMaster;
import com.example.kaola.myapplication.database.greendao.DaoSession;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangchao on 2019/1/7.
 */

public class DataReportHelper {
    public static final String DB_NAME = "datareport.db";

    private DaoSession mDaoSession;

    private DataReportHelper() {
        init();
    }

    private static final class INSTANCE {
        public static DataReportHelper NEW_INSTANCE = new DataReportHelper();
    }

    public static DataReportHelper getInstance() {
        return DataReportHelper.INSTANCE.NEW_INSTANCE;
    }

    public void init() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.context, DB_NAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public Single<Long> insert(DataReportData dataReportData) {
        Log.e("logx","xxxxxxxxxxxx insert");
        return Single.fromCallable(() -> mDaoSession.insert(dataReportData)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Single<Boolean> delete(DataReportData dataReportData) {
        return Single.fromCallable(() ->
                {
                    mDaoSession.delete(dataReportData);
                    return true;
                }
        );
    }

    public Single<Boolean> update(DataReportData dataReportData) {
        return Single.fromCallable(() ->
                {
                    mDaoSession.update(dataReportData);
                    return true;
                }
        );
    }


    public Single<List<DataReportData>> getAllData() {
        return Single.fromCallable(() ->
                mDaoSession.loadAll(DataReportData.class)
        );
    }

}
