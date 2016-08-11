package com.ink.cert.api.service;

import com.ink.base.utils.context.SpringApplicationContext;
import com.ink.cert.api.module.CertInfo;

/**
 * 证书加解密服务工厂类
 * Created by aiyungui on 2016/6/22.
 */
public class CertSecretServiceFactory {

    /**
     * 获取证书加解密服务
     * @param certInfo
     * @return
     */
    public static CertSecretService getCertSecretService(CertInfo certInfo){

        CertSecretService certSecretService = null;
        String serviceName;
        try{
            if ("0".equals(certInfo.getEndecryType())){//证书解密
                serviceName = "localCertSecretService" + certInfo.getCertType().toUpperCase();
            }else{//密钥解密
                serviceName = "localCertSecretService" + certInfo.getArithmeticType().toUpperCase();
            }
            certSecretService = (CertSecretService) SpringApplicationContext.getBean(serviceName);

        }catch (Exception e){
            e.printStackTrace();
        }

        return certSecretService;
    }
}
