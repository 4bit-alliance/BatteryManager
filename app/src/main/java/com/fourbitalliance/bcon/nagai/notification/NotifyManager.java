package com.fourbitalliance.bcon.nagai.notification;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fourbitalliance.bcon.MainActivity;
import com.fourbitalliance.bcon.MyApplication;
import com.fourbitalliance.bcon.R;
import com.fourbitalliance.bcon.waigoma.PreferenceManager;

/**
 * 通知を飛ばすために使うクラス
 */
public class NotifyManager {
    private final MyApplication myApplication = MyApplication.getInstance();

    /**
     * チャネル初期設定
     */
    public void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importanceHigh = NotificationManager.IMPORTANCE_HIGH;
            int importanceDef = NotificationManager.IMPORTANCE_DEFAULT;
            int importanceLow = NotificationManager.IMPORTANCE_LOW;
            String title = myApplication.getString(R.string.app_name);

            android.app.NotificationChannel channelBg = new android.app.NotificationChannel(Channel.DEFAULT, title, importanceLow);
            android.app.NotificationChannel channel = new android.app.NotificationChannel(Channel.ALARM, title + "_ALARMチャネル", importanceHigh);
            android.app.NotificationChannel channel2 = new android.app.NotificationChannel(Channel.WARN, title + "_WARMチャネル", importanceHigh);

            channel.enableVibration(true);
            channel2.enableVibration(true);


            channel.setDescription("アラームチャネル");
            channel2.setDescription("警告チャネル");

            android.app.NotificationManager notificationManager = myApplication.getSystemService(android.app.NotificationManager.class);
            notificationManager.createNotificationChannel(channelBg);
            notificationManager.createNotificationChannel(channel);
            notificationManager.createNotificationChannel(channel2);
        }
    }

    /**
     * ALARMチャネルを使ってアラーム通知を送れる <br>
     * 使い方 new NotifyManager().notifyAlarm()
     */
    public void notifyAlarm(String text) {
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(myApplication, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        PreferenceManager pm = new PreferenceManager(PreferenceManager.Settings.FILE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(myApplication, Channel.ALARM)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("警告アラーム")
                .setContentText(text)
                .setColor(Color.GREEN)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setLights(Color.BLUE, 1000, 3000)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setFullScreenIntent(pendingIntent, true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(myApplication);

        notificationManager.notify(R.string.app_name, builder.build());
    }

    /**
     * ALARMチャネルを使ってアラーム通知を送れる<br>
     * 使い方 new NotifyManager().notifyWarn()
     */
    public void notifyWarn(String text){
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(myApplication, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(myApplication, Channel.WARN)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("通知")
                .setContentText(text)
                .setColor(Color.GREEN)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setLights(Color.BLUE, 1000, 3000)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setFullScreenIntent(pendingIntent, true)
                .setVibrate(new long[]{100, 0, 100, 0, 100, 0});

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(myApplication);

        notificationManager.notify(R.string.app_name, builder.build());
    }

    /**
     * Service 用通知
     *
     * @param intent Intent
     * @return Notification.Builder
     */
    public Notification.Builder notifyService(Intent intent) {
        int requestCode = intent.getIntExtra("REQUEST_CODE", 0);
        Context context = myApplication.getApplicationContext();
        Notification.Builder notifyService = null;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context, requestCode, new Intent(context.getApplicationContext(), MainActivity.class), PendingIntent.FLAG_IMMUTABLE);

            notifyService = new Notification.Builder(context, Channel.DEFAULT)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setColor(Color.GREEN)
                    .setContentTitle("B-CON background")
                    .setContentText("バッテリー残量")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .setStyle(new Notification.InboxStyle());
        }
        return notifyService;
    }

    public void setNotifyText(Notification.Builder builder, String text, int id) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(myApplication);
        builder.setContentText(text).build();

        notificationManager.notify(id, builder.build());
    }

    public static class Channel {
        public static String DEFAULT = "default";
        public static String ALARM = "ALARM";
        public static String WARN = "WARN";
    }
}
