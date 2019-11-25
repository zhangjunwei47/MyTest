package com.example.kaola.myapplication.designpattern.singleton.kotlin

/**
 * @author zhangchao on 2019-06-12.
 */

class DoubleCheckSingleton private constructor() {
    companion object {
        val instance: DoubleCheckSingleton by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DoubleCheckSingleton()
        }
    }
}