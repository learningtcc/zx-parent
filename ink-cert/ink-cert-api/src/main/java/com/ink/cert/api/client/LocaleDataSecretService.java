package com.ink.cert.api.client;

import com.ink.base.log.util.LogConst;
import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.CertConfigConstant;
import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.module.CertInfo;
import com.ink.cert.api.module.CertLog;
import com.ink.cert.api.module.MsgOutput;
import com.ink.cert.api.service.CertSecretService;
import com.ink.cert.api.service.CertSecretServiceFactory;
import com.ink.cert.api.util.CertUtil;
import com.ink.cert.api.util.check.CertCacheUtil;
import com.ink.cert.api.util.check.MsgCheck;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * 本地数据加解密方式
 * Created by aiyungui on 2016/7/5.
 */
@Service("localeDataSecretService")
class LocaleDataSecretService {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(LocaleDataSecretService.class);
    @Autowired
    private CustomizedPropertyConfigurer customizedPropertyConfigurer;
    @Autowired
    private CertCacheUtil certPropertiesCacheUtil;
    /**
     * 证书加密
     * @param merchantCode
     * @param certCode
     * @param message
     * @return
     */
    public MsgOutput dataEncrypt(String merchantCode, String certCode, String message) {
        //初步参数验证
        MsgOutput msgOutput = null;
        CertInfo certInfo = null;
        try{
            String propertiesPath = customizedPropertyConfigurer.getProperty(CertConfigConstant.PROPERTIES_STORE_PATH);
            certInfo = certPropertiesCacheUtil.getCertInfo(merchantCode, certCode, propertiesPath);
            msgOutput = checkMsg(merchantCode, certCode,certInfo);
            if (null == msgOutput.getResultCode()){
                CertSecretService certSecretService = CertSecretServiceFactory.getCertSecretService(certInfo);
                Object responseMsg = certSecretService.dataEncrypt(certInfo,message);
                msgOutput.setResultCode(ResultConstant.EXECUTE_SUCCESS);
                msgOutput.setMessage(responseMsg);
            }

        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE,
                    e.getMessage(),e,null);
            if (null == msgOutput){
                msgOutput =  new MsgOutput();
            }
            msgOutput.setResultCode(ResultConstant.EXECUTE_FAILURE);
            msgOutput.setResultMsg(e.getMessage());
        }

        saveSecretLog(certInfo,message,"加密",msgOutput);
        return msgOutput;
    }

    /**
     * 证书解密
     * @param merchantCode
     * @param certCode
     * @param message
     * @return
     */
    public MsgOutput dataDecrypt(String merchantCode, String certCode, String message) {

        MsgOutput msgOutput= null;
        CertInfo certInfo = null;
        try{
            String propertiesPath = customizedPropertyConfigurer.getProperty(CertConfigConstant.PROPERTIES_STORE_PATH);
            certInfo = certPropertiesCacheUtil.getCertInfo(merchantCode, certCode, propertiesPath);
            //初步参数验证
            msgOutput = checkMsg(merchantCode, certCode,certInfo);
            if (null == msgOutput.getResultCode()){
                CertSecretService certSecretService = CertSecretServiceFactory.getCertSecretService(certInfo);
                Object responseMsg = certSecretService.dataDecrypt(certInfo, message);
                msgOutput.setResultCode(ResultConstant.EXECUTE_SUCCESS);
                msgOutput.setMessage(responseMsg);
            }
        }catch (Exception e){
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_DECRYPT_CODE,
                    e.getMessage(),e,null);
            if (null == msgOutput){
                msgOutput =  new MsgOutput();
            }
            msgOutput.setResultCode(ResultConstant.EXECUTE_FAILURE);
            msgOutput.setResultMsg(e.getMessage());
        }

        saveSecretLog(certInfo,message,"解密",msgOutput);
        return msgOutput;
    }

    /**
     * 检查参数
     * @param merchantCode
     * @param certCode
     * @return
     */
    private MsgOutput checkMsg(String merchantCode, String certCode,CertInfo certInfo){
        //参数验证
        MsgOutput msgOutput = MsgCheck.paramCheck(merchantCode, certCode);
        if (null != msgOutput.getResultCode()){
            return msgOutput;
        }
        //获取缓存信息
        if (certInfo == null || certInfo.getMerchatCode() == null){
            msgOutput.setResultCode(ResultConstant.EXECUTE_FAILURE);
            msgOutput.setResultMsg("没有维护证书信息");
            return msgOutput;
        }
        //如加减密方式为证书，则判断证书是否已下载到本地
        if ("0".equals(certInfo.getEndecryType())){
            String certPath = customizedPropertyConfigurer.getProperty(CertConfigConstant.LOCAL_CERT_STORE_PATH);
            String filePath = CertUtil.getFilePath(certPath,CertUtil.getCertFileName(certInfo));
            File file = new File(filePath);
            if (!file.exists()){//证书不存在
                msgOutput.setResultCode(ResultConstant.EXECUTE_FAILURE);
                msgOutput.setResultMsg("没有找到证书文件");
                return msgOutput;
            }
        }

        return msgOutput;
    }

    /**
     * 保存日志
     * @param certInfo
     * @param message
     * @param operateType
     * @param msgOutput
     */
    private void saveSecretLog(CertInfo certInfo, String message,
                               String operateType,MsgOutput msgOutput ) {

        try{
            if (certInfo == null || certInfo.getMerchatCode() == null){
                yinkerLogger.info(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE, "没有找到证书信息");
            }else{
                CertLog certLog = new CertLog();
                certLog.setMerchantCode(certInfo.getMerchatCode());
                certLog.setCertCode(certInfo.getCertCode());
                certLog.setCertType(certInfo.getCertType());
                certLog.setCertName(certInfo.getCertName());
                certLog.setArithmeticType(certInfo.getArithmeticType());
                certLog.setOperateType(operateType);
                certLog.setSecretKey(certInfo.getSecretKey());
                certLog.setOldMsg(message);
                if (msgOutput.getMessage() != null){
                    certLog.setNewMsg(msgOutput.getMessage().toString());
                }
                certLog.setResultCode(msgOutput.getResultCode());
                certLog.setResultMsg(msgOutput.getResultMsg());
                certLog.setRequestId(MDC.get(LogConst.REQ_ID));
                certLog.setOperateTime(new Date());
                //记录日志信息
                yinkerLogger.info(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE, certLog);
            }
        }catch (Exception e){
            yinkerLogger.info(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE, "证书中心日志存储异常");
        }

    }
}
