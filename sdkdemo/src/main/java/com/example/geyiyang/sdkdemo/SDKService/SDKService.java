package com.example.geyiyang.sdkdemo.SDKService;

import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by geyiyang on 2017/9/16.
 */

public class SDKService {
    public boolean saveFile2SDK(String sFilename, byte[] sData) {
        boolean flag=false;
        String state = Environment.getExternalStorageState();

        FileOutputStream  fos = null;
        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);//存储到picture目录下
//        File root=Environment.getExternalStorageDirectory();
        Log.i("SDKCardSave", "---> "+root.getAbsolutePath()+"--->"+state);
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(root, sFilename);
            try{
                fos = new FileOutputStream(root.getAbsolutePath()+"/"+sFilename);
                fos.write(sData);
                flag=true;
                Log.i("SDKCardSave2", "---> "+root.getAbsolutePath()+"--->"+state.toString());
            }catch(Exception e){
            e.printStackTrace();
            }finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        return flag;

    }

    public String readContendFromSDcard(String sFilename) {
        String result = "";
        String state = Environment.getExternalStorageState();
        FileInputStream fin=null;
        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(root.getAbsolutePath() + "/" + sFilename);
            byte[] buffer = new byte[16];
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int len=-1;
            if (file.exists()) {
                try
                {
                    fin = new FileInputStream(file);
                    while ((len = fin.read(buffer)) != -1) {
                        bo.write(buffer,0,len);
                    }
                    return new String(bo.toByteArray());
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    if (fin!=null) {
                        try {
                            fin.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            else
                return "";
        }
        return "";
    }
}
