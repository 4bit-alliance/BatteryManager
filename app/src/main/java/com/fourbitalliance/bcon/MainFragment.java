package com.fourbitalliance.bcon;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fourbitalliance.bcon.nagai.Sample;
import com.fourbitalliance.bcon.waigoma.PreferenceManager;
import com.fourbitalliance.bcon.waigoma.info.BatteryInfoFragment;
import com.fourbitalliance.bcon.waigoma.setting.BatterySettingFragment;


public class MainFragment extends Fragment {
    private final Sample batteryReceiver = new Sample();
    private final IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // フラグメントで表示する画面をlayoutファイルからインフレートする
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ImageButton infoBt = view.findViewById(R.id.batteryInfoButton);
        ImageButton settingBt = view.findViewById(R.id.batterySettingButton);
        Switch switchLevelCap = view.findViewById(R.id.switch_level_cap);


        // ボタンクリック時の挙動 (バッテリー情報)
        infoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new BatteryInfoFragment());
            }
        });

        settingBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new BatterySettingFragment());
            }
        });
        switchLevelCap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                new PreferenceManager(PreferenceManager.Settings.FILE).editBool(PreferenceManager.Settings.ENABLE_LEVEL, b);
                batteryReceiver.changeText();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().registerReceiver(batteryReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().unregisterReceiver(batteryReceiver);
    }

    // Fragmentを切り替えるメソッド
    private void replaceFragment(Fragment fragment) {
        // FragmentManager取得
        FragmentManager manager = getParentFragmentManager();
        // FragmentTransaction開始
        FragmentTransaction transaction = manager.beginTransaction();
        // レイアウトをfragment(引数)に置き換え (追加)
        transaction.replace(R.id.activity_main, fragment);
        // 置き換えのFragmentTransactionをバックスタックに保存
        transaction.addToBackStack(null);
        // FragmentTransactionをコミット
        transaction.commit();
    }
}
