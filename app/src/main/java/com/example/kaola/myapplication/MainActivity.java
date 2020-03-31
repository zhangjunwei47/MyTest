package com.example.kaola.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.kaola.myapplication.animationtest.Animation2Activity;
import com.example.kaola.myapplication.animationtest.AnimationActivity;
import com.example.kaola.myapplication.aspect.IDoubleClick;
import com.example.kaola.myapplication.designpattern.DesignPatternActivity;
import com.example.kaola.myapplication.landport.LandPortActivity;
import com.example.kaola.myapplication.other.AudioFocusActivity;
import com.example.kaola.myapplication.player.PlayerActivity;
import com.example.kaola.myapplication.servicelife.ServiceLifeActivity;
import com.example.kaola.myapplication.util.DataBaseTestUtil;
import com.example.kaola.myapplication.util.ThreadUtil;
import com.example.kaola.myapplication.util.UuidUtil;
import com.zc.test.R;

/**
 * 测试
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.play_btn).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PlayerActivity.class));
        });

        findViewById(R.id.animation_btn).setOnClickListener(v -> {
                    startActivity(new Intent(MainActivity.this, AnimationActivity.class));
                }
        );

        findViewById(R.id.design_pattern_btn).setOnClickListener(v -> {
                    startActivity(new Intent(MainActivity.this, DesignPatternActivity.class));
                }
        );
        findViewById(R.id.service_btn).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ServiceLifeActivity.class));
        });
        findViewById(R.id.land_port_btn).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LandPortActivity.class));
        });

        //   findViewById(R.id.read_file_btn).setOnClickListener(v -> UuidUtil.init());
        findViewById(R.id.read_file_btn).setOnClickListener(new View.OnClickListener() {
            @IDoubleClick
            @Override
            public void onClick(View v) {
                UuidUtil.init();
            }
        });
        findViewById(R.id.animation2_btn).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Animation2Activity.class));
        });

        findViewById(R.id.audio_focus_btn).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AudioFocusActivity.class));
        });
        findViewById(R.id.download_btn).setOnClickListener(v -> {
            ThreadUtil.getInstance().addListenerTest();
        });
        findViewById(R.id.database_test_btn).setOnClickListener(v -> {
            DataBaseTestUtil.testx();
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}