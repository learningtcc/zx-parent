/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AccCardQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private java.lang.Long id;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后修改时间 */
	private java.util.Date lastUpdateTimeBegin;
	private java.util.Date lastUpdateTimeEnd;
	/** 银行卡号 */
	private java.lang.String cardNo;
	/** 商户编号 标识当前用户所属的商户平台，如果是平台自有用户，可不填 */
	private java.lang.Long mchId;
	/** 第三方客户号 */
	private java.lang.String custId;
	/** 客户类型 0-个人 1-单位 */
	private java.lang.Boolean custType;
	/** 银行卡类型 0-借记卡 1-信用卡 2-准贷记卡 3-储蓄账户 4-企业结算账户 9-未知 */
	private java.lang.String cardType;
	/** 银行预留手机号 */
	private java.lang.String bankMblNo;
	/** 证件类型 01-身份证 02-户口本 03-军人身份证 04-港、澳居民往来内地通行证 05-台湾居民来往大陆通行证 06-护照 07-工商营业执照 08-法人证书 09-组织机构代码证 10-其他 */
	private java.lang.String idType;
	/** 证件号码 */
	private java.lang.String idNo;
	/** 姓名 */
	private java.lang.String custName;
	/** 状态 1-启用 2-停用 9-注销 */
	private java.lang.Boolean status;
	/** version */
	private java.lang.Integer version;
	/** expDate */
	private java.lang.String expDate;
	/** cvn2 */
	private java.lang.String cvn2;
	/** delFlag */
	private java.lang.Boolean delFlag;
	/** 所属银行编号 */
	private java.lang.String bankCode;
	/** 记录卡的唯一标识 */
	private java.lang.Long bindCardId;
	/** (卡序列号)-民生托管生成 */
	private java.lang.String cardSerialNo;
	/** 客户号 */
	private java.lang.Long uid;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}	
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	
	public java.util.Date getLastUpdateTimeBegin() {
		return this.lastUpdateTimeBegin;
	}
	
	public void setLastUpdateTimeBegin(java.util.Date value) {
		this.lastUpdateTimeBegin = value;
	}	
	
	public java.util.Date getLastUpdateTimeEnd() {
		return this.lastUpdateTimeEnd;
	}
	
	public void setLastUpdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastUpdateTimeEnd = calendar.getTime();
		}else {
			this.lastUpdateTimeEnd = value;
		}
	}
	
	public java.lang.String getCardNo() {
		return this.cardNo;
	}
	
	public void setCardNo(java.lang.String value) {
		this.cardNo = value;
	}
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.String getCustId() {
		return this.custId;
	}
	
	public void setCustId(java.lang.String value) {
		this.custId = value;
	}
	
	public java.lang.Boolean getCustType() {
		return this.custType;
	}
	
	public void setCustType(java.lang.Boolean value) {
		this.custType = value;
	}
	
	public java.lang.String getCardType() {
		return this.cardType;
	}
	
	public void setCardType(java.lang.String value) {
		this.cardType = value;
	}
	
	public java.lang.String getBankMblNo() {
		return this.bankMblNo;
	}
	
	public void setBankMblNo(java.lang.String value) {
		this.bankMblNo = value;
	}
	
	public java.lang.String getIdType() {
		return this.idType;
	}
	
	public void setIdType(java.lang.String value) {
		this.idType = value;
	}
	
	public java.lang.String getIdNo() {
		return this.idNo;
	}
	
	public void setIdNo(java.lang.String value) {
		this.idNo = value;
	}
	
	public java.lang.String getCustName() {
		return this.custName;
	}
	
	public void setCustName(java.lang.String value) {
		this.custName = value;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.lang.String getExpDate() {
		return this.expDate;
	}
	
	public void setExpDate(java.lang.String value) {
		this.expDate = value;
	}
	
	public java.lang.String getCvn2() {
		return this.cvn2;
	}
	
	public void setCvn2(java.lang.String value) {
		this.cvn2 = value;
	}
	
	public java.lang.Boolean getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(java.lang.Boolean value) {
		this.delFlag = value;
	}
	
	public java.lang.String getBankCode() {
		return this.bankCode;
	}
	
	public void setBankCode(java.lang.String value) {
		this.bankCode = value;
	}
	
	public java.lang.Long getBindCardId() {
		return this.bindCardId;
	}
	
	public void setBindCardId(java.lang.Long value) {
		this.bindCardId = value;
	}
	
	public java.lang.String getCardSerialNo() {
		return this.cardSerialNo;
	}
	
	public void setCardSerialNo(java.lang.String value) {
		this.cardSerialNo = value;
	}
	
	public java.lang.Long getUid() {
		return this.uid;
	}
	
	public void setUid(java.lang.Long value) {
		this.uid = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

