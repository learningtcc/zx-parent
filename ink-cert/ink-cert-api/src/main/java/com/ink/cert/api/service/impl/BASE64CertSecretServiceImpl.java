package com.ink.cert.api.service.impl;

import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.module.CertInfo;
import com.ink.cert.api.service.CertSecretService;
import com.ink.cert.api.util.secret.BASE64;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * md5加解密
 * Created by aiyungui on 2016/6/22.
 */
@Service("localCertSecretServiceBASE64")
public class BASE64CertSecretServiceImpl implements CertSecretService {
    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(BASE64CertSecretServiceImpl.class);
    /**
     * 数据加密
     *
     * @param certInfo 证书信息
     * @param message  报文
     * @return 加密后的报文
     */
    @Override
    public Object dataEncrypt(CertInfo certInfo, String message)throws Exception {

        String encryptMsg;
        try{
            encryptMsg =  BASE64.encrypt(message);
        }catch(Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"BASE64加密失败,certInfo:" + certInfo + ",报文：" + message ,e);
            throw (e);
        }

        return encryptMsg;
    }

    /**
     * 数据解密
     *md5为不可逆加密方式，因此如有调用此解密接口则返回原报文
     * @param certInfo 证书信息
     * @param message  报文
     * @return 解密后的报文
     */
    @Override
    public Object dataDecrypt(CertInfo certInfo, String message) throws Exception{

        String decryptMsg;
        try {
            decryptMsg = BASE64.decrypt(message);
        } catch (IOException e) {
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"BASE64解密失败,certInfo:" + certInfo + ",报文：" + message ,e);
            throw (e);
        }
        return decryptMsg;
    }
}
