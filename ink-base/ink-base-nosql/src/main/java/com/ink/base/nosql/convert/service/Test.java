package com.ink.base.nosql.convert.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * User: kingstar
 * Date: 16-3-16
 * Time: 下午12:08
 */
public class Test {

    public static void main (String adf[]){
        Test test=new Test();
       // test.saveFile();
    }


    private File saveFile(File savedir, String fileName, File files)
            throws Exception {
        if (!savedir.exists())
            savedir.mkdirs();// 如果目录不存在就创建
        File file = new File(savedir, fileName);
        FileOutputStream fileoutstream = new FileOutputStream(file);
        FileInputStream input = new FileInputStream(files);
        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = input.read(bytes)) != -1) {
            fileoutstream.write(bytes, 0, length);
        }
        fileoutstream.close();
        input.close();
        return file;
    }
}
