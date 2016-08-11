package com.ink.cert.core.service.impl;

import com.ink.cert.core.po.CertInfo;
import com.ink.cert.core.service.ICertFileService;
import com.ink.cert.core.util.CoreCertUtil;
import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.CertConfigConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.util.CertUtil;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import com.ink.tfs.client.TFSOperator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 证书文件服务实现类
 * Created by aiyungui on 2016/6/22.
 */
@Service("certFileService")
public class CertFileServiceImpl implements ICertFileService {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(CertFileServiceImpl.class);
    @Autowired
    private TFSOperator tfsOperator;
    @Autowired
    private CustomizedPropertyConfigurer customizedPropertyConfigurer;

    /**
     * 下载证书文件
     * @param certInfo
     * @return
     */
    @Override
    public boolean downLoadCertFile(CertInfo certInfo) {
        boolean result = false;
        //获取配置信息
        String storePath = customizedPropertyConfigurer.getProperty(CertConfigConstant.CERT_STORE_PATH);
        try{
            //从tfs上下载文件
            byte[] content =  tfsOperator.getTfsByteById(certInfo.getCertId());
            //存储至本地
            storeLocal(storePath,content,certInfo);
            result = true;
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_CERT_MAINTAIN_CODE,"下载证书失败，" + ",证书文件名："+certInfo.getFileName(),e);
        }

        return result;
    }

    /**
     * 把证书文件存储至本地
     * @param storePath
     * @param content
     * @param certInfo
     */
    private void storeLocal(String storePath, byte[] content, CertInfo certInfo)throws Exception {

        try{
            String filePath = CertUtil.getFilePath(storePath, CoreCertUtil.getCertFileName(certInfo));
            FileUtils.writeByteArrayToFile(new File(filePath),content);
        }catch (Exception e){
            throw (e);
        }
    }
}
