package com.fourbitalliance.batterymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.fourbitalliance.batterymanager.waigoma.PreferenceManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_BatteryManager);
        setContentView(R.layout.activity_main);

        // 初期設定
        new PreferenceManager(PreferenceManager.Settings.FILE).setup();

        // MainFragment表示
        addFragment(new MainFragment());
    }

    // TODO 戻るボタンを実装したい
//    public void setupBackButton(boolean enableBackButton) {
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(enableBackButton);
//    }

    // Fragmentを表示させるメソッド定義 (表示したいの引数へ)
    private void addFragment(Fragment fragment) {
        // FragmentManager取得
        FragmentManager manager = getSupportFragmentManager();
        // FragmentTransaction開始
        FragmentTransaction transaction = manager.beginTransaction();
        // MainFragment追加
        transaction.add(R.id.activity_main, fragment);
        // commitすると反映される
        transaction.commit();
    }

}