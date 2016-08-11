package com.ink.msgcenter.api.util;

/**
 * 消息代码常量
 * @author zongtt
 * 2016年5月13日10:24:58
 */
public class MsgCode {
	/**
	 * 成功
	 */
	public static final String SUCCESS = "RT0000";
	
	/**
	 * 参数不符合规范
	 */
	public static final String PARAM_NOT_MATCH_DOC = "RT0002";
	
	/**
	 * 商户不存在
	 */
	public static final String MERCHANT_NOT_FOUND = "RT2001";
	
	/**
	 * 商户通道未配置
	 */
	public static final String MERCHANT_CHN_NOT_EXISTS = "RT2002";
	
	
	/**
	 * 模板不存在
	 */
	public static final String TEMPLATE_NOT_EXISTS = "RT3001";
	/**
	 * 模板商户不匹配
	 */
	public static final String TEMPLATE_MERCHANT_NOT_MATCH = "RT3002";
	
	/**
	 * 邮件发送成功
	 */
	public static final String EMAIL_SEND_SUCCESS = "MS1000";
	/**
	 * 邮件发送失败
	 */
	public static final String EMAIL_SEND_FAIL = "MS1001";
	/**
	 * 短信提交成功
	 */
	public static final String SMS_SUBMIT_SUCCESS = "MS2000";
	/**
	 * 短信提交失败
	 */
	public static final String SMS_SUBMIT_FAIL = "MS2001";
	/**
	 * 短信接收成功
	 */
	public static final String SMS_RECV_SUCCESS = "MS2002";

	/**
	 * 短信接收失败
	 */
	public static final String SMS_RECV_FAIL = "MS2003";
}
