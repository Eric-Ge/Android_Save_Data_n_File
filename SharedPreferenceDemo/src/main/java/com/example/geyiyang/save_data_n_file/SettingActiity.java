package com.example.geyiyang.save_data_n_file;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;

/**
 * Created by geyiyang on 2017/9/15.
 */

public class SettingActiity extends PreferenceActivity implements
        Preference.OnPreferenceChangeListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settingdemo);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }

    /**
     *
     * @param preferenceScreen
     * @param preference 表示用户点击的preference，用getkey（）返回preferrence对象
     * @return
     */
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
