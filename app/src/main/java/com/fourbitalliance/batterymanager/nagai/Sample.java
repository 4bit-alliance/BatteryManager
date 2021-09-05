package com.fourbitalliance.batterymanager.nagai;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fourbitalliance.batterymanager.MainActivity;
import com.fourbitalliance.batterymanager.R;

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
            batterylevel.setText("現在" + percentage + "%");

            // 画像を表示
            if (percentage == 100) {
                batteryImage.setImageResource(R.drawable.battery1);

            } else if (percentage >= 75) {
                batteryImage.setImageResource(R.drawable.battery2);

            } else if (percentage >= 50) {
                batteryImage.setImageResource(R.drawable.battery3);

            } else if (percentage >= 20) {
                batteryImage.setImageResource(R.drawable.battery4);

            } else {
                batteryImage.setImageResource(R.drawable.battery5);
            }

        }
    }
}
