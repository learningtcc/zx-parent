package com.ink.cert.core.mq;

import com.ink.cert.core.po.CertInfo;
import com.ink.cert.core.service.ICertFileService;
import com.ink.base.log.util.YinkerLogger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 下载证书文件
 * Created by aiyungui on 2016/6/21.
 */
public class ServerDownLoadCertListener {

    @Autowired
   private ICertFileService certFileService;

    /**
     * 下载证书文件
     * @param certInfo
     */
    public void downLoadFile(CertInfo certInfo){

        try{
            certFileService.downLoadCertFile(certInfo);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
