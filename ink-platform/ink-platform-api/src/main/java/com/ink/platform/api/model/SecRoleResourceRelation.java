package com.ink.platform.api.model;

import java.io.Serializable;
import java.util.Date;

public class SecRoleResourceRelation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8472743277973010858L;
	
	private Long id;//主键
	
	private Long roleId;//角色ID
	
	private Long resourceId;//资源ID
	
	private Long operationId;//操作ID
	
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

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
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

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecRoleResourceRelation [id=");
		builder.append(id);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append(", resourceId=");
		builder.append(resourceId);
		builder.append(", operationId=");
		builder.append(operationId);
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
