package com.ink.channel.core.jdPay.util;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.cert.api.client.DataSecurityClient;
import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.api.dubbo.DataSecretService;
import com.ink.cert.api.module.MsgOutput;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.utils.SpringContextHolder;

public class JDVerifyInfoUtils {
	private static YinkerLogger logger = YinkerLogger.getLogger(JDVerifyInfoUtils.class);
	private static final String charset = "UTF-8";
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> doPost(String res_url,String jsonStr,String merchantNo){
		Map<String,String> mapRes=new HashMap<String,String>();
		mapRes.put("exceptionCode",LdysConstant.successCode);
		String feedback="";
		String reqStr="";
		try{
			IdCodeGenerator certificateUtil = (IdCodeGenerator) SpringContextHolder.getBean("certificateUtil");
			DataSecurityClient dataSecretService = (DataSecurityClient) SpringContextHolder.getBean(DataSecurityClient.class);
			String asileNo=certificateUtil.getIpMapsConfig().get(JDConstant.Asile_NO);
			String DES3KEY = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERIFYINFO_DES3KEY);
			String version = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERIFYINFO_VERSION);
			// 2.构造base64编码的请求数据
			String encodeRequestData = BASE64Util.encode(jsonStr.getBytes(charset));
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, "encodeRequestData---" + encodeRequestData);
			// 3.生成签名数据
			String data=version+charset+encodeRequestData;
			MsgOutput out= dataSecretService.dataEncrypt(merchantNo, DES3KEY, data);
			if(out==null||!ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode())){
				//证书中心加密异常
		    	mapRes.put("ret_code",SystemCodeEnums.JD_FAILE_CODE.getCode());
				mapRes.put("ret_msg","京东四要素验证请求加密失败");
		    	logger.error(ChannelConstants.LOGGER_MODULE_NAME, "京东四要素验证请求参数加密异常："+out.getMessage());
		    	return mapRes;
			}
			String sign=out.getMessage().toString();
			JSONObject param = new JSONObject();
			param.put("version", version);
			param.put("checkSign", sign);
			param.put("data", encodeRequestData);
			param.put("charset", charset);
			// 4.进行urlEncode
			reqStr = JRAlgorithmUtils.urlEncode(param.toJSONString(),
					charset);
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, "reqStr---" + reqStr);
		}catch (Exception e) {
			mapRes.put("resultCode",SystemCodeEnums.JD_FAILE_CODE.getCode());
			mapRes.put("resultInfo",SystemCodeEnums.JD_FAILE_CODE.getMsg());
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, "京东四要素验证请求超时",e);
			return mapRes;
		}	
		// 5.发送请求
		try {
			feedback = streamPost(res_url, reqStr);
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, "feedback---" + feedback);
		} catch (ConnectTimeoutException e) {
			//请求异常
			mapRes.put("resultCode",ChannelConstants.REQUEST_EXCEPTION_CODE);
			mapRes.put("resultInfo",ChannelConstants.REQUEST_EXCEPTION_MSG);
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, "京东四要素验证请求超时",e);
			return mapRes;
		} catch (SocketTimeoutException e) {
			//响应异常
			mapRes.put("resultCode",ChannelConstants.RESPONSE_EXCEPTION_CODE);
			mapRes.put("resultInfo",ChannelConstants.RESPONSE_EXCEPTION_MSG);
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, "京东四要素验证响应超时",e);
			return mapRes;
		}catch (Exception e) {
			//其他异常
			mapRes.put("resultCode",SystemCodeEnums.JD_FAILE_CODE.getCode());
			mapRes.put("resultInfo",SystemCodeEnums.JD_FAILE_CODE.getMsg());
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, "京东四要素验证渠道异常",e);
			return mapRes;
		}
		// 6.数据解码
		String responseData=null;
		try {
			if(StringUtils.isBlank(feedback)){
				//请求返回值为空
				mapRes.put("resultCode",SystemCodeEnums.JD_FAILE_CODE.getCode());
				mapRes.put("resultInfo",SystemCodeEnums.JD_FAILE_CODE.getMsg());
				return mapRes;
			}
			responseData = decodeResponseJson(feedback,merchantNo);
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, "responseData---" + responseData);
			mapRes=(Map<String,String>)JSONObject.parseObject(responseData, Map.class);
		} catch (Exception e) {
			mapRes.put("exceptionCode",LdysConstant.successCode);
		}
        return mapRes;
	}
	/**生成签名数据*/
	public static String getSignature(String version, String charset,
			String data, String privateKey) throws Exception {
		String localSign = new String(SHA256Util.encrypt(version + charset + data + privateKey));
		return localSign;
	}

	@SuppressWarnings("unchecked")
	public static String decodeResponseJson(String responseJson,String merchantNo) throws Exception {
		String strRS ="";
		IdCodeGenerator certificateUtil = (IdCodeGenerator) SpringContextHolder.getBean("certificateUtil");
		DataSecretService dataSecretService = (DataSecretService) SpringContextHolder.getBean("dataSecretService");
		String asileNo=certificateUtil.getIpMapsConfig().get(JDConstant.Asile_NO);
		String DES3KEY = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERIFYINFO_DES3KEY);
		String version = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERIFYINFO_VERSION);
		Map<String, String> map = FastJsonUtils.toBean(responseJson,HashMap.class);
		String responseData = map.get("data");
		String checkSign=map.get("checkSign");
		String data=version+charset+responseData;
		MsgOutput out=dataSecretService.dataEncrypt(merchantNo, DES3KEY, data);
		 if(ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode())){
		 String sign=out.getMessage().toString();
			if(checkSign.equals(sign)){
				byte[] decodeBase64 = BASE64Util.decode(responseData);
				strRS= new String(decodeBase64, charset);
			}
		 }else{
			 throw new Exception();
		 }
		return strRS;
	}
	/**
	 * 发送请求
	 * @param url
	 * @param param
	 * @return
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @throws Exception
	 */
	public static String streamPost(String url, String param) throws ConnectTimeoutException, SocketTimeoutException,Exception{
		// HashMap类型的对象传输
		HttpClient client = null;
		PostMethod postMethod = null;
		try {
			client = new HttpClient();
			postMethod = new PostMethod(url);
			RequestEntity requestEntity = new StringRequestEntity(param);
			postMethod.setRequestEntity(requestEntity);
			client.executeMethod(postMethod);
			String result = JRAlgorithmUtils
					.urlDecode(postMethod.getResponseBodyAsString(), charset);
			return result;
		}catch (ConnectTimeoutException connectTimeoutException) {
            throw connectTimeoutException;
        }catch (SocketTimeoutException socketTimeoutException) {
            throw socketTimeoutException;
        } catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
	}
}
