package com.ink.cert.service.service;

import com.ink.cert.core.po.CertInfo;

/**
 * 证书加解密处理
 * Created by aiyungui on 2016/6/22.
 */
public interface CertSecretService {

    /**
     * 数据加密
     * @param certInfo 证书信息
     * @param message 报文
     * @return 加密后的报文
     */
    Object dataEncrypt(CertInfo certInfo,String message) throws Exception;

    /**
     * 数据解密
     * @param certInfo 证书信息
     * @param message 报文
     * @return 解密后的报文
     */
    Object dataDecrypt(CertInfo certInfo,String message) throws Exception;
}
