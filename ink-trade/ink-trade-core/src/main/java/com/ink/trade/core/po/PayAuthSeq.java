package com.ink.trade.core.po;

import java.util.Date;

public class PayAuthSeq {
    /**  */
    private Long id;

    /**  */
    private Long accountId;

    /**  */
    private String accountName;

    /**  */
    private String accountId2;

    /**  */
    private String accountName2;

    /**  */
    private String cardName;

    /**  */
    private String cardNum;

    /**  */
    private Byte priority;

    /**  */
    private String cardCreateAddress;

    /**  */
    private String cardType;

    /**  */
    private String cardHolder;

    /**  */
    private Integer credentialType;

    /**  */
    private Long payAuthId;

    /**  */
    private Date asileAuthStartDate;

    /**  */
    private Date asileAuthEndDate;

    /**  */
    private String credentialNum;

    /**  */
    private String remark;

    /**  */
    private Date createTime;

    /**  */
    private Long createrId;

    /**  */
    private String createrName;

    /**  */
    private String isDelete;

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

    public String getAccountId2() {
        return accountId2;
    }

    public void setAccountId2(String accountId2) {
        this.accountId2 = accountId2 == null ? null : accountId2.trim();
    }

    public String getAccountName2() {
        return accountName2;
    }

    public void setAccountName2(String accountName2) {
        this.accountName2 = accountName2 == null ? null : accountName2.trim();
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public String getCardCreateAddress() {
        return cardCreateAddress;
    }

    public void setCardCreateAddress(String cardCreateAddress) {
        this.cardCreateAddress = cardCreateAddress == null ? null : cardCreateAddress.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder == null ? null : cardHolder.trim();
    }

    public Integer getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(Integer credentialType) {
        this.credentialType = credentialType;
    }

    public Long getPayAuthId() {
        return payAuthId;
    }

    public void setPayAuthId(Long payAuthId) {
        this.payAuthId = payAuthId;
    }

    public Date getAsileAuthStartDate() {
        return asileAuthStartDate;
    }

    public void setAsileAuthStartDate(Date asileAuthStartDate) {
        this.asileAuthStartDate = asileAuthStartDate;
    }

    public Date getAsileAuthEndDate() {
        return asileAuthEndDate;
    }

    public void setAsileAuthEndDate(Date asileAuthEndDate) {
        this.asileAuthEndDate = asileAuthEndDate;
    }

    public String getCredentialNum() {
        return credentialNum;
    }

    public void setCredentialNum(String credentialNum) {
        this.credentialNum = credentialNum == null ? null : credentialNum.trim();
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }
}