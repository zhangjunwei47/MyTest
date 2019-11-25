package com.example.kaola.myapplication.other

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

import android.media.AudioManager
import com.google.android.exoplayer2.util.Log
import com.zc.test.R


/**
 * @author zhangchao on 2019-09-02.
 */

class AudioFocusActivity : AppCompatActivity(), View.OnClickListener {

    var focusOne: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_focus)
        findViewById<View>(R.id.audio_focus_one).setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        val id = v?.id
        when (id) {
            R.id.audio_focus_one -> runOne()
            //          R.id.audio_focus_two -> runTwo()
        }
    }


    var mAudioManager: AudioManager? = null

    private fun runOne() {
        //1 初始化AudioManager对象
        mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager?
        //2 申请焦点
        mAudioManager?.run {
            var state = requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
            Log.d("logx", "xxxxx state  = " + state)
        }
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun runTwo()
//    {
//        mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager?
//        mAudioManager.requestAudioFocus(AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN))
//    }


    private val mAudioFocusChange = object : AudioManager.OnAudioFocusChangeListener {
        override fun onAudioFocusChange(focusChange: Int) {
            when (focusChange) {
                AudioManager.AUDIOFOCUS_LOSS -> {
                    //长时间丢失焦点,当其他应用申请的焦点为AUDIOFOCUS_GAIN时，
                    //会触发此回调事件，例如播放QQ音乐，网易云音乐等
                    //通常需要暂停音乐播放，若没有暂停播放就会出现和其他音乐同时输出声音
                    Log.d("logx", "xxxxx AUDIOFOCUS_LOSS")
                    //释放焦点，该方法可根据需要来决定是否调用
                    //若焦点释放掉之后，将不会再自动获得
                    //mAudioManager.abandonAudioFocus(this)
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                    //短暂性丢失焦点，当其他应用申请AUDIOFOCUS_GAIN_TRANSIENT或AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE时，
                    //会触发此回调事件，例如播放短视频，拨打电话等。
                    //通常需要暂停音乐播放
                    Log.d("logx", "xxxxxxx AUDIOFOCUS_LOSS_TRANSIENT")
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ->
                    //短暂性丢失焦点并作降音处理
                    Log.d("logx", "xxxxxxxxxx AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK")
                AudioManager.AUDIOFOCUS_GAIN -> {
                    //当其他应用申请焦点之后又释放焦点会触发此回调
                    //可重新播放音乐
                    Log.d("logx", "xxxxxxxxxxx AUDIOFOCUS_GAIN")
                }
            }
        }
    }

}
