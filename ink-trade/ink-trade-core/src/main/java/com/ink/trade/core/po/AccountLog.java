package com.ink.trade.core.po;

import java.math.BigDecimal;
import java.util.Date;

public class AccountLog {
    /** 主键 */
    private Long id;

    /** 用户名 */
    private String userName;

    /** 用户id */
    private Long userId;

    /** 进账出账金额 */
    private BigDecimal amt;

    /** 创建时间 */
    private Date createTime;

    /** 备注 */
    private String remark;

    /** 订单号 */
    private String ordId;

    /** 退款id */
    private Long refundId;

    /** 入账/出账 */
    private Integer inOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public Integer getInOut() {
        return inOut;
    }

    public void setInOut(Integer inOut) {
        this.inOut = inOut;
    }
}