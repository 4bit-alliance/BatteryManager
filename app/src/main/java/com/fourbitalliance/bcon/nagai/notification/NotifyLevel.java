package com.fourbitalliance.bcon.nagai.notification;

import android.content.Intent;

import com.fourbitalliance.bcon.waigoma.PreferenceManager;
import com.fourbitalliance.bcon.waigoma.info.BatteryInfoManager;

public class NotifyLevel {
    private final BatteryInfoManager batteryInfoManager;
    private final PreferenceManager preferenceManager = new PreferenceManager(PreferenceManager.Settings.FILE);
    private final NotifyManager notifyManager = new NotifyManager();

    public NotifyLevel(Intent intent) {
        batteryInfoManager = new BatteryInfoManager(intent);
    }

    public void warn() {
        int currentLevel = batteryInfoManager.level();
        int maxWarnLevel = preferenceManager.getInt(PreferenceManager.Settings.WARN_MAX_PERCENT);
        int minWarnLevel = preferenceManager.getInt(PreferenceManager.Settings.WARN_MIN_PERCENT);

        // WARNアラーム無効
        if (!preferenceManager.getBool(PreferenceManager.Settings.ENABLE_WARN)) return;
        // WARNレベルの内側
        if (minWarnLevel < currentLevel && currentLevel < maxWarnLevel) return;

        if (batteryInfoManager.isCharging()) {
            // 充電中
            if (maxWarnLevel == currentLevel) notifyManager.notifyWarn("メッセージ：十分なバッテリー残量になりました。");
        } else {
            // 充電してないとき
            if (minWarnLevel == currentLevel) notifyManager.notifyWarn("メッセージ：バッテリー残量が減ってきています。");
        }
    }

    public void alarm() {
        int currentLevel = batteryInfoManager.level();
        int maxAlarmLevel = preferenceManager.getInt(PreferenceManager.Settings.ALARM_MAX_PERCENT);
        int minAlarmLevel = preferenceManager.getInt(PreferenceManager.Settings.ALARM_MIN_PERCENT);

        // WARNアラーム無効
        if (!preferenceManager.getBool(PreferenceManager.Settings.ENABLE_ALARM)) return;
        // WARNレベルの内側
        if (minAlarmLevel < currentLevel && currentLevel < maxAlarmLevel) return;

        if (batteryInfoManager.isCharging()) {
            // 充電中
            if (maxAlarmLevel == currentLevel) notifyManager.notifyAlarm("メッセージ：バッテリー残量が" + maxAlarmLevel + "%になりました。");
        } else {
            // 充電してないとき
            if (minAlarmLevel == currentLevel) notifyManager.notifyAlarm("メッセージ：バッテリー残量が" + minAlarmLevel + "%になりました。");
        }
    }
}
