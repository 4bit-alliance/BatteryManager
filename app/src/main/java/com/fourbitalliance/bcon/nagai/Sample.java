package com.fourbitalliance.bcon.nagai;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fourbitalliance.bcon.MainActivity;
import com.fourbitalliance.bcon.R;

public class Sample extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TextView batterylevel = ((MainActivity)context).findViewById(R.id.batteryLevel);
        TextView batterystate = ((MainActivity)context).findViewById(R.id.batteryState);
        ImageView batteryImage = ((MainActivity)context).findViewById(R.id.batteryImage);

        String action = intent.getAction();

        if (action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {
            // バッテリー情報を取得

            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            String message = "";

            switch (status) {
                case BatteryManager.BATTERY_STATUS_FULL:
                    message = "充電完了";
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    message = "充電中";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    message = "放電中";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    message = "充電していません";
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    message = "不明";
                    break;
            }
            batterystate.setText(message);

            // バッテリー残量
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int percentage = level * 100 / scale;
            batterylevel.setText(percentage + "%");
            ViewGroup.LayoutParams lp = batterylevel.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;


            // 画像を表示
            if (percentage == 100) {
                batteryImage.setImageResource(R.drawable.bat_100);
                mlp.setMargins(0, 0,0,0);

            } else if (percentage > 90) {
                batteryImage.setImageResource(R.drawable.bat_90);
                mlp.setMargins(0, 70,0,0);

            } else if (percentage > 80) {
                batteryImage.setImageResource(R.drawable.bat_80);
                mlp.setMargins(0,140,0,0);

            } else if (percentage > 70) {
                batteryImage.setImageResource(R.drawable.bat_70);
                mlp.setMargins(0, 210, 0, 0);

            } else if (percentage > 60) {
                    batteryImage.setImageResource(R.drawable.bat_60);
                    mlp.setMargins(0,280,0,0);

            } else if (percentage > 50) {
                batteryImage.setImageResource(R.drawable.bat_50);
                mlp.setMargins(0,350,0,0);

            } else if (percentage > 40) {
                batteryImage.setImageResource(R.drawable.bat_40);
                mlp.setMargins(0, 420, 0, 0);

            } else if (percentage > 30) {
                batteryImage.setImageResource(R.drawable.bat_30);
                mlp.setMargins(0, 490, 0, 0);

            } else if (percentage > 20) {
                batteryImage.setImageResource(R.drawable.bat_20);
                mlp.setMargins(0, 560, 0, 0);

            } else if (percentage > 10) {
                batteryImage.setImageResource(R.drawable.bat_10);
                mlp.setMargins(0,630,0,0);

            } else {
                batteryImage.setImageResource(R.drawable.bat_0);
                mlp.setMargins(0,700,0,0);

            }

            batterylevel.setLayoutParams(mlp);

        }
    }
}
