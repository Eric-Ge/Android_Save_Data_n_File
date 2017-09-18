package com.example.geyiyang.sdkdemo.ImageSave;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by geyiyang on 2017/9/16.
 */

public class ImageDownloadfmInternet {
    public String DownloadImg(String sFilename,byte[] sData) {
        String state = Environment.getExternalStorageState();
        File root = Environment.getExternalStorageDirectory();
        FileOutputStream fos = null;
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(root, sFilename);
            try {
                fos = new FileOutputStream(root.getAbsolutePath() + "/" + sFilename);
                fos.write(sData);
                return root.getAbsolutePath() + "/" + sFilename;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }


}

