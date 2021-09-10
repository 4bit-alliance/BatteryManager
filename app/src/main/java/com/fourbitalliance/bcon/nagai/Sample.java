package com.fourbitalliance.bcon.nagai;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fourbitalliance.bcon.MainActivity;
import com.fourbitalliance.bcon.MyApplication;
import com.fourbitalliance.bcon.R;
import com.fourbitalliance.bcon.waigoma.PreferenceManager;
import com.fourbitalliance.bcon.waigoma.info.BatteryInfoManager;
import com.fourbitalliance.bcon.waigoma.service.MainService;

public class Sample extends BroadcastReceiver {
    MainService main = new MainService();

    private Context context;
    private Intent intent;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        changeText();
    }

    public void changeText() {
        TextView batteryLevel = ((MainActivity)context).findViewById(R.id.batteryLevel);
        TextView batteryState = ((MainActivity)context).findViewById(R.id.batteryState);
        ImageView batteryImage = ((MainActivity)context).findViewById(R.id.batteryImage);

        String action = intent.getAction();

        if (action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {
            PreferenceManager pm = new PreferenceManager(PreferenceManager.Settings.FILE);
            BatteryInfoManager bim = new BatteryInfoManager(intent);

            // バッテリー残量
            int percentage = -1;
            if (!(pm.getBool(PreferenceManager.Settings.ENABLE_LEVEL))) {
                percentage = bim.level();
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
                batteryState.setText(message);
                batteryLevel.setText(percentage + "%");
            } else {
                percentage = bim.maxPercentCapacity();
                long maxPercentCap = bim.maxPercentCapacity();
                batteryLevel.setText(maxPercentCap + "%");

                if (maxPercentCap >= 80) {
                    batteryState.setText("正常です");
                } else {
                    batteryState.setText("劣化しています");
                }

            }

            ViewGroup.LayoutParams lp = batteryLevel.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;


            // 画像を表示
            if (!(pm.getBool(PreferenceManager.Settings.ENABLE_LEVEL))) {
                if (percentage == 100) {
                    batteryImage.setImageResource(R.drawable.bat_100);
                    mlp.setMargins(0, 0,0,0);

                } else if (percentage > 90) {
                    batteryImage.setImageResource(R.drawable.bat_90);
                    mlp.setMargins(0, 70,0,0);
                    MyApplication.getInstance().notifyWarm();

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
            } else {
                if (percentage == 100) {
                    batteryImage.setImageResource(R.drawable.capa_100);
                    mlp.setMargins(0, 0,0,0);

                } else if (percentage > 90) {
                    batteryImage.setImageResource(R.drawable.capa_90);
                    mlp.setMargins(0, 70,0,0);

                } else if (percentage > 80) {
                    batteryImage.setImageResource(R.drawable.capa_80);
                    mlp.setMargins(0,140,0,0);

                } else if (percentage > 70) {
                    batteryImage.setImageResource(R.drawable.capa_70);
                    mlp.setMargins(0, 210, 0, 0);

                } else if (percentage > 60) {
                    batteryImage.setImageResource(R.drawable.capa_60);
                    mlp.setMargins(0,280,0,0);

                } else if (percentage > 50) {
                    batteryImage.setImageResource(R.drawable.capa_50);
                    mlp.setMargins(0,350,0,0);

                } else if (percentage > 40) {
                    batteryImage.setImageResource(R.drawable.capa_40);
                    mlp.setMargins(0, 420, 0, 0);

                } else if (percentage > 30) {
                    batteryImage.setImageResource(R.drawable.capa_30);
                    mlp.setMargins(0, 490, 0, 0);

                } else if (percentage > 20) {
                    batteryImage.setImageResource(R.drawable.capa_20);
                    mlp.setMargins(0, 560, 0, 0);

                } else if (percentage > 10) {
                    batteryImage.setImageResource(R.drawable.capa_10);
                    mlp.setMargins(0,630,0,0);

                } else {
                    batteryImage.setImageResource(R.drawable.capa_0);
                    mlp.setMargins(0,700,0,0);

                }
            }


            batteryLevel.setLayoutParams(mlp);

        }
    }
}
