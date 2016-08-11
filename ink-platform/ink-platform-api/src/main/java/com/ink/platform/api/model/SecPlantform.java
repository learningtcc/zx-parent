package com.ink.platform.api.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SecPlantform implements Serializable{
	/**
	 * 
	 */
		private static final long serialVersionUID = -7154192291251046893L;

	    private Long id;//平台ID
	
	    private String plantformName;//平台名称
	    
	    private String plantformCode;//平台标识
	    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    private Date createTime;//创建时间
	    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date updateTime;//修改时间
		
		private Long creatorId;//创建者ID
		private String creator;//创建人
		
		private Integer version;//版本
		
		private Integer delFlag;//删除标识，0-正常 1-删除
		
		private Integer status;//状态 1-启用 2-停用 9-注销
		
		private String remark;//备注

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getPlantformName() {
			return plantformName;
		}

		public void setPlantformName(String plantformName) {
			this.plantformName = plantformName;
		}

		public String getPlantformCode() {
			return plantformCode;
		}

		public void setPlantformCode(String plantformCode) {
			this.plantformCode = plantformCode;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public Long getCreatorId() {
			return creatorId;
		}

		public void setCreatorId(Long creatorId) {
			this.creatorId = creatorId;
		}

		public Integer getVersion() {
			return version;
		}

		public void setVersion(Integer version) {
			this.version = version;
		}

		public Integer getDelFlag() {
			return delFlag;
		}

		public void setDelFlag(Integer delFlag) {
			this.delFlag = delFlag;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getCreator() {
			return creator;
		}

		public void setCreator(String creator) {
			this.creator = creator;
		}
		
}
