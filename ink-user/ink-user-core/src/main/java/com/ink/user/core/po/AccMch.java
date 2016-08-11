package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ink.base.util.DateConvertUtils;
import com.ink.user.common.constant.PatsAtpConstant;

public class AccMch implements Serializable{
    /** 
	 * @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1141977290451195255L;
	
	// date formats
	public static final String FORMAT_CREATE_TIME = PatsAtpConstant.DATE_TIME_FORMAT;
	public static final String FORMAT_LAST_UPDATE_TIME = PatsAtpConstant.DATE_TIME_FORMAT;
	public static final String FORMAT_BIRTHDAY = PatsAtpConstant.DATE_FORMAT;

	/** 数据库表主键 */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date lastUpdateTime;

    /** 商户编号 */
    private Long mchId;

    /** 客户号, 填写手机号 作为商户的法人资金账户 */
    private String custId;

    /** 商户名称 */
    private String mchName;

    /** 商户类别表主键 */
    private Long accMccId;

    /** 商户类别 */
    private String mcc;

    /** 商户性质 1-国营 2-股份制 3-集体 4-中外合资、合作 5-外商独资 6-私营合伙 7-私营独资 8-个体 9-其他 */
    private Integer mchNature;

    /** 组织机构代码 */
    private String orgCode;

    /** 注册登记号 */
    private String regNo;

    /** 注册资本 */
    private BigDecimal regAmt;

    /** 注册地址 */
    private String regAddress;

    /** 营业地址 */
    private String busAddress;

    /** 业务联系人 */
    private String contact;

    /** 联系移动电话 */
    private String mblNo;

    /** 联系固定电话 */
    private String telNo;

    /** 联系邮箱 */
    private String email;

    /** 状态 1-启用 2-停用 3-待审核 4-审核拒绝 9-注销 */
    private Integer status;

    /** 删除标识 0-正常 1-删除 */
    private Boolean delFlag;

    /** 预留字段1 */
    private String filler1;

    /** 预留字段2 */
    private String filler2;

    /** 预留字段3 */
    private String filler3;

    /**  */
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }


    public Long getMchId() {
		return mchId;
	}

	public void setMchId(Long mchId) {
		this.mchId = mchId;
	}

	public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName == null ? null : mchName.trim();
    }

    public Long getAccMccId() {
        return accMccId;
    }

    public void setAccMccId(Long accMccId) {
        this.accMccId = accMccId;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc == null ? null : mcc.trim();
    }

    public Integer getMchNature() {
        return mchNature;
    }

    public void setMchNature(Integer mchNature) {
        this.mchNature = mchNature;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo == null ? null : regNo.trim();
    }

    public BigDecimal getRegAmt() {
        return regAmt;
    }

    public void setRegAmt(BigDecimal regAmt) {
        this.regAmt = regAmt;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress == null ? null : regAddress.trim();
    }

    public String getBusAddress() {
        return busAddress;
    }

    public void setBusAddress(String busAddress) {
        this.busAddress = busAddress == null ? null : busAddress.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getMblNo() {
        return mblNo;
    }

    public void setMblNo(String mblNo) {
        this.mblNo = mblNo == null ? null : mblNo.trim();
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo == null ? null : telNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getFiller1() {
        return filler1;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1 == null ? null : filler1.trim();
    }

    public String getFiller2() {
        return filler2;
    }

    public void setFiller2(String filler2) {
        this.filler2 = filler2 == null ? null : filler2.trim();
    }

    public String getFiller3() {
        return filler3;
    }

    public void setFiller3(String filler3) {
        this.filler3 = filler3 == null ? null : filler3.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public String getLastUpdateTimeString() {
		return DateConvertUtils.format(getLastUpdateTime(), FORMAT_LAST_UPDATE_TIME);
	}
	public void setLastUpdateTimeString(String value) {
		setLastUpdateTime(DateConvertUtils.parse(value, FORMAT_LAST_UPDATE_TIME,Date.class));
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,Date.class));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccMch [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", mchName=");
		builder.append(mchName);
		builder.append(", accMccId=");
		builder.append(accMccId);
		builder.append(", mcc=");
		builder.append(mcc);
		builder.append(", mchNature=");
		builder.append(mchNature);
		builder.append(", orgCode=");
		builder.append(orgCode);
		builder.append(", regNo=");
		builder.append(regNo);
		builder.append(", regAmt=");
		builder.append(regAmt);
		builder.append(", regAddress=");
		builder.append(regAddress);
		builder.append(", busAddress=");
		builder.append(busAddress);
		builder.append(", contact=");
		builder.append(contact);
		builder.append(", mblNo=");
		builder.append(mblNo);
		builder.append(", telNo=");
		builder.append(telNo);
		builder.append(", email=");
		builder.append(email);
		builder.append(", status=");
		builder.append(status);
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append(", filler1=");
		builder.append(filler1);
		builder.append(", filler2=");
		builder.append(filler2);
		builder.append(", filler3=");
		builder.append(filler3);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
	
}