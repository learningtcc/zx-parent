package com.ink.balance.api.constants;

/**
 * @author bo.chen
 * @Description 支付方式常量，供交易系統MQ推送使用
 * @date 2016年4月20日 上午10:18:27
 */
public class PayStatusConst {

    // 待支付
    public final static String UN_PAY = "1";

    // 支付成功
    public final static String PAY_SUCCESS = "2";

    // 支付失败
    public final static String PAY_FAIL = "3";
}
