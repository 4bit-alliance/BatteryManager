package com.fourbitalliance.bcon.waigoma.info;

import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.fourbitalliance.bcon.MyApplication;

public class BatteryInfoManager {
    private final Intent intent;
    private final BatteryManager batteryManager;

    public BatteryInfoManager(Intent intent) {
        this.intent = intent;
        batteryManager = (BatteryManager) MyApplication.getInstance().getSystemService(Context.BATTERY_SERVICE);
    }

    /**
     * バッテリー残量を取得する
     *
     * @return int バッテリー残量 (%)
     */
    public int batteryLevel(){
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        return level;
    }

    public int level() {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        return (int) (level * 100 / (float) scale);
    }

    /**
     * バッテリーステータスを取得する
     *
     * @return String <br>
     *     <ul>
     *         <li>充電完了</li>
     *         <li>充電中</li>
     *         <li>放電中</li>
     *         <li>充電されていない</li>
     *         <li>不明</li>
     *     </ul>
     */
    public String status() {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        switch (status) {
            case BatteryManager.BATTERY_STATUS_FULL:
                return "充電完了";
            case BatteryManager.BATTERY_STATUS_CHARGING:
                return "充電中";
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                return "放電中";
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                return "充電されていない";
            default:
                return "不明";
        }
    }

    /**
     * 充電中かどうかを取得する
     *
     * @return boolean 充電中 == true
     */
    public boolean isCharging() {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        return status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
    }

    /**
     * 給電タイプを取得する
     *
     * @return String <br>
     *     <ul>
     *         <li>Battery - 放電中</li>
     *         <li>USB - USB充電中</li>
     *         <li>AC - AC充電中</li>
     *     </ul>
     */
    public String chargeType() {
        if (!isCharging()) return "Battery";

        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        switch (chargePlug) {
            case BatteryManager.BATTERY_PLUGGED_USB:
                return "USB";
            case BatteryManager.BATTERY_PLUGGED_AC:
                return "AC";
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                return "Wireless";
        }

        return "null";
    }

    /**
     * バッテリーの健康状態を取得する
     *
     * @return String <br>
     *     <ul>
     *         <li>良好</li>
     *         <li>壊れている</li>
     *         <li>低温状態</li>
     *         <li>温度異常</li>
     *         <li>電圧異常</li>
     *         <li>不明</li>
     *     </ul>
     */
    public String health() {
        int status = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
        switch (status) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                return "良好";
            case BatteryManager.BATTERY_HEALTH_DEAD:
                return "壊れている";
            case BatteryManager.BATTERY_HEALTH_COLD:
                return "低温状態";
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                return "温度異常";
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                return "電圧異常";
            default:
                return "不明";
        }
    }

    /**
     * バッテリーテクノロジーを取得する
     *
     * @return String
     */
    public String technology() {
        return intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
    }

    /**
     * バッテリー温度を取得する (単位: ℃/摂氏)
     *
     * @return int
     */
    public float temperature() {
        return intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10f;
    }

    /**
     * バッテリー電圧を取得する (単位: V/ボルト)
     *
     * @return float
     */
    public float voltage() {
        int volt = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
        return volt / 1000f;
    }

    /**
     *  バッテリーデザイン容量 (単位: mAh)
     *
     * @return int
     */
    public int designCapacity() {
        Object mPowerProfile;
        double batteryCapacity = 0;
        final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";

        try {
            mPowerProfile = Class.forName(POWER_PROFILE_CLASS)
                    .getConstructor(Context.class)
                    .newInstance(MyApplication.getInstance());

            batteryCapacity = (double) Class
                    .forName(POWER_PROFILE_CLASS)
                    .getMethod("getBatteryCapacity")
                    .invoke(mPowerProfile);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (int) batteryCapacity;
    }

    /**
     * バッテリー最大容量
     * 現在の容量 / 最大% * 100
     *
     * @return long
     */
    public long maxCapacity() {
        int percentage = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        percentage = percentage <= 0 ? 1 : percentage;
        long counter = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
        return ((counter / percentage) * 100) / 1000;
    }

    /**
     * バッテリーの寿命を表す % , 現在の最大容量 / 出荷時の最大容量
     *
     * @return int
     */
    public int maxPercentCapacity() {
        return (int) (((float) maxCapacity() / designCapacity()) * 100f);
    }

    /**
     * バッテリー残量 (単位: mAh)
     *
     * @return long
     */
    public long remaining() {
        return batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER) / 1000;
    }
}
