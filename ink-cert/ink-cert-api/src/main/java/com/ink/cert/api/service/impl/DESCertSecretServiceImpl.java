package com.ink.cert.api.service.impl;

import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.SecretConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.module.CertInfo;
import com.ink.cert.api.service.CertSecretService;
import com.ink.cert.api.util.secret.DES;

import org.springframework.stereotype.Service;

/**
 * DES加解密
 * Created by aiyungui on 2016/6/24.
 */
@Service("localCertSecretServiceDES")
public class DESCertSecretServiceImpl implements CertSecretService {

     private YinkerLogger yinkerLogger = YinkerLogger.getLogger(DESCertSecretServiceImpl.class);
    /**
     * 数据加密
     *
     * @param certInfo 证书信息
     * @param message  报文
     * @return 加密后的报文
     */
    @Override
    public Object dataEncrypt(CertInfo certInfo, String message) throws Exception {

        String resMsg;
        try{
            resMsg = DES.encrypt(message, certInfo.getSecretKey(), SecretConstant.ENCODE);
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"DES加密失败,certInfo:" + certInfo + ",报文：" + message ,e);
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
            resMsg = DES.decrypt(message, certInfo.getSecretKey(), SecretConstant.ENCODE);
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"DES解失败,certInfo:" + certInfo + ",报文：" + message ,e);
            throw(e);
        }
        return resMsg;
    }
}
