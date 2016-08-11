package com.ink.route.core.po;

import java.math.BigDecimal;
import java.util.Date;

public class RouteInfo {
    /**  */
    private Long id;

    /** 支持额度类型 */
    private Byte limitType;

    /** 支持小额上线 */
    private BigDecimal limitAmt;

    /**  */
    private String routeName;

    /**  */
    private String routeCode;

    /**  */
    private String payType;

    /**  */
    private Integer priority;

    /**  */
    private String routeStatus;

    /**  */
    private Long bankId;

    /**  */
    private String bankName;

    /**  */
    private Long asileId;

    /**  */
    private Date routeStartTime;

    /**  */
    private Date routeEndTime;

    /**  */
    private String routeTimeOutUnit;

    /**  */
    private Integer routeTimeOutNum;

    /**  */
    private String isSpecial;

    /**  */
    private String merchantId;

    /**  */
    private String merchantName;

    /**  */
    private String asileName;

    /**  */
    private String auditerName;

    /**  */
    private String auditerId;

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
    private Date updateTime;

    /**  */
    private Long updaterId;

    /**  */
    private String updaterName;

    /**  */
    private String isDelete;

    /**  */
    private String businessLineNo;

    /**  */
    private String businessLineNanme;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getLimitType() {
        return limitType;
    }

    public void setLimitType(Byte limitType) {
        this.limitType = limitType;
    }

    public BigDecimal getLimitAmt() {
        return limitAmt;
    }

    public void setLimitAmt(BigDecimal limitAmt) {
        this.limitAmt = limitAmt;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(String routeStatus) {
        this.routeStatus = routeStatus == null ? null : routeStatus.trim();
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Long getAsileId() {
        return asileId;
    }

    public void setAsileId(Long asileId) {
        this.asileId = asileId;
    }

    public Date getRouteStartTime() {
        return routeStartTime;
    }

    public void setRouteStartTime(Date routeStartTime) {
        this.routeStartTime = routeStartTime;
    }

    public Date getRouteEndTime() {
        return routeEndTime;
    }

    public void setRouteEndTime(Date routeEndTime) {
        this.routeEndTime = routeEndTime;
    }

    public String getRouteTimeOutUnit() {
        return routeTimeOutUnit;
    }

    public void setRouteTimeOutUnit(String routeTimeOutUnit) {
        this.routeTimeOutUnit = routeTimeOutUnit == null ? null : routeTimeOutUnit.trim();
    }

    public Integer getRouteTimeOutNum() {
        return routeTimeOutNum;
    }

    public void setRouteTimeOutNum(Integer routeTimeOutNum) {
        this.routeTimeOutNum = routeTimeOutNum;
    }

    public String getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(String isSpecial) {
        this.isSpecial = isSpecial == null ? null : isSpecial.trim();
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getAsileName() {
        return asileName;
    }

    public void setAsileName(String asileName) {
        this.asileName = asileName == null ? null : asileName.trim();
    }

    public String getAuditerName() {
        return auditerName;
    }

    public void setAuditerName(String auditerName) {
        this.auditerName = auditerName == null ? null : auditerName.trim();
    }

    public String getAuditerId() {
        return auditerId;
    }

    public void setAuditerId(String auditerId) {
        this.auditerId = auditerId == null ? null : auditerId.trim();
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

    public String getBusinessLineNo() {
        return businessLineNo;
    }

    public void setBusinessLineNo(String businessLineNo) {
        this.businessLineNo = businessLineNo == null ? null : businessLineNo.trim();
    }

    public String getBusinessLineNanme() {
        return businessLineNanme;
    }

    public void setBusinessLineNanme(String businessLineNanme) {
        this.businessLineNanme = businessLineNanme == null ? null : businessLineNanme.trim();
    }
}