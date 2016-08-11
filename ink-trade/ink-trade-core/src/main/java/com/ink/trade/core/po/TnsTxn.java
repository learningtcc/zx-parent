package com.ink.trade.core.po;

import java.util.Date;

public class TnsTxn {
    /** 主键 */
    private Long id;

    /** 交易性质 */
    private String txnNature;

    /** 交易名称 */
    private String txnName;

    /** 交易码 */
    private String txnCode;

    /** 状态 */
    private Integer status;

    /** 创建时间 */
    private Date createTime;

    /** 操作人 */
    private String operator;

    /** 是否删除 */
    private Integer isDelete;

    /** 版本 */
    private Integer version;

    /** 备注 */
    private String remark;

    /** 最后修改时间 */
    private Date lastupdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTxnNature() {
        return txnNature;
    }

    public void setTxnNature(String txnNature) {
        this.txnNature = txnNature == null ? null : txnNature.trim();
    }

    public String getTxnName() {
        return txnName;
    }

    public void setTxnName(String txnName) {
        this.txnName = txnName == null ? null : txnName.trim();
    }

    public String getTxnCode() {
        return txnCode;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode == null ? null : txnCode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getLastupdateTime() {
        return lastupdateTime;
    }

    public void setLastupdateTime(Date lastupdateTime) {
        this.lastupdateTime = lastupdateTime;
    }
}