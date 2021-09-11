package com.fourbitalliance.bcon.waigoma;

import android.content.Context;
import android.content.SharedPreferences;

import com.fourbitalliance.bcon.MyApplication;

public class PreferenceManager {
    private final SharedPreferences pref;

    public PreferenceManager(String fileName) {
        this.pref = MyApplication.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    /**
     * 初回起動時に設定可能項目のデフォルト値を設定
     */
    public void setup() {
        if (pref.contains(Settings.INITIALIZE)) return;
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Settings.ENABLE_LEVEL, true);
        editor.putBoolean(Settings.INITIALIZE, true);
        editor.putBoolean(Settings.ENABLE_ALARM, true);
        editor.putBoolean(Settings.ENABLE_WARN, true);
        editor.putInt(Settings.ALARM_MAX_PERCENT, 100);
        editor.putInt(Settings.ALARM_MIN_PERCENT, 5);
        editor.putInt(Settings.WARN_MAX_PERCENT, 95);
        editor.putInt(Settings.WARN_MIN_PERCENT, 15);
        editor.putBoolean(Settings.DARK_MODE, false);
        editor.putBoolean(Settings.BACKGROUND, true);
        editor.apply();
    }

    /**
     * 設定可能項目の String を編集できる
     *
     * @param key String - 設定項目名 Settings クラスからどうぞ
     * @param value String - 編集後の値
     */
    public void editStr(String key, String value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 設定可能項目の int を編集できる
     *
     * @param key String - 設定項目名 Settings クラスからどうぞ
     * @param value int - 編集後の値
     */
    public void editInt(String key, int value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 設定可能項目の boolean を編集できる
     *
     * @param key String - 設定項目名 Settings クラスからどうぞ
     * @param value boolean - 編集後の値
     */
    public void editBool(String key, boolean value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 設定可能項目の String を取得できる
     *
     * @param key String - 設定項目名 Settings クラスからどうぞ
     */
    public String getStr(String key) {
        return pref.getString(key, "");
    }

    /**
     * 設定可能項目の int を取得できる
     *
     * @param key String - 設定項目名 Settings クラスからどうぞ
     */
    public int getInt(String key) {
        return pref.getInt(key, -1);
    }

    /**
     * 設定可能項目の boolean を取得できる
     *
     * @param key String - 設定項目名 Settings クラスからどうぞ
     */
    public boolean getBool(String key) {
        return pref.getBoolean(key, false);
    }

    /**
     * 設定可能項目が存在しているかチェックする
     *
     * @param key String - 設定項目名 Settings クラスからどうぞ
     * @return boolean - exists == true
     */
    public boolean exists(String key) {
        return pref.contains(key);
    }

    /**
     * 設定可能項目一覧
     */
    public static class Settings {
        /**
         * ファイル名
         */
        public final static String FILE = "bcon_settings";
        /**
         * 初期化されているか確認用 - boolean
         */
        public final static String INITIALIZE = "initialize";
        /**
         * バッテリー残量か寿命か判別用 - boolean
         */
        public final static String ENABLE_LEVEL = "enable_level";
        /**
         * アラーム有効 or 無効 - boolean
         */
        public final static String ENABLE_ALARM = "enable_alarm";
        /**
         * 警告(通知)有効 or 無効 - boolean
         */
        public final static String ENABLE_WARN = "enable_warn";
        /**
         * アラームが発火する最大 % - int
         */
        public final static String ALARM_MAX_PERCENT = "alarm_max_percent";
        /**
         * アラームが発火する最小 % - int
         */
        public final static String ALARM_MIN_PERCENT = "alarm_min_percent";
        /**
         * 警告が発火する最大 % - int
         */
        public final static String WARN_MAX_PERCENT = "warn_max_percent";
        /**
         * 警告が発火する最小 % - int
         */
        public final static String WARN_MIN_PERCENT = "warn_min_percent";
        /**
         * ダークモード有効 or 無効 - boolean
         */
        public final static String DARK_MODE = "dark_mode";
        /**
         * バックグラウンドサービス有効 or 無効 - boolean
         */
        public final static String BACKGROUND = "background";
    }
}
