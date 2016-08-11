package com.ink.trade.core.query;

import java.io.Serializable;

import com.ink.base.BaseQuery;

public class AsileSignQuery extends BaseQuery implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 6062066203861321734L;
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
    private Boolean status;
    //版本号
    private Integer version;
    //删除标识
    private Boolean isDelete;
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
    private Integer idType;
    //手机号
    private String phone;
    //cvv2
    private String cvv2;
    private String mchId;
    //有效开始日期
    private java.util.Date expireStartDate;
    //有效结束日期
    private java.util.Date expireEndDate;
    //银行简称
    private String bankShort;
    //用户id
    private String userId;
    private String payType;//支付类型
    
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    //columns END
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getMchId() {
        return mchId;
    }
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChanelNo() {
		return chanelNo;
	}
	public void setChanelNo(String chanelNo) {
		this.chanelNo = chanelNo;
	}
	public String getChanelName() {
		return chanelName;
	}
	public void setChanelName(String chanelName) {
		this.chanelName = chanelName;
	}
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getLastupdateTime() {
		return lastupdateTime;
	}
	public void setLastupdateTime(java.util.Date lastupdateTime) {
		this.lastupdateTime = lastupdateTime;
	}
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
	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}
	public java.util.Date getExpireStartDate() {
		return expireStartDate;
	}
	public void setExpireStartDate(java.util.Date expireStartDate) {
		this.expireStartDate = expireStartDate;
	}
	public java.util.Date getExpireEndDate() {
		return expireEndDate;
	}
	public void setExpireEndDate(java.util.Date expireEndDate) {
		this.expireEndDate = expireEndDate;
	}
	public String getBankShort() {
		return bankShort;
	}
	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}
    
    
    
}
