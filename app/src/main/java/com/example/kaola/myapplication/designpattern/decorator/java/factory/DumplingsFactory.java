package com.example.kaola.myapplication.designpattern.decorator.java.factory;

/**
 * @author zhangchao on 2019-07-18.
 */

public class DumplingsFactory {
    ADumplings aDumplings;

    public Dumplings getFruitDumplings() {
        aDumplings = new FruitDumplings();
        aDumplings.makeDumplings();
        return aDumplings.getDumpling();
    }

    public Dumplings getMeatDumplings() {
        aDumplings = new MeatDumplings();
        aDumplings.makeDumplings();
        return aDumplings.getDumpling();
    }
}
