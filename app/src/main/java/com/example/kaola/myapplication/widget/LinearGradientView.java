package com.example.kaola.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zc.test.R;


/**
 * @author zhangchao on 2019-06-04.
 */

public class LinearGradientView extends View {
    public LinearGradientView(Context context) {
        super(context);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        int colorStart = getResources().getColor(R.color.end_color);
        int colorEnd = getResources().getColor(R.color.start_color);

        Paint paint = new Paint();
        LinearGradient backGradient = new LinearGradient(0, 0, 0, height, new int[]{colorStart, colorEnd}, null, Shader.TileMode.CLAMP);
        paint.setShader(backGradient);
        canvas.drawRect(0, 0, width, height, paint);

    }
}
