package com.example.kaola.myapplication.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;


import com.zc.test.R;

import java.util.ArrayList;

/**
 * @author zhangchao on 2018/6/20.
 */

public class NotificationUtil {
    private static final String channel_id = "1000";

    public static void init(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel_id);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout_view);
        builder.setCustomContentView(remoteViews);
        builder.setSmallIcon(R.mipmap.notification_icon);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(1000, notification);
        }
    }

    public static void getNotificationColor(Context context) {
        int color = -1;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel_id);
        Notification notification = builder.build();
        int layoutID = notification.contentView.getLayoutId();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(layoutID, null, false);
        TextView textView = viewGroup.findViewById(android.R.id.title);
        if (textView == null) {
            final ArrayList<TextView> arrayList = new ArrayList<>();
            filterView(viewGroup, new Filter() {
                @Override
                public void filter(View view) {
                    if (view instanceof TextView) {
                        arrayList.add((TextView) view);
                    }
                }
            });
            for (int i = 0, j = arrayList.size(); i < j; i++) {
                TextView textView1 = arrayList.get(i);
                Log.e("logx", "xxxxxxxxx 获取到的字体的大小: " + textView1.getTextSize() + "xxxx 颜色值 = " + textView1.getCurrentTextColor());
            }
        } else {
            Log.e("logx", "xxxxxxxxx 获取到的颜色值 text 不为空");
            color = textView.getCurrentTextColor();
        }
        Log.e("logx", "xxxxxxxxx 获取到的颜色值为: " + color);
    }

    private static void filterView(View view, Filter filter) {
        if (view == null || filter == null) {
            return;
        }
        filter.filter(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0, j = viewGroup.getChildCount(); i < j; i++) {
                View view1 = viewGroup.getChildAt(i);
                filterView(view1, filter);
            }
        }

    }

    private static interface Filter {
        void filter(View view);
    }

}
