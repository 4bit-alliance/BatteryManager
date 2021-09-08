package com.fourbitalliance.bcon.waigoma.service;

import android.content.Intent;
import android.os.Build;

import com.fourbitalliance.bcon.MainActivity;
import com.fourbitalliance.bcon.waigoma.PreferenceManager;

public class BackgroundManager {
    public void enable(MainActivity mainActivity) {
        // Background
        if (!new PreferenceManager(PreferenceManager.Settings.FILE).getBool(PreferenceManager.Settings.BACKGROUND)) return;

        Intent intent = new Intent(mainActivity.getApplication(), MainService.class);
        intent.putExtra("REQUEST_CODE", 1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) mainActivity.startForegroundService(intent);
    }

    public void disable(MainActivity mainActivity) {
        if (new PreferenceManager(PreferenceManager.Settings.FILE).getBool(PreferenceManager.Settings.BACKGROUND)) return;

        Intent intent = new Intent(mainActivity.getApplication(), MainService.class);
        mainActivity.stopService(intent);
    }
}
