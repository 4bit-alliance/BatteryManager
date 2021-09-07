package com.fourbitalliance.batterymanager;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
