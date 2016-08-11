package com.ink.tfs.client;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author haoyunfeng
 * @date 2016/5/17
 */
public class FileUtils {

    /**
     * the traditional io way
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if(!f.exists()){
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());
        BufferedInputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while(-1 != (len = in.read(buffer,0,buf_size))){
                bos.write(buffer,0,len);
            }
            return bos.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                in.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }


    /**
     * NIO way
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArrayNIO(String filename)throws IOException{

        File f = new File(filename);
        if(!f.exists()){
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try{
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)channel.size());
            while((channel.read(byteBuffer)) > 0){
                // do nothing
//              System.out.println("reading");
            }
            return byteBuffer.array();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                channel.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            try{
                fs.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Mapped File  way
     * MappedByteBuffer 可以在处理大文件时，提升性能
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArrayLarge(String filename)throws IOException{

        FileChannel fc = null;
        try{
            fc = new RandomAccessFile(filename,"r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int)fc.size()];
            if (byteBuffer.remaining() > 0) {
//              System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                fc.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从路径中获取文件名
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath){
        String fileName = filePath;
        String temp[] = filePath.replaceAll("\\\\","/").split("/");
        if (temp.length > 1) {
            fileName = temp[temp.length - 1];
        }
        return fileName;
    }

    /**
     * 获取文件扩展名
     *
     * @param filename
     * @date 2016.5.5
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取文件名（不包含扩展名）
     * @param filename
     * @return
     */
    public static String getPrefixName(String filename){
        return filename.substring(0,filename.lastIndexOf("."));
    }

    /**
     * 获取加时间戳的文件名
     * @param filename
     * @return
     */
    public static String getNameByTime(String filename){
        return getPrefixName(filename)+"-"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"."+getExtensionName(filename);
    }
}