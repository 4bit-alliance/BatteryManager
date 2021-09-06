package com.fourbitalliance.batterymanager.waigoma;

import android.content.Intent;
import android.os.BatteryManager;

public class BatteryInfoAPI {
    private final Intent intent;

    public BatteryInfoAPI(Intent intent) {
        this.intent = intent;
    }

    /**
     * バッテリー残量を取得する
     *
     * @return int バッテリー残量 (%)
     */
    public int level() {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        return (int) (level * 100 / (float) scale);
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
        if (chargePlug == BatteryManager.BATTERY_PLUGGED_USB) return "USB";
        else if (chargePlug == BatteryManager.BATTERY_PLUGGED_AC) return "AC";

        return "null";
    }
}
