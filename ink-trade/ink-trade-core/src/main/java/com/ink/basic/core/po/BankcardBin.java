/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.basic.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class BankcardBin extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "BankcardBin";
	
	public static final String ALIAS_ID = "id";
	
	public static final String ALIAS_BANK_NAME = "银行名称";
	
	public static final String ALIAS_BANK_SIMPLE_CODE = "银行简码";
	
	public static final String ALIAS_BANK_ORG = "机构代码";
	
	public static final String ALIAS_CARD_BIN = "发卡行标识";
	
	public static final String ALIAS_CARD_BIN_LEN = " 发卡行标识长度";
	
	public static final String ALIAS_CARD_CATEGORY = "卡种 1:借记卡 2:贷记卡3:预付费卡4:准贷记卡";
	
	public static final String ALIAS_MODIFY_TIME = "修改时间";
	
	public static final String ALIAS_CREATE_TIME = "创建时间";
	
	public static final String ALIAS_CARD_NAME = "卡名";
	
	public static final String ALIAS_CARD_NO_LEN = "卡号长度";
	
	
	//date formats
	public static final String FORMAT_MODIFY_TIME = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	
	//columns START
	//id
	private Long id;
	//银行名称
	private String bankName;
	//银行简码
	private String bankSimpleCode;
	//机构代码
	private String bankOrg;
	//发卡行标识
	private String cardBin;
	// 发卡行标识长度
	private Integer cardBinLen;
	//卡种 1:借记卡 2:贷记卡3:预付费卡4:准贷记卡
	private Integer cardCategory;
	//修改时间
	private java.util.Date modifyTime;
	//创建时间
	private java.util.Date createTime;
	//卡名
	private String cardName;
	//卡号长度
	private Integer cardNoLen;
	//columns END

	public BankcardBin(){
	}

	public BankcardBin(
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
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}
	public void setBankSimpleCode(java.lang.String value) {
		this.bankSimpleCode = value;
	}
	
	public java.lang.String getBankSimpleCode() {
		return this.bankSimpleCode;
	}
	public void setBankOrg(java.lang.String value) {
		this.bankOrg = value;
	}
	
	public java.lang.String getBankOrg() {
		return this.bankOrg;
	}
	public void setCardBin(java.lang.String value) {
		this.cardBin = value;
	}
	
	public java.lang.String getCardBin() {
		return this.cardBin;
	}
	public void setCardBinLen(Integer value) {
		this.cardBinLen = value;
	}
	
	public Integer getCardBinLen() {
		return this.cardBinLen;
	}
	public void setCardCategory(Integer value) {
		this.cardCategory = value;
	}
	
	public Integer getCardCategory() {
		return this.cardCategory;
	}
	public String getModifyTimeString() {
		return DateConvertUtils.format(getModifyTime(), FORMAT_MODIFY_TIME);
	}
	public void setModifyTimeString(String value) {
		setModifyTime(DateConvertUtils.parse(value, FORMAT_MODIFY_TIME,java.util.Date.class));
	}
	
	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}
	
	public java.util.Date getModifyTime() {
		return this.modifyTime;
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
	public void setCardName(java.lang.String value) {
		this.cardName = value;
	}
	
	public java.lang.String getCardName() {
		return this.cardName;
	}
	public void setCardNoLen(Integer value) {
		this.cardNoLen = value;
	}
	
	public Integer getCardNoLen() {
		return this.cardNoLen;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("BankName",getBankName())
			.append("BankSimpleCode",getBankSimpleCode())
			.append("BankOrg",getBankOrg())
			.append("CardBin",getCardBin())
			.append("CardBinLen",getCardBinLen())
			.append("CardCategory",getCardCategory())
			.append("ModifyTime",getModifyTime())
			.append("CreateTime",getCreateTime())
			.append("CardName",getCardName())
			.append("CardNoLen",getCardNoLen())
			.toString();
	}
	
	
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BankcardBin == false) return false;
		if(this == obj) return true;
		BankcardBin other = (BankcardBin)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

