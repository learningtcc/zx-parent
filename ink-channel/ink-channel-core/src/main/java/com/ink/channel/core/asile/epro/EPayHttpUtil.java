package com.ink.channel.core.asile.epro;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;
import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.bestpay.BestpayConstants;
import com.ink.channel.core.bestpay.ObjectJsonUtil;
import com.ink.channel.core.bestpay.RsaCipher;
import com.ink.channel.core.bestpay.request.EPayBaseRequestBean;
import com.ink.channel.core.bestpay.response.EPayBaseResponseBean;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.utils.Dom4jXmlUtil;
import com.ink.channel.core.utils.HttpClientUtils;
/**
 * 翼支付http请求工具类
 * @author huohb
 *
 */
public class EPayHttpUtil {
	
	private static YinkerLogger logger = YinkerLogger.getLogger(EPayHttpUtil.class);
	public static <T extends EPayBaseResponseBean> T postJson(String url, EPayBaseRequestBean req, Class<T> resClazz) {
		T t = null;
		BeanUtil.wrapBaseReqBean(req);
		String reqJson = BeanUtil.generateJsonToPost(req);
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,"请求url:"+url+",请求参数json::"+reqJson.toString());
		String res = null;
		// 发送http请求
		try {
			res = HttpClientUtils.execute(url, reqJson, "application/json", "utf-8").toString();
		} catch (UnsupportedEncodingException e) {
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,"不支持的编码格式",e);
			try {
				t = resClazz.newInstance();
			} catch (Exception e1) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,"cannot create instance for "+resClazz.getName(),e1);
				return null;
			}
			t.setCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
			t.setMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
			t.setReqSeq(req.getReqSeq());
			return t;
		}catch (ConnectTimeoutException e) {
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,"渠道请求超时异常",e);
			try {
				t = resClazz.newInstance();
			} catch (Exception e1) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,"cannot create instance for "+resClazz.getName(),e1);
				return null;
			}
			t.setCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
			t.setMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
			t.setReqSeq(req.getReqSeq());
			return t;
		}catch (SocketTimeoutException e) {
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,"响应超时异常",e);
			try {
				t = resClazz.newInstance();
			} catch (Exception e1) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,"cannot create instance for "+resClazz.getName(),e1);
				return null;
			}
			t.setCode(ChannelConstants.RESPONSE_EXCEPTION_CODE);
			t.setMsg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
			t.setReqSeq(req.getReqSeq());
			return t;
		}
		// 解析响应报文
		JSONObject respJson = JSONObject.parseObject(res);
		String data = respJson.getString("data");
		String sign = respJson.getString("sign");
		try {
			// 验签
			checkSign(sign,data);
		} catch (Exception e) {
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,"翼支付验签失败",e);
			return null;
		}
		JSONObject dataJson = JSONObject.parseObject(data);
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,"响应结果："+dataJson.toJSONString());
		String code = dataJson.getString("code");
		String msg = dataJson.getString("msg");
		String result = dataJson.getString("result");
		
		// 判断返回码
		if (StringUtils.isBlank(code) || (!"000000".equals(code))) {
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,"返回码不正常");
			try {
				t = resClazz.newInstance();
			} catch (Exception e) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,"cannot create instance for "+resClazz.getName(),e);
				return null;
			}
			t.setCode(code);
			t.setMsg(msg);
			t.setReqSeq(req.getReqSeq());
			return t;
		}
		// 生成对应的业务bean给调用层
		try {
			t = ObjectJsonUtil.jsonToObj(result, resClazz);
			t.setCode(code);
			t.setMsg(msg);
			t.setReqSeq(req.getReqSeq());
		} catch (IOException e) {
			try {
				t = ObjectJsonUtil.jsonToObj(data, resClazz);
			} catch (IOException e1) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,"读取JSON出错",e1);
				return null;
			}
		}
		return t;
	}

	public static <T extends EPayBaseResponseBean> T postXml(String url, EPayBaseRequestBean req, Class<T> resClazz) {
		T t = null;
		String reqXml = BeanUtil.generateXMLToPost(req);

		String res = null;
		// 发送http请求
		try {
			res = HttpClientUtils.execute(url, reqXml, "text/xml", "utf-8").toString();
		} catch (UnsupportedEncodingException e) {
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,"不支持的编码格式",e);
			try {
				t = resClazz.newInstance();
			} catch (Exception e1) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,"cannot create instance for "+resClazz.getName(),e1);
				return null;
			}
			t.setCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
			t.setMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
			t.setReqSeq(req.getReqSeq());
			return t;
		}catch (ConnectTimeoutException e) {
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,"链接异常",e);
			try {
				t = resClazz.newInstance();
			} catch (Exception e1) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,"cannot create instance for "+resClazz.getName(),e1);
				return null;
			}
			t.setCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
			t.setMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
			t.setReqSeq(req.getReqSeq());
			return t;
		}catch (SocketTimeoutException e) {
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,"响应异常",e);
			try {
				t = resClazz.newInstance();
			} catch (Exception e1) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,"cannot create instance for "+resClazz.getName(),e1);
				return null;
			}
			t.setCode(ChannelConstants.RESPONSE_EXCEPTION_CODE);
			t.setMsg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
			t.setReqSeq(req.getReqSeq());
			return t;
		}
		// 采用Dom4j将返回的xml进行解析成Document
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(res);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		// 解析sign节点
    	Element element = doc.getRootElement();
    	Element signElement = element.element("sign");
    	String signValue = signElement.getStringValue();
    	
    	// 解析data节点
    	Element dataElement = element.element("data");
    	String dataXml = dataElement.asXML();
		String dataValue = subXmlNode(dataXml, "data");
		try{
			// 验签
			checkSign(signValue,dataValue);
			// 生成泛型实例
			t = resClazz.newInstance();
		}catch(Exception e){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,"cannot create instance for "+resClazz.getName(),e);
			return null;
		}
		try {
			Dom4jXmlUtil.getInstance().convertElementToBean(dataElement, t);
		} catch (Exception e) {
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,"XML转VO出错");
			return null;
		}
		return t;
	}
	
	private static void checkSign(String signVal,String dataVal){
		try {
			if(!RsaCipher.verify(dataVal, BestpayConstants.BESTPAY_CERT, signVal)){
				logger.error("响应报文被篡改");
				throw new RuntimeException("响应报文被篡改");
			}
		} catch (GeneralSecurityException e) {
			logger.error("验签出错",e);
			throw new RuntimeException(e);
		}
	}
	
	private static String subXmlNode(String xml,String nodeName){
		return xml.substring(("<"+nodeName+">").length(), xml.length()-("</"+nodeName+">").length());
	}
}
