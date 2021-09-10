package com.fourbitalliance.bcon.waigoma.service;

import android.content.Intent;
import android.os.Build;

import com.fourbitalliance.bcon.MainActivity;
import com.fourbitalliance.bcon.waigoma.PreferenceManager;

public class BackgroundManager {
    /**
     * Background処理を有効にする
     *
     * @param mainActivity MainActivity
     */
    public void enable(MainActivity mainActivity) {
        // Backgroundで動いていないことを確認
        if (!new PreferenceManager(PreferenceManager.Settings.FILE).getBool(PreferenceManager.Settings.BACKGROUND)) {
            if (MainService.isRunning()) {
                disable(mainActivity);
                return;
            }
        } else {
            if (MainService.isRunning()) return;
        }

        Intent intent = new Intent(mainActivity.getApplication(), MainService.class);
        intent.putExtra("REQUEST_CODE", 1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) mainActivity.startForegroundService(intent);
    }

    /**
     * Background処理を無効にする
     *
     * @param mainActivity MainActivity
     */
    public void disable(MainActivity mainActivity) {
        // Backgroundで動いていることを確認
        if (new PreferenceManager(PreferenceManager.Settings.FILE).getBool(PreferenceManager.Settings.BACKGROUND)) {
            if (!MainService.isRunning()) {
                enable(mainActivity);
                return;
            }
        } else {
            if (!MainService.isRunning()) return;
        }

        Intent intent = new Intent(mainActivity.getApplication(), MainService.class);
        mainActivity.stopService(intent);
    }
}
