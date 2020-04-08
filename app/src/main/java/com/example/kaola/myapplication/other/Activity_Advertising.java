package com.example.kaola.myapplication.other;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kaola.myapplication.util.DeviceUtil;
import com.zc.test.R;

/**
 * @author zhangchao on 2018/10/10.
 */

public class Activity_Advertising extends Activity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising);
        imageView = findViewById(R.id.img_flash);
        textView = findViewById(R.id.text1);
        initViewx();
    }


    private void initViewx() {
        int width = DeviceUtil.getScreenWidth(this.getApplicationContext());
        if (width <= 828) {
            imageView.setImageResource(R.mipmap.x);
            textView.setText("屏幕宽度为: " + width + " 加载的图为: 640");
        } else if (width <= 1080) {
            imageView.setImageResource(R.mipmap.xx);
            textView.setText("屏幕宽度为: " + width + " 加载的图为: 1080");
        } else {
            imageView.setImageResource(R.mipmap.xxx);
            textView.setText("屏幕宽度为: " + width + " 加载的图为: 1125");
        }
    }
}
