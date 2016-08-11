//package com.ink.user.core.po;
//
//import java.io.Serializable;
//import java.util.Date;
//
//public class AccOrg implements Serializable{
//    /** 
//	 * @Fields serialVersionUID : TODO
//	*/ 
//	private static final long serialVersionUID = 1646705025852596463L;
//
//	/** 数据库表主键 */
//    private String id;
//
//    /** 创建人 */
//    private String owner;
//
//    /** 创建人所属机构 */
//    private String ownerGroup;
//
//    /** 创建时间 */
//    private Date createTime;
//
//    /** 最后修改时间 */
//    private Date lastUpdateTime;
//
//    /** 机构编号 */
//    private Long orgId;
//
//    /** 机构名称 */
//    private String orgName;
//
//    /** 机构简称 */
//    private String orgSname;
//
//    /** 机构类型 1-银行 2-支付机构 */
//    private Integer orgType;
//
//    /** 业务联系人 */
//    private String contact;
//
//    /** 联系移动电话 */
//    private String mblNo;
//
//    /** 联系固定电话 */
//    private String telNo;
//
//    /** 联系邮箱 */
//    private String email;
//
//    /** 状态 1-启用 2-停用 3-待审核 4-审核拒绝 9-注销 */
//    private Integer status;
//
//    /** 删除标识 0-正常 1-删除 */
//    private Boolean delFlag;
//
//    /** 预留字段1 */
//    private String filler1;
//
//    /** 预留字段2 */
//    private String filler2;
//
//    /** 预留字段3 */
//    private String filler3;
//
//    /**  */
//    private Integer version;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getOwner() {
//        return owner;
//    }
//
//    public void setOwner(String owner) {
//        this.owner = owner == null ? null : owner.trim();
//    }
//
//    public String getOwnerGroup() {
//        return ownerGroup;
//    }
//
//    public void setOwnerGroup(String ownerGroup) {
//        this.ownerGroup = ownerGroup == null ? null : ownerGroup.trim();
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Date getLastUpdateTime() {
//        return lastUpdateTime;
//    }
//
//    public void setLastUpdateTime(Date lastUpdateTime) {
//        this.lastUpdateTime = lastUpdateTime;
//    }
//
//    public Long getOrgId() {
//		return orgId;
//	}
//
//	public void setOrgId(Long orgId) {
//		this.orgId = orgId;
//	}
//
//	public String getOrgName() {
//        return orgName;
//    }
//
//    public void setOrgName(String orgName) {
//        this.orgName = orgName == null ? null : orgName.trim();
//    }
//
//    public String getOrgSname() {
//        return orgSname;
//    }
//
//    public void setOrgSname(String orgSname) {
//        this.orgSname = orgSname == null ? null : orgSname.trim();
//    }
//
//    public Integer getOrgType() {
//        return orgType;
//    }
//
//    public void setOrgType(Integer orgType) {
//        this.orgType = orgType;
//    }
//
//    public String getContact() {
//        return contact;
//    }
//
//    public void setContact(String contact) {
//        this.contact = contact == null ? null : contact.trim();
//    }
//
//    public String getMblNo() {
//        return mblNo;
//    }
//
//    public void setMblNo(String mblNo) {
//        this.mblNo = mblNo == null ? null : mblNo.trim();
//    }
//
//    public String getTelNo() {
//        return telNo;
//    }
//
//    public void setTelNo(String telNo) {
//        this.telNo = telNo == null ? null : telNo.trim();
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email == null ? null : email.trim();
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public Boolean getDelFlag() {
//        return delFlag;
//    }
//
//    public void setDelFlag(Boolean delFlag) {
//        this.delFlag = delFlag;
//    }
//
//    public String getFiller1() {
//        return filler1;
//    }
//
//    public void setFiller1(String filler1) {
//        this.filler1 = filler1 == null ? null : filler1.trim();
//    }
//
//    public String getFiller2() {
//        return filler2;
//    }
//
//    public void setFiller2(String filler2) {
//        this.filler2 = filler2 == null ? null : filler2.trim();
//    }
//
//    public String getFiller3() {
//        return filler3;
//    }
//
//    public void setFiller3(String filler3) {
//        this.filler3 = filler3 == null ? null : filler3.trim();
//    }
//
//    public Integer getVersion() {
//        return version;
//    }
//
//    public void setVersion(Integer version) {
//        this.version = version;
//    }
//
//	@Override
//	public String toString() {
//		return "AccOrg [id=" + id + ", owner=" + owner + ", ownerGroup="
//				+ ownerGroup + ", createTime=" + createTime
//				+ ", lastUpdateTime=" + lastUpdateTime + ", orgId=" + orgId
//				+ ", orgName=" + orgName + ", orgSname=" + orgSname
//				+ ", orgType=" + orgType + ", contact=" + contact + ", mblNo="
//				+ mblNo + ", telNo=" + telNo + ", email=" + email + ", status="
//				+ status + ", delFlag=" + delFlag + ", filler1=" + filler1
//				+ ", filler2=" + filler2 + ", filler3=" + filler3
//				+ ", version=" + version + "]";
//	}
//}