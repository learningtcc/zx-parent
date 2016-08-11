package com.ink.cert.api.service.impl;

import com.umpay.api.paygate.v40.Mer2Plat_v40;
import com.umpay.api.paygate.v40.Plat2Mer_v40;
import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.CertConfigConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.module.CertInfo;
import com.ink.cert.api.service.CertSecretService;
import com.ink.cert.api.util.CertUtil;
import com.ink.cert.api.util.check.CertCacheUtil;
import com.ink.cert.api.util.secret.Ldys;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;

/**
 * 联动优势p8加密
 * Created by aiyungui on 2016/7/1.
 */
@Service("localCertSecretServiceP8")
public class P8CertSecretServiceImpl implements CertSecretService {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(P8CertSecretServiceImpl.class);
    @Autowired
    private CustomizedPropertyConfigurer customizedPropertyConfigurer;
    @Autowired
    private CertCacheUtil certCacheUtil;
    /**
     * 数据加密
     *
     * @param certInfo 证书信息
     * @param message  报文 json格式  包含两部分：1报文参数，2证书相关信息
     * @return 加密后的报文
     */
    @Override
    public Object dataEncrypt(CertInfo certInfo, String message) throws Exception {
        Object resultObject;
        try{
            //转换参数信息
            Map<String,Map<String,Object>> encryptParamMap = Ldys.getSecretParam(message);
            Map<String,Object> msgMap = encryptParamMap.get("msgMap");
            //设置配置文件
            Map<String,Object> certInfoMap = encryptParamMap.get("certInfoMap");
            setPropertiesFile(certInfo,certInfoMap,msgMap);
            //加密
            String encryptFlag = String.valueOf(certInfoMap.get("encryptFlag"));
            if ("makeReqDataByPost".equalsIgnoreCase(encryptFlag)){
                resultObject = Mer2Plat_v40.makeReqDataByPost(msgMap);
            }else if("merNotifyResData".equalsIgnoreCase(encryptFlag)){
                resultObject = Mer2Plat_v40.merNotifyResData(msgMap);
            }else{
                throw new Exception("加密类型不正确");
            }
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"ldys加密失败,certInfo:" + certInfo + ",报文：" + message ,e);
            throw (e);
        }

        return resultObject;
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
        Map data;
        try{
            //转换参数信息
            Map<String,Map<String,Object>> encryptParamMap = Ldys.getSecretParam(message);
            Map<String,Object> certInfoMap = encryptParamMap.get("certInfoMap");
            Map<String,Object> msgMap = encryptParamMap.get("msgMap");
            //设置配置文件
            setPropertiesFile(certInfo,certInfoMap,msgMap);
            //解密
            String decryptFlag = String.valueOf(certInfoMap.get("decryptFlag"));
            if ("resData".equalsIgnoreCase(decryptFlag)){
                data = Plat2Mer_v40.getResData(String.valueOf(msgMap.get("message")));
            }else if("resDataByMeta".equalsIgnoreCase(decryptFlag)){
                data = Plat2Mer_v40.getResDataByMeta(String.valueOf(msgMap.get("message")));
            }else if("platNotifyData".equalsIgnoreCase(decryptFlag)){
                data = Plat2Mer_v40.getPlatNotifyData(msgMap.get("message"));
            }else{
                throw new Exception("解密类型不正确");
            }

        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,"ldys加密失败,certInfo:" + certInfo + ",报文：" + message ,e);
            throw (e);
        }

        return data;
    }

    /**
     * 设置配置文件
     *  |-检查证书文件是否存在
     *  |-检查配置文件是否存在，不存在则创建一个联动优势的配置文件
     * @param certInfoMap
     */
    private void setPropertiesFile(CertInfo certInfo,Map<String, Object> certInfoMap,Map<String,Object> msgMap) throws Exception{

        InputStream in = P8CertSecretServiceImpl.class.getClassLoader().getResourceAsStream("SignVerProp.properties");
        String storePath = customizedPropertyConfigurer.getProperty(CertConfigConstant.LOCAL_CERT_STORE_PATH);
        if(in == null) {//没有找到证书文件，则创建一个
            //检查crt证书是否存在
            String merchantCode = (String) certInfoMap.get("crtMerchantCode");
            String certCode = (String) certInfoMap.get("crtCertCode");
            String propertiesPath = customizedPropertyConfigurer.getProperty(CertConfigConstant.PROPERTIES_STORE_PATH);
            CertInfo crtCertInfo = certCacheUtil.getCertInfo(merchantCode,certCode,propertiesPath);

            if(crtCertInfo == null || null == crtCertInfo.getMerchatCode()){
                throw(new Exception("没有找到crt证书信息"));
            }
            String certPath = CertUtil.getFilePath(storePath, CertUtil.getCertFileName(certInfo));

            if (!CertUtil.isFileExists(certPath)){
                throw(new Exception("找不到crt证书文件"));
            }

            certInfoMap.put("certPath",certPath);
            certInfoMap.put("p8FilePath",CertUtil.getFilePath(storePath, certInfo.getFileName()));
            Ldys.createProperties(certInfoMap,msgMap);
        }else{
            certInfoMap.put("p8FilePath", CertUtil.getFilePath(storePath, certInfo.getFileName()));
            Ldys.addPropertiesForP8cert(certInfoMap, msgMap);
        }
    }
}
