package com.example.geyiyang.save_in_internalmemory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geyiyang.save_in_internalmemory.file.FileService;

public class MainActivity extends AppCompatActivity {
    private Button mButton1,mButton2,mButton3;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.editText);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileService mService = new FileService(MainActivity.this);
                Boolean flag=mService.SaveContextToCache("Cache.txt", mEditText.getText().toString().getBytes());
                if (flag) {
                    Toast.makeText(MainActivity.this, "File has been saved to" + MainActivity.this.getCacheDir().getAbsolutePath(),
                            Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "File save failed",
                        Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileService mService = new FileService(MainActivity.this);
                Boolean flag=mService.SaveContextToFile("file.txt", mEditText.getText().toString().getBytes());
                if (flag) {
                    Toast.makeText(MainActivity.this, "File has been saved to" + MainActivity.this.getFilesDir().getAbsolutePath(),
                            Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "File save failed",
                        Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileService mService = new FileService(MainActivity.this);
                mEditText.setText(mService.readContentfromFile("file.txt"));
            }
        });

    }
}
