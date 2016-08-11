package com.ink.basic.core.po;

import java.util.Date;

public class BasicFeeItem {
    /**  */
    private Long id;

    /**  */
    private String feeItemCode;

    /**  */
    private String feeItemName;

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
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeeItemCode() {
        return feeItemCode;
    }

    public void setFeeItemCode(String feeItemCode) {
        this.feeItemCode = feeItemCode == null ? null : feeItemCode.trim();
    }

    public String getFeeItemName() {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName) {
        this.feeItemName = feeItemName == null ? null : feeItemName.trim();
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}