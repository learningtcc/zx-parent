package com.ink.trade.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class BindCardQueryInput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4730397090830335242L;
    @NotNull(message="商户号不可为空")
    private String merchantId;
    @NotNull(message="用户号不可为空")
    private String userId;
    @NotNull(message="支付类型不可为空")
    private String payType;


    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BindCardInput [version=");
        builder.append(", merchantId=");
        builder.append(merchantId);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", payType=");
        builder.append(payType);
        builder.append("]");
        return builder.toString();
    }

}
