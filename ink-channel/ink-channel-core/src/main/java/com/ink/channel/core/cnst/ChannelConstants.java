package com.ink.channel.core.cnst;
/**
 * 渠道常量类
 * @author huohb
 *
 */
public class ChannelConstants {
	
	/*
	 * 渠道日志模块名
	 */
	public static final String LOGGER_MODULE_NAME = "1001005";
	/**
	 * 翼支付日志
	 */
	public static final String BEST_PAY_VALIDATE_CODE="10010050001";
	public static final String BEST_PAY_FAS_SING_CODE="10010050002";
	public static final String BEST_PAY_ACCOUNT_CODE="10010050003";
	public static final String BEST_QUERY_PAY_ACCOUNT_CODE="10010050004";
	public static final String BEST_PAY_CARD_CODE="10010050005";
	public static final String BEST_QUERY_PAY_CARD_CODE="10010050006";
	/**
	 * 民生
	 * 
	 */
	public static final String MAS_PAY_VALIDATE_CODE="10010050007";
	public static final String MAS_PAY_FAS_SING_CODE="10010050008";
	public static final String MAS_PAY_ACCOUNT_CODE="10010050009";
	public static final String MAS_QUERY_PAY_ACCOUNT_CODE="10010050010";
	public static final String MAS_PAY_CARD_CODE="10010050011";
	public static final String MAS_QUERY_PAY_CARD_CODE="10010050012";
	public static final String MAS_AUTH_QUICK_PAY_CODE="10010050013";
	public static final String MAS_QUICK_PAY_CODE="10010050014";
	public static final String MAS_QUICK_QUERY="10010050039";
	/**
	 * 易宝
	 */
	public static final String YEEPAY_PAY_VALIDATE_CODE="10010050015";
	public static final String YEEPAY_PAY_FAS_SING_CODE="10010050016";
	public static final String YEEPAY_PAY_ACCOUNT_CODE="10010050017";
	public static final String YEEPAY_QUERY_PAY_ACCOUNT_CODE="10010050018";
	public static final String YEEPAY_PAY_CARD_CODE="10010050019";
	public static final String YEEPAY_QUERY_PAY_CARD_CODE="10010050020";
	public static final String YEEPAY_REFUND_CODE="10010050021";
	/**
	 * 快钱
	 */
	public static final String QUICKM_PAY_VALIDATE_CODE="10010050022";
	public static final String QUICKM_PAY_FAS_SING_CODE="10010050023";
	public static final String QUICKM_PAY_ACCOUNT_CODE="10010050024";
	public static final String QUICKM_QUERY_PAY_ACCOUNT_CODE="10010050025";
	public static final String QUICKM_PAY_CARD_CODE="10010050026";
	public static final String QUICKM_QUERY_PAY_CARD_CODE="10010050027";
	public static final String QUICKM_AUTH_QUICK_PAY_CODE="10010050028";
	public static final String QUICKM_QUICK_PAY_CODE="10010050029";
	public static final String QUICKM_MAS_REFUND_CODE="10010050030";
	public static final String QUICKM_MAS_REVOKE_CODE="10010050031";
	/**
	 * 宝付
	 */
	public static final String BFOO_PAY_VALIDATE_CODE="10010050032";
	public static final String BFOO_PAY_FAS_SING_CODE="10010050033";
	public static final String BFOO_PAY_ACCOUNT_CODE="10010050034";
	public static final String BFOO_QUERY_PAY_ACCOUNT_CODE="10010050035";
	public static final String BFOO_PAY_CARD_CODE="10010050036";
	public static final String BFOO_QUERY_PAY_CARD_CODE="10010050037";
	public static final String BFOO_UNBIND_CARD_CODE="10010050038";
	/**
	 * 京东网银在线
	 */
	public static final String JD_AUTH_QUICK_PAY_CODE="10010050040";
	public static final String JD_QUICK_PAY_CODE="10010050041";
	public static final String JD_QUICK_QUERY="10010050042";
	public static final String JD_QUICK_AGAIN_VALID_CODE="10010050043";
	public static final String JD_QUICK_AGAIN_PAY_CODE="10010050044"; 
	
	/**
	 * 联动优势
	 */
	public static final String LDYS_QUICK_AUTH_PAY_CODE="10010050045";
	public static final String LDYS_QUICK_PAY_CODE="10010050046";
	public static final String LDYS_QUICK_QUERY="10010050047";
	public static final String LDYS_QUICK_AGAIN_VALID_CODE="10010050048";
	public static final String LDYS_QUICK_AGAIN_PAY_CODE="10010050049"; 
	
	
	/*
	 * 渠道与平台返回码关联关系默认值（用于在数据库中未查询到）
	 */
	public static final String CHANNEL_PLAT_RES_CODE_DEFAULT = "EB0003";
	/*
	 * 渠道与平台返回信息关联关系默认值（用于在数据库中未查询到）
	 */
	public static final String CHANNEL_PLAT_RES_MSG_DEFAULT = "交易失败";
	
	/**易宝交易成功码*/
	public static final String YEEPAY_SUCCESS_CODE="000000";

	/**易宝交易成功信息*/
	public static final String YEEPAY_SUCCESS_MSG="成功";
	/**渠道处理异常*/
	public static String REQUEST_EXCEPTION_CODE="RQ000001";
	public static String REQUEST_EXCEPTION_MSG="渠道请求超时";
	
	public static String RESPONSE_EXCEPTION_CODE="RS000001";
	public static String RESPONSE_EXCEPTION_MSG="渠道响应超时";
	
	//public static String OTHER_EXCEPTION_CODE="QT000001";
	//public static String OTHER_EXCEPTION_MSG="渠道异常";
	
}
