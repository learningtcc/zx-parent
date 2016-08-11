/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午2:36:58
 */
package com.ink.route.api.model.in;

import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午2:36:58
 */
public class AsileBankQueryInput extends BaseQuery {

	private static final long serialVersionUID = 3148176768559230877L;

	/** 主键 */
	private java.lang.Long id;
	/** 通道名称 */
	private java.lang.String asileName;
	/** 通道id */
	private java.lang.String asileCode;
	/** 银行名称 */
	private java.lang.String bankName;
	/** 银行id */
	private java.lang.String bankCode;
	/** 通道产品编码 */
	private java.lang.String asileProductCode;
	/** 通道产品名称 */
	private java.lang.String asileProductName;
	/** 是否直连 */
	private java.lang.String asileDirectBank;
	/** 对公对私 */
	private java.lang.String asilePublicPrivate;
	/** 提供接口方式 */
	private java.lang.String asileIntefaceType;
	/** 鉴权方式 */
	private java.lang.String asileAuthType;
	/** 鉴权要素(转换为二进制位数表示) */
	private java.lang.Integer asileAuthElements;
	/** 授权方式 */
	private java.lang.String asileAuthMode;
	/** 支持银行卡类型 */
	private java.lang.String asileBankType;
	/** 支付类型 */
	private java.lang.String asilePayType;
	// 下限
	private BigDecimal asileAmtStart;
	// 上限
	private BigDecimal asileAmtEnd;
	/** 单笔限额 */
	private BigDecimal asileCrashLimit;
	/** 单卡单日限额 */
	private BigDecimal cardCrashDayLimit;
	/** 单卡单月限额 */
	private BigDecimal cardCrashMonthLimit;
	/** 单卡单日限额 */
	private java.lang.Integer cardDayLimits;
	/** 批量限额 */
	private BigDecimal batchCrashLimit;
	/** 批量限次 */
	private java.lang.Integer batchLimit;
	/** 备注 */
	private java.lang.String remark;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** createrId */
	private java.lang.Long createrId;
	/** 创建人 */
	private java.lang.String createrName;
	/** updateTime */
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;
	/** updaterId */
	private java.lang.Long updaterId;
	/** updaterName */
	private java.lang.String updaterName;
	/** 是否删除 */
	private java.lang.String isDelete;
	/** 回盘时间 */
	private java.util.Date asileOnlyTimeBegin;
	private java.util.Date asileOnlyTimeEnd;
	/** 通道服务开始时间 */
	private java.util.Date asileServiceBeginTime;
	/** 通道服务结束时间 */
	private java.util.Date asileServiceEndTime;
	/** 优先级 */
	private java.lang.Integer priority;
	/** 0是分离，1是不分 */
	private java.lang.String borrowSplit;
	/** 短信发送方 */
	private java.lang.String smsSender;
	/** 银行简码 */
	private java.lang.String bankShort;
	/** 渠道银行编码 */
	private java.lang.String asileBankCode;

	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}

	public BigDecimal getAsileAmtStart() {
		return asileAmtStart;
	}

	public void setAsileAmtStart(BigDecimal asileAmtStart) {
		this.asileAmtStart = asileAmtStart;
	}

	public BigDecimal getAsileAmtEnd() {
		return asileAmtEnd;
	}

	public void setAsileAmtEnd(BigDecimal asileAmtEnd) {
		this.asileAmtEnd = asileAmtEnd;
	}

	public java.lang.String getAsileName() {
		return this.asileName;
	}

	public void setAsileName(java.lang.String value) {
		this.asileName = value;
	}

	public java.lang.String getAsileCode() {
		return this.asileCode;
	}

	public void setAsileCode(java.lang.String value) {
		this.asileCode = value;
	}

	public java.lang.String getBankName() {
		return this.bankName;
	}

	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}

	public java.lang.String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(java.lang.String value) {
		this.bankCode = value;
	}

	public java.lang.String getAsileProductCode() {
		return this.asileProductCode;
	}

	public void setAsileProductCode(java.lang.String value) {
		this.asileProductCode = value;
	}

	public java.lang.String getAsileProductName() {
		return this.asileProductName;
	}

	public void setAsileProductName(java.lang.String value) {
		this.asileProductName = value;
	}

	public java.lang.String getAsileDirectBank() {
		return this.asileDirectBank;
	}

	public void setAsileDirectBank(java.lang.String value) {
		this.asileDirectBank = value;
	}

	public java.lang.String getAsilePublicPrivate() {
		return this.asilePublicPrivate;
	}

	public void setAsilePublicPrivate(java.lang.String value) {
		this.asilePublicPrivate = value;
	}

	public java.lang.String getAsileIntefaceType() {
		return this.asileIntefaceType;
	}

	public void setAsileIntefaceType(java.lang.String value) {
		this.asileIntefaceType = value;
	}

	public java.lang.String getAsileAuthType() {
		return this.asileAuthType;
	}

	public void setAsileAuthType(java.lang.String value) {
		this.asileAuthType = value;
	}

	public java.lang.Integer getAsileAuthElements() {
		return this.asileAuthElements;
	}

	public void setAsileAuthElements(java.lang.Integer value) {
		this.asileAuthElements = value;
	}

	public java.lang.String getAsileAuthMode() {
		return this.asileAuthMode;
	}

	public void setAsileAuthMode(java.lang.String value) {
		this.asileAuthMode = value;
	}

	public java.lang.String getAsileBankType() {
		return this.asileBankType;
	}

	public void setAsileBankType(java.lang.String value) {
		this.asileBankType = value;
	}

	public java.lang.String getAsilePayType() {
		return this.asilePayType;
	}

	public void setAsilePayType(java.lang.String value) {
		this.asilePayType = value;
	}

	public BigDecimal getAsileCrashLimit() {
		return this.asileCrashLimit;
	}

	public void setAsileCrashLimit(BigDecimal value) {
		this.asileCrashLimit = value;
	}

	public BigDecimal getCardCrashDayLimit() {
		return this.cardCrashDayLimit;
	}

	public void setCardCrashDayLimit(BigDecimal value) {
		this.cardCrashDayLimit = value;
	}

	public BigDecimal getCardCrashMonthLimit() {
		return this.cardCrashMonthLimit;
	}

	public void setCardCrashMonthLimit(BigDecimal value) {
		this.cardCrashMonthLimit = value;
	}

	public java.lang.Integer getCardDayLimits() {
		return this.cardDayLimits;
	}

	public void setCardDayLimits(java.lang.Integer value) {
		this.cardDayLimits = value;
	}

	public BigDecimal getBatchCrashLimit() {
		return this.batchCrashLimit;
	}

	public void setBatchCrashLimit(BigDecimal value) {
		this.batchCrashLimit = value;
	}

	public java.lang.Integer getBatchLimit() {
		return this.batchLimit;
	}

	public void setBatchLimit(java.lang.Integer value) {
		this.batchLimit = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
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
		if (value != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		} else {
			this.createTimeEnd = value;
		}
	}

	public java.lang.Long getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(java.lang.Long value) {
		this.createrId = value;
	}

	public java.lang.String getCreaterName() {
		return this.createrName;
	}

	public void setCreaterName(java.lang.String value) {
		this.createrName = value;
	}

	public java.util.Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}

	public void setUpdateTimeBegin(java.util.Date value) {
		this.updateTimeBegin = value;
	}

	public java.util.Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}

	public void setUpdateTimeEnd(java.util.Date value) {
		if (value != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.updateTimeEnd = calendar.getTime();
		} else {
			this.updateTimeEnd = value;
		}
	}

	public java.lang.Long getUpdaterId() {
		return this.updaterId;
	}

	public void setUpdaterId(java.lang.Long value) {
		this.updaterId = value;
	}

	public java.lang.String getUpdaterName() {
		return this.updaterName;
	}

	public void setUpdaterName(java.lang.String value) {
		this.updaterName = value;
	}

	public java.lang.String getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(java.lang.String value) {
		this.isDelete = value;
	}

	public java.util.Date getAsileOnlyTimeBegin() {
		return this.asileOnlyTimeBegin;
	}

	public void setAsileOnlyTimeBegin(java.util.Date value) {
		this.asileOnlyTimeBegin = value;
	}

	public java.util.Date getAsileOnlyTimeEnd() {
		return this.asileOnlyTimeEnd;
	}

	public void setAsileOnlyTimeEnd(java.util.Date value) {
		if (value != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.asileOnlyTimeEnd = calendar.getTime();
		} else {
			this.asileOnlyTimeEnd = value;
		}
	}

	public java.util.Date getAsileServiceBeginTime() {
		return this.asileServiceBeginTime;
	}

	public void setAsileServiceBeginTime(java.util.Date value) {
		this.asileServiceBeginTime = value;
	}

	public java.util.Date getAsileServiceEndTime() {
		return this.asileServiceEndTime;
	}

	public void setAsileServiceEndTime(java.util.Date value) {
		this.asileServiceEndTime = value;
	}

	public java.lang.Integer getPriority() {
		return this.priority;
	}

	public void setPriority(java.lang.Integer value) {
		this.priority = value;
	}

	public java.lang.String getBorrowSplit() {
		return this.borrowSplit;
	}

	public void setBorrowSplit(java.lang.String value) {
		this.borrowSplit = value;
	}

	public java.lang.String getSmsSender() {
		return this.smsSender;
	}

	public void setSmsSender(java.lang.String value) {
		this.smsSender = value;
	}

	public java.lang.String getBankShort() {
		return this.bankShort;
	}

	public void setBankShort(java.lang.String value) {
		this.bankShort = value;
	}

	public java.lang.String getAsileBankCode() {
		return this.asileBankCode;
	}

	public void setAsileBankCode(java.lang.String value) {
		this.asileBankCode = value;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
