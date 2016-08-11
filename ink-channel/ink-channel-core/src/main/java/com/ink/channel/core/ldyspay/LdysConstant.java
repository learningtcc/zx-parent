package com.ink.channel.core.ldyspay;

public class LdysConstant {
	
	public final static String MER_ID=com.ink.channel.core.utils.Constants.MerchantSuffix;;
	
	public final static String CHARSET="UTF-8";//编码格式
	
	public final static String SING_TYPE="RSA";//加密类型
	
	public final static String NOTIFY_URL="notifyUrlQuick";//回调地址
	
	public final static String RES_FORMAT="HTML";//响应类型
	
	public final static String VERSION="4.0";//版本号
	
	public final static String MEDIA_TYPE="MOBILE";//媒介类型 默认手机号
	
	public final static String AMT_TYPE="RMB";//付款币种
	
	public final static String PAY_TYPE="DEBITCARD";//支付方式   鉴权  借记卡
	
	public final static String PAY_CATEGORY="02";//支付方式   支付 借记卡
	
	public final static String EXPRIE_TIME="300";//有效期
	
	public final static String CHANNELID="LDYS";//渠道号
	
	public final static String CERTCODEP8="certCodeP8";//p8证书
	
	public final static String CERTCODECRT="certCodeCrt";//crt证书
	
	public final static String platUrl="http://pay.soopay.net";//固定请求地址
	
	public final static String productName="spay";//固定项目名
	
	public final static String encryptParamters="card_id,valid_date,cvv2,pass_wd,identity_code,card_holder,"
			+ "recv_account,recv_user_name,identity_holder,identityCode,cardHolder,mer_cust_name,account_name,bank_account,endDate";
	
	public final static String encryptCode="EN00001";//加密异常
	
	public final static String decodeCode="DE00001";//解密异常
	
	public final static String successCode="CG00001";//成功code
	
}
