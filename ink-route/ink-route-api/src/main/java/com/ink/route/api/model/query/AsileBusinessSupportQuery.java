/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.api.model.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AsileBusinessSupportQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 主键 */
	private java.lang.Long id;
	/** 商户号 */
	private java.lang.String mchId;
	/** 渠道号 */
	private java.lang.String channelNo;
	/** 支持的支付方式，转化为二进制，第一位为1代表支持代收，第二位为1代表支付代付，第三位快捷，第四位认证无短信，第五位认证有短信，第六位鉴权 */
	private java.lang.Integer payType;
	/** 商户在渠道开通的商户号 */
	private java.lang.String channelMchId;
	/** 渠道提供的终端号 */
	private java.lang.String terminalId;
	/** 证书编号 */
	private java.lang.String certId;
	/** 备注 */
	private java.lang.String remark;
	/** 扩展字段 */
	private java.lang.String ext1;
	/** 1 启用 0 停用 */
	private java.lang.Integer status;
	/** 扩展字段 */
	private java.lang.String ext2;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后更新时间 */
	private java.util.Date lastupdateTimeBegin;
	private java.util.Date lastupdateTimeEnd;
	/** 操作人 */
	private java.lang.String operator;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.String value) {
		this.mchId = value;
	}
	
	public java.lang.String getChannelNo() {
		return this.channelNo;
	}
	
	public void setChannelNo(java.lang.String value) {
		this.channelNo = value;
	}
	
	public java.lang.Integer getPayType() {
		return this.payType;
	}
	
	public void setPayType(java.lang.Integer value) {
		this.payType = value;
	}
	
	public java.lang.String getChannelMchId() {
		return this.channelMchId;
	}
	
	public void setChannelMchId(java.lang.String value) {
		this.channelMchId = value;
	}
	
	public java.lang.String getTerminalId() {
		return this.terminalId;
	}
	
	public void setTerminalId(java.lang.String value) {
		this.terminalId = value;
	}
	
	public java.lang.String getCertId() {
		return this.certId;
	}
	
	public void setCertId(java.lang.String value) {
		this.certId = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getExt1() {
		return this.ext1;
	}
	
	public void setExt1(java.lang.String value) {
		this.ext1 = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.String getExt2() {
		return this.ext2;
	}
	
	public void setExt2(java.lang.String value) {
		this.ext2 = value;
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
	
	public java.util.Date getLastupdateTimeBegin() {
		return this.lastupdateTimeBegin;
	}
	
	public void setLastupdateTimeBegin(java.util.Date value) {
		this.lastupdateTimeBegin = value;
	}	
	
	public java.util.Date getLastupdateTimeEnd() {
		return this.lastupdateTimeEnd;
	}
	
	public void setLastupdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastupdateTimeEnd = calendar.getTime();
		}else {
			this.lastupdateTimeEnd = value;
		}
	}
	
	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

