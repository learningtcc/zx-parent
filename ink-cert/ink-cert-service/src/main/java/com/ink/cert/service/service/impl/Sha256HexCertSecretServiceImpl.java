package com.ink.cert.service.service.impl;

import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.core.po.CertInfo;
import com.ink.cert.service.service.CertSecretService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * sha256加解密方式
 * Created by aiyungui on 2016/6/23.
 */
@Service("certSecretServiceSHA256HEX")
public class Sha256HexCertSecretServiceImpl implements CertSecretService{

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(PfxCertSecretServiceImpl.class);
    /**
     * 数据加密
     *
     * @param certInfo 证书信息
     * @param message  报文
     * @return 加密后的报文
     */
    @Override
    public Object dataEncrypt(CertInfo certInfo, String message) throws Exception {

        try{
            if(StringUtils.isNotBlank(certInfo.getSecretKey()))
                message = message + certInfo.getSecretKey();
            return DigestUtils.sha256Hex(message);
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"pfx加密失败,certInfo:" + certInfo + ",报文：" + message ,e);
            throw (e);
        }

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
        throw (new Exception("Sha256不提供解密实现"));
    }
}
