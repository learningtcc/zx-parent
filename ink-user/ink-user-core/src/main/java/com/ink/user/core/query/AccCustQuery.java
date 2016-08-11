/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AccCustQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private Long id;
	/** 创建时间 */
	private Date createTimeBegin;
	private Date createTimeEnd;
	/** 最后修改时间 */
	private Date lastUpdateTimeBegin;
	private Date lastUpdateTimeEnd;
	/** 商户编号 标识当前用户所属的商户平台，如果是平台自有用户，可不填 */
	private Long mchId;
	/** 第三方客户号 */
	private String custId;
	/** 客户类型 0-个人 1-单位 */
	private Integer custType;
	/** 证件类型 01-身份证 02-户口本 03-军人身份证 04-港、澳居民往来内地通行证 05-台湾居民来往大陆通行证 06-护照 07-工商营业执照 08-法人证书 09-组织机构代码证 10-其他 */
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
	private Date birthdayBegin;
	private Date birthdayEnd;
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
	/** 密码 */
	private String pswd;
	/** 密码错误次数 */
	private Integer pswdErrCnt;
	/** 密码错误次数上限 */
	private Integer pswdErrlmtCnt;
	/** 密码找回问题 */
	private String pswdQuestion;
	/** 密码找回答案 */
	private String pswdAnswer;
	/** 密码错误超限锁定标志 0-正常 1-锁定 */
	private Integer pswdErrLock;
	/** 锁定时间 单位: 小时 值为0时不自动解锁 密码错误超限自动解锁时间 */
	private Integer pswdErrLockhours;
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
	/** version */
	private Integer version;
	/** 客户号 */
	private Long uid;
	/** 民生会员号 */
	private String thirdMemberNo;
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(Date value) {
		this.createTimeBegin = value;
	}	
	
	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	
	public Date getLastUpdateTimeBegin() {
		return this.lastUpdateTimeBegin;
	}
	
	public void setLastUpdateTimeBegin(Date value) {
		this.lastUpdateTimeBegin = value;
	}	
	
	public Date getLastUpdateTimeEnd() {
		return this.lastUpdateTimeEnd;
	}
	
	public void setLastUpdateTimeEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastUpdateTimeEnd = calendar.getTime();
		}else {
			this.lastUpdateTimeEnd = value;
		}
	}
	
	public Long getMchId() {
		return this.mchId;
	}
	
	public void setMchId(Long value) {
		this.mchId = value;
	}
	
	public String getCustId() {
		return this.custId;
	}
	
	public void setCustId(String value) {
		this.custId = value;
	}
	
	
	public String getIdType() {
		return this.idType;
	}
	
	public void setIdType(String value) {
		this.idType = value;
	}
	
	public String getIdNo() {
		return this.idNo;
	}
	
	public void setIdNo(String value) {
		this.idNo = value;
	}
	
	public Long getPacId() {
		return this.pacId;
	}
	
	public void setPacId(Long value) {
		this.pacId = value;
	}
	
	public String getCustName() {
		return this.custName;
	}
	
	public void setCustName(String value) {
		this.custName = value;
	}
	
	public String getSex() {
		return this.sex;
	}
	
	public void setSex(String value) {
		this.sex = value;
	}
	
	public Date getBirthdayBegin() {
		return this.birthdayBegin;
	}
	
	public void setBirthdayBegin(Date value) {
		this.birthdayBegin = value;
	}	
	
	public Date getBirthdayEnd() {
		return this.birthdayEnd;
	}
	
	public void setBirthdayEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.birthdayEnd = calendar.getTime();
		}else {
			this.birthdayEnd = value;
		}
	}
	
	public String getMblNo() {
		return this.mblNo;
	}
	
	public void setMblNo(String value) {
		this.mblNo = value;
	}
	
	public String getTelNo() {
		return this.telNo;
	}
	
	public void setTelNo(String value) {
		this.telNo = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getZipcode() {
		return this.zipcode;
	}
	
	public void setZipcode(String value) {
		this.zipcode = value;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getPswd() {
		return this.pswd;
	}
	
	public void setPswd(String value) {
		this.pswd = value;
	}
	
	public Integer getPswdErrCnt() {
		return this.pswdErrCnt;
	}
	
	public void setPswdErrCnt(Integer value) {
		this.pswdErrCnt = value;
	}
	
	public Integer getPswdErrlmtCnt() {
		return this.pswdErrlmtCnt;
	}
	
	public void setPswdErrlmtCnt(Integer value) {
		this.pswdErrlmtCnt = value;
	}
	
	public String getPswdQuestion() {
		return this.pswdQuestion;
	}
	
	public void setPswdQuestion(String value) {
		this.pswdQuestion = value;
	}
	
	public String getPswdAnswer() {
		return this.pswdAnswer;
	}
	
	public void setPswdAnswer(String value) {
		this.pswdAnswer = value;
	}
	
	public Integer getPswdErrLockhours() {
		return this.pswdErrLockhours;
	}
	
	public void setPswdErrLockhours(Integer value) {
		this.pswdErrLockhours = value;
	}
	
	public String getFiller1() {
		return this.filler1;
	}
	
	public void setFiller1(String value) {
		this.filler1 = value;
	}
	
	public String getFiller2() {
		return this.filler2;
	}
	
	public void setFiller2(String value) {
		this.filler2 = value;
	}
	
	public String getFiller3() {
		return this.filler3;
	}
	
	public void setFiller3(String value) {
		this.filler3 = value;
	}
	
	public Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(Integer value) {
		this.version = value;
	}
	
	public Long getUid() {
		return this.uid;
	}
	
	public void setUid(Long value) {
		this.uid = value;
	}
	
	public String getThirdMemberNo() {
		return this.thirdMemberNo;
	}
	
	public void setThirdMemberNo(String value) {
		this.thirdMemberNo = value;
	}
	
	public Integer getCustType() {
		return custType;
	}

	public void setCustType(Integer custType) {
		this.custType = custType;
	}

	public Integer getPswdErrLock() {
		return pswdErrLock;
	}

	public void setPswdErrLock(Integer pswdErrLock) {
		this.pswdErrLock = pswdErrLock;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccCustQuery [id=");
		builder.append(id);
		builder.append(", createTimeBegin=");
		builder.append(createTimeBegin);
		builder.append(", createTimeEnd=");
		builder.append(createTimeEnd);
		builder.append(", lastUpdateTimeBegin=");
		builder.append(lastUpdateTimeBegin);
		builder.append(", lastUpdateTimeEnd=");
		builder.append(lastUpdateTimeEnd);
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
		builder.append(", birthdayBegin=");
		builder.append(birthdayBegin);
		builder.append(", birthdayEnd=");
		builder.append(birthdayEnd);
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
		builder.append(", pswd=");
		builder.append(pswd);
		builder.append(", pswdErrCnt=");
		builder.append(pswdErrCnt);
		builder.append(", pswdErrlmtCnt=");
		builder.append(pswdErrlmtCnt);
		builder.append(", pswdQuestion=");
		builder.append(pswdQuestion);
		builder.append(", pswdAnswer=");
		builder.append(pswdAnswer);
		builder.append(", pswdErrLock=");
		builder.append(pswdErrLock);
		builder.append(", pswdErrLockhours=");
		builder.append(pswdErrLockhours);
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

