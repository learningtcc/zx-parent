package com.ink.cert.api.util.secret;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * keyStore证书加解密
 * Created by aiyungui on 2016/7/5.
 */
public class KeyStoreCert {

    /**
     * 加密
     * @param secretKey
     * @param certPath
     * @return
     * @throws Exception
     */
    public static SSLConnectionSocketFactory getSSLConnectionSocketFactory(String secretKey,String certPath) throws Exception{

        SSLConnectionSocketFactory sslsf = null;
        try{
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new FileInputStream(certPath), secretKey.toCharArray());
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(keyStore, new TrustSelfSignedStrategy()).build();
            sslsf = new SSLConnectionSocketFactory( sslcontext);
        }catch (Exception e){
            throw (e);
        }finally {

        }

        return sslsf;
    }
}
