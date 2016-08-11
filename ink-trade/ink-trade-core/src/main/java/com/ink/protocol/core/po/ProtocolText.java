package com.ink.protocol.core.po;

import java.util.Date;

public class ProtocolText {
    /**  */
    private Long id;

    /**  */
    private String agreeProtocolName;

    /**  */
    private String agreeProtocolNum;

    /**  */
    private Byte agreeProtocolType;

    /**  */
    private String agreeProtocolContent;

    /**  */
    private String agreeProtocolTypeNum;

    /**  */
    private Date protocolStartTime;

    /**  */
    private Date protocolEndTime;

    /**  */
    private String agreeProtocolStatus;

    /**  */
    private String auditerName;

    /**  */
    private Long auditerId;

    /**  */
    private Date auditTime;

    /**  */
    private String auditOpinion;

    /**  */
    private String asuitStatus;

    /**  */
    private String remark;

    /**  */
    private Date createTime;

    /**  */
    private Long createrId;

    /**  */
    private String createrName;

    /**  */
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgreeProtocolName() {
        return agreeProtocolName;
    }

    public void setAgreeProtocolName(String agreeProtocolName) {
        this.agreeProtocolName = agreeProtocolName == null ? null : agreeProtocolName.trim();
    }

    public String getAgreeProtocolNum() {
        return agreeProtocolNum;
    }

    public void setAgreeProtocolNum(String agreeProtocolNum) {
        this.agreeProtocolNum = agreeProtocolNum == null ? null : agreeProtocolNum.trim();
    }

    public Byte getAgreeProtocolType() {
        return agreeProtocolType;
    }

    public void setAgreeProtocolType(Byte agreeProtocolType) {
        this.agreeProtocolType = agreeProtocolType;
    }

    public String getAgreeProtocolContent() {
        return agreeProtocolContent;
    }

    public void setAgreeProtocolContent(String agreeProtocolContent) {
        this.agreeProtocolContent = agreeProtocolContent == null ? null : agreeProtocolContent.trim();
    }

    public String getAgreeProtocolTypeNum() {
        return agreeProtocolTypeNum;
    }

    public void setAgreeProtocolTypeNum(String agreeProtocolTypeNum) {
        this.agreeProtocolTypeNum = agreeProtocolTypeNum == null ? null : agreeProtocolTypeNum.trim();
    }

    public Date getProtocolStartTime() {
        return protocolStartTime;
    }

    public void setProtocolStartTime(Date protocolStartTime) {
        this.protocolStartTime = protocolStartTime;
    }

    public Date getProtocolEndTime() {
        return protocolEndTime;
    }

    public void setProtocolEndTime(Date protocolEndTime) {
        this.protocolEndTime = protocolEndTime;
    }

    public String getAgreeProtocolStatus() {
        return agreeProtocolStatus;
    }

    public void setAgreeProtocolStatus(String agreeProtocolStatus) {
        this.agreeProtocolStatus = agreeProtocolStatus == null ? null : agreeProtocolStatus.trim();
    }

    public String getAuditerName() {
        return auditerName;
    }

    public void setAuditerName(String auditerName) {
        this.auditerName = auditerName == null ? null : auditerName.trim();
    }

    public Long getAuditerId() {
        return auditerId;
    }

    public void setAuditerId(Long auditerId) {
        this.auditerId = auditerId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion == null ? null : auditOpinion.trim();
    }

    public String getAsuitStatus() {
        return asuitStatus;
    }

    public void setAsuitStatus(String asuitStatus) {
        this.asuitStatus = asuitStatus == null ? null : asuitStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}