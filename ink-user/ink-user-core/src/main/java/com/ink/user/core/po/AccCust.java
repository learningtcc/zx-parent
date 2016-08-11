package com.ink.user.core.po;

import java.io.Serializable;
import java.util.Date;

import com.ink.user.common.constant.PatsAtpConstant;
import com.ink.base.util.DateConvertUtils;

public class AccCust implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 2600548593478287236L;

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

	/** 商户编号 标识当前用户所属的商户平台，如果是平台自有用户，可不填 */
	private Long mchId;

	/** 客户号, 填写手机号 */
	private String custId;

	/** 客户类型 0-个人 1-单位 */
	private Integer custType;

	/**
	 * 证件类型 1-身份证 2-户口本 3-军人身份证 4-港、澳居民往来内地通行证 5-台湾居民来往大陆通行证 6-护照 7-工商营业执照
	 * 8-法人证书 9-组织机构代码证 10-其他
	 */
	private String idType;

	/** 证件号码 */
	private String idNo;

	/** 主账号 */
	private Long pacId;

	/** 姓名 */
	private String custName;

	/** 性别 M-男 F-女 O-未知 */
	private String sex;

	/** 出生日期 */
	private Date birthday;

	/** 联系移动电话 */
	private String mblNo;

	/** 联系固定电话 */
	private String telNo;

	/** 联系邮箱 */
	private String email;

	/** 邮编 */
	private String zipcode;

	/** 联系地址 */
	private String address;

	/** 状态 1-启用 2-停用 9-注销 */
	private Integer status;

	/** 删除标识 0-正常 1-删除 */
	private Integer delFlag;

	/** 预留字段1 */
	private String filler1;

	/** 预留字段2 */
	private String filler2;

	/** 预留字段3 */
	private String filler3;

	/**  */
	private Integer version;

	/** 平台用户唯一标识 */
	private Long uid;

	/** 民生会员号 */
	private String thirdMemberNo;

	public String getThirdMemberNo() {
		return thirdMemberNo;
	}

	public void setThirdMemberNo(String thirdMemberNo) {
		this.thirdMemberNo = thirdMemberNo;
	}

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

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId == null ? null : custId.trim();
	}

	public Integer getCustType() {
		return custType;
	}

	public void setCustType(Integer custType) {
		this.custType = custType;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo == null ? null : idNo.trim();
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName == null ? null : custName.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode == null ? null : zipcode.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
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

	public Long getMchId() {
		return mchId;
	}

	public void setMchId(Long mchId) {
		this.mchId = mchId;
	}

	public Long getPacId() {
		return pacId;
	}

	public void setPacId(Long pacId) {
		this.pacId = pacId;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	// 前端页面日期格式转换
	public String getLastUpdateTimeString() {
		return DateConvertUtils.format(getLastUpdateTime(), FORMAT_LAST_UPDATE_TIME);
	}

	public void setLastUpdateTimeString(String value) {
		setLastUpdateTime(DateConvertUtils.parse(value, FORMAT_LAST_UPDATE_TIME, Date.class));
	}

	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}

	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME, Date.class));
	}

	public String getBirthdayString() {
		return DateConvertUtils.format(getBirthday(), FORMAT_BIRTHDAY);
	}

	public void setBirthdayString(String value) {
		setBirthday(DateConvertUtils.parse(value, FORMAT_BIRTHDAY, Date.class));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccCust [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", custType=");
		builder.append(custType);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", pacId=");
		builder.append(pacId);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", mblNo=");
		builder.append(mblNo);
		builder.append(", telNo=");
		builder.append(telNo);
		builder.append(", email=");
		builder.append(email);
		builder.append(", zipcode=");
		builder.append(zipcode);
		builder.append(", address=");
		builder.append(address);
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
		builder.append(", uid=");
		builder.append(uid);
		builder.append(", thirdMemberNo=");
		builder.append(thirdMemberNo);
		builder.append("]");
		return builder.toString();
	}

}