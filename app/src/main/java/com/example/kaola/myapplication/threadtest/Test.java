package com.example.kaola.myapplication.threadtest;

/**
 * @ClassName Test
 * @Description TODO
 * @Author zhangchao
 * @Date 2020/5/25 16:15
 * @Version 1.0
 */
public class Test {
    static Object object = new Object();

    public static void main(String[] args) {
        test();
    }

    static void test() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("线程1开始执行");
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程1执行完毕");
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("行程2开始执行");
                    object.notify();
                    System.out.println("行程2执行完毕");
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
