package com.ink.platform.api.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



public class SecResource implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -6746300832492513816L;

	private String sysCode;
	private Long id;//资源ID
	
	private Long pid;//父级资源
    private String state;
    private String away;
    private boolean  checked;
    private String operationTree;//操作功能
    private String rcode;//操作功能
    
	public String getOperationTree() {
		return operationTree;
	}

	public void setOperationTree(String operationTree) {
		this.operationTree = operationTree;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	///////
    private String resourceName;//资源名称
	
	private String resourceCode;//资源标识
	
	private String permission;//权限表达式，采用shiro规范
	
	private Integer resourceLevel;//资源层次
	
	private Integer resourcePosition;//资源位置
	private String position;//资源位置
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;//创建时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;//修改时间
	
	private Long operatorId;//操作者ID
	
	private Integer version;//版本
	
	private Integer delFlag;//删除标识，0-正常 1-删除
	
	private Integer status;//状态 1-启用 2-停用 9-注销
	
	private String remark;//备注
	private String creator;//创建人

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}




	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAway() {
		return away;
	}

	public void setAway(String away) {
		this.away = away;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getResourceLevel() {
		return resourceLevel;
	}

	public void setResourceLevel(Integer resourceLevel) {
		this.resourceLevel = resourceLevel;
	}

	public Integer getResourcePosition() {
		return resourcePosition;
	}

	public void setResourcePosition(Integer resourcePosition) {
		this.resourcePosition = resourcePosition;
	}


	

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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



	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}


	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecResource [sysCode=");
		builder.append(sysCode);
		builder.append(", id=");
		builder.append(id);
		builder.append(", pid=");
		builder.append(pid);
		builder.append(", state=");
		builder.append(state);
		builder.append(", away=");
		builder.append(away);
		builder.append(", checked=");
		builder.append(checked);
		builder.append(", operationTree=");
		builder.append(operationTree);
		builder.append(", rcode=");
		builder.append(rcode);
		builder.append(", resourceName=");
		builder.append(resourceName);
		builder.append(", resourceCode=");
		builder.append(resourceCode);
		builder.append(", permission=");
		builder.append(permission);
		builder.append(", resourceLevel=");
		builder.append(resourceLevel);
		builder.append(", resourcePosition=");
		builder.append(resourcePosition);
		builder.append(", position=");
		builder.append(position);
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
		builder.append(", creator=");
		builder.append(creator);
		builder.append("]");
		return builder.toString();
	}

	
}
