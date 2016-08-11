package com.ink.cert.service.dubbo;

import com.ink.cert.service.service.CertSecretService;
import com.ink.cert.service.service.CertSecretServiceFactory;
import com.ink.cert.service.util.check.CertCacheUtil;
import com.ink.cert.service.util.check.MsgCheck;
import com.ink.base.log.util.LogConst;
import com.ink.base.log.util.YinkerLogger;
import com.ink.cert.api.constant.MQConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.module.MsgOutput;
import com.ink.cert.api.dubbo.DataSecretService;
import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.core.po.CertInfo;
import com.ink.cert.core.po.CertLog;
import com.ink.cert.service.util.check.CertFileUtil;
import org.slf4j.MDC;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 数据加解密服务实现类
 * Created by aiyungui on 2016/6/22.
 */
@Service("dataSecretService")
public class DataSecretServiceImpl implements DataSecretService{

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(DataSecretServiceImpl.class);
    @Autowired
    private CertCacheUtil certCacheUtil;
    @Autowired
    private CertFileUtil certFileUtil;
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 数据加密
     *
     * @param merchantCode 商户编号
     * @param certCode     证书编号
     * @param message      报文
     * @return 消息处理结果
     */
    @Override
    public MsgOutput dataEncrypt(String merchantCode, String certCode, String message) {

        MsgOutput msgOutput = null;
        CertInfo certInfo = null;
        try{
            //验证
            certInfo = certCacheUtil.getCertInfo(merchantCode,certCode);
            msgOutput = checkMsg(certInfo,merchantCode, certCode);
            if (msgOutput.getResultCode() == null){
                //信息加密
                CertSecretService certSecretService = CertSecretServiceFactory.getCertSecretService(certInfo);
                Object responseMsg = certSecretService.dataEncrypt(certInfo, message);
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
        //日志存储
        saveSecretLog(certInfo,message,"1",msgOutput);
        return msgOutput;
    }

    /**
     * 数据解密
     *
     * @param merchantCode 商户编号
     * @param certCode     证书编号
     * @param message      报文
     * @return 消息处理结果
     */
    @Override
    public MsgOutput dataDecrypt(String merchantCode, String certCode, String message) {
        MsgOutput msgOutput = null;
        CertInfo certInfo = null;
        try{
            //验证
            certInfo = certCacheUtil.getCertInfo(merchantCode,certCode);
            msgOutput = checkMsg(certInfo,merchantCode, certCode);
            if (msgOutput.getResultCode() == null){
                //信息加密
                CertSecretService certSecretService = CertSecretServiceFactory.getCertSecretService(certInfo);
                Object responseMsg = certSecretService.dataDecrypt(certInfo, message);
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

        //日志存储
        saveSecretLog(certInfo,message,"2",msgOutput);

        return msgOutput;
    }

    /**
     * 检查信息
     * @param merchantCode
     * @param certCode
     * @return
     */
    private MsgOutput checkMsg(CertInfo certInfo,String merchantCode, String certCode) throws Exception{
        //参数验证
        MsgOutput msgOutput = MsgCheck.paramCheck(merchantCode, certCode);
        if (null != msgOutput.getResultCode()){
            return msgOutput;
        }
        if (certInfo == null || null == certInfo.getCertCode()){
            msgOutput.setResultCode(ResultConstant.EXECUTE_FAILURE);
            msgOutput.setResultMsg("没有维护证书信息");
            return msgOutput;
        }
        //如加减密方式为证书，则判断证书是否已下载到本地
        if ("0".equals(certInfo.getEndecryType())){
            boolean isHave = certFileUtil.findCertFile(certInfo);
            if (!isHave){//证书不存在
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
            //mq记录日志信息
            amqpTemplate.convertAndSend(MQConstant.CERT_LOG_EXCHANGE
                    , MQConstant.CERT_LOG_KEY, certLog);
            yinkerLogger.info(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE, certLog);
        }catch (Exception e){
            yinkerLogger.info(SysConstant.MODULE_DATA_SECRET_CODE,SysConstant.INFO_DATA_ENCRYPT_CODE, "证书中心日志存储异常");
        }
    }
}
