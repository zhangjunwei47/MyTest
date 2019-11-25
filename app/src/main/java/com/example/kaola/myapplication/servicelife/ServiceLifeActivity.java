package com.example.kaola.myapplication.servicelife;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.exoplayer2.util.Log;
import com.zc.test.R;

/**
 * @author zhangchao on 2019-06-26.
 */

public class ServiceLifeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_life);
        findViewById(R.id.oneBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Intent intent = new Intent(ServiceLifeActivity.this, ServiceLife.class);
                    startForegroundService(intent);
                } else {
                    Intent intent = new Intent(ServiceLifeActivity.this, ServiceLife.class);
                    startService(intent);
                }
            }
        });
    }


}
