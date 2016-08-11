package com.ink.cert.service.service.impl;

import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.CertConfigConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.util.CertUtil;
import com.ink.cert.api.util.RsaKeyReadUtil;
import com.ink.cert.api.util.secret.RSA;
import com.ink.cert.core.po.CertInfo;
import com.ink.cert.core.util.CoreCertUtil;
import com.ink.cert.service.service.CertSecretService;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

/**
 * CER证书RSA非对称加密算法实现
 * Created by aiyungui on 2016/6/23.
 */
@Service("certSecretServiceCER")
public class CerCertSecretServiceImpl implements CertSecretService{

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(CerCertSecretServiceImpl.class);
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
            String storePath = customizedPropertyConfigurer.getProperty(CertConfigConstant.CERT_STORE_PATH);
            String certPath = CertUtil.getFilePath(storePath, CoreCertUtil.getCertFileName(certInfo));

            PublicKey publicKey = RsaKeyReadUtil.getPublicKey(certPath, certInfo.getMerchatCode(),certInfo.getCertCode());
            resMsg = RSA.encryptByPublicKey(message, publicKey);
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"cer加密失败,certInfo:" + certInfo + ",报文：" + message ,e);
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
            String storePath = customizedPropertyConfigurer.getProperty(CertConfigConstant.CERT_STORE_PATH);
            String certPath = CertUtil.getFilePath(storePath, CoreCertUtil.getCertFileName(certInfo));

            PublicKey publicKey = RsaKeyReadUtil.getPublicKey(certPath, certInfo.getMerchatCode(),certInfo.getCertCode());
            resMsg = RSA.decryptByPublicKey(message, publicKey);
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"cer解密失败,certInfo:" + certInfo + ",报文：" + message ,e);
            throw(e);
        }

        return resMsg;
    }

}
