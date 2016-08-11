package com.ink.channel.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

public class AuthorityInput implements Serializable{
    private static final long serialVersionUID = -4092464797952723563L;
    @NotEmpty
    private String validCode;
    @NotEmpty
    @Digits(fraction = 0, integer = 30)
    private String cardNo;
    @NotEmpty
    private String identityId;
    @NotEmpty
    private String reqId;
    @NotEmpty
    @Digits(fraction = 0, integer = 11)
    private String phoneNo;
    @NotEmpty
    private String idNo;
    @NotEmpty
    private String bankShort;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String channelId;
    @NotEmpty
    private String token;//令牌
    /** 0借记卡1贷记卡 **/
    private String cardType;
    // 01-身份证 02-户口本 03-军人身份证 04-港、澳居民往来内地通行证 05-台湾居民来往大陆通行证 06-护照 07-工商营业执照 08-法人证书 09-组织机构代码证 10-其他
    private String idType;
    @NotEmpty
	private String merchantNo;//商户号
    
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
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

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getBankShort() {
        return bankShort;
    }

    public void setBankShort(String bankShort) {
        this.bankShort = bankShort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
		builder.append("AuthorityInput [validCode=");
		builder.append(validCode);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", identityId=");
		builder.append(identityId);
		builder.append(", reqId=");
		builder.append(reqId);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", bankShort=");
		builder.append(bankShort);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", token=");
		builder.append(token);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
}
