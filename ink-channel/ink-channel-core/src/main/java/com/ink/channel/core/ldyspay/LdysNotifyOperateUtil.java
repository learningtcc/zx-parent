package com.ink.channel.core.ldyspay;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.cert.api.client.DataSecurityClient;
import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.api.module.MsgOutput;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.utils.Constants;
import com.ink.channel.core.utils.SpringContextHolder;

/**
 * 联动优势回调处理工具类
 * Created by huohb on 2016/7/6.
 */
public class LdysNotifyOperateUtil {

    private static final YinkerLogger logger = YinkerLogger.getLogger(LdysNotifyOperateUtil.class);
    /**
     * 联动优势回调解密工具方法
     * @param resultMap
     * @param channelMerNo
     * @return
     */
    public static Map<String,String> decrpt(Map<String,String> resultMap,String channelMerNo){
        Map<String,String> mapRes = new HashMap<String,String>();
        mapRes.put("exceptionCode", LdysConstant.successCode);
        try {
            DataSecurityClient dataSecretService = SpringContextHolder.getBean(DataSecurityClient.class);
            IdCodeGenerator certificateUtil = (IdCodeGenerator) SpringContextHolder.getBean("certificateUtil");
            String asileNo=certificateUtil.getIpMapsConfig().get(LdysConstant.CHANNELID);//渠道号
            // 根据渠道商户号查询平台商户号规则：渠道商户号+渠道号+后缀
            String platMerNo = certificateUtil.getIpMapsConfig().get(channelMerNo+LdysConstant.CHANNELID+ Constants.CHANNEL_MERCHANT_SUFFIX);
            String p8CertCode = certificateUtil.getIpMapsConfig().get(platMerNo+asileNo+LdysConstant.CERTCODEP8);//p8证书
            String crtCertCode = certificateUtil.getIpMapsConfig().get(platMerNo+asileNo+LdysConstant.CERTCODECRT);//crt证书

            //相应后  去证书中心  验签    以下参数是固定值  无需修改
            Map<String,Object> msgMapResult = new HashMap<>();
            msgMapResult.put("message", resultMap);//参数字符编码集
            msgMapResult.put("mer_id", platMerNo);//参数字符编码集

            Map<String,Object> certInfoMapResult = new HashMap<>();
            certInfoMapResult.put("decryptFlag", "platNotifyData");//接口名称  platNotifyData
            certInfoMapResult.put("plat.url",LdysConstant.platUrl);
            certInfoMapResult.put("plat.pay.product.name",LdysConstant.productName);
            certInfoMapResult.put("Encrypt.Paramters",LdysConstant.encryptParamters);
            certInfoMapResult.put("crtMerchantCode",platMerNo);
            certInfoMapResult.put("crtCertCode",crtCertCode);

            Map<String, Map<String, Object>> paramMapResult = Maps.newHashMap();
            paramMapResult.put("ldysParam",msgMapResult);
            paramMapResult.put("certInfo",certInfoMapResult);
            String messageResult = JSON.toJSON(paramMapResult).toString();
            //证书中心响应结果
            MsgOutput msgOutputResult = dataSecretService.dataDecrypt(platMerNo, p8CertCode, messageResult);
            if(msgOutputResult==null||!ResultConstant.EXECUTE_SUCCESS.equals(msgOutputResult.getResultCode())){
                //证书中心 响应验签异常
                mapRes.put("exceptionCode", LdysConstant.decodeCode);
                logger.error(ChannelConstants.LOGGER_MODULE_NAME, "联动优势响应解密异常："+msgOutputResult.getResultMsg());
                return mapRes;
            }
            mapRes= (Map<String, String>)msgOutputResult.getMessage();
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "联动优势请求解密后最终结果："+mapRes.toString());
        } catch (Exception e) {
            mapRes.put("exceptionCode", LdysConstant.decodeCode);
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, "联动优势响应解密异常",e);
        }
        return mapRes;
    }

    /**
     * 联动优势回调响应渠道加密
     * @param map
     * @return
     */
    public static String encrpt(Map<String,String> map){

        try {
            DataSecurityClient dataSecretService = SpringContextHolder.getBean(DataSecurityClient.class);
            IdCodeGenerator certificateUtil = (IdCodeGenerator) SpringContextHolder.getBean("certificateUtil");
            String asileNo=certificateUtil.getIpMapsConfig().get(LdysConstant.CHANNELID);//渠道号
            // 根据渠道商户号查询平台商户号规则：渠道商户号+渠道号+后缀
            String platMerNo = certificateUtil.getIpMapsConfig().get(map.get("mer_id")+LdysConstant.CHANNELID+ Constants.CHANNEL_MERCHANT_SUFFIX);
            String p8CertCode = certificateUtil.getIpMapsConfig().get(platMerNo+asileNo+LdysConstant.CERTCODEP8);//p8证书
            String crtCertCode = certificateUtil.getIpMapsConfig().get(platMerNo+asileNo+LdysConstant.CERTCODECRT);//crt证书
            // 拼接参数 去证书中心获取 签名       下面是固定参数 无需改变
            Map<String,String> certInfoMap = new HashMap<>();
            //merNotifyResData
            certInfoMap.put("plat.url",LdysConstant.platUrl);
            certInfoMap.put("plat.pay.product.name",LdysConstant.productName);
            certInfoMap.put("Encrypt.Paramters",LdysConstant.encryptParamters);
            certInfoMap.put("crtMerchantCode",platMerNo);
            certInfoMap.put("crtCertCode",crtCertCode);
            certInfoMap.put("encryptFlag", "merNotifyResData");//接口名称  makeReqDataByPost|merNotifyResData

            Map<String, Map<String, String>> paramMap = Maps.newHashMap();
            paramMap.put("ldysParam",map);
            paramMap.put("certInfo",certInfoMap);
            String message = JSON.toJSON(paramMap).toString();
            //证书中心响应
            MsgOutput msgOutput = dataSecretService.dataEncrypt(platMerNo, p8CertCode, message);
            if(msgOutput==null||!ResultConstant.EXECUTE_SUCCESS.equals(msgOutput.getResultCode())){
                //证书中心 响应验签异常
                logger.error(ChannelConstants.LOGGER_MODULE_NAME, "联动优势响应解密异常:"+msgOutput.getResultMsg());
                return "";
            }
            return msgOutput.getMessage().toString();
        } catch (Exception e) {
            logger.error("联动优势回调响应渠道加密异常",e);
        }
        return "";
    }
}
