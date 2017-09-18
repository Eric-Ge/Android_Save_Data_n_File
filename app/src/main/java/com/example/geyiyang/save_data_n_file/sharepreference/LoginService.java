package com.example.geyiyang.save_data_n_file.sharepreference;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by geyiyang on 2017/9/14.
 */

public class LoginService {
    private Context mContext;

    public LoginService(Context sContext) {
        mContext=sContext;
    }
    public boolean SaveLoginMsg(String name,String password) {
        boolean flag=false;
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences("login", Context.MODE_PRIVATE);//第一个是文件名，自动以xml格式保存
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("Username", name);
        mEditor.putString("Password", password);
        flag=mEditor.commit();
        return flag;
    }

    public boolean SaveSharePreference(String sFilename, Map<String, Object> map) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(sFilename, Context.MODE_PRIVATE);//第一个是文件名，自动以xml格式保存
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            String mKey=entry.getKey();
            Object mValue=entry.getValue();
            if (mValue instanceof String) {
                mEditor.putString(mKey, (String)mValue);
            }
            if (mValue instanceof Integer) {
                mEditor.putInt(mKey, (Integer)mValue);
            }
            if (mValue instanceof Long) {
                mEditor.putLong(mKey, (Long)mValue);
            }
            if (mValue instanceof Boolean) {
                mEditor.putBoolean(mKey, (Boolean)mValue);
            }
            if (mValue instanceof Float) {
                mEditor.putFloat(mKey, (Float) mValue);
            }
        }
        boolean flag = false;
        flag = mEditor.commit();
        return flag;
    }

    public Map<String, ?> getSharePreference(String sFilename) {
        Map<String,?> mMap=null;
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(sFilename, Context.MODE_PRIVATE);//第一个是文件名，自动以xml格式保存
        mMap=mSharedPreferences.getAll();
        return mMap;
    }
}
