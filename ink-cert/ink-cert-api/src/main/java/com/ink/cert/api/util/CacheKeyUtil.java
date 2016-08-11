package com.ink.cert.api.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存证书私钥、公钥信息
 * Created by aiyungui on 2016/6/23.
 */
public class CacheKeyUtil {

    private static Map<String,Object> publicKeyMap = new HashMap<String,Object>();
    private static Map<String,Object> privateKeyMap = new HashMap<String,Object>();

    public static void putPublicKey(String key,Object value){
        publicKeyMap.put(key,value);
    }

    public static Object getPublicKey(String key){
       return publicKeyMap.get(key);
    }

    public static void putPrivateKey(String key,Object value){
        privateKeyMap.put(key,value);
    }

    public static Object getPrivateKey(String key){
        return privateKeyMap.get(key);
    }

    public static Map<String, Object> getPublicKeyMap() {
        return publicKeyMap;
    }

    public static Map<String, Object> getPrivateKeyMap() {
        return privateKeyMap;
    }
}
