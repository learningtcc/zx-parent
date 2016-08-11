package com.ink.channel.core.model.in;


public class AsileValidCodeInput {

    /**用户名**/
    private String userName;
    /**证件号**/
    private String idNo;
    // 01-身份证 02-户口本 03-军人身份证 04-港、澳居民往来内地通行证 05-台湾居民来往大陆通行证 06-护照 07-工商营业执照 08-法人证书 09-组织机构代码证 10-其他
    /**证件类型**/
    private String idType;
    /**银行卡号**/
    private String cardNo;
    /**签约id，唯一用户标识**/
    private String identityid;
    /**请求流水**/
    private String reqId;
    /**银行预留手机号**/
    private String phoneNo;
    /**银行简称*/
    private String bankShort;
    /**渠道号**/
    private String channelId;
    
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
}
