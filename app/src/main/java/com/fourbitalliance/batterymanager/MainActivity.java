package com.fourbitalliance.batterymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.fourbitalliance.batterymanager.nagai.Sample;

public class MainActivity extends AppCompatActivity {
    private final Sample batteryReceiver = new Sample();
    private final IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_BatteryManager);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(batteryReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(batteryReceiver);
        super.onPause();
    }


}