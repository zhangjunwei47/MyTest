package com.example.kaola.myapplication.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {
    private static final String TAG = "MyClassLoader";
    private String classLoaderName;
    private final String fileExtension = ".class";
    private String mClassPath = "";

    public MyClassLoader(String className) {
        super(); //将系统加载器当做该类的父加载器
        classLoaderName = className;
    }

    public MyClassLoader(ClassLoader classLoader, String className) {
        super(classLoader);//讲传入的类当做该类的加载器
        classLoaderName = className;
    }

    /**
     * @param classPath
     */
    public void setClassPath(String classPath) {
        this.mClassPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("使用自定义的 classLoader findClass!");
        byte[] data = this.loadClassFileByPath(name);
        return this.defineClass(name, data, 0, data.length);
    }


    /**
     * 从文件名称加载class
     *
     * @param name
     * @return
     */
    private byte[] loadClassFileByPath(String name) {
        InputStream is = null;
        ByteArrayOutputStream stream = null;
        byte[] data = null;
        try {
            this.classLoaderName = this.classLoaderName.replace(".", "/");
            String path = mClassPath + name + this.fileExtension;
            System.out.println("load class path =" + path);
            is = new FileInputStream(new File(path));
            int ch = 0;
            while (-1 != (ch = is.read())) {
                stream.write(ch);
            }
            data = stream.toByteArray();
        } catch (Exception e) {

        } finally {
            try {
                is.close();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static void main(String[] args) {
        try {
            testLoadClass();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testLoadClass() throws Exception {
        System.out.println("testLoadClass");
        MyClassLoader myClassLoader = new MyClassLoader("loader1");
        Class<?> clazz = myClassLoader.loadClass("com.example.kaola.myapplication.jvm.MyTest");
        Object object = clazz.newInstance();
        System.out.print("testLoadClass ok = " + object);
        System.out.println("加载的classloader = " + object.getClass().getClassLoader());

        MyClassLoader myClassLoader2 = new MyClassLoader("loader2");
        Class<?> clazz2 = myClassLoader2.loadClass("com.example.kaola.myapplication.jvm.MyTest");
        Object object2 = clazz2.newInstance();
        System.out.print("testLoadClass ok = " + object2);
        System.out.println("加载的classloader = " + object2.getClass().getClassLoader());
    }

    public static void testLoad2Class() throws Exception {
        System.out.println("testLoad2Class");
        MyClassLoader myClassLoader = new MyClassLoader("loader1");
        myClassLoader.setClassPath("/Users/zhaojing/temp/jvm/");
        Class<?> clazz = myClassLoader.loadClass("com.example.kaola.myapplication.jvm.MyTest");
        Object object = clazz.newInstance();
        System.out.println("testLoad2Class ok = " + object);
        System.out.println("加载的classloader = " + object.getClass().getClassLoader());

        MyClassLoader myClassLoader2 = new MyClassLoader("loader1");
        myClassLoader.setClassPath("/Users/zhaojing/temp/jvm/");

        Class<?> clazz2 = myClassLoader2.loadClass("com.example.kaola.myapplication.jvm.MyTest");
        Object object2 = clazz2.newInstance();
        System.out.println("testLoad2Class ok = " + object2);
        System.out.println("加载的classloader = " + object2.getClass().getClassLoader());
    }


}