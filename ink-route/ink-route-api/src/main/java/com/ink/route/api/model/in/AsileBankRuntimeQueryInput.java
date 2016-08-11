/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午2:36:58
 */
package com.ink.route.api.model.in;

import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午2:36:58
 */
public class AsileBankRuntimeQueryInput  extends BaseQuery{
	
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
		/** 备注 */
		private java.lang.String remark;
		/** 创建时间 */
		private java.util.Date createTime;
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
		/** 版本号 */
		private java.lang.Integer version;
		/** isActive */
		private java.lang.Integer isActive;
		/** 不可用开始时间 */
		private java.util.Date inactivaStartTime;
		/** 不可用结束时间 */
		private java.util.Date inactivaEndTime;
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
		
		public java.lang.String getIsDelete() {
			return this.isDelete;
		}
		
		public void setIsDelete(java.lang.String value) {
			this.isDelete = value;
		}
		
		public java.lang.Integer getVersion() {
			return this.version;
		}
		
		public void setVersion(java.lang.Integer value) {
			this.version = value;
		}
		
		public java.lang.Integer getIsActive() {
			return this.isActive;
		}
		
		public void setIsActive(java.lang.Integer value) {
			this.isActive = value;
		}
		
		public java.util.Date getInactivaStartTime() {
			return this.inactivaStartTime;
		}
		
		public void setInactivaStartTime(java.util.Date value) {
			this.inactivaStartTime = value;
		}
		
		public java.util.Date getInactivaEndTime() {
			return this.inactivaEndTime;
		}
		
		public void setInactivaEndTime(java.util.Date value) {
			this.inactivaEndTime = value;
		}
		

		public java.util.Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(java.util.Date createTime) {
	        this.createTime = createTime;
	    }

	    public String toString() {
			return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
		}
		
}
