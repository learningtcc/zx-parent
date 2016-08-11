package com.ink.trade.api.platform.trade.model.base;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.util.DateConvertUtils;
import com.ink.base.BaseEntity;

public class AuthOrderEntity extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final String TABLE_ALIAS = "AuthOrder";
	public static final String FORMAT_EXPIRE_START_DATE = DATE_FORMAT;
	public static final String FORMAT_EXPIRE_END_DATE = DATE_FORMAT;
	public static final String FORMAT_LASTUPDATE_TIME = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_TOKEN_CREATE_TIME = DATE_FORMAT;
	
	//主键
	private Long id;
	//订单号
	private String orderId;
	//请求流水号
	private String reqId;
	//交易码
	private String txnCode;
	//交易名称
	private String txnName;
	//签约状态
	private Integer status;
	//渠道编号
	private String channelNo;
	//版本号
	private Integer version;
	//用户id
	private String userId;
	//商户号
	private String mchId;
	//银行名称简写
	private String bankNameShort;
	//银行卡号
	private String cardNo;
	//姓名
	private String userName;
	//证件号
	private String idNo;
	//证件类型
	private String idType;
	//手机号
	private String phone;
	//cvv2
	private String cvv2;
	//银行卡类型
	private String cardType;
	//签约信息/
	private String signId;
	//有效期
	private String expireDate;
	//最后更新时间
	private java.util.Date lastupdateTime;
	//创建时间
	private java.util.Date createTime;
	//repCode
	private String repCode;
	//repMsg
	private String repMsg;
	//asileRepCode
	private String asileRepCode;
	//asileRepMsg
	private String asileRepMsg;
	//columns END
	//渠道token
    private String token;
    private Date tokenCreateTime;
	public AuthOrderEntity(){
	}

	public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public AuthOrderEntity(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setOrderId(java.lang.String value) {
		this.orderId = value;
	}
	
	public java.lang.String getOrderId() {
		return this.orderId;
	}
	public void setReqId(java.lang.String value) {
		this.reqId = value;
	}
	
	public java.lang.String getReqId() {
		return this.reqId;
	}
	public void setTxnCode(java.lang.String value) {
		this.txnCode = value;
	}
	
	public java.lang.String getTxnCode() {
		return this.txnCode;
	}
	public void setTxnName(java.lang.String value) {
		this.txnName = value;
	}
	
	public java.lang.String getTxnName() {
		return this.txnName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setChannelNo(java.lang.String value) {
		this.channelNo = value;
	}
	
	public java.lang.String getChannelNo() {
		return this.channelNo;
	}
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setMchId(java.lang.String value) {
		this.mchId = value;
	}
	
	public java.lang.String getMchId() {
		return this.mchId;
	}
	public void setBankNameShort(java.lang.String value) {
		this.bankNameShort = value;
	}
	
	public java.lang.String getBankNameShort() {
		return this.bankNameShort;
	}
	public void setCardNo(java.lang.String value) {
		this.cardNo = value;
	}
	
	public java.lang.String getCardNo() {
		return this.cardNo;
	}
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public Date getTokenCreateTime() {
        return tokenCreateTime;
    }

    public void setTokenCreateTime(Date tokenCreateTime) {
        this.tokenCreateTime = tokenCreateTime;
    }

    public void setIdNo(java.lang.String value) {
		this.idNo = value;
	}
	
	public java.lang.String getIdNo() {
		return this.idNo;
	}
	public void setIdType(String value) {
		this.idType = value;
	}
	
	public String getIdType() {
		return this.idType;
	}
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setCvv2(java.lang.String value) {
		this.cvv2 = value;
	}
	
	public java.lang.String getCvv2() {
		return this.cvv2;
	}
	
	public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getLastupdateTimeString() {
		return DateConvertUtils.format(getLastupdateTime(), FORMAT_LASTUPDATE_TIME);
	}
	public void setLastupdateTimeString(String value) {
		setLastupdateTime(DateConvertUtils.parse(value, FORMAT_LASTUPDATE_TIME,java.util.Date.class));
	}
	
	public void setLastupdateTime(java.util.Date value) {
		this.lastupdateTime = value;
	}
	public String getTokenCreateTimeString() {
		return DateConvertUtils.format(getTokenCreateTime(), FORMAT_TOKEN_CREATE_TIME);
	}
	public void setTokenCreateTimeString(String value) {
		setTokenCreateTime(DateConvertUtils.parse(value, FORMAT_TOKEN_CREATE_TIME,java.util.Date.class));
	}
	public java.util.Date getLastupdateTime() {
		return this.lastupdateTime;
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setRepCode(java.lang.String value) {
		this.repCode = value;
	}
	
	public java.lang.String getRepCode() {
		return this.repCode;
	}
	public void setRepMsg(java.lang.String value) {
		this.repMsg = value;
	}
	
	public java.lang.String getRepMsg() {
		return this.repMsg;
	}
	public void setAsileRepCode(java.lang.String value) {
		this.asileRepCode = value;
	}
	
	public java.lang.String getAsileRepCode() {
		return this.asileRepCode;
	}
	public void setAsileRepMsg(java.lang.String value) {
		this.asileRepMsg = value;
	}
	
	public java.lang.String getAsileRepMsg() {
		return this.asileRepMsg;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OrderId",getOrderId())
			.append("ReqId",getReqId())
			.append("TxnCode",getTxnCode())
			.append("TxnName",getTxnName())
			.append("status",getStatus())
			.append("ChannelNo",getChannelNo())
			.append("Version",getVersion())
			.append("UserId",getUserId())
			.append("MchId",getMchId())
			.append("BankNameShort",getBankNameShort())
			.append("CardNo",getCardNo())
			.append("UserName",getUserName())
			.append("IdNo",getIdNo())
			.append("IdType",getIdType())
			.append("Phone",getPhone())
			.append("Cvv2",getCvv2())
			.append("ExpireDate",getExpireDate())
			.append("LastupdateTime",getLastupdateTime())
			.append("CreateTime",getCreateTime())
			.append("RepCode",getRepCode())
			.append("RepMsg",getRepMsg())
			.append("AsileRepCode",getAsileRepCode())
			.append("AsileRepMsg",getAsileRepMsg())
			.append("Token",getToken())
			.append("TokenCreateTime",getTokenCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AuthOrderEntity == false) return false;
		if(this == obj) return true;
		AuthOrderEntity other = (AuthOrderEntity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	public String getStatusString(){
	    String returnStatus="成功";
	    if(null!=this.getStatus()){
	    	if(this.getStatus()==0){
	    		returnStatus="失败";
	    	}else if(this.getStatus()==1){
	    		returnStatus="成功";
	    	}else if(this.getStatus()==2){
	    		returnStatus="待确认签约";
	    	}
	    }
	    return returnStatus;
	}
}


