package com.ink.asile.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;


public class AsileSign extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AsileSign";
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_LASTUPDATE_TIME = DATE_FORMAT;
	public static final String FORMAT_EXPIRE_START_DATE = DATE_FORMAT;
	public static final String FORMAT_EXPIRE_END_DATE = DATE_FORMAT;
	
	//columns START
	//主键
	private Long id;
	//渠道编号
	private String chanelNo;
	//渠道名称
	private String chanelName;
	//签约 信息
	private String signId;
	//绑卡信息表主键
	private Long cid;
	//状态
	private Integer status;
	//版本号
	private Integer version;
	//删除标识
	private Integer isDelete;
	//备注
	private String remark;
	//签约时间
	private java.util.Date createTime;
	//最后修改时间
	private java.util.Date lastupdateTime;
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
	//有效期
	private String expireDate;
	//银行简称
	private String bankShort;
	//用户id
	private String userId;
	//columns END
    private String mchId;
    private String cardType;
    private String reqId;
    private String authOrderId;
    private String payType;
    
	public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public AsileSign(){
	}

	public AsileSign(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setChanelNo(java.lang.String value) {
		this.chanelNo = value;
	}
	
	public java.lang.String getChanelNo() {
		return this.chanelNo;
	}
	public void setChanelName(java.lang.String value) {
		this.chanelName = value;
	}
	
	public java.lang.String getChanelName() {
		return this.chanelName;
	}
	public void setSignId(java.lang.String value) {
		this.signId = value;
	}
	
	public java.lang.String getSignId() {
		return this.signId;
	}
	public void setCid(java.lang.Long value) {
		this.cid = value;
	}
	
	public java.lang.Long getCid() {
		return this.cid;
	}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setVersion(Integer value) {
		this.version = value;
	}
	
	public Integer getVersion() {
		return this.version;
	}

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
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
	
	public java.util.Date getLastupdateTime() {
		return this.lastupdateTime;
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
	
	public void setBankShort(java.lang.String value) {
		this.bankShort = value;
	}
	
	public java.lang.String getBankShort() {
		return this.bankShort;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getAuthOrderId() {
        return authOrderId;
    }

    public void setAuthOrderId(String authOrderId) {
        this.authOrderId = authOrderId;
    }

    
	
	public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "AsileSign [id=" + id + ", chanelNo=" + chanelNo + ", chanelName=" + chanelName + ", signId=" + signId
                        + ", cid=" + cid + ", status=" + status + ", version=" + version + ", isDelete=" + isDelete
                        + ", remark=" + remark + ", createTime=" + createTime + ", lastupdateTime=" + lastupdateTime
                        + ", cardNo=" + cardNo + ", userName=" + userName + ", idNo=" + idNo + ", idType=" + idType
                        + ", phone=" + phone + ", cvv2=" + cvv2 
                        + ", expireDate=" + expireDate + ", bankShort=" + bankShort + ", userId=" + userId
                        + ", mchId=" + mchId + ", cardType=" + cardType + ", reqId=" + reqId + ", authOrderId="
                        + authOrderId + "]";
    }

    public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AsileSign == false) return false;
		if(this == obj) return true;
		AsileSign other = (AsileSign)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

