package com.ink.msgcenter.mq;

public class MQConstant {
	
	public static final String MQ_EMAIL_EXCHANGE = "msg.email.exchange";
	public static final String MQ_SMS_EXCHANGE = "msg.sms.exchange";

	/**
	 * 普通邮件路由KEY
	 */
	public static final String MQ_EMAIL_SEND_KEY = "msg.email.send.key";
	
	public static final String MQ_EMAIL_STORE_KEY = "msg.email.store.key";
	/*普通短信路由KEY(单个)*/
	public static final String MQ_SMS_KEY_SINGLE = "msg.sms.key.single";
	/*普通短信路由KEY(单个)*/
	public static final String MQ_SMS_KEY_MASS = "msg.sms.key.mass";
	/*短信日志理由KEY*/
	public static final String MQ_SMS_STORE_KEY = "msg.sms.store.key";
}
