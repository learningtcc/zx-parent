package com.ink.basic.core.po;

import java.util.Date;

public class BasicCardBin {
    /**  */
    private Long id;

    /**  */
    private String createCardName;

    /**  */
    private String createCardCode;

    /**  */
    private String cardName;

    /**  */
    private Integer cardLength;

    /**  */
    private String cardId;

    /**  */
    private String cardType;

    /**  */
    private String cardTypeCode;

    /**  */
    private Integer createBankLength;

    /**  */
    private String remark;

    /**  */
    private Date createTime;

    /**  */
    private Long createrId;

    /**  */
    private String createrName;

    /**  */
    private Date updateTime;

    /**  */
    private Long updaterId;

    /**  */
    private String updaterName;

    /**  */
    private String isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateCardName() {
        return createCardName;
    }

    public void setCreateCardName(String createCardName) {
        this.createCardName = createCardName == null ? null : createCardName.trim();
    }

    public String getCreateCardCode() {
        return createCardCode;
    }

    public void setCreateCardCode(String createCardCode) {
        this.createCardCode = createCardCode == null ? null : createCardCode.trim();
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public Integer getCardLength() {
        return cardLength;
    }

    public void setCardLength(Integer cardLength) {
        this.cardLength = cardLength;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardTypeCode() {
        return cardTypeCode;
    }

    public void setCardTypeCode(String cardTypeCode) {
        this.cardTypeCode = cardTypeCode == null ? null : cardTypeCode.trim();
    }

    public Integer getCreateBankLength() {
        return createBankLength;
    }

    public void setCreateBankLength(Integer createBankLength) {
        this.createBankLength = createBankLength;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName == null ? null : updaterName.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }
}