package com.ink.balance.api.constants;

/**
 * @author bo.chen
 * @Description 支付方式常量，供交易系統MQ推送使用
 * @date 2016年4月20日 上午10:18:27
 */
public class PayMethodConst {

    /**
     * 1：代收、2：代付、3：网银、4：快捷、5：认证
     */

    // 代收
    public final static String COLLECT = "1";

    // 代付
    public final static String PAY = "2";

    // 网银
    public final static String BANK = "3";

    // 快捷
    public final static String SHORTCUT = "4";

    // 认证
    public final static String AUTH = "5";
}
