package com.ink.cert.api.util;


import com.ink.cert.api.module.CertInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 证书中心工具类
 * Created by aiyungui on 2016/6/23.
 */
public class CertUtil {

    /**
     * 获取证书缓存code
     * @param merchantCode
     * @param certCode
     * @return
     */
    public static String getCertCacheKey(String merchantCode,String certCode){
        if(StringUtils.isBlank(merchantCode) || StringUtils.isBlank(certCode)){
            return  "";
        }
        return merchantCode + "-" + certCode;
    }

    /**
     * 获取证书本地路径
     * @param dirPath
     * @param fileName
     * @return
     */
    public static String getFilePath(String dirPath,String fileName){
        if(StringUtils.isBlank(dirPath) || StringUtils.isBlank(fileName)){
            return  "";
        }
        return dirPath + File.separator + fileName;
    }

    /**
     * 验证文件是否存在
     * @param filePath
     */
    public static boolean isFileExists(String filePath){
       File file = new File(filePath);
        return file.exists();
    }

    /**
     * 获取证书文件名称
     * @param certInfo
     * @return
     */
    public static String getCertFileName(CertInfo certInfo){

        if (certInfo == null){
            return "";
        }

        return certInfo.getMerchatCode() + "_" + certInfo.getCertCode() + "_" + certInfo.getFileName();
    }

    /**
     * 截取数组
     * @param array
     * @param startIndexInclusive
     * @param endIndexExclusive
     * @return
     */
    public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return new byte[0];
        }

        byte[] subarray = new byte[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * 把数组1与数组2进行组合
     * @param array1
     * @param array2
     * @return
     */
    public static byte[] addAll(byte[] array1, byte[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * 克隆byte数组
     * @param array
     * @return
     */
    public static byte[] clone(byte[] array) {
        if (array == null) {
            return null;
        }
        return (byte[]) array.clone();
    }
}
