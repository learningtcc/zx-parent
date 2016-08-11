package com.ink.channel.api.model.in;


import java.io.Serializable;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

public class ValidCodeInput implements Serializable{
    private static final long serialVersionUID = 8222068113090604793L;
    /**用户名**/
    @NotEmpty(message="用户名不能为空")
    private String userName;
    /**证件号**/
    @NotEmpty(message="证件号不能为空")
    private String idNo;
    /**证件类型**/
    @NotEmpty(message="证件类型不能为空")
    private String idType;
    /**银行卡号**/
    @NotEmpty(message="银行卡号不能为空")
    private String cardNo;
    /**签约id，唯一用户标识**/
    @NotEmpty(message="签约id不能为空")
    private String identityid;
    /**请求流水**/
    @NotEmpty(message="请求流水不能为空")
    private String reqId;
    /**银行简称*/
    @NotEmpty(message="银行简称不能为空")
    private String bankShort;
    /**银行预留手机号**/
    @NotEmpty(message="银行预留手机号不能为空")
    @Digits(fraction = 0, integer = 11)
    private String phoneNo;
    /**渠道号**/
    @NotEmpty(message="渠道号不能为空")
    private String channelId;
    @NotEmpty
	private String merchantNo;//商户号
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIdNo() {
        return idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getIdType() {
        return idType;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getIdentityid() {
        return identityid;
    }
    public void setIdentityid(String identityid) {
        this.identityid = identityid;
    }
    public String getReqId() {
        return reqId;
    }
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
	public String getBankShort() {
		return bankShort;
	}
	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValidCodeInput [userName=");
		builder.append(userName);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", identityid=");
		builder.append(identityid);
		builder.append(", reqId=");
		builder.append(reqId);
		builder.append(", bankShort=");
		builder.append(bankShort);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
}
