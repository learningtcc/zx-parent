package com.ink.channel.core.boofoopay.constant;

import com.ink.channel.core.utils.Constants;

/**
 * 宝付支付相关常量类
 *
 * @author zhengchao
 * @create 2016-2-27 下午14:21:00
 */
public class BoofooConstant {

    /**
     * 成功
     */
    public static final String BOOFOO_SUCCESS = "0000";
    /**
     * 失败
     */
    public static final String BOOFOO_FAIL = "BF00111";
    /**
     * 处理中
     */
    public static final String BOOFOO_INIT = "BF00115";
    /**
     * 宝付渠道号
     */
    public static final String ASILE_NO = "10005";
    /**
     * 商户号后缀
     */
    public static final String MEMBER_SUFFIX = Constants.MerchantSuffix;
    /**
     * 终端号后缀
     */
    public static final String TERMINAL_SUFFIX = "Terminal";
    /**
     * URL后缀
     */
    public static final String URL_SUFFIX = "Url";
    /**
     * 证书后缀
     */
    public static final String CERT_SUFFIX = "Cert";
    /**
     * 公钥后缀
     */
    public static final String CERT_PUB_SUFFIX = "Pub";
    /**
     * 私钥后缀
     */
    public static final String CERT_PRI_SUFFIX = "Pri";
    /**
     * 代付
     */
    public static final String PAYAUTHENTICATION_URL = "payAuthentication";
    /**
     * 代付查询
     */
    public static final String QUERYPAYAUTHENTICATION_URL = "queryPayAuthentication";
    /**
     * 绑卡
     */
    public static final String BINDCARD_URL = "bindCard";
    /**
     * 解除绑卡
     */
    public static final String UNBINDCARD_URL = "unbindCard";
    /**
     * 查询绑卡
     */
    public static final String QUERYBINDCARD_URL = "queryBindCard";
    /**
     * 认证支付
     */
    public static final String RZPAY_URL = "rzPay";
    /**
     * 认证支付查询
     */
    public static final String QUERYRZPAY_URL = "queryRzPay";
    /**
     * 代收
     */
    public static final String PAY2ACCOUNT_URL = "pay2Account";
    /**
     * 查询代收
     */
    public static final String QUERYPAY2ACCOUNT_URL = "queryPay2Account";
    /**
     * 发送短验
     */
    public static final String VALIDATION_CODE = "validationCode";


}
