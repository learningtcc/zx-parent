package com.ink.platform.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class SecOrg implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1345068578930009447L;
	
	private List<SecOrg> children ;
	
    private String url;
    
	private boolean checked;
	
	private int subjectType;//主体类型组织机构1
	
	private int titleIdentify;//主副岗


	private Long id;//组织机构ID
	
	private String orgName;//组织机构名称
	
	private String orgCode;//组织机构标识
	
	private Long parentOrgId;//父级组织机构
	
	private String parentName;//父级组织机构
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;//修改时间
	
	private Long creatorId;//创建者ID
	
	private String userName;//创建者姓名
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
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


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(int subjectType) {
		this.subjectType = subjectType;
	}

	public List<SecOrg> getChildren() {
		return children;
	}

	public void setChildren(List<SecOrg> children) {
		this.children = children;
	}

	public int getTitleIdentify() {
		return titleIdentify;
	}

	public void setTitleIdentify(int titleIdentify) {
		this.titleIdentify = titleIdentify;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecOrg [children=");
		builder.append(children);
		builder.append(", url=");
		builder.append(url);
		builder.append(", checked=");
		builder.append(checked);
		builder.append(", subjectType=");
		builder.append(subjectType);
		builder.append(", titleIdentify=");
		builder.append(titleIdentify);
		builder.append(", id=");
		builder.append(id);
		builder.append(", orgName=");
		builder.append(orgName);
		builder.append(", orgCode=");
		builder.append(orgCode);
		builder.append(", parentOrgId=");
		builder.append(parentOrgId);
		builder.append(", parentName=");
		builder.append(parentName);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", creatorId=");
		builder.append(creatorId);
		builder.append(", userName=");
		builder.append(userName);
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
