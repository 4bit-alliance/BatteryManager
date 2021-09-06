package com.fourbitalliance.batterymanager.waigoma;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.view.View;
import android.widget.TextView;

import com.fourbitalliance.batterymanager.R;

public class BatteryInfo {
    private final View batteryInfoView;
    private final BatteryInfoFragment batteryInfoFragment;

    public BatteryInfo(BatteryInfoFragment batteryInfoFragment, View batteryInfoView) {
        this.batteryInfoFragment = batteryInfoFragment;
        this.batteryInfoView = batteryInfoView;
    }

    public void setText() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = batteryInfoFragment.getActivity().registerReceiver(null, ifilter);
        TextView batteryLevelText = batteryInfoView.findViewById(R.id.batteryLevelText);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        batteryLevelText.setText((level * 100 / scale) + "%");

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

        TextView batteryChargingText = batteryInfoView.findViewById(R.id.batteryChargingText);
        TextView batterySourceText = batteryInfoView.findViewById(R.id.batterySourceText);

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
