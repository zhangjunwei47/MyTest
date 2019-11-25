package com.example.kaola.myapplication.designpattern.decorator.java.factory;

/**
 * @author zhangchao on 2019-07-18.
 */

public class FruitDumplings extends ADumplings {
    Dumplings dumplings;

    @Override
    public void makeDumplings() {
        dumplings = new Dumplings();
        dumplings.setName("我是水果饺子");
    }

    @Override
    public Dumplings getDumpling() {
        return dumplings;
    }
}
