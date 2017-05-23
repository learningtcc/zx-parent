package com.ink.route.api.enums;

/**
 * Created by YKDZ075 on 2017/5/21.
 */
public enum PayTypeEnum {
    QuickPay(1,"快捷支付"),WithHolding(2,"代扣"),Payment(3,"代付"),CertifiedaymentP(4,"认证支付");
    private Integer code;
    private String mes;

   private PayTypeEnum(Integer code, String mes) {
        this.code = code;
        this.mes = mes;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
