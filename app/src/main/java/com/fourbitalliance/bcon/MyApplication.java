package com.fourbitalliance.bcon;

import android.app.Application;

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
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
