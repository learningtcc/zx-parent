package com.ink.cert.core.util;

import com.ink.cert.core.po.CertInfo;

/**
 * 证书相关工具类
 * Created by aiyungui on 2016/7/6.
 */
public class CoreCertUtil {


    /**
     * 获取证书文件名称
     * @param certInfo
     * @return
     */
    public static String getCertFileName(CertInfo certInfo){

        if (certInfo == null){
            return "";
        }

        return certInfo.getMerchatCode() + "_" + certInfo.getCertCode() + "_" + certInfo.getFileName();
    }
}
