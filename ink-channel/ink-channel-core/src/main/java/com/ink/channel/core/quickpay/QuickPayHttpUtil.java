package com.ink.channel.core.quickpay;

import java.net.SocketTimeoutException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.quickpay.message.ErrorMsgContent;
import com.ink.channel.core.quickpay.message.MasMessage;
import com.ink.channel.core.quickpay.message.MasMessageBack;
import com.ink.channel.core.utils.Dom4jXmlUtil;
import com.ink.channel.core.utils.JaxbUtil;

/**
 * 快钱http请求工具类
 * @author huohb
 *
 */
public class QuickPayHttpUtil {
	
	private static YinkerLogger logger = YinkerLogger.getLogger(QuickPayHttpUtil.class);
	
	public static MasMessageBack postXml(String url,MasMessage msg){
		
		String reqXml = JaxbUtil.convertToXml(msg);
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,"快钱请求报文："+reqXml);
		String resXml = null;
		try {
			resXml = Post.sendPost(url, reqXml);
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,"快钱响应报文："+resXml);
		}catch(ConnectTimeoutException  connectTimeoutException){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.REQUEST_EXCEPTION_MSG,connectTimeoutException);
			return getMasMessageBack(ChannelConstants.REQUEST_EXCEPTION_CODE,ChannelConstants.REQUEST_EXCEPTION_MSG);
        }catch(SocketTimeoutException socketTimeoutException){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.REQUEST_EXCEPTION_MSG,socketTimeoutException);
        	return getMasMessageBack(ChannelConstants.RESPONSE_EXCEPTION_CODE,ChannelConstants.RESPONSE_EXCEPTION_MSG);
        }catch (Exception e) {
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,"请求快钱失败",e);
			throw new RuntimeException(e);
		}
		return JaxbUtil.converyToJavaBean(resXml, MasMessageBack.class);
	}
	
	public static <T> T postXml(String url, Object req, Class<T> resClazz) {

		String reqXml = generatePostXml(req);
		logger.info("发送的报文："+reqXml);
		logger.info("请求路径："+url);
		String res = null;
		// 发送http请求
		try {
			res = Post.sendPost(url, reqXml);
		}catch (Exception e) {
			logger.error("快钱发送https请求失败",e);
			throw new RuntimeException(e);
		}
		// 采用Dom4j将返回的xml进行解析成Document
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(res);
		} catch (DocumentException e) {
			logger.error("快钱解析XML出错");
			throw new RuntimeException(e);
		}
    	// 生成泛型实例
		T t = null;
		try{
			t = resClazz.newInstance();
		}catch(Exception e){
			logger.error("cannot create instance for "+resClazz.getName(),e);
			throw new RuntimeException("cannot create instance for "+resClazz.getName());
		}
		try {
			Dom4jXmlUtil.getInstance().convertElementToBean(doc.getRootElement(), t);
		} catch (Exception e) {
			logger.error("XML转VO出错");
			throw new RuntimeException(e);
		}
		return t;
	}
	/**
	 * 生成发送到快钱的XML
	 * @param req
	 * @return
	 */
	private static String generatePostXml(Object req){
		
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		strBuf.append("<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">");
		strBuf.append("<version>1.0</version>");
		strBuf.append(JaxbUtil.convertToNoDeclareXml(req));
		strBuf.append("</MasMessage>");
		return strBuf.toString();
	}
	private static  MasMessageBack getMasMessageBack(String code,String msg){
		MasMessageBack back=new MasMessageBack();
		ErrorMsgContent errorMsgContent = new ErrorMsgContent();
		errorMsgContent.setErrorCode(code);
		errorMsgContent.setErrorMessage(msg);
		back.setErrorMsgContent(errorMsgContent);
		return back;
	}
}
