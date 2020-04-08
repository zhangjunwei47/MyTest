package com.example.kaola.myapplication.designpattern.decorator.kotlin.factory

import android.util.Log


/**
 * @author zhangchao on 2019-07-18.
 */

class FruitDumplings : ADumplings() {
    lateinit var aDumplings: Dumplings

    override fun makeDumplings() {
        aDumplings = Dumplings("水果")
    }

    override fun getDumplings(): Dumplings {
        Log.d("logx", "返回kotlin类型的水果饺子")
        return aDumplings
    }

}