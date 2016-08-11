/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午2:36:58
 */
package com.ink.trade.api.platform.asile.model.in;

import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午2:36:58
 */
public class AsileResCodeQueryInput  extends BaseQuery{
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 主键 */
	private java.lang.Long id;
	/** 通道名称 */
	private java.lang.String asileName;
	/** 通道id */
	private java.lang.String asileCode;
	/** 通道支付方式 */
	private java.lang.String asilePayType;
	/** 交易类型 */
	private java.lang.String transType;
	/** 通道响应码 */
	private java.lang.String asileResCode;
	/** 通道响应信息 */
	private java.lang.String asileResMsg;
	/** 平台响应码 */
	private java.lang.String resCode;
	/** 平台响应信息 */
	private java.lang.String resMsg;
	/** 备注 */
	private java.lang.String remark;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 创建人id */
	private java.lang.Long createrId;
	/** 创建人 */
	private java.lang.String createrName;
	/** 更新时间 */
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;
	/** updaterId */
	private java.lang.Long updaterId;
	/** updaterName */
	private java.lang.String updaterName;
	/** 是否删除 */
	private java.lang.Integer isDelete;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
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
	
	public java.lang.String getAsilePayType() {
		return this.asilePayType;
	}
	
	public void setAsilePayType(java.lang.String value) {
		this.asilePayType = value;
	}
	
	public java.lang.String getTransType() {
		return this.transType;
	}
	
	public void setTransType(java.lang.String value) {
		this.transType = value;
	}
	
	public java.lang.String getAsileResCode() {
		return this.asileResCode;
	}
	
	public void setAsileResCode(java.lang.String value) {
		this.asileResCode = value;
	}
	
	public java.lang.String getAsileResMsg() {
		return this.asileResMsg;
	}
	
	public void setAsileResMsg(java.lang.String value) {
		this.asileResMsg = value;
	}
	
	public java.lang.String getResCode() {
		return this.resCode;
	}
	
	public void setResCode(java.lang.String value) {
		this.resCode = value;
	}
	
	public java.lang.String getResMsg() {
		return this.resMsg;
	}
	
	public void setResMsg(java.lang.String value) {
		this.resMsg = value;
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
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
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
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.updateTimeEnd = calendar.getTime();
		}else {
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
	
	public java.lang.Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.Integer value) {
		this.isDelete = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
