package com.ink.trade.core.cnst;

/**
 * 交易系统返回代码定义
 * 
 * @author yangchen
 * @date 2016年4月14日 下午6:39:34
 */
public class TradeRespConstant {

	public static final String TRADE_ERROR_0001 = "TRADE_ERROR_0001";

	public static final String TRADE_ERROR_0001_MSG = "参数格式不合法：";

	public static final String TRADE_SUCCESS = "000000";
	public static final String TRADE_SUCCESS_MSG = "成功";
	public static final String TRADE_PROCESSING="000001";
	public static final String TRADE_PROCESSING_MSG="交易处理中";
	public static final String TRADE_FAIL_UNKNOWN="000002";
	public static final String TRADE_FAIL_UNKNOWN_MSG="交易失败原因未知";
	public static final String TRADE_SYSERROR = "EE0001";
	public static final String TRADE_SYSERROR_MSG = "系统异常";

	public static final String TRADE_CODE_ERROR="ES0016";
	public static final String TRADE_CODE_ERROR_MSG="交易类型不正确";
	public static final String ORDER_STATUS_ERROR="ES0053";
	public static final String ORDER_STATUS_ERROR_MSG="订单状态不正常";
	public static final String CARD_NOT_BIND = "ES0044";
	public static final String CARD_NOT_BIND_MSG = "银行卡未绑定";
	public static final String TRADE_SIGN_0001 = "ES0047";
	public static final String TRADE_SIGN_0001_MSG = "银行卡已签约";

	public static final String TRADE_SIGN_0003="EB0012";
	public static final String TRADE_SIGN_0003_MSG="卡类型不支持";
	public static final String TRADE_Order_0001 = "ES0049";
	public static final String TRADE_Order_0001_MSG = "交易订单号重复！";
	
	public static final String TRADE_TYPE_ERROR = "ES0050";
	public static final String TRADE_TYPE_ERROR_MSG = "交易类型不正确！";
	
	public static final String TRADE_STATUS_ERROR = "ES0051";
	public static final String TRADE_STATUS_ERROR_MSG = "交易状态不正常！";
	
	public static final String CARD_NO_UNSUPPORT = "ES0011";
	public static final String CARDNO_UNSUPPORT_MSG = "交易卡号不支持！";
   	public static final String BANK_UNSUPPORT="ES0054";
   	public static final String BANK_UNSUPPORT_MSG="商户不支持该银行";
	public static final String CHANNEL_RESPONSE_TIMEOUT="RS000001";
    public static final String CHANNEL_RESPONSE_TIMEOUT_MSG="渠道响应超时";
    public static final String CHANNEL_REQUEST_TIMEOUT="RQ000001";
    public static final String CHANNEL_REQUEST_TIMEOUT_MSG="渠道请求超时";

	public static final String CHECK_ACC_DUBBO_EX="EE0005";
	public static final String CHECK_ACC_DUBBO_EX_MSG="调用账户系统dubbo服务 异常 ";
	
	public static final String NO_USEFUL_CHANNEL ="ES0042";
	public static final String NO_USEFUL_CHANNEL_MSG="无可用通道 "; 
	
	public static final String CHANNEL_SYSERROR ="ES0050";
	public static final String CHANNEL_SYSERROR_MSG="交易路由异常 ";
	public static final String ACCOUNT_FROZEN_FILE ="ES0052";
    public static final String ACCOUNT_FROZEN_FILE_MSG="账户提现资金冻结失败 ";
	
	public static final String NO_BIND_LIST ="ES0055";
	public static final String NO_BIND_LIST_MSG="没有查询到绑卡信息 "; 
	public static final String PAY2_SYSERROR ="ES0051";
	public static final String PAY2_SYSERROR_MSG="调用代付异常 "; 
	
	public static final String BALANCE_NOT_ENOUGH="ES0048";
	public static final String BALANCE_NOT_ENOUGH_MSG="用户账户余额不足";
}
