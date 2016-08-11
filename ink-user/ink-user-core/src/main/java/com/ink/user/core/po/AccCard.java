package com.ink.user.core.po;

import java.io.Serializable;
import java.util.Date;

public class AccCard implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialersionUID = -7227165082681443014L;

	/** 数据库表主键 */
	private String id;

	/** 创建时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date lastUpdateTime;

	/** 银行卡号 */
	private String cardNo;

	/** 商户信息表主键 */
//	private Long accMchId;

	/** 商户编号 标识当前用户所属的商户平台，如果是平台自有用户，可不填 */
	private Long mchId;

	/** 客户信息表主键 */
//	private Long accCustId;

	/** 客户号, 填写手机号 */
	private String custId;

	/** 客户类型 0-个人 1-单位 */
	private Integer custType;

	/** 银行卡类型 0-借记卡 1-信用卡 2-准贷记卡 3-储蓄账户 4-企业结算账户 9-未知 */
	private String cardType;

	/** 银行预留手机号 */
	private String bankMblNo;
	
	/** 证件类型 */
	private String idType;
	/** 证件号码 */
	private String idNo;

	/** 姓名 */
	private String custName;

	/** 状态 1-启用 2-停用 9-注销 */
	private Integer status;

	/**  */
	private Integer version;

	/** 信用卡有效期 */
	private String expDate;

	/** 信用卡验证码 */
	private String cvn2;

	/** 删除标识 0-正常 1-删除 */
	private Integer delFlag;

	/** 所属银行编号 */
	private String bankCode;

	/** 记录卡的唯一标识 */
	private Long bindCardId;

	/** (卡序列号)-民生托管生成 */
	private String cardSerialNo;
	/**平台客户号*/
	private Long uid;

	public Long getMchId() {
		return mchId;
	}

	public void setMchId(Long mchId) {
		this.mchId = mchId;
	}

	public Long getBindCardId() {
		return bindCardId;
	}

	public void setBindCardId(Long bindCardId) {
		this.bindCardId = bindCardId;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getCardSerialNo() {
		return cardSerialNo;
	}

	public void setCardSerialNo(String cardSerialNo) {
		this.cardSerialNo = cardSerialNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo == null ? null : cardNo.trim();
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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBankMblNo() {
		return bankMblNo;
	}

	public void setBankMblNo(String bankMblNo) {
		this.bankMblNo = bankMblNo == null ? null : bankMblNo.trim();
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCvn2() {
		return cvn2;
	}

	public void setCvn2(String cvn2) {
		this.cvn2 = cvn2;
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
		builder.append("AccCard [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", custType=");
		builder.append(custType);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", bankMblNo=");
		builder.append(bankMblNo);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", status=");
		builder.append(status);
		builder.append(", version=");
		builder.append(version);
		builder.append(", expDate=");
		builder.append(expDate);
		builder.append(", cvn2=");
		builder.append(cvn2);
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append(", bankCode=");
		builder.append(bankCode);
		builder.append(", bindCardId=");
		builder.append(bindCardId);
		builder.append(", cardSerialNo=");
		builder.append(cardSerialNo);
		builder.append(", uid=");
		builder.append(uid);
		builder.append("]");
		return builder.toString();
	}
	
}