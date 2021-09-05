package com.fourbitalliance.batterymanager.waigoma;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.TableLayout;
import android.widget.TextView;

import com.fourbitalliance.batterymanager.MainActivity;
import com.fourbitalliance.batterymanager.R;

public class BatteryInfo {
    private final BatteryInfoActivity mainActivity;
    private final TableLayout tableLayout;

    public BatteryInfo(BatteryInfoActivity mainActivity, TableLayout tableLayout) {
        this.mainActivity = mainActivity;
        this.tableLayout = tableLayout;
    }

    public void setText() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = mainActivity.registerReceiver(null, ifilter);

        // 充電中か否か
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                             status == BatteryManager.BATTERY_STATUS_FULL;

        // 何で充電してるか
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        setBatteryInfoText(isCharging, usbCharge, acCharge);
    }

    private void setBatteryInfoText(boolean isCharging, boolean usbCharge, boolean acCharge) {
        TextView batteryChargingText = tableLayout.findViewById(R.id.batteryChargingText);
        TextView batterySourceText = tableLayout.findViewById(R.id.batterySourceText);
        if (isCharging) {
            batteryChargingText.setText("Charging");
            if (usbCharge) {
                batterySourceText.setText("USB Charging");
            } else if (acCharge) {
                batteryChargingText.setText("AC Charging");
            }
        } else {
            batteryChargingText.setText("Discharging");
            batterySourceText.setText("Battery");
        }
    }
}
