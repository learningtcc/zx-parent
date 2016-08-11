package com.ink.platform.api.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class SecLog implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -116974323805040152L;

	private Long id;//日志ID
	
	private Long userId;//用户ID
	private String userName;//操作人姓名
	
	private String actionType;//操作类型
	
	private String action;//行为
	
	private String recordBefore;//操作之前的记录
	
	private String recordAfter;//操作之后的记录
	
	private String result;//结果，1成功，0失败
	
	private String endTime;//
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date  createTime;//创建时间
	
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRecordBefore() {
		return recordBefore;
	}

	public void setRecordBefore(String recordBefore) {
		this.recordBefore = recordBefore;
	}

	public String getRecordAfter() {
		return recordAfter;
	}

	public void setRecordAfter(String recordAfter) {
		this.recordAfter = recordAfter;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecLog [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", actionType=");
		builder.append(actionType);
		builder.append(", action=");
		builder.append(action);
		builder.append(", recordBefore=");
		builder.append(recordBefore);
		builder.append(", recordAfter=");
		builder.append(recordAfter);
		builder.append(", result=");
		builder.append(result);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", createTime=");
		builder.append(createTime);
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
