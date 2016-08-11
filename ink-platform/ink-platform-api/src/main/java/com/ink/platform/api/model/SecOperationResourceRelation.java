package com.ink.platform.api.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 操作，资源关联表
 * @author lww
 *
 */
public class SecOperationResourceRelation {

	private String id;
	private String resourceId;
	private String operationId;
	
	private Integer status;
	private Integer del_flag;
	private String remark;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date update_time;
	private Integer version;
	private String creator_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getOperationId() {
		return operationId;
	}
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(Integer del_flag) {
		this.del_flag = del_flag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecOperationResourceRelation [id=");
		builder.append(id);
		builder.append(", resourceId=");
		builder.append(resourceId);
		builder.append(", operationId=");
		builder.append(operationId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", del_flag=");
		builder.append(del_flag);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", create_time=");
		builder.append(create_time);
		builder.append(", update_time=");
		builder.append(update_time);
		builder.append(", version=");
		builder.append(version);
		builder.append(", creator_id=");
		builder.append(creator_id);
		builder.append("]");
		return builder.toString();
	}
	
	

}
