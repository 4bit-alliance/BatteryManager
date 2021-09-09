package com.fourbitalliance.bcon;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", "B-CON通知チャネル", importance);

            channel.setDescription("チャネル一覧");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void notifyTest() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(android.R.drawable.ic_menu_info_details)
                .setContentTitle("タイトル")
                .setContentText("メッセージ・メッセージ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(R.string.app_name, builder.build());
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
