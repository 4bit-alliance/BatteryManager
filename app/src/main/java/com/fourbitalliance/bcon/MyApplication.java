package com.fourbitalliance.bcon;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fourbitalliance.bcon.waigoma.PreferenceManager;
import com.fourbitalliance.bcon.waigoma.setting.ChangeTheme;

public class MyApplication extends Application {
    private static MyApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // 初期設定
        PreferenceManager pm = new PreferenceManager(PreferenceManager.Settings.FILE);
        boolean first = pm.exists(PreferenceManager.Settings.INITIALIZE);
        pm.setup();

        // テーマ設定
        ChangeTheme ct = new ChangeTheme();
        if (first) ct.setupTheme();
        else ct.setTheme();

        //通知（チャネル作成）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("ALARM", "B-CON_ALARMチャネル", importance);
            NotificationChannel channel2 = new NotificationChannel("WARM", "B-CON_WARMチャネル", importance);

            channel.setDescription("アラームチャネル");
            channel2.setDescription("警告チャネル");


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            notificationManager.createNotificationChannel(channel2);


        }
    }

    public void notifyAlarm() {
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "ALARM")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("警告アラーム")
                .setContentText("メッセージ：バッテリー残量が100%になりました。")
                .setColor(Color.GREEN)
                .setContentIntent(pendingIntent)
                //.setDefaults(Notification.DEFAULT_LIGHTS)
                //.setLights(Color.BLUE, 1000, 3000)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(R.string.app_name, builder.build());
    }

    public void notifyWarm(){
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "WARM")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("充電完了")
                .setContentText("メッセージ：十分なバッテリー残量になりました。")
                .setColor(Color.GREEN)
                .setContentIntent(pendingIntent)
                //.setDefaults(Notification.DEFAULT_LIGHTS)
                //.setLights(Color.BLUE, 1000, 3000)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(R.string.app_name, builder.build());
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
