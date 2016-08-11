package com.ink.cert.api.service.impl;

import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.CertConfigConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.module.CertInfo;
import com.ink.cert.api.service.CertSecretService;
import com.ink.cert.api.util.CertUtil;
import com.ink.cert.api.util.RsaKeyReadUtil;
import com.ink.cert.api.util.secret.RSA;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

/**
 * CER证书RSA非对称加密算法实现
 * Created by aiyungui on 2016/6/23.
 */
@Service("localCertSecretServicePFX")
public class PfxCertSecretServiceImpl implements CertSecretService {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(PfxCertSecretServiceImpl.class);
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
    public Object dataEncrypt(CertInfo certInfo, String message) throws Exception{

        String resMsg;
        try{
            String storePath = customizedPropertyConfigurer.getProperty(CertConfigConstant.LOCAL_CERT_STORE_PATH);
            String certPath = CertUtil.getFilePath(storePath, CertUtil.getCertFileName(certInfo));

            PrivateKey privateKey = RsaKeyReadUtil.getPrivateKey(certPath, certInfo.getMerchatCode(),certInfo.getCertCode(),certInfo.getSecretKey());
            resMsg = RSA.encryptByPrivateKey(message, privateKey);
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"pfx加密失败,certInfo:" + certInfo + ",报文：" + message ,e);
            throw(e);
        }

        return resMsg;
    }

    /**
     * 数据解密
     *
     * @param certInfo 证书信息
     * @param message  报文
     * @return 解密后的报文
     */
    @Override
    public Object dataDecrypt(CertInfo certInfo, String message) throws Exception {
        String resMsg;
        try{
            String storePath = customizedPropertyConfigurer.getProperty(CertConfigConstant.LOCAL_CERT_STORE_PATH);
            String certPath = CertUtil.getFilePath(storePath,CertUtil.getCertFileName(certInfo));

            PrivateKey privateKey = RsaKeyReadUtil.getPrivateKey(certPath,certInfo.getMerchatCode(),certInfo.getCertCode(),certInfo.getSecretKey());
            resMsg = RSA.decryptByPrivateKey(message, privateKey);
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"pfx解密失败,certInfo:" + certInfo + ",报文：" + message ,e);
            throw(e);
        }

        return resMsg;
    }

}
