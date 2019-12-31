package com.example.kaola.myapplication.designpattern.singleton.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        readTest();
    }


    private static void readTest() throws IOException, ClassNotFoundException {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println("直接获取的单例为: " + lazySingleton);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("hahaha"));
        objectOutputStream.writeObject(lazySingleton);

        File file = new File("hahaha");
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        /**
         *  readObject源码里面有个方法是 invokeReadResolve(源码无法看见), 需要单例里面重写这 readResolve这个方法
         */
        LazySingleton lazySingleton1 = (LazySingleton) inputStream.readObject();
        System.out.println("读取到的单例为: " + lazySingleton1);
    }
}
