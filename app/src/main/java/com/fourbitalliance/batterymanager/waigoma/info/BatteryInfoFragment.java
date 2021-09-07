package com.fourbitalliance.batterymanager.waigoma.info;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fourbitalliance.batterymanager.MainActivity;
import com.fourbitalliance.batterymanager.R;

public class BatteryInfoFragment extends Fragment {
    private final BatteryInfo batteryInfo = new BatteryInfo();
    private final IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 表示する画面をインフレート
        View view = inflater.inflate(R.layout.battery_info, container, false);

        // 所属親アクティビティ取得
        MainActivity activity = (MainActivity) getActivity();
        // 戻るボタン
//        activity.setupBackButton(true);

        // アクションバーメニューが使えるようになる
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().registerReceiver(batteryInfo, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().unregisterReceiver(batteryInfo);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() != R.id.home) return super.onOptionsItemSelected(item);
        // 戻るボタンを押したとき
        // 遷移前に表示していたFragmentに戻る
        getParentFragmentManager().popBackStack();
        return true;
    }
}
