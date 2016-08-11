package com.ink.channel.api.enums;

/**
 * Created by huohb on 2016/4/22.
 */
public enum ChannelBizType {
    SIGN("0001"),//确认绑卡
    PAY("0002"),//代收
    QUICK_AUTH("0003"),//快捷支付发短验
    QUICK_PAY("0004"),//快捷支付支付
    WITHDRAW("0005"),//代付
    PAY_QUERY("0006"),//代收查询
    WITHDRAW_QUERY("0007"),//代付查询
    REFUND("0008"),//退款
    VALIDCODE("0009"),//绑卡发短验 
    QUICK_AGAIN_PAY("0010"),//快捷再次支付
    QUICK_QUERY("0011"),//快捷查询
    QUICK_AGAIN_VALID_CODE("0012"),//快捷支付再次支付发短验
    AUTHEN_NO_VALID_CODE_BIND_CARD("0013"),//支付认证鉴权绑卡
    AUTHEN_NO_VALID_CODE_PAY("0014"),//支付认证支付
    AUTHEN_NO_VALID_CODE_QUERY_PAY("0015")//支付认证查询
    ;
    private ChannelBizType(String code){
        this.code = code;
    }
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
