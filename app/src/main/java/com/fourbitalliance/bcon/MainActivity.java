package com.fourbitalliance.bcon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_BatteryManager);
        setContentView(R.layout.activity_main);

        // MainFragment表示
        if (!isDupFragment()) addFragment(new MainFragment());
    }

    // Fragmentが重ならない努力 (ダークモード切替)
    private boolean isDupFragment() {
        FragmentManager manager = getSupportFragmentManager();
        return manager.getFragments().size() > 0;
    }

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