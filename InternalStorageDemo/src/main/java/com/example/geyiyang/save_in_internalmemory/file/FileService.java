package com.example.geyiyang.save_in_internalmemory.file;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by geyiyang on 2017/9/15.
 */

public class FileService {
    private Context mContext;
    public FileService(Context context) {
        mContext = context;
    }

    /**
     * 读取内部存储的文件
     * @param sFilename 不包含分隔符的文件名称
     * @return 读取的文件String格式
     */

    public String readContentfromFile(String sFilename) {
        FileInputStream inputStream = null;
        File file=mContext.getFilesDir();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            inputStream = new FileInputStream(file + "/txt" + "/" + sFilename);
//            inputStream = mContext.openFileInput(sFilename)
            int len=-1;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer,0,len);
            }
            return new String(outputStream.toByteArray());
        }catch(Exception e){
        e.printStackTrace();
        }
        return "";
    }

    /**
     * 存取到内部储存
     * @param sFilename 不包含分隔符的文件名称
     * @param sData 存取的数据
     * @return 存取成功标志符
     */
    public boolean SaveContextToFile(String sFilename,byte[] sData) {
        FileOutputStream outputStream=null;
        Log.i("MainActivity", "---> "+mContext.getFilesDir().getAbsolutePath());
        File file=mContext.getFilesDir();
        boolean flag=false;
        try {
            File folder = new File(file.getAbsolutePath()  + "/txt");//新建文件夹管理目录
            if (!folder.exists()) {
                folder.mkdir();
            }
            outputStream = new FileOutputStream(folder.getAbsolutePath() + "/" + sFilename);
//            outputStream=mContext.openFileOutput(sFilename, Context.MODE_PRIVATE);
            outputStream.write(sData,0,sData.length);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(outputStream!=null)
                try{
                    outputStream.close();
                }catch(Exception e){
                e.printStackTrace();
                }
        }

        return flag;
    }

    /**
     * 将文件存储到Cashe
     * @param sFilename 不包含分隔符的文件名称
     * @param data byte数据数组
     * @return flag 是否保存成功
     */

    public boolean SaveContextToCache(String sFilename,byte[] data) {
        boolean flag=false;
        File file = mContext.getCacheDir();
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file.getAbsolutePath() + "/" + sFilename);
            fos.write(data,0,data.length);
            flag=true;
        }catch(Exception e){
        e.printStackTrace();
        }
        return flag;
    }

}
