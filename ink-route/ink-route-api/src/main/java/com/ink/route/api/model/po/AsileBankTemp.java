/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.api.model.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

import java.math.BigDecimal;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AsileBankTemp extends BaseEntity implements java.io.Serializable {
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
//	public static final String ALIAS_ASILE_PAY_TYPE = "支付类型";
//	
//	public static final String ALIAS_ASILE_AMT_START = "交易金额下限";
//	
//	public static final String ALIAS_ASILE_AMT_END = "交易金额上限";
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
//	public static final String ALIAS_ASILE_SERVICE_BEGIN_TIME = "通道服务开始时间";
//	
//	public static final String ALIAS_ASILE_SERVICE_END_TIME = "通道服务结束时间";
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
	//支付类型
	private String asilePayType;
	//交易金额下限
	private BigDecimal asileAmtStart;
	//交易金额上限
	private BigDecimal asileAmtEnd;
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
	//通道服务开始时间
	private java.util.Date asileServiceBeginTime;
	//通道服务结束时间
	private java.util.Date asileServiceEndTime;
	//columns END

	public AsileBankTemp(){
	}

	public AsileBankTemp(
		Long id
	){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setAsileName(String value) {
		this.asileName = value;
	}
	
	public String getAsileName() {
		return this.asileName;
	}
	public void setAsileCode(String value) {
		this.asileCode = value;
	}
	
	public String getAsileCode() {
		return this.asileCode;
	}
	public void setBankName(String value) {
		this.bankName = value;
	}
	
	public String getBankName() {
		return this.bankName;
	}
	public void setBankCode(String value) {
		this.bankCode = value;
	}
	
	public String getBankCode() {
		return this.bankCode;
	}
	public void setAsilePayType(String value) {
		this.asilePayType = value;
	}
	
	public String getAsilePayType() {
		return this.asilePayType;
	}
	public void setAsileAmtStart(BigDecimal value) {
		this.asileAmtStart = value;
	}
	
	public BigDecimal getAsileAmtStart() {
		return this.asileAmtStart;
	}
	public void setAsileAmtEnd(BigDecimal value) {
		this.asileAmtEnd = value;
	}
	
	public BigDecimal getAsileAmtEnd() {
		return this.asileAmtEnd;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
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
	public void setCreaterId(Long value) {
		this.createrId = value;
	}
	
	public Long getCreaterId() {
		return this.createrId;
	}
	public void setCreaterName(String value) {
		this.createrName = value;
	}
	
	public String getCreaterName() {
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
	public void setUpdaterId(Long value) {
		this.updaterId = value;
	}
	
	public Long getUpdaterId() {
		return this.updaterId;
	}
	public void setUpdaterName(String value) {
		this.updaterName = value;
	}
	
	public String getUpdaterName() {
		return this.updaterName;
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
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AsileName",getAsileName())
			.append("AsileCode",getAsileCode())
			.append("BankName",getBankName())
			.append("BankCode",getBankCode())
			.append("AsilePayType",getAsilePayType())
			.append("AsileAmtStart",getAsileAmtStart())
			.append("AsileAmtEnd",getAsileAmtEnd())
			.append("Remark",getRemark())
			.append("CreateTime",getCreateTime())
			.append("CreaterId",getCreaterId())
			.append("CreaterName",getCreaterName())
			.append("UpdateTime",getUpdateTime())
			.append("UpdaterId",getUpdaterId())
			.append("UpdaterName",getUpdaterName())
			.append("AsileServiceBeginTime",getAsileServiceBeginTime())
			.append("AsileServiceEndTime",getAsileServiceEndTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("主键:").append(id).append(separator);
			sb.append("通道名称:").append(asileName).append(separator);
			sb.append("通道code:").append(asileCode).append(separator);
			sb.append("银行名称:").append(bankName).append(separator);
			sb.append("银行code:").append(bankCode).append(separator);
			sb.append("支付类型:").append(asilePayType).append(separator);
			sb.append("交易金额下限:").append(asileAmtStart).append(separator);
			sb.append("交易金额上限:").append(asileAmtEnd).append(separator);
			sb.append("备注:").append(remark).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("createrId:").append(createrId).append(separator);
			sb.append("创建人:").append(createrName).append(separator);
			sb.append("updateTime:").append(getUpdateTimeString()).append(separator);
			sb.append("updaterId:").append(updaterId).append(separator);
			sb.append("updaterName:").append(updaterName).append(separator);
			sb.append("通道服务开始时间:").append(getAsileServiceBeginTimeString()).append(separator);
			sb.append("通道服务结束时间:").append(getAsileServiceEndTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AsileBankTemp == false) return false;
		if(this == obj) return true;
		AsileBankTemp other = (AsileBankTemp)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

