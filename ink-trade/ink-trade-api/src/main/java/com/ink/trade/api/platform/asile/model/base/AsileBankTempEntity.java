/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午3:38:56
 */
package com.ink.trade.api.platform.asile.model.base;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午3:38:56
 */
public class AsileBankTempEntity extends BaseEntity implements Serializable{
	
private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AsileBankTemp";
//	
//	public static final String ALIAS_ID = "主键";
//	
//	public static final String ALIAS_ASILE_NAME = "通道名称";
//	
//	public static final String ALIAS_ASILE_CODE = "通道code";
//	
//	public static final String ALIAS_BANK_NAME = "银行名称";
//	
//	public static final String ALIAS_BANK_CODE = "银行code";
//	
//	public static final String ALIAS_ASILE_BANK_TYPE = "支持银行卡类型";
//	
//	public static final String ALIAS_ASILE_PAY_TYPE = "支付类型";
//	
//	public static final String ALIAS_ASILE_CRASH_LIMIT = "单笔限额";
//	
//	public static final String ALIAS_CARD_CRASH_DAY_LIMIT = "单卡单日限额";
//	
//	public static final String ALIAS_CARD_CRASH_MONTH_LIMIT = "单卡单月限额";
//	
//	public static final String ALIAS_CARD_DAY_LIMITS = "单卡单日限额";
//	
//	public static final String ALIAS_REMARK = "备注";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_CREATER_ID = "createrId";
//	
//	public static final String ALIAS_CREATER_NAME = "创建人";
//	
//	public static final String ALIAS_UPDATE_TIME = "updateTime";
//	
//	public static final String ALIAS_UPDATER_ID = "updaterId";
//	
//	public static final String ALIAS_UPDATER_NAME = "updaterName";
//	
//	public static final String ALIAS_IS_DELETE = "是否删除";
//	
//	public static final String ALIAS_ASILE_SERVICE_BEGIN_TIME = "通道服务开始时间";
//	
//	public static final String ALIAS_ASILE_SERVICE_END_TIME = "通道服务结束时间";
//	
//	public static final String ALIAS_PRIORITY = "优先级";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	public static final String FORMAT_ASILE_SERVICE_BEGIN_TIME = DATE_FORMAT;
	public static final String FORMAT_ASILE_SERVICE_END_TIME = DATE_FORMAT;
	
	//columns START
	//主键
	private Long id;
	//通道名称
	private String asileName;
	//通道code
	private String asileCode;
	//银行名称
	private String bankName;
	//银行code
	private String bankCode;
	//支持银行卡类型
	private String asileBankType;
	//支付类型
	private String asilePayType;
	//下限
	private BigDecimal asileAmtStart;
	//上限
	private BigDecimal asileAmtEnd;
	//单笔限额
	private BigDecimal asileCrashLimit;
	//单卡单日限额
	private BigDecimal cardCrashDayLimit;
	//单卡单月限额
	private BigDecimal cardCrashMonthLimit;
	//单卡单日限额
	private Integer cardDayLimits;
	//备注
	private String remark;
	//创建时间
	private java.util.Date createTime;
	//createrId
	private Long createrId;
	//创建人
	private String createrName;
	//updateTime
	private java.util.Date updateTime;
	//updaterId
	private Long updaterId;
	//updaterName
	private String updaterName;
	//是否删除
	private String isDelete;
	//通道服务开始时间
	private java.util.Date asileServiceBeginTime;
	//通道服务结束时间
	private java.util.Date asileServiceEndTime;
	//优先级
	private Integer priority;
	//columns END

	public AsileBankTempEntity(){
	}


	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setAsileName(java.lang.String value) {
		this.asileName = value;
	}
	
	public java.lang.String getAsileName() {
		return this.asileName;
	}
	public void setAsileCode(java.lang.String value) {
		this.asileCode = value;
	}
	
	public java.lang.String getAsileCode() {
		return this.asileCode;
	}
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}
	public void setBankCode(java.lang.String value) {
		this.bankCode = value;
	}
	
	public java.lang.String getBankCode() {
		return this.bankCode;
	}
	public void setAsileBankType(java.lang.String value) {
		this.asileBankType = value;
	}
	
	public java.lang.String getAsileBankType() {
		return this.asileBankType;
	}
	public void setAsilePayType(java.lang.String value) {
		this.asilePayType = value;
	}
	
	public java.lang.String getAsilePayType() {
		return this.asilePayType;
	}
	public void setAsileCrashLimit(BigDecimal value) {
		this.asileCrashLimit = value;
	}
	
	public BigDecimal getAsileCrashLimit() {
		return this.asileCrashLimit;
	}
	public void setCardCrashDayLimit(BigDecimal value) {
		this.cardCrashDayLimit = value;
	}
	
	public BigDecimal getCardCrashDayLimit() {
		return this.cardCrashDayLimit;
	}
	public void setCardCrashMonthLimit(BigDecimal value) {
		this.cardCrashMonthLimit = value;
	}
	
	public BigDecimal getCardCrashMonthLimit() {
		return this.cardCrashMonthLimit;
	}
	public void setCardDayLimits(java.lang.Integer value) {
		this.cardDayLimits = value;
	}
	
	public java.lang.Integer getCardDayLimits() {
		return this.cardDayLimits;
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
	public void setCreaterId(java.lang.Long value) {
		this.createrId = value;
	}
	
	public java.lang.Long getCreaterId() {
		return this.createrId;
	}
	public void setCreaterName(java.lang.String value) {
		this.createrName = value;
	}
	
	public java.lang.String getCreaterName() {
		return this.createrName;
	}
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdaterId(java.lang.Long value) {
		this.updaterId = value;
	}
	
	public java.lang.Long getUpdaterId() {
		return this.updaterId;
	}
	public void setUpdaterName(java.lang.String value) {
		this.updaterName = value;
	}
	
	public java.lang.String getUpdaterName() {
		return this.updaterName;
	}
	public void setIsDelete(java.lang.String value) {
		this.isDelete = value;
	}
	
	public java.lang.String getIsDelete() {
		return this.isDelete;
	}
	public String getAsileServiceBeginTimeString() {
		return DateConvertUtils.format(getAsileServiceBeginTime(), FORMAT_ASILE_SERVICE_BEGIN_TIME);
	}
	public void setAsileServiceBeginTimeString(String value) {
		setAsileServiceBeginTime(DateConvertUtils.parse(value, FORMAT_ASILE_SERVICE_BEGIN_TIME,java.util.Date.class));
	}
	
	public void setAsileServiceBeginTime(java.util.Date value) {
		this.asileServiceBeginTime = value;
	}
	
	public java.util.Date getAsileServiceBeginTime() {
		return this.asileServiceBeginTime;
	}
	public String getAsileServiceEndTimeString() {
		return DateConvertUtils.format(getAsileServiceEndTime(), FORMAT_ASILE_SERVICE_END_TIME);
	}
	public void setAsileServiceEndTimeString(String value) {
		setAsileServiceEndTime(DateConvertUtils.parse(value, FORMAT_ASILE_SERVICE_END_TIME,java.util.Date.class));
	}
	
	public void setAsileServiceEndTime(java.util.Date value) {
		this.asileServiceEndTime = value;
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


	public java.util.Date getAsileServiceEndTime() {
		return this.asileServiceEndTime;
	}
	public void setPriority(java.lang.Integer value) {
		this.priority = value;
	}
	
	public java.lang.Integer getPriority() {
		return this.priority;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AsileBankTempEntity [id=");
		builder.append(id);
		builder.append(", asileName=");
		builder.append(asileName);
		builder.append(", asileCode=");
		builder.append(asileCode);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", bankCode=");
		builder.append(bankCode);
		builder.append(", asileBankType=");
		builder.append(asileBankType);
		builder.append(", asilePayType=");
		builder.append(asilePayType);
		builder.append(", asileAmtStart=");
		builder.append(asileAmtStart);
		builder.append(", asileAmtEnd=");
		builder.append(asileAmtEnd);
		builder.append(", asileCrashLimit=");
		builder.append(asileCrashLimit);
		builder.append(", cardCrashDayLimit=");
		builder.append(cardCrashDayLimit);
		builder.append(", cardCrashMonthLimit=");
		builder.append(cardCrashMonthLimit);
		builder.append(", cardDayLimits=");
		builder.append(cardDayLimits);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", createrId=");
		builder.append(createrId);
		builder.append(", createrName=");
		builder.append(createrName);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", updaterId=");
		builder.append(updaterId);
		builder.append(", updaterName=");
		builder.append(updaterName);
		builder.append(", isDelete=");
		builder.append(isDelete);
		builder.append(", asileServiceBeginTime=");
		builder.append(asileServiceBeginTime);
		builder.append(", asileServiceEndTime=");
		builder.append(asileServiceEndTime);
		builder.append(", priority=");
		builder.append(priority);
		builder.append("]");
		return builder.toString();
	}
	
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	

}
