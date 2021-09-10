package com.fourbitalliance.bcon.waigoma.service;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.fourbitalliance.bcon.nagai.notification.NotifyManager;
import com.fourbitalliance.bcon.waigoma.info.BatteryInfoManager;

public class MainService extends Service {
    private static boolean running = false;
    private Notification.Builder builder;
    private final IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotifyManager nm = new NotifyManager();
        builder = nm.notifyService(intent);
        Notification notification = builder.build();
        this.registerReceiver(broadcastReceiver, intentFilter);
        startForeground(1, notification);
        running = true;
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
        stopSelf();
        running = false;
    }

    private void checkBatteryLevel (Intent intent) {
        BatteryInfoManager bim = new BatteryInfoManager(intent);
        NotifyManager nm = new NotifyManager();
        nm.setNotifyText(builder, bim.level() + " %");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // バッテリー残量が変化したら呼ばれる
    // 今は通知ボックス内のバッテリー残量が変動するだけ
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkBatteryLevel(intent);
        }
    };

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean b) {
        running = b;
    }
}
