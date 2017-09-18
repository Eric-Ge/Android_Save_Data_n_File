package com.example.geyiyang.sdkdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.geyiyang.sdkdemo.SDKService.SDKService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 应用打开后自动将输入的字符串保存到SDcardd的pictured中，名为sdkfile.txt
 * 点击按钮后通过异步方式获取PATH指向的图片资源，显示到ImageView并保存到SDcard的picture目录
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button mButton;
    private ImageView mImageView;
    private final String PATH = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SDKService sdkService = new SDKService();
        boolean flag = sdkService.saveFile2SDK("sdkFile.txt", "那就是s青藏s高原应s该乱码s的呀为s什么s没有乱码".getBytes());
        Log.i(TAG, "--->" + flag);
        Log.i(TAG, "--->" + sdkService.readContendFromSDcard("sdkFile.txt"));

        mButton = (Button) findViewById(R.id.button);
        mImageView = (ImageView) findViewById(R.id.imageView);
        //异步任务获取图片并保存到sdcard
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Mytask().execute(PATH);
            }
        });
    }

    public class Mytask extends AsyncTask<String, Integer, byte[]> {
        private static final String TAG = "Mytask";
        @Override
        protected byte[] doInBackground(String... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = null;
            HttpURLConnection httpURLConnection = null;
            try {
                Log.i(TAG, "doInBackground: --->connected to "+params[0]);
                URL url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                Log.i(TAG, "doInBackground: --->ResponseCode"+httpURLConnection.getResponseCode());
                if (httpURLConnection.getResponseCode() == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    Log.i(TAG, "doInBackground: --->resposeCode==200");
                    while ((len = inputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer,0,len);
                    }
                    return byteArrayOutputStream.toByteArray();
//下面这个方式用于读取文本
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                    //字节流转换为字符流，再用bufferreader的每行读取功能
//                    StringBuffer sb = new StringBuffer();
//                    String readline = "";
//                    while ((readline = bufferedReader.readLine()) != null) {
//                        sb.append(readline);
//                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();}
            catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (byteArrayOutputStream != null) {
                    try {
                        inputStream.close();
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                httpURLConnection.disconnect();

//                    bufferedReader.close();

//                    return sb.toString().getBytes();
            }


            return new byte[0];
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);
            SDKService sdkService = new SDKService();
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            mImageView.setImageBitmap(bitmap);
            boolean flag = sdkService.saveFile2SDK("baidu.png", bytes);
            Log.i(TAG, "onPostExecute: --->write sd card："+flag);
        }

    }

}
