package com.ink.channel.core.boofoopay.constant;

/**
 * 宝付证书路径相关常量类
 * 
 * @author zhengchao
 * @create 2016-2-27 下午14:21:00
 */
public class CertificateConstant {

	/** 私钥路径 */
	public static final String PRIVATE_KEY_OLD = "boofoopay/m_pri.pfx";//代付私钥
	public static final String PRIVATE_KEY = "boofoopay/bfkey_100000276@@100000990.pfx";//代收 绑卡 和支付发短验私钥
	public static final String PRIVATE_KEY_NO_VALIDATIONCODE = "boofoopay/bfkey_100000276@@100000994.pfx";//代收 绑卡 和支付不发验证码私钥
	public static final String DS_PRIVATE_KEY="boofoopay/bfkey_100000749@@100000933.pfx";
	/**公钥路径 */
	public static final String PUBLIC_KEY_OLD = "boofoopay/baofoo_pub.cer";//代付公钥 
	public static final String PUBLIC_KEY = "boofoopay/bfkey_100000276@@100000990.cer";// 代收绑卡和支付发短验公钥
	public static final String PUBLIC_KEY_NO_VALIDATIONCODE = "boofoopay/bfkey_100000276@@100000994.cer";//代收 绑卡 和支付不发验证码公钥
	public static final String DS_PUBLIC_KEY="boofoopay/bfkey_100000749@@100000933.cer";
	/**私钥密码*/
	public static final String PRIVATE_KEY_PWD="123456";
	public static final String DS_PRIVATE_KEY_PWD="100000749_272769";

}
