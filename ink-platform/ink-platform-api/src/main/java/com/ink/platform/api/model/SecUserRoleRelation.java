package com.ink.platform.api.model;

import java.io.Serializable;
import java.util.Date;

public class SecUserRoleRelation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5733215070238290746L;
	
	private Long id;//主键
	
	private Long roleId;//角色ID
	
	private Integer subjectType;//主体类型，1-组织机构，2-用户
	
	private Long subjectId;//主体ID
	
	private Integer titleCode;//主副岗标识
	
    private Date createTime;//创建时间
	
	private Date updateTime;//修改时间
	
	private Long operatorId;//操作人ID
	
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
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

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
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

	public Integer getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(Integer titleCode) {
		this.titleCode = titleCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecUserRoleRelation [id=");
		builder.append(id);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append(", subjectType=");
		builder.append(subjectType);
		builder.append(", subjectId=");
		builder.append(subjectId);
		builder.append(", titleCode=");
		builder.append(titleCode);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", operatorId=");
		builder.append(operatorId);
		builder.append(", version=");
		builder.append(version);
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append(", status=");
		builder.append(status);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}
	
}
