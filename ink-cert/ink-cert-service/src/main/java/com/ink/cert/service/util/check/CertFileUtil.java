package com.ink.cert.service.util.check;

import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.CertConfigConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.util.CertUtil;
import com.ink.cert.core.po.CertInfo;
import com.ink.cert.core.service.ICertFileService;
import com.ink.cert.core.util.CoreCertUtil;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 证书文件工具类
 * Created by aiyungui on 2016/6/22.
 */
@Component("certFileUtil")
public class CertFileUtil {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(CertFileUtil.class);
    @Autowired
    private CustomizedPropertyConfigurer customizedPropertyConfigurer;
    @Autowired
    private ICertFileService certFileService;

    /**
     * 查询文件是否存在
     *      1、检查本地是否存在
     *      2、不存在则尝试从tfs上进行下载
     * @param certInfo
     * @return
     */
    public boolean findCertFile(CertInfo certInfo){
        boolean result = false;
        try{
            String storePath = customizedPropertyConfigurer.getProperty(CertConfigConstant.CERT_STORE_PATH);
            if(isFileExists(storePath, CoreCertUtil.getCertFileName(certInfo))){
                result = true;
            }else {
                //不存在尝试从tfs上下载证书文件
                certFileService.downLoadCertFile(certInfo);
                result = true;
            }
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE, "检查证书文件异常：" + e.getMessage(), e);
            result = false;
        }

        return result;

    }

    /**
     * 判断文件是否存在
     * @param storePath
     * @param fileName
     * @return
     */
    private boolean isFileExists(String storePath, String fileName) {

        String filePath = CertUtil.getFilePath(storePath, fileName);
        File file = new File(filePath);
        return file.exists();
    }


}
