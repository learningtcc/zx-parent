/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月8日 上午10:10:44
 */
package com.ink.trade.api.platform.basic.model.in;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月8日 上午10:10:44
 */
public class BankcardBinQueryInput extends BaseQuery implements Serializable{
	
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Integer id;
	/** 银行名称 */
	private java.lang.String bankName;
	/** 银行简码 */
	private java.lang.String bankSimpleCode;
	/** 机构代码 */
	private java.lang.String bankOrg;
	/** 发卡行标识 */
	private java.lang.String cardBin;
	/**  发卡行标识长度 */
	private Integer cardBinLen;
	/** 卡种 1:借记卡 2:贷记卡3:预付费卡4:准贷记卡 */
	private Integer cardCategory;
	/** 修改时间 */
	private java.util.Date modifyTimeBegin;
	private java.util.Date modifyTimeEnd;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 卡名 */
	private java.lang.String cardName;
	/** 卡号长度 */
	private Integer cardNoLen;
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}
	
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public java.lang.String getBankSimpleCode() {
		return this.bankSimpleCode;
	}
	
	public void setBankSimpleCode(java.lang.String value) {
		this.bankSimpleCode = value;
	}
	
	public java.lang.String getBankOrg() {
		return this.bankOrg;
	}
	
	public void setBankOrg(java.lang.String value) {
		this.bankOrg = value;
	}
	
	public java.lang.String getCardBin() {
		return this.cardBin;
	}
	
	public void setCardBin(java.lang.String value) {
		this.cardBin = value;
	}
	
	public Integer getCardBinLen() {
		return this.cardBinLen;
	}
	
	public void setCardBinLen(Integer value) {
		this.cardBinLen = value;
	}
	
	public Integer getCardCategory() {
		return this.cardCategory;
	}
	
	public void setCardCategory(Integer value) {
		this.cardCategory = value;
	}
	
	public java.util.Date getModifyTimeBegin() {
		return this.modifyTimeBegin;
	}
	
	public void setModifyTimeBegin(java.util.Date value) {
		this.modifyTimeBegin = value;
	}	
	
	public java.util.Date getModifyTimeEnd() {
		return this.modifyTimeEnd;
	}
	
	public void setModifyTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.modifyTimeEnd = calendar.getTime();
		}else {
			this.modifyTimeEnd = value;
		}
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
	
	public java.lang.String getCardName() {
		return this.cardName;
	}
	
	public void setCardName(java.lang.String value) {
		this.cardName = value;
	}
	
	public Integer getCardNoLen() {
		return this.cardNoLen;
	}
	
	public void setCardNoLen(Integer value) {
		this.cardNoLen = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

}
