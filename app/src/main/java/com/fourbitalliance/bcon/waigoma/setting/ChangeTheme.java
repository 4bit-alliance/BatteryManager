package com.fourbitalliance.bcon.waigoma.setting;

import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;

import com.fourbitalliance.bcon.MyApplication;
import com.fourbitalliance.bcon.waigoma.PreferenceManager;

public class ChangeTheme {
    private final PreferenceManager preferenceManager = new PreferenceManager(PreferenceManager.Settings.FILE);

    public void setTheme() {
        if (preferenceManager.getBool(PreferenceManager.Settings.DARK_MODE)) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void setupTheme() {
        int nightModeFlags = MyApplication.getInstance().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                preferenceManager.editBool(PreferenceManager.Settings.DARK_MODE, true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                preferenceManager.editBool(PreferenceManager.Settings.DARK_MODE, false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }
    }
}
