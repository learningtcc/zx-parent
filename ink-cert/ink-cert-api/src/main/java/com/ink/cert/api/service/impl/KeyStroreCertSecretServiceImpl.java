package com.ink.cert.api.service.impl;

import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.CertConfigConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.module.CertInfo;
import com.ink.cert.api.service.CertSecretService;
import com.ink.cert.api.util.CertUtil;
import com.ink.cert.api.util.secret.KeyStoreCert;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * keystore 类型加解密
 * Created by aiyungui on 2016/6/23.
 */
@Service("localCertSecretServiceKEYSTORE")
public class KeyStroreCertSecretServiceImpl implements CertSecretService {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(KeyStroreCertSecretServiceImpl.class);
    @Autowired
    private CustomizedPropertyConfigurer customizedPropertyConfigurer;
    /**
     * 数据加密
     *
     * @param certInfo 证书信息
     * @param message  报文
     * @return 加密后的报文
     */
    @Override
    public Object dataEncrypt(CertInfo certInfo, String message) throws Exception {

        SSLConnectionSocketFactory sslsf = null;
        try{
            String storePath = customizedPropertyConfigurer.getProperty(CertConfigConstant.LOCAL_CERT_STORE_PATH);
            String certPath = CertUtil.getFilePath(storePath, CertUtil.getCertFileName(certInfo));

            sslsf = KeyStoreCert.getSSLConnectionSocketFactory(certInfo.getSecretKey(),certPath);
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"keyStore加密失败,certInfo:" + certInfo + ",报文：" + message ,e);
            throw (e);
        }

        return sslsf;
    }

    /**
     * 数据解密
     *不做处理
     * @param certInfo 证书信息
     * @param message  报文
     * @return 解密后的报文
     */
    @Override
    public Object dataDecrypt(CertInfo certInfo, String message) throws Exception {
        throw (new Exception("keyStore不提供解密实现"));
    }
}
