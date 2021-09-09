package com.fourbitalliance.bcon.waigoma;

import android.content.Context;
import android.content.SharedPreferences;

import com.fourbitalliance.bcon.MyApplication;

public class PreferenceManager {
    private final SharedPreferences pref;

    public PreferenceManager(String fileName) {
        this.pref = MyApplication.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public void setup() {
        if (pref.contains(Settings.INITIALIZE)) return;
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Settings.ENABLE_LEVEL, true);
        editor.putBoolean(Settings.INITIALIZE, true);
        editor.putBoolean(Settings.ENABLE_ALARM, true);
        editor.putBoolean(Settings.ENABLE_WARN, true);
        editor.putInt(Settings.ALARM_MAX_PERCENT, 100);
        editor.putInt(Settings.ALARM_MIN_PERCENT, 0);
        editor.putInt(Settings.WARN_MAX_PERCENT, 95);
        editor.putInt(Settings.WARN_MIN_PERCENT, 5);
        editor.putBoolean(Settings.DARK_MODE, false);
        editor.putBoolean(Settings.BACKGROUND, false);
        editor.apply();
    }

    public void editStr(String key, String value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void editInt(String key, int value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void editBool(String key, boolean value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public String getStr(String key) {
        return pref.getString(key, "");
    }

    public int getInt(String key) {
        return pref.getInt(key, -1);
    }

    public boolean getBool(String key) {
        return pref.getBoolean(key, false);
    }

    public boolean exists(String key) {
        return pref.contains(key);
    }

    public static class Settings {
        public final static String FILE = "bcon_settings";
        public final static String INITIALIZE = "initialize";
        public final static String ENABLE_LEVEL = "enable_level";
        public final static String ENABLE_ALARM = "enable_alarm";
        public final static String ENABLE_WARN = "enable_warn";
        public final static String ALARM_MAX_PERCENT = "alarm_max_percent";
        public final static String ALARM_MIN_PERCENT = "alarm_min_percent";
        public final static String WARN_MAX_PERCENT = "warn_max_percent";
        public final static String WARN_MIN_PERCENT = "warn_min_percent";
        public final static String DARK_MODE = "dark_mode";
        public final static String BACKGROUND = "background";
    }
}
