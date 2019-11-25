package com.example.kaola.myapplication.designpattern.decorator.kotlin.factory

/**
 * @author zhangchao on 2019-07-18.
 */

class DumplingsFactory {
    var mADumplings: ADumplings? = null

    fun getFruitDumplings(): Dumplings? {
        mADumplings = FruitDumplings()
        return mADumplings?.run {
            makeDumplings()
            getDumplings()
        }
    }

    fun getMeatDumplings(): Dumplings? {
        mADumplings = MeatDumplings()
        return mADumplings?.run {
            makeDumplings()
            getDumplings()
        }
    }
}