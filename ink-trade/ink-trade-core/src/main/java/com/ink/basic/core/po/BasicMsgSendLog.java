package com.ink.basic.core.po;

import java.util.Date;

public class BasicMsgSendLog {
    /**  */
    private Long id;

    /**  */
    private Long accountId;

    /**  */
    private String accountName;

    /**  */
    private String sendPlatformId;

    /**  */
    private String sendPlatformName;

    /**  */
    private String msgNote;

    /**  */
    private String msgLevel;

    /**  */
    private Integer receivingDeviceType;

    /**  */
    private String receivingDeviceNote;

    /**  */
    private String msgSendStatus;

    /**  */
    private String remark;

    /**  */
    private Date createTime;

    /**  */
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getSendPlatformId() {
        return sendPlatformId;
    }

    public void setSendPlatformId(String sendPlatformId) {
        this.sendPlatformId = sendPlatformId == null ? null : sendPlatformId.trim();
    }

    public String getSendPlatformName() {
        return sendPlatformName;
    }

    public void setSendPlatformName(String sendPlatformName) {
        this.sendPlatformName = sendPlatformName == null ? null : sendPlatformName.trim();
    }

    public String getMsgNote() {
        return msgNote;
    }

    public void setMsgNote(String msgNote) {
        this.msgNote = msgNote == null ? null : msgNote.trim();
    }

    public String getMsgLevel() {
        return msgLevel;
    }

    public void setMsgLevel(String msgLevel) {
        this.msgLevel = msgLevel == null ? null : msgLevel.trim();
    }

    public Integer getReceivingDeviceType() {
        return receivingDeviceType;
    }

    public void setReceivingDeviceType(Integer receivingDeviceType) {
        this.receivingDeviceType = receivingDeviceType;
    }

    public String getReceivingDeviceNote() {
        return receivingDeviceNote;
    }

    public void setReceivingDeviceNote(String receivingDeviceNote) {
        this.receivingDeviceNote = receivingDeviceNote == null ? null : receivingDeviceNote.trim();
    }

    public String getMsgSendStatus() {
        return msgSendStatus;
    }

    public void setMsgSendStatus(String msgSendStatus) {
        this.msgSendStatus = msgSendStatus == null ? null : msgSendStatus.trim();
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}