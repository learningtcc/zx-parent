package com.ink.cert.api.util.secret;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * base64加减密
 * Created by aiyungui on 2016/6/23.
 */
public class BASE64 {

    /**
     * 加密
     * @param message
     * @return
     * @throws Exception
     */
    public static String encrypt(String message)throws IOException{

        return new BASE64Encoder().encode(message.getBytes("UTF-8"));

    }

    /**
     * 解密
     * @param message
     * @return
     * @throws Exception
     */
    public static String decrypt(String message)throws IOException {

        return new String(new BASE64Decoder().decodeBuffer(message), "UTF-8");

    }
}
