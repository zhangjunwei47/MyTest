package com.example.kaola.myapplication.designpattern.decorator.kotlin.factory

/**
 * @author zhangchao on 2019-07-18.
 */

class MeatDumplings : ADumplings() {
    lateinit var mDumplings: Dumplings
    override fun getDumplings(): Dumplings {
        return mDumplings
    }

    override fun makeDumplings() {
        mDumplings = Dumplings("肉馅饺子")
    }


}