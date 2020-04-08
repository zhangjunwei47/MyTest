package com.example.kaola.myapplication.designpattern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View


import com.example.kaola.myapplication.designpattern.decorator.java.APancake
import com.example.kaola.myapplication.designpattern.decorator.java.EggsPancake
import com.example.kaola.myapplication.designpattern.decorator.java.MeatPancake
import com.example.kaola.myapplication.designpattern.decorator.java.Pancake
import com.example.kaola.myapplication.designpattern.decorator.kotlin.factory.Dumplings
import com.example.kaola.myapplication.designpattern.decorator.kotlin.factory.DumplingsFactory
import com.zc.test.R

/**
 * @author zhangchao on 2019-06-12.
 */

class DesignPatternActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_design_pattern)
        findViewById<View>(R.id.singleton_btn).setOnClickListener(this)
        findViewById<View>(R.id.decorator_btn).setOnClickListener(this)
        findViewById<View>(R.id.facade_btn).setOnClickListener(this)
        findViewById<View>(R.id.prototype_btn).setOnClickListener(this)
        findViewById<View>(R.id.factory_btn).setOnClickListener(this)
    }


    private fun testSingleton() {

    }

    private fun testDecorator() {
        var pancake: APancake
        pancake = Pancake()
        pancake = EggsPancake(pancake)
        pancake = EggsPancake(pancake)
        pancake = MeatPancake(pancake)
        pancake.decorator()
    }

    private fun testFacade() {

    }

    private fun testPrototype() {

    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.singleton_btn -> {
                testSingleton()
            }
            R.id.decorator_btn -> {
                testDecorator()
            }
            R.id.facade_btn -> {
                testFacade()
            }
            R.id.prototype_btn -> {
                testPrototype()
            }
            R.id.factory_btn -> {
                testFactory()
            }
            else -> {
            }
        }
    }

    fun testFactory() {
        var dumplings = DumplingsFactory().getFruitDumplings()
        dumplings?.run {
        }

        var dumplingsx = DumplingsFactory().getMeatDumplings()
        dumplingsx?.run {
        }
    }
}
