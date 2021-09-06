package com.fourbitalliance.batterymanager.waigoma;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private final SharedPreferences pref;

    public PreferenceManager(String fileName, Context context) {
        this.pref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
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

}
