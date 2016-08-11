package com.ink.channel.core.ldyspay;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.cert.api.client.DataSecurityClient;
import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.api.module.MsgOutput;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.utils.HttpClientUtils;
import com.ink.channel.core.utils.SpringContextHolder;
import com.umpay.api.common.ReqData;

public class LdysMessEncryptUtils {
	private static YinkerLogger logger = YinkerLogger.getLogger(LdysMessEncryptUtils.class);
	@SuppressWarnings("unchecked")
	public static Map<String,String> messEncrypt(Map<String,Object> msgMap,String merchantNo){
		Map<String, String> mapRes=new HashMap<>();
		mapRes.put("exceptionCode", LdysConstant.successCode);
		String p8CertCode ="";
		String crtCertCode="";
		String resultStr="";
		String post_url="";
		ReqData reqDataPost=null;
		DataSecurityClient dataSecretService=null;
		try{
			dataSecretService = (DataSecurityClient) SpringContextHolder.getBean(DataSecurityClient.class);
			IdCodeGenerator certificateUtil = (IdCodeGenerator) SpringContextHolder.getBean("certificateUtil");
			String asileNo=certificateUtil.getIpMapsConfig().get(LdysConstant.CHANNELID);//渠道号
			p8CertCode = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+LdysConstant.CERTCODEP8);//p8证书
			crtCertCode = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+LdysConstant.CERTCODECRT);//crt证书
			// 拼接参数 去证书中心获取 签名       下面是固定参数 无需改变
			Map<String,Object> certInfoMap = new HashMap<>();
		    certInfoMap.put("plat.url",LdysConstant.platUrl);
		    certInfoMap.put("plat.pay.product.name",LdysConstant.productName);
		    certInfoMap.put("Encrypt.Paramters",LdysConstant.encryptParamters);
		    certInfoMap.put("crtMerchantCode",merchantNo);
		    certInfoMap.put("crtCertCode",crtCertCode);
		    certInfoMap.put("encryptFlag", "makeReqDataByPost");//接口名称  makeReqDataByPost|merNotifyResData
		    
		    Map<String, Map<String, Object>> paramMap = Maps.newHashMap();
		    logger.info(ChannelConstants.LOGGER_MODULE_NAME, "联动优势请求参数："+msgMap.toString());
		    paramMap.put("ldysParam",msgMap);
		    paramMap.put("certInfo",certInfoMap);
		    String message = JSON.toJSON(paramMap).toString();
		    //证书中心响应
		    MsgOutput msgOutput = dataSecretService.dataEncrypt(merchantNo, p8CertCode, message);
		    if(msgOutput==null||!ResultConstant.EXECUTE_SUCCESS.equals(msgOutput.getResultCode())){
		    	//证书中心加密异常
		    	mapRes.put("ret_code",SystemCodeEnums.LDYS_FAILE_CODE.getCode());
				mapRes.put("ret_msg","联动优势请求加密失败");
		    	logger.error(ChannelConstants.LOGGER_MODULE_NAME, "联动优势请求参数加密异常："+msgOutput.getMessage());
		    	return mapRes;
		    }
		    reqDataPost=(ReqData)msgOutput.getMessage();
			post_url = reqDataPost.getUrl();   //post请求的地址  
		}catch(Exception e){
			//发送请求前  出现异常 直接返回渠道异常
			mapRes.put("ret_code",SystemCodeEnums.LDYS_FAILE_CODE.getCode());
			mapRes.put("ret_msg",SystemCodeEnums.LDYS_FAILE_CODE.getMsg());
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, "联动优势渠道异常",e);
			return mapRes;
		}
		try {
			//发送请求
			resultStr = (String)HttpClientUtils.execute(post_url,reqDataPost.getField());
			//请求相应结果如果为空   则渠道异常
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, "联动优势请求返回信息："+resultStr);
		} catch (ConnectTimeoutException e) {
			//请求异常
			mapRes.put("ret_code",ChannelConstants.REQUEST_EXCEPTION_CODE);
			mapRes.put("ret_msg",ChannelConstants.REQUEST_EXCEPTION_MSG);
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, "联动优势请求超时",e);
			return mapRes;
		} catch (SocketTimeoutException e) {
			//响应异常
			mapRes.put("ret_code",ChannelConstants.RESPONSE_EXCEPTION_CODE);
			mapRes.put("ret_msg",ChannelConstants.RESPONSE_EXCEPTION_MSG);
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, "联动优势响应超时",e);
			return mapRes;
		}catch (Exception e) {
			//其他异常
			mapRes.put("ret_code",SystemCodeEnums.LDYS_FAILE_CODE.getCode());
			mapRes.put("ret_msg",SystemCodeEnums.LDYS_FAILE_CODE.getMsg());
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, "联动优势渠道异常",e);
			return mapRes;
		}
		try{
			if(StringUtils.isBlank(resultStr)){
				//请求返回值为空
				mapRes.put("ret_code",SystemCodeEnums.LDYS_FAILE_CODE.getCode());
				mapRes.put("ret_msg",SystemCodeEnums.LDYS_FAILE_CODE.getMsg());
				return mapRes;
			}
			//相应后  去证书中心  验签    以下参数是固定值  无需修改
			Map<String,Object> msgMapResult = new HashMap<>();
			msgMapResult.put("message", resultStr);//参数字符编码集
	
	        Map<String,Object> certInfoMapResult = new HashMap<>();
	        certInfoMapResult.put("plat.url",LdysConstant.platUrl);
	        certInfoMapResult.put("plat.pay.product.name",LdysConstant.productName);
	        certInfoMapResult.put("Encrypt.Paramters",LdysConstant.encryptParamters);
	        certInfoMapResult.put("crtMerchantCode",merchantNo);
	        certInfoMapResult.put("crtCertCode",crtCertCode);
	        certInfoMapResult.put("decryptFlag", "resData");//接口名称  resData|resDataByMeta|platNotifyData
	        Map<String, Map<String, Object>> paramMapResult = Maps.newHashMap();
	        paramMapResult.put("ldysParam",msgMapResult);
	        paramMapResult.put("certInfo",certInfoMapResult);
	        String messageResult = JSON.toJSON(paramMapResult).toString();
	        //证书中心响应结果
	        MsgOutput msgOutputResult = dataSecretService.dataDecrypt(merchantNo, p8CertCode, messageResult);
	        if(msgOutputResult==null||!ResultConstant.EXECUTE_SUCCESS.equals(msgOutputResult.getResultCode())){
	        	//证书中心 响应验签异常
	        	mapRes.put("exceptionCode", LdysConstant.decodeCode);
	        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, "联动优势响应解密异常："+msgOutputResult.getResultMsg());
		    	return mapRes;
		    }
	        mapRes= (Map<String, String>)msgOutputResult.getMessage();
	        logger.info(ChannelConstants.LOGGER_MODULE_NAME, "联动优势请求解密后最终结果："+mapRes.toString());
        }catch (Exception e) {
        	//响应后的其他异常
        	mapRes.put("exceptionCode", LdysConstant.decodeCode);
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, "联动优势响应解密异常",e);
		}
        return mapRes;
	}
}
