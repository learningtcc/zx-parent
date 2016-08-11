package com.ink.cert.api.dubbo;

import com.ink.cert.api.module.MsgOutput;

/**
 * 数据加解密接口
 * Created by aiyungui on 2016/6/22.
 */
public interface DataSecretService {



    /**
     * 数据加密
     * @param merchantCode 商户编号
     * @param certCode 证书编号
     * @param message 报文
     * @return 消息处理结果
     */
    MsgOutput dataEncrypt(String merchantCode,String certCode,String message);

    /**
     * 数据解密
     * @param merchantCode 商户编号
     * @param certCode 证书编号
     * @param message 报文
     * @return 消息处理结果
     */
    MsgOutput dataDecrypt(String merchantCode,String certCode,String message);
}
