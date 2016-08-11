package com.ink.channel.core.boofoopay;

import java.net.SocketTimeoutException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang3.StringUtils;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.cert.api.client.DataSecurityClient;
import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.api.module.MsgOutput;
import com.ink.channel.core.boofoopay.constant.BoofooConstant;
import com.ink.channel.core.boofoopay.constant.CertificateConstant;
import com.ink.channel.core.boofoopay.request.BaseRequest;
import com.ink.channel.core.boofoopay.response.RootCollection;
import com.ink.channel.core.boofoopay.response.RootContent;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.utils.SpringContextHolder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BooFooRequest {
    private static YinkerLogger logger = YinkerLogger.getLogger(BooFooRequest.class);
    // 证书中心服务
    private static DataSecurityClient dataSecretService = SpringContextHolder.getBean(DataSecurityClient.class);
    // zookeeper配置中心服务
    private static IdCodeGenerator certificateUtil = SpringContextHolder.getBean("certificateUtil");

    /**
     * 出金（资金流出：代付）
     * @param merNo
     * @param req
     * @param bizCode
     * @param resClazz
     * @param <T>
     * @return
     */
    public static <T extends RootContent> T excuteRequest2Card(String merNo, Object req, String bizCode, Class<T> resClazz) {
    	T res = null;
    	String responseStr="";
    	// 公钥证书号，从zookeeper获取规则：商户号+渠道号+业务标识+证书后缀+公钥后缀
        String pubCertCode = certificateUtil.getIpMapsConfig().get(merNo + BoofooConstant.ASILE_NO + bizCode + BoofooConstant.CERT_SUFFIX + BoofooConstant.CERT_PUB_SUFFIX);
    	try {
	    	JSONObject boofooRequestData = JSONObject.fromObject(req);//将java对象转换为json对象
	        JSONArray jsons = new JSONArray();
	        jsons.add(boofooRequestData);
	        JSONObject json = new JSONObject();
	        json.put("trans_reqData", jsons.toString());
	        JSONArray arr = new JSONArray();
	        arr.add(json);
	        JSONObject json2 = new JSONObject();
	        json2.put("trans_reqDatas", arr.toString());
	        JSONObject json3 = new JSONObject();
	        json3.put("trans_content", json2);
	        logger.info(ChannelConstants.LOGGER_MODULE_NAME, "BooFooRequest入参：" + json3.toString());
	        // 请求URL，从zookeeper获取规则：商户号+渠道号+业务标识+URL后缀
	        String request_url = certificateUtil.getIpMapsConfig().get(merNo + BoofooConstant.ASILE_NO + bizCode + BoofooConstant.URL_SUFFIX);
	        // 终端号，从zookeeper获取规则：商户号+渠道号+业务标识+terminal后缀
	        String terminalId = certificateUtil.getIpMapsConfig().get(merNo + BoofooConstant.ASILE_NO + bizCode + BoofooConstant.TERMINAL_SUFFIX);
	        // 商户号，从zookeeper获取规则：商户号+渠道号+商户号后缀
	        String memberId = certificateUtil.getIpMapsConfig().get(merNo + BoofooConstant.ASILE_NO + BoofooConstant.MEMBER_SUFFIX);
	        // 私钥证书号，从zookeeper获取规则：商户号+渠道号+业务标识+证书后缀+私钥后缀
	        String priCertCode = certificateUtil.getIpMapsConfig().get(merNo + BoofooConstant.ASILE_NO + bizCode + BoofooConstant.CERT_SUFFIX + BoofooConstant.CERT_PRI_SUFFIX);
	        
	        // 版本号
	        String version = BooFooConfigurationUtil.getInstance().getValue("version");
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "路径" + BooFooRequest.class.getClassLoader().getResource(CertificateConstant.PRIVATE_KEY_OLD).getPath() + "参数:{}" +
                    json3.toString());
            //对请求JSON数据进行证书中心私钥加密
            MsgOutput msgOutput = dataSecretService.dataEncrypt(merNo, priCertCode, SecurityUtil.Base64Encode(json3.toString()));
            logger.debug("证书中心加密返回码"+msgOutput.getResultCode()+"，返回信息"+msgOutput.getResultMsg());
            if (!ResultConstant.EXECUTE_SUCCESS.equals(msgOutput.getResultCode())) {
                logger.error("商户" + merNo + "证书" + priCertCode + "调用证书中心私钥加密失败，证书中心返回码"+msgOutput.getResultCode()+"，返回信息"+msgOutput.getResultMsg());
                throw new RuntimeException("encrypt failed");
            }
            String dataContent =  msgOutput.getMessage().toString();
            String params = "version=%s&member_id=%s&terminal_id=%s&data_type=%s&data_content=%s";
            params = String.format(params, version, memberId, terminalId, "json", dataContent);

            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "请求报文（加密）：" + params);
            responseStr = HttpRequestUtil.sendHttpPost(request_url, params).toString();
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "响应报文(密文):{}" + responseStr);
        } catch (Exception e) {
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, e.getMessage(), e);
            return wrapCardExceptionCode(resClazz,e);
        }    
         try{  
        	 if(StringUtils.isBlank(responseStr)){
        		 try {
     				res = resClazz.newInstance();
     			} catch (InstantiationException | IllegalAccessException e1) {
     				logger.error(ChannelConstants.LOGGER_MODULE_NAME, e1.getMessage(), e1);
     				return null;
     			}
                 res.setReturn_code(SystemCodeEnums.BF_FAILE_CODE.getCode());
                 res.setReturn_msg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
                 return res;
        	 }
            //对响应报文进行公钥解密。
            MsgOutput out = dataSecretService.dataDecrypt(merNo,pubCertCode,responseStr);
            logger.info(ChannelConstants.LOGGER_MODULE_NAME,"证书中心解密返回码"+out.getResultCode()+"，返回信息"+out.getResultMsg());
            if(!ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode())){
                logger.error("商户" + merNo + "证书" + pubCertCode + "调用证书中心公钥解密失败，证书中心返回码"+out.getResultCode()+",返回信息"+out.getResultMsg());
                try {
    				res = resClazz.newInstance();
    			} catch (InstantiationException | IllegalAccessException e1) {
    				logger.error(ChannelConstants.LOGGER_MODULE_NAME, e1.getMessage(), e1);
    				return null;
    			}
                res.setReturn_code(LdysConstant.decodeCode);
                res.setReturn_msg("宝付代付解密验签失败");
            }
            responseStr=out.getMessage().toString();
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "响应报文(解密后):{}" + responseStr);
            JSONObject jsonObject = JSONObject.fromObject((SecurityUtil.Base64Decode(responseStr)));
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "[解密响应报文]:" + jsonObject);
            if (jsonObject != null) {
                return parsePaymentJsonToBean(resClazz,jsonObject);
            }
        } catch (Exception e) {
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, e.getMessage(), e);
            try {
				res = resClazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME, e1.getMessage(), e1);
				return null;
			}
            res.setReturn_code(SystemCodeEnums.BF_FAILE_CODE.getCode());
            res.setReturn_msg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
        }
        return res;
    }

    /**
     * 入金（资金流入：代收，认证支付）
     * @param merNo
     * @param req
     * @param bizCode
     * @param resClazz
     * @param <T>
     * @return
     */
    public static <T extends RootCollection> T excuteRequest2Account(String merNo, BaseRequest req, String bizCode, Class<T> resClazz) {
    	T res = null;
    	String responseStr="";
    	// 公钥证书号，从zookeeper获取规则：商户号+渠道号+业务标识+证书后缀+公钥后缀
        String pubCertCode = certificateUtil.getIpMapsConfig().get(merNo + BoofooConstant.ASILE_NO + bizCode + BoofooConstant.CERT_SUFFIX + BoofooConstant.CERT_PUB_SUFFIX);
    	try {
	    	// 请求URL，从zookeeper获取规则：商户号+渠道号+业务标识+URL后缀
	        String requestUrl = certificateUtil.getIpMapsConfig().get(merNo + BoofooConstant.ASILE_NO + bizCode + BoofooConstant.URL_SUFFIX);
	        // 终端号，从zookeeper获取规则：商户号+渠道号+业务标识+terminal后缀
	        String terminalId = certificateUtil.getIpMapsConfig().get(merNo + BoofooConstant.ASILE_NO + bizCode + BoofooConstant.TERMINAL_SUFFIX);
	        // 商户号，从zookeeper获取规则：商户号+渠道号+商户号后缀
	        String memberId = certificateUtil.getIpMapsConfig().get(merNo + BoofooConstant.ASILE_NO + BoofooConstant.MEMBER_SUFFIX);
	        // 私钥证书号，从zookeeper获取规则：商户号+渠道号+业务标识+证书后缀+私钥后缀
	        String priCertCode = certificateUtil.getIpMapsConfig().get(merNo + BoofooConstant.ASILE_NO + bizCode + BoofooConstant.CERT_SUFFIX + BoofooConstant.CERT_PRI_SUFFIX);
	        // 版本号
	        String version = BooFooConfigurationUtil.getInstance().getValue("version");
	
	        // 设置商户号和终端号到request
	        req.setMember_id(memberId);
	        req.setTerminal_id(terminalId);
	        // 将java对象转换为json对象
	        JSONObject boofooRequestData = JSONObject.fromObject(req);
	        logger.info(ChannelConstants.LOGGER_MODULE_NAME, "BooFooRequest入参：" + boofooRequestData.toString());
	        
            //对请求JSON数据进行私钥加密。
            MsgOutput encryptOutput = dataSecretService.dataEncrypt(merNo,priCertCode,SecurityUtil.Base64Encode(boofooRequestData.toString()));
            logger.debug("证书中心加密返回码"+encryptOutput.getResultCode()+"，返回信息"+encryptOutput.getResultMsg());
            if(!ResultConstant.EXECUTE_SUCCESS.equals(encryptOutput.getResultCode())){
                logger.error("商户" + merNo + "证书" + priCertCode + "调用证书中心加密失败");
                throw new RuntimeException("encrypt failed");
            }
            String params = "txn_sub_type=%s&txn_type=%s&version=%s&member_id=%s&terminal_id=%s&data_type=%s&data_content=%s";
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, boofooRequestData.get("txn_sub_type").toString());
            params = String.format(params, boofooRequestData.get("txn_sub_type").toString(), "0431", version, memberId, terminalId, "json", encryptOutput.getMessage().toString());
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "请求报文（加密）：" + params);
            responseStr = HttpRequestUtil.sendHttpPost(requestUrl, params).toString();
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "响应报文(密文):{}" + responseStr);
    	}catch (Exception e) {
                logger.error(ChannelConstants.LOGGER_MODULE_NAME, e.getMessage(), e);
                return wrapAccountExceptionCode(resClazz,e);
        }
        try{
        	 if(StringUtils.isBlank(responseStr)){
        		 try {
     				res = resClazz.newInstance();
     			} catch (InstantiationException | IllegalAccessException e1) {
     				logger.error(ChannelConstants.LOGGER_MODULE_NAME, e1.getMessage(), e1);
     				return null;
     			}
                 res.setResp_code(SystemCodeEnums.BF_FAILE_CODE.getCode());
                 res.setResp_msg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
                 return res;
        	 }
            //对响应报文进行公钥解密。
            MsgOutput decryptOutput = dataSecretService.dataDecrypt(merNo,pubCertCode,responseStr);
            logger.debug("证书中心解密返回码"+decryptOutput.getResultCode()+"，返回信息"+decryptOutput.getResultMsg());
            if(!ResultConstant.EXECUTE_SUCCESS.equals(decryptOutput.getResultCode())){
                logger.error("商户" + merNo + "证书" + pubCertCode + "调用证书中心解密失败");
                try {
    				res = resClazz.newInstance();
    			} catch (InstantiationException | IllegalAccessException e1) {
    				logger.error(ChannelConstants.LOGGER_MODULE_NAME, e1.getMessage(), e1);
    			}
                res.setResp_code(LdysConstant.decodeCode);
                res.setResp_msg("宝付解密验签失败！");
                return res;
            }
            responseStr = decryptOutput.getMessage().toString();
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "响应报文(解密后):{}" + responseStr);
            String responseDataString = SecurityUtil.Base64Decode(responseStr);
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "[解码后响应报文]:" + responseDataString);
            JSONObject jsonObject = JSONObject.fromObject(responseDataString.toString());
            res = (T) JSONObject.toBean(jsonObject, resClazz);
            return res;
        }catch (Exception e) {
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, e.getMessage(), e);
            try {
				res = resClazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME, e1.getMessage(), e1);
				return null;
			}
            res.setResp_code(SystemCodeEnums.BF_FAILE_CODE.getCode());
            res.setResp_msg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
        }
        return res;
    }

    /**
     * 转换json到javaBean
     * @param resClazz
     * @param jsonObject
     * @param <T>
     * @return
     * @throws Exception
     */
    private static <T extends RootContent> T parsePaymentJsonToBean(Class<T> resClazz,JSONObject jsonObject) throws Exception{
        T res = null;
        JSONObject contentObject = JSONObject.fromObject(jsonObject.get("trans_content").toString());
        JSONObject headObject = JSONObject.fromObject(contentObject.get("trans_head").toString());
        if (BoofooConstant.BOOFOO_SUCCESS.equals(headObject.get("return_code").toString())) {
            JSONArray jsonArray1 = JSONArray.fromObject(contentObject.get("trans_reqDatas").toString());
            JSONObject jsonObject3 = JSONObject.fromObject(jsonArray1.getJSONObject(0));
            JSONObject jsonObject4 = JSONObject.fromObject(jsonObject3.get("trans_reqData").toString());
            res = (T) JSONObject.toBean(jsonObject4, resClazz);
        } else {
            res = resClazz.newInstance();
        }
        res.setReturn_code(headObject.getString("return_code"));
        res.setReturn_msg(headObject.getString("return_msg"));
        return res;
    }

    /**
     * 填充异常信息
     * @param resClazz
     * @param e
     * @param <T>
     * @return
     */
    private static <T extends RootContent> T wrapCardExceptionCode(Class<T> resClazz,Exception e){
        T res = null;
        try {
            res = resClazz.newInstance();
        } catch (Exception e1) {
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, "BooFooRequest的resClazz.newInstance()类型转换错误" + resClazz.getName(), e1);
            return null;
        }
        // 连接超时
        if(e instanceof ConnectTimeoutException){
            res.setReturn_code(ChannelConstants.REQUEST_EXCEPTION_CODE);
            res.setReturn_msg(ChannelConstants.REQUEST_EXCEPTION_MSG);
        }// 响应超时
        else if(e instanceof SocketTimeoutException){
            res.setReturn_code(ChannelConstants.RESPONSE_EXCEPTION_CODE);
            res.setReturn_msg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
        }// 系统异常
        else {
            res.setReturn_code(SystemCodeEnums.BF_FAILE_CODE.getCode());
            res.setReturn_msg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
        }
        return res;
    }

    /**
     * 填充异常信息
     * @param resClazz
     * @param e
     * @param <T>
     * @return
     */
    private static <T extends RootCollection> T wrapAccountExceptionCode(Class<T> resClazz,Exception e){
        T res = null;
        try {
            res = resClazz.newInstance();
        } catch (Exception e1) {
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, "BooFooRequest的resClazz.newInstance()类型转换错误" + resClazz.getName(), e1);
            return null;
        }
        // 连接超时
        if(e instanceof ConnectTimeoutException){
            res.setResp_code(ChannelConstants.REQUEST_EXCEPTION_CODE);
            res.setResp_msg(ChannelConstants.REQUEST_EXCEPTION_MSG);
        }// 响应超时
        else if(e instanceof SocketTimeoutException){
            res.setResp_code(ChannelConstants.RESPONSE_EXCEPTION_CODE);
            res.setResp_msg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
        }// 系统异常
        else {
        	 res.setResp_code(SystemCodeEnums.BF_FAILE_CODE.getCode());
             res.setResp_msg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
        }
        return res;
    }

}
