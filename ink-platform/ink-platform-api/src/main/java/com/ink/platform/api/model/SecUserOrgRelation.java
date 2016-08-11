package com.ink.platform.api.model;

import java.io.Serializable;
import java.util.Date;

public class SecUserOrgRelation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8737682304005709900L;
	
	//表关联查询字段
	private String orgName;//组织机构名称： 组织机构表
	private Long parentOrgId;//父级组织机构：组织机构表
	private String username;//用户名。真实姓名 ： 用户表查询
	
	////================
	private Long id;//主键
	
	private Long orgId;//组织机构ID
	
	private Long userId;//用户ID
	
	private String title;//职位
	
	private int titleIdentify;//职位标识：1.主岗，2：副岗
	
	private Date createTime;//创建时间
		
	private Date updateTime;//修改时间
	
	private Long creatorId;//创建者ID
	
	private Integer version;//版本
	
	private Integer delFlag;//删除标识，0-正常 1-删除
	
	private Integer status;//状态 1-启用 2-停用 9-注销
	
	private String remark;//备注

	public Long getId() {
		return id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getTitleIdentify() {
		return titleIdentify;
	}

	public void setTitleIdentify(int titleIdentify) {
		this.titleIdentify = titleIdentify;
	}

}
