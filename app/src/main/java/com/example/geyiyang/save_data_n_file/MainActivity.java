package com.example.geyiyang.save_data_n_file;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geyiyang.save_data_n_file.sharepreference.LoginService;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button mButton1,mButton2;
    private EditText mEditText1,mEditText2;
    private CheckBox mCheckBox1,mCheckBox2;
    private LoginService mLoginService;
    private Map<String,?> mStringMap=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);
        mCheckBox1 = (CheckBox) findViewById(R.id.checkBox1);
        mCheckBox2 = (CheckBox) findViewById(R.id.checkBox2);
        mEditText1 = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);
        mLoginService =new LoginService(MainActivity.this);
        mStringMap = mLoginService.getSharePreference("login");
        if (mStringMap != null && !mStringMap.isEmpty()) {
            mEditText1.setText((String) mStringMap.get("username"));
            mCheckBox1.setChecked((Boolean) mStringMap.get("isSaveUsernameChecked"));
            mCheckBox2.setChecked((Boolean) mStringMap.get("isAutoLoginChecked"));
        }
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText1.getText().toString().trim().equals("admin")
                        &&mEditText2.getText().toString().trim().equals("123"))
                {
                    Toast.makeText(MainActivity.this,"Login successfully",Toast.LENGTH_LONG).show();
                    Map<String, Object> map = new HashMap<>();
                    if (mCheckBox1.isChecked()) {
                        map.put("username",mEditText1.getText().toString());
                    }
                    else
                    {
                        map.put("username", "");
                    }
                    map.put("isSaveUsernameChecked", mCheckBox1.isChecked());
                    map.put("isAutoLoginChecked", mCheckBox2.isChecked());
                    mLoginService.SaveSharePreference("login", map);
               }
               else
                {
                    Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
