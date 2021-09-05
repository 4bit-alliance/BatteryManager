package com.fourbitalliance.batterymanager.waigoma;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.fourbitalliance.batterymanager.R;

public class BatteryInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_BatteryManager);
        setContentView(R.layout.battery_info);
        TableLayout tableLayout = findViewById(R.id.batteryInfoTable);
        BatteryInfo batteryInfo = new BatteryInfo(this, tableLayout);
        batteryInfo.setText();
    }
}
