package com.ink.channel.core.bestpay;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.PropertyResourceBundle;

public class RsaUtil {
    public static ServerCert serverCert = new ServerCert();
    public static PrivateKey key;
    public static String cert;

    public static PropertyResourceBundle props = null;
    
    /**
     * 获取私钥
     * @return
     * @throws GeneralSecurityException
     */
    public static PrivateKey getPrivateKey()
            throws GeneralSecurityException {
        if (key == null) {
            key = serverCert.getServerPrivateKey();
        }
        return key;
    }

    /**
     * 获取公钥
     * @return
     * @throws GeneralSecurityException
     */
    public static String getPublicKey()
            throws GeneralSecurityException {
        if ((cert == null) || ("".equals(cert))) {
            X509Certificate x509Certificate = null;
            x509Certificate = serverCert.getServerCert();
            cert = CertUtils.certToBase64Str(x509Certificate);
        }
        return cert;
    }

    /**
     * 加签
     * @param plainText
     * @return
     * @throws UnsupportedEncodingException
     * @throws GeneralSecurityException
     */
    public static String sign(String plainText)
            throws UnsupportedEncodingException, GeneralSecurityException {
        byte[] bytes = RsaCipher.sign(plainText.getBytes("UTF-8"), getPrivateKey());
        return Base64Utils.encode(bytes);
    }

    /**
     * 初始化证书信息
     */
    static {
        // 加载提现相关配置
        InputStream is = null;
        // 配置文件路径
        String BESTPAY_FILE_PATH = "bestpay/bestpay.properties";
        try {
            is = RsaUtil.class.getClassLoader().getResourceAsStream(BESTPAY_FILE_PATH);
            props = new PropertyResourceBundle(is);
            
            serverCert.setCertAlias(props.getString("fas.certAlias"));
            serverCert.setKeyStorePwd(props.getString("fas.keyStorePwd"));
            serverCert.setKeyStore(props.getString("fas.keyStore"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}