package com.fourbitalliance.bcon.waigoma.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fourbitalliance.bcon.MainActivity;
import com.fourbitalliance.bcon.R;
import com.fourbitalliance.bcon.waigoma.PreferenceManager;

public class BatterySettingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 表示する画面をインフレート
        View view = inflater.inflate(R.layout.bcon_setting, container, false);

        // 所属親アクティビティ取得
        MainActivity activity = (MainActivity) getActivity();
        // 戻るボタン
//        activity.setupBackButton(true);

        // アクションバーメニューが使えるようになる
        setHasOptionsMenu(true);

        setListener(view);
        setSettingsView(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() != R.id.home) return super.onOptionsItemSelected(item);
        // 戻るボタンを押したとき
        // 遷移前に表示していたFragmentに戻る
        getParentFragmentManager().popBackStack();
        return true;
    }

    public void setSettingsView(View view) {
        PreferenceManager pm = new PreferenceManager(PreferenceManager.Settings.FILE);
        Switch alarmSw = view.findViewById(R.id.switch_alarm);
        Switch warnSw = view.findViewById(R.id.switch_warn);
        Switch darkModeSw = view.findViewById(R.id.switch_darkmode);
        Switch backgroundSw = view.findViewById(R.id.switch_background);
        SeekBar maxPercentBar = view.findViewById(R.id.max_percent_seekBar);
        SeekBar minPercentBar = view.findViewById(R.id.min_percent_seekBar);
        SeekBar upperPercentBar = view.findViewById(R.id.upper_percent_seekBar);
        SeekBar lowerPercentBar = view.findViewById(R.id.lower_percent_seekBar);
        TextView maxPercentText = view.findViewById(R.id.max_percent_text);
        TextView minPercentText = view.findViewById(R.id.min_percent_text);
        TextView upperPercentText = view.findViewById(R.id.upper_percent_text);
        TextView lowerPercentText = view.findViewById(R.id.lower_percent_text);

        alarmSw.setChecked(pm.getBool(PreferenceManager.Settings.ENABLE_ALARM));
        warnSw.setChecked(pm.getBool(PreferenceManager.Settings.ENABLE_WARN));
        darkModeSw.setChecked(pm.getBool(PreferenceManager.Settings.DARK_MODE));
        backgroundSw.setChecked(pm.getBool(PreferenceManager.Settings.BACKGROUND));
        maxPercentBar.setProgress(pm.getInt(PreferenceManager.Settings.ALARM_MAX_PERCENT));
        minPercentBar.setProgress(pm.getInt(PreferenceManager.Settings.ALARM_MIN_PERCENT));
        upperPercentBar.setProgress(pm.getInt(PreferenceManager.Settings.WARN_MAX_PERCENT));
        lowerPercentBar.setProgress(pm.getInt(PreferenceManager.Settings.WARN_MIN_PERCENT));
        maxPercentText.setText(pm.getInt(PreferenceManager.Settings.ALARM_MAX_PERCENT) + " %");
        minPercentText.setText(pm.getInt(PreferenceManager.Settings.ALARM_MIN_PERCENT) + " %");
        upperPercentText.setText(pm.getInt(PreferenceManager.Settings.WARN_MAX_PERCENT) + " %");
        lowerPercentText.setText(pm.getInt(PreferenceManager.Settings.WARN_MIN_PERCENT) + " %");
    }

    public void setListener(View view) {
        PreferenceManager pm = new PreferenceManager(PreferenceManager.Settings.FILE);
        Switch alarmSw = view.findViewById(R.id.switch_alarm);
        Switch warnSw = view.findViewById(R.id.switch_warn);
        Switch darkModeSw = view.findViewById(R.id.switch_darkmode);
        Switch backgroundSw = view.findViewById(R.id.switch_background);
        SeekBar maxPercentBar = view.findViewById(R.id.max_percent_seekBar);
        SeekBar minPercentBar = view.findViewById(R.id.min_percent_seekBar);
        SeekBar upperPercentBar = view.findViewById(R.id.upper_percent_seekBar);
        SeekBar lowerPercentBar = view.findViewById(R.id.lower_percent_seekBar);
        TextView maxPercentText = view.findViewById(R.id.max_percent_text);
        TextView minPercentText = view.findViewById(R.id.min_percent_text);
        TextView upperPercentText = view.findViewById(R.id.upper_percent_text);
        TextView lowerPercentText = view.findViewById(R.id.lower_percent_text);

        alarmSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                pm.editBool(PreferenceManager.Settings.ENABLE_ALARM, alarmSw.isChecked());
            }
        });
        warnSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                pm.editBool(PreferenceManager.Settings.ENABLE_WARN, warnSw.isChecked());
            }
        });
        darkModeSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                pm.editBool(PreferenceManager.Settings.DARK_MODE, darkModeSw.isChecked());
            }
        });
        backgroundSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                pm.editBool(PreferenceManager.Settings.BACKGROUND, backgroundSw.isChecked());
            }
        });

        maxPercentBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxPercentText.setText(progress + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pm.editInt(PreferenceManager.Settings.ALARM_MAX_PERCENT, seekBar.getProgress());
            }
        });
        minPercentBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minPercentText.setText(progress + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pm.editInt(PreferenceManager.Settings.ALARM_MIN_PERCENT, seekBar.getProgress());
            }
        });
        upperPercentBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                upperPercentText.setText(progress + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pm.editInt(PreferenceManager.Settings.WARN_MAX_PERCENT, seekBar.getProgress());
            }
        });
        lowerPercentBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lowerPercentText.setText(progress + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pm.editInt(PreferenceManager.Settings.WARN_MIN_PERCENT, seekBar.getProgress());
            }
        });
    }

}
