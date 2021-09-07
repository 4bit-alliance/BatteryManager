package com.fourbitalliance.bcon.waigoma.info;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fourbitalliance.bcon.R;

public class BatteryInfo extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TextView batteryLevelText = ((AppCompatActivity)context).findViewById(R.id.batteryLevelText);
        TextView batteryStatusText = ((AppCompatActivity)context).findViewById(R.id.batteryStatusText);
        TextView batterySourceText = ((AppCompatActivity)context).findViewById(R.id.batterySourceText);
        TextView batteryHealthText = ((AppCompatActivity)context).findViewById(R.id.batteryHealthText);
        TextView batteryTechnologyText = ((AppCompatActivity)context).findViewById(R.id.batteryTechnologyText);
        TextView batteryTemperatureText = ((AppCompatActivity)context).findViewById(R.id.batteryTemperatureText);
        TextView batteryVoltageText = ((AppCompatActivity)context).findViewById(R.id.batteryVoltageText);
        TextView batteryDesignCapacityText = ((AppCompatActivity)context).findViewById(R.id.batteryDesignCapacityText);
        TextView batteryCapacityText = ((AppCompatActivity)context).findViewById(R.id.batteryCapacityText);
        TextView batteryRemainingText = ((AppCompatActivity)context).findViewById(R.id.batteryRemainingText);

        BatteryInfoManager batteryInfo = new BatteryInfoManager(intent);

        // バッテリー残量
        batteryLevelText.setText(batteryInfo.level() + " %");
        // バッテリーステータス
        batteryStatusText.setText(batteryInfo.status());
        // 給電タイプ
        batterySourceText.setText(batteryInfo.chargeType());
        // バッテリー状態
        batteryHealthText.setText(batteryInfo.health());
        // バッテリーの種類
        batteryTechnologyText.setText(batteryInfo.technology());
        // バッテリー温度
        batteryTemperatureText.setText(batteryInfo.temperature() + " ℃");
        // バッテリー電圧
        batteryVoltageText.setText(batteryInfo.voltage() + " V");
        // バッテリーデザイン容量
        batteryDesignCapacityText.setText(batteryInfo.designCapacity() + " mAh");
        // バッテリー容量
        batteryCapacityText.setText(batteryInfo.maxCapacity() + " mAh");
        // バッテリー残量
        batteryRemainingText.setText(batteryInfo.remaining() + " mAh");
    }
}
