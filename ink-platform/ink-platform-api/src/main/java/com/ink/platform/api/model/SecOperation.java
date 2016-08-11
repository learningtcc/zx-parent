package com.ink.platform.api.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SecOperation implements Serializable{

	private static final long serialVersionUID = -4947207292013686504L;

	private boolean checked;
	private Long id;//操作ID
    private String operationName;//操作名称
    
    private String operationCode;//操作标识
    private String url;//操作名称
	private String opcode;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;//创建时间
	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;//修改时间
	
	private Long creatorId;//创建者ID
	
	private String creator;//
	
	private Integer version;//版本
	
	private Integer delFlag;//删除标识，0-正常 1-删除
	
	private Integer status;//状态 1-启用 2-停用 9-注销
	
	private String remark;//备注

	
	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getOperationName() {
		return operationName;
	}


	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}


	public String getOperationCode() {
		return operationCode;
	}


	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getOpcode() {
		return opcode;
	}


	public void setOpcode(String opcode) {
		this.opcode = opcode;
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


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
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


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecOperation [checked=");
		builder.append(checked);
		builder.append(", id=");
		builder.append(id);
		builder.append(", operationName=");
		builder.append(operationName);
		builder.append(", operationCode=");
		builder.append(operationCode);
		builder.append(", url=");
		builder.append(url);
		builder.append(", opcode=");
		builder.append(opcode);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", creatorId=");
		builder.append(creatorId);
		builder.append(", creator=");
		builder.append(creator);
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
