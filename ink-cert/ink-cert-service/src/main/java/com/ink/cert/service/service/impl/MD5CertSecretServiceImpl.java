package com.ink.cert.service.service.impl;

import com.ink.cert.service.service.CertSecretService;
import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.util.secret.MD5;
import com.ink.cert.core.po.CertInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * md5加解密
 * Created by aiyungui on 2016/6/22.
 */
@Service("certSecretServiceMD5")
public class MD5CertSecretServiceImpl implements CertSecretService {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(MD5CertSecretServiceImpl.class);
    /**
     * 数据加密
     *
     * @param certInfo 证书信息
     * @param message  报文
     * @return 加密后的报文
     */
    @Override
    public Object dataEncrypt(CertInfo certInfo, String message)throws Exception{

        String encryptMsg;
        try {
            String secretKey = certInfo.getSecretKey();

            if (StringUtils.isNotBlank(secretKey)){
                encryptMsg = MD5.md5(message, secretKey);
            }else{
                encryptMsg = MD5.md5(message);
            }
        } catch (Exception e) {
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"md5加密失败,certInfo:" + certInfo + ",报文：" + message ,e);

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
        throw (new Exception("MD5不提供解密实现"));
    }
}
