package com.ink.channel.core.jdPay.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * Created by Lenovo on 2016/7/5.
 */
public class CallBackUtil {
    /**
     * 京东报文Base64解密
     * @param str
     * @return
     * @throws Exception
     */
    public static String base64Decode(String str) throws Exception{
        com.sun.org.apache.xml.internal.security.Init.init();
        return new String(Base64.decode(str));
    }
}
