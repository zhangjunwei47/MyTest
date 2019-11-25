package com.example.kaola.myapplication.util;

import android.util.Log;

import com.example.kaola.myapplication.database.DataReportData;
import com.example.kaola.myapplication.database.DataReportHelper;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DataBaseTestUtil {

    public static void testx() {
        DataReportHelper.getInstance().init();

        Observable.interval(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long aLong) {
                test();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public  static void test() {
        Observable.interval(110, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(
                new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        DataReportHelper.getInstance().insert(getData()).subscribe(a -> {
                            Log.e("logx", "xxxxxxxx 大小= " + a);
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
        Observable.interval(115, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(
                new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        DataReportHelper.getInstance().insert(getData()).subscribe(s -> {

                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
        Observable.interval(110, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(
                new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        DataReportHelper.getInstance().insert(getData()).subscribe(s -> {

                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
        Observable.interval(110, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(
                new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        DataReportHelper.getInstance().insert(getData()).subscribe(s -> {

                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
        Observable.interval(115, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(
                new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        DataReportHelper.getInstance().insert(getData()).subscribe(s -> {

                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
        Observable.interval(110, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread()).subscribe(
                new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        DataReportHelper.getInstance().insert(getData()).subscribe(a -> {

                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );

        DataReportHelper.getInstance().insert(getData()).subscribe(aLong -> {

        });

    }

    public static int index = 0;

    private static DataReportData getData() {
        DataReportData dataReportData = new DataReportData();
        dataReportData.setType(index++);
        return dataReportData;
    }
}
