package com.ink.cert.core.service;

import com.ink.cert.core.po.CertInfo;

/**
 * 证书文件服务
 * Created by aiyungui on 2016/6/22.
 */
public interface ICertFileService {

    public boolean downLoadCertFile(CertInfo certInfo);
}
