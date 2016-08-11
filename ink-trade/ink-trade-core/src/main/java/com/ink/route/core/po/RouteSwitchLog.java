package com.ink.route.core.po;

import java.util.Date;

public class RouteSwitchLog {
    /**  */
    private Long id;

    /**  */
    private String routeName;

    /**  */
    private String routeCode;

    /**  */
    private String beforeSwithStatus;

    /**  */
    private String afterSwithStatus;

    /**  */
    private String remark;

    /**  */
    private String payType;

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

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode == null ? null : routeCode.trim();
    }

    public String getBeforeSwithStatus() {
        return beforeSwithStatus;
    }

    public void setBeforeSwithStatus(String beforeSwithStatus) {
        this.beforeSwithStatus = beforeSwithStatus == null ? null : beforeSwithStatus.trim();
    }

    public String getAfterSwithStatus() {
        return afterSwithStatus;
    }

    public void setAfterSwithStatus(String afterSwithStatus) {
        this.afterSwithStatus = afterSwithStatus == null ? null : afterSwithStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
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