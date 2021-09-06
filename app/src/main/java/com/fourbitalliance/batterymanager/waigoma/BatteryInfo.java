package com.fourbitalliance.batterymanager.waigoma;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fourbitalliance.batterymanager.R;

public class BatteryInfo extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TextView batteryLevelText = ((AppCompatActivity)context).findViewById(R.id.batteryLevelText);
        TextView batteryChargingText = ((AppCompatActivity)context).findViewById(R.id.batteryChargingText);
        TextView batterySourceText = ((AppCompatActivity)context).findViewById(R.id.batterySourceText);

        BatteryInfoAPI bi = new BatteryInfoAPI(intent);

        // バッテリー残量
        batteryLevelText.setText(bi.level() + "%");
        // 充電中
        String charging = bi.isCharging() ? "Charging" : "Discharging";
        batteryChargingText.setText(charging);
        // 給電タイプ
        batterySourceText.setText(bi.chargeType());
    }
}
