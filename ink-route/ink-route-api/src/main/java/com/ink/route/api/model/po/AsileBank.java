/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.api.model.po;

import java.math.BigDecimal;

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

public class AsileBank extends BaseEntity implements java.io.Serializable,Comparable<AsileBank> {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AsileBank";
//	
//	public static final String ALIAS_ID = "主键";
//	
//	public static final String ALIAS_ASILE_NAME = "通道名称";
//	
//	public static final String ALIAS_ASILE_CODE = "通道id";
//	
//	public static final String ALIAS_BANK_NAME = "银行名称";
//	
//	public static final String ALIAS_BANK_CODE = "银行id";
//	
//	public static final String ALIAS_ASILE_PRODUCT_CODE = "通道产品编码";
//	
//	public static final String ALIAS_ASILE_PRODUCT_NAME = "通道产品名称";
//	
//	public static final String ALIAS_ASILE_DIRECT_BANK = "是否直连";
//	
//	public static final String ALIAS_ASILE_PUBLIC_PRIVATE = "对公对私";
//	
//	public static final String ALIAS_ASILE_INTEFACE_TYPE = "提供接口方式";
//	
//	public static final String ALIAS_ASILE_AUTH_TYPE = "鉴权方式";
//	
//	public static final String ALIAS_ASILE_AUTH_ELEMENTS = "鉴权要素(转换为二进制位数表示)";
//	
//	public static final String ALIAS_ASILE_AUTH_MODE = "授权方式";
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
//	public static final String ALIAS_BATCH_CRASH_LIMIT = "批量限额";
//	
//	public static final String ALIAS_BATCH_LIMIT = "批量限次";
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
//	public static final String ALIAS_ASILE_ONLY_TIME = "回盘时间";
//	
//	public static final String ALIAS_ASILE_SERVICE_BEGIN_TIME = "通道服务开始时间";
//	
//	public static final String ALIAS_ASILE_SERVICE_END_TIME = "通道服务结束时间";
//	
//	public static final String ALIAS_PRIORITY = "优先级";
//	
//	public static final String ALIAS_BORROW_SPLIT = "0是分离，1是不分";
//	
//	public static final String ALIAS_SMS_SENDER = "短信发送方";
//	
//	public static final String ALIAS_BANK_SHORT = "银行简码";
//	
//	public static final String ALIAS_ASILE_BANK_CODE = "渠道银行编码";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	public static final String FORMAT_ASILE_ONLY_TIME = DATE_FORMAT;
	public static final String FORMAT_ASILE_SERVICE_BEGIN_TIME = DATE_FORMAT;
	public static final String FORMAT_ASILE_SERVICE_END_TIME = DATE_FORMAT;
	
	//columns START
	//主键
	private Long id;
	//通道名称
	private String asileName;
	//通道id
	private String asileCode;
	//银行名称
	private String bankName;
	//银行id
	private String bankCode;
	//通道产品编码
	private String asileProductCode;
	//通道产品名称
	private String asileProductName;
	//是否直连
	private String asileDirectBank;
	//对公对私
	private String asilePublicPrivate;
	//提供接口方式
	private String asileIntefaceType;
	//鉴权方式
	private String asileAuthType;
	//鉴权要素(转换为二进制位数表示)
	private Integer asileAuthElements;
	//授权方式
	private String asileAuthMode;
	//支持银行卡类型
	private String asileBankType;
	//支付类型
	private String asilePayType;
	//单笔限额
	private BigDecimal asileCrashLimit;
	//单卡单日限额
	private BigDecimal cardCrashDayLimit;
	//单卡单月限额
	private BigDecimal cardCrashMonthLimit;
	//单卡单日限额
	private Integer cardDayLimits;
	//批量限额
	private BigDecimal batchCrashLimit;
	//批量限次
	private Integer batchLimit;
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
	//回盘时间
	private java.util.Date asileOnlyTime;
	//通道服务开始时间
	private java.util.Date asileServiceBeginTime;
	//通道服务结束时间
	private java.util.Date asileServiceEndTime;
	//优先级
	private Integer priority;
	//0是分离，1是不分
	private String borrowSplit;
	//短信发送方
	private String smsSender;
	//银行简码
	private String bankShort;
	//渠道银行编码
	private String asileBankCode;
	//columns END

	public AsileBank(){
	}

	public AsileBank(
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
	public void setAsileProductCode(java.lang.String value) {
		this.asileProductCode = value;
	}
	
	public java.lang.String getAsileProductCode() {
		return this.asileProductCode;
	}
	public void setAsileProductName(java.lang.String value) {
		this.asileProductName = value;
	}
	
	public java.lang.String getAsileProductName() {
		return this.asileProductName;
	}
	public void setAsileDirectBank(java.lang.String value) {
		this.asileDirectBank = value;
	}
	
	public java.lang.String getAsileDirectBank() {
		return this.asileDirectBank;
	}
	public void setAsilePublicPrivate(java.lang.String value) {
		this.asilePublicPrivate = value;
	}
	
	public java.lang.String getAsilePublicPrivate() {
		return this.asilePublicPrivate;
	}
	public void setAsileIntefaceType(java.lang.String value) {
		this.asileIntefaceType = value;
	}
	
	public java.lang.String getAsileIntefaceType() {
		return this.asileIntefaceType;
	}
	public void setAsileAuthType(java.lang.String value) {
		this.asileAuthType = value;
	}
	
	public java.lang.String getAsileAuthType() {
		return this.asileAuthType;
	}
	public void setAsileAuthElements(java.lang.Integer value) {
		this.asileAuthElements = value;
	}
	
	public java.lang.Integer getAsileAuthElements() {
		return this.asileAuthElements;
	}
	public void setAsileAuthMode(java.lang.String value) {
		this.asileAuthMode = value;
	}
	
	public java.lang.String getAsileAuthMode() {
		return this.asileAuthMode;
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
	public void setBatchCrashLimit(BigDecimal value) {
		this.batchCrashLimit = value;
	}
	
	public BigDecimal getBatchCrashLimit() {
		return this.batchCrashLimit;
	}
	public void setBatchLimit(java.lang.Integer value) {
		this.batchLimit = value;
	}
	
	public java.lang.Integer getBatchLimit() {
		return this.batchLimit;
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
	public String getAsileOnlyTimeString() {
		return DateConvertUtils.format(getAsileOnlyTime(), FORMAT_ASILE_ONLY_TIME);
	}
	public void setAsileOnlyTimeString(String value) {
		setAsileOnlyTime(DateConvertUtils.parse(value, FORMAT_ASILE_ONLY_TIME,java.util.Date.class));
	}
	
	public void setAsileOnlyTime(java.util.Date value) {
		this.asileOnlyTime = value;
	}
	
	public java.util.Date getAsileOnlyTime() {
		return this.asileOnlyTime;
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
	
	public java.util.Date getAsileServiceEndTime() {
		return this.asileServiceEndTime;
	}
	public void setPriority(java.lang.Integer value) {
		this.priority = value;
	}
	
	public java.lang.Integer getPriority() {
		return this.priority;
	}
	public void setBorrowSplit(java.lang.String value) {
		this.borrowSplit = value;
	}
	
	public java.lang.String getBorrowSplit() {
		return this.borrowSplit;
	}
	public void setSmsSender(java.lang.String value) {
		this.smsSender = value;
	}
	
	public java.lang.String getSmsSender() {
		return this.smsSender;
	}
	public void setBankShort(java.lang.String value) {
		this.bankShort = value;
	}
	
	public java.lang.String getBankShort() {
		return this.bankShort;
	}
	public void setAsileBankCode(java.lang.String value) {
		this.asileBankCode = value;
	}
	
	public java.lang.String getAsileBankCode() {
		return this.asileBankCode;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AsileName",getAsileName())
			.append("AsileCode",getAsileCode())
			.append("BankName",getBankName())
			.append("BankCode",getBankCode())
			.append("AsileProductCode",getAsileProductCode())
			.append("AsileProductName",getAsileProductName())
			.append("AsileDirectBank",getAsileDirectBank())
			.append("AsilePublicPrivate",getAsilePublicPrivate())
			.append("AsileIntefaceType",getAsileIntefaceType())
			.append("AsileAuthType",getAsileAuthType())
			.append("AsileAuthElements",getAsileAuthElements())
			.append("AsileAuthMode",getAsileAuthMode())
			.append("AsileBankType",getAsileBankType())
			.append("AsilePayType",getAsilePayType())
			.append("AsileCrashLimit",getAsileCrashLimit())
			.append("CardCrashDayLimit",getCardCrashDayLimit())
			.append("CardCrashMonthLimit",getCardCrashMonthLimit())
			.append("CardDayLimits",getCardDayLimits())
			.append("BatchCrashLimit",getBatchCrashLimit())
			.append("BatchLimit",getBatchLimit())
			.append("Remark",getRemark())
			.append("CreateTime",getCreateTime())
			.append("CreaterId",getCreaterId())
			.append("CreaterName",getCreaterName())
			.append("UpdateTime",getUpdateTime())
			.append("UpdaterId",getUpdaterId())
			.append("UpdaterName",getUpdaterName())
			.append("IsDelete",getIsDelete())
			.append("AsileOnlyTime",getAsileOnlyTime())
			.append("AsileServiceBeginTime",getAsileServiceBeginTime())
			.append("AsileServiceEndTime",getAsileServiceEndTime())
			.append("Priority",getPriority())
			.append("BorrowSplit",getBorrowSplit())
			.append("SmsSender",getSmsSender())
			.append("BankShort",getBankShort())
			.append("AsileBankCode",getAsileBankCode())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("主键:").append(id).append(separator);
			sb.append("通道名称:").append(asileName).append(separator);
			sb.append("通道id:").append(asileCode).append(separator);
			sb.append("银行名称:").append(bankName).append(separator);
			sb.append("银行id:").append(bankCode).append(separator);
			sb.append("通道产品编码:").append(asileProductCode).append(separator);
			sb.append("通道产品名称:").append(asileProductName).append(separator);
			sb.append("是否直连:").append(asileDirectBank).append(separator);
			sb.append("对公对私:").append(asilePublicPrivate).append(separator);
			sb.append("提供接口方式:").append(asileIntefaceType).append(separator);
			sb.append("鉴权方式:").append(asileAuthType).append(separator);
			sb.append("鉴权要素(转换为二进制位数表示):").append(asileAuthElements).append(separator);
			sb.append("授权方式:").append(asileAuthMode).append(separator);
			sb.append("支持银行卡类型:").append(asileBankType).append(separator);
			sb.append("支付类型:").append(asilePayType).append(separator);
			sb.append("单笔限额:").append(asileCrashLimit).append(separator);
			sb.append("单卡单日限额:").append(cardCrashDayLimit).append(separator);
			sb.append("单卡单月限额:").append(cardCrashMonthLimit).append(separator);
			sb.append("单卡单日限额:").append(cardDayLimits).append(separator);
			sb.append("批量限额:").append(batchCrashLimit).append(separator);
			sb.append("批量限次:").append(batchLimit).append(separator);
			sb.append("备注:").append(remark).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("createrId:").append(createrId).append(separator);
			sb.append("创建人:").append(createrName).append(separator);
			sb.append("updateTime:").append(getUpdateTimeString()).append(separator);
			sb.append("updaterId:").append(updaterId).append(separator);
			sb.append("updaterName:").append(updaterName).append(separator);
			sb.append("是否删除:").append(isDelete).append(separator);
			sb.append("回盘时间:").append(getAsileOnlyTimeString()).append(separator);
			sb.append("通道服务开始时间:").append(getAsileServiceBeginTimeString()).append(separator);
			sb.append("通道服务结束时间:").append(getAsileServiceEndTimeString()).append(separator);
			sb.append("优先级:").append(priority).append(separator);
			sb.append("0是分离，1是不分:").append(borrowSplit).append(separator);
			sb.append("短信发送方:").append(smsSender).append(separator);
			sb.append("银行简码:").append(bankShort).append(separator);
			sb.append("渠道银行编码:").append(asileBankCode).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AsileBank == false) return false;
		if(this == obj) return true;
		AsileBank other = (AsileBank)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	@Override
	public int compareTo(AsileBank arg0) {
		return this.getPriority().compareTo(arg0.getPriority());
	}
}

