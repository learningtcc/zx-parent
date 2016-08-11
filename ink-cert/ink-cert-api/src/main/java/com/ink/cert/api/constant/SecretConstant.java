package com.ink.cert.api.constant;

/**
 * 加解密常量
 * Created by aiyungui on 2016/6/23.
 */
public class SecretConstant {


    /**RSA*/
    public final static String RSA_CHIPER = "RSA/ECB/PKCS1Padding";
    /** 1024bit 加密块 大小 */
    public final static int ENCRYPT_KEYSIZE = 117;
    /** 1024bit 解密块 大小 */
    public final static int DECRYPT_KEYSIZE = 128;

    public final static String KEY_X509 = "X509";
    public final static String KEY_PKCS12 = "PKCS12";

    /** 编码 */
    public final static String ENCODE = "UTF-8";
}
