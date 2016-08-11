package com.ink.platform.api.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SecUser implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8551873187011641633L;
	private String orgId;
	private String orgName;
	private String roleName;
	private String roleId;
	private String creator;
	private String userOrgId;//用户组织关系表id
	private String userRoleId;//用户角色关系表id
	private int titleIdentify;
	private int subjectType;//主体类型组织机构1,用户2

	private Integer udelFlag; //删除标识，0-正常 1-删除
	
	private Integer ustatus; //状态，1-启用 2-停用 9-注销


	private Long creatorId;//创建者id
	
	private Long id;//用户ID
	
	private String state;
	private String url;
	private String away;
		
	private String username;//用户名。真实姓名
	
	private String loginName;//登录名
	
	private String password;//密码
	
	private String title;//职务岗位
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;//创建时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;//更新时间
	
	private Integer version; //版本

	private Integer delFlag; //删除标识，0-正常 1-删除
	
	private Integer status; //状态，1-启用 2-停用 9-注销
	
	private String remark;//备注
	public Integer getUdelFlag() {
		return udelFlag;
	}

	public void setUdelFlag(Integer udelFlag) {
		this.udelFlag = udelFlag;
	}

	public Integer getUstatus() {
		return ustatus;
	}

	public void setUstatus(Integer ustatus) {
		this.ustatus = ustatus;
	}

	public int getTitleIdentify() {
		return titleIdentify;
	}

	public void setTitleIdentify(int titleIdentify) {
		this.titleIdentify = titleIdentify;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAway() {
		return away;
	}

	public void setAway(String away) {
		this.away = away;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(String userOrgId) {
		this.userOrgId = userOrgId;
	}

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public int getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(int subjectType) {
		this.subjectType = subjectType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecUser [orgId=");
		builder.append(orgId);
		builder.append(", orgName=");
		builder.append(orgName);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", userOrgId=");
		builder.append(userOrgId);
		builder.append(", userRoleId=");
		builder.append(userRoleId);
		builder.append(", titleIdentify=");
		builder.append(titleIdentify);
		builder.append(", subjectType=");
		builder.append(subjectType);
		builder.append(", udelFlag=");
		builder.append(udelFlag);
		builder.append(", ustatus=");
		builder.append(ustatus);
		builder.append(", creatorId=");
		builder.append(creatorId);
		builder.append(", id=");
		builder.append(id);
		builder.append(", state=");
		builder.append(state);
		builder.append(", url=");
		builder.append(url);
		builder.append(", away=");
		builder.append(away);
		builder.append(", username=");
		builder.append(username);
		builder.append(", loginName=");
		builder.append(loginName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", title=");
		builder.append(title);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
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
