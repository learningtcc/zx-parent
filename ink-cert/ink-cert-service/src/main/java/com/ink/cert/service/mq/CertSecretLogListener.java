package com.ink.cert.service.mq;

import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.core.po.CertLog;
import com.ink.cert.core.service.ICertLogManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 证书加减密日志记录MQ
 * Created by aiyungui on 2016/6/21.
 */
public class CertSecretLogListener {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(CertSecretLogListener.class);
    @Autowired
    private ICertLogManager certLogManager;

    /**
     * 记录日志
     * @param certLog
     */
    public void operateLog(CertLog certLog){

        try{
            certLogManager.save(certLog);
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_SECRET_LOG_CODE,
                    "记录证书加解密日志失败：" + certLog,e,null);
        }

    }

}
