package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccProd implements Serializable{
    /** 
	 * @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2019662861668724250L;

	/** 数据库表主键 */
    private Long id;

    /** 创建人 */
    private String owner;

    /** 创建人所属机构 */
    private String ownerGroup;

    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date lastUpdateTime;

    /** 产品编号(帐户级别) 0001-未实名账户 0002-已实名账户 0003-VIP帐户 0004-公司账户 */
    private String prodId;

    /** 产品名称 */
    private String prodName;

    /** 客户类型 0-个人 1-单位 */
    private Integer custType;

    /** 密码错误次数上限 */
    private Integer pswdErrlmtCnt;

    /** 锁定时间 单位: 小时 值为0时不自动解锁 密码错误超限自动解锁时间 */
    private Integer pswdErrLockhours;

    /** 错误次数自动消除时间 单位: 小时 */
    private Integer pswdErrCleanhours;

    /** 日收入金额上限 */
    private BigDecimal dayInlmtAmt;

    /** 日收入笔数上限 */
    private Integer dayInlmtCnt;

    /** 日支出金额上限 */
    private BigDecimal dayPaylmtAmt;

    /** 日支出笔数上限 */
    private Integer dayPaylmtCnt;

    /** 月收入金额上限 */
    private BigDecimal monInlmtAmt;

    /** 月收入笔数上限 */
    private Integer monInlmtCnt;

    /** 月支出金额上限 */
    private BigDecimal monPaylmtAmt;

    /** 月支出笔数上限 */
    private Integer monPaylmtCnt;

    /** 日免签支付金额上限 */
    private BigDecimal dayNopswdlmtAmt;

    /** 日免签支付笔数上限 */
    private Integer dayNopswdlmtCnt;

    /** 状态 1-启用 2-停用 9-注销 */
    private Integer status;

    /**  */
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(String ownerGroup) {
        this.ownerGroup = ownerGroup == null ? null : ownerGroup.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public Integer getCustType() {
        return custType;
    }

    public void setCustType(Integer custType) {
        this.custType = custType;
    }

    public Integer getPswdErrlmtCnt() {
        return pswdErrlmtCnt;
    }

    public void setPswdErrlmtCnt(Integer pswdErrlmtCnt) {
        this.pswdErrlmtCnt = pswdErrlmtCnt;
    }

    public Integer getPswdErrLockhours() {
        return pswdErrLockhours;
    }

    public void setPswdErrLockhours(Integer pswdErrLockhours) {
        this.pswdErrLockhours = pswdErrLockhours;
    }

    public Integer getPswdErrCleanhours() {
        return pswdErrCleanhours;
    }

    public void setPswdErrCleanhours(Integer pswdErrCleanhours) {
        this.pswdErrCleanhours = pswdErrCleanhours;
    }

    public BigDecimal getDayInlmtAmt() {
        return dayInlmtAmt;
    }

    public void setDayInlmtAmt(BigDecimal dayInlmtAmt) {
        this.dayInlmtAmt = dayInlmtAmt;
    }

    public Integer getDayInlmtCnt() {
        return dayInlmtCnt;
    }

    public void setDayInlmtCnt(Integer dayInlmtCnt) {
        this.dayInlmtCnt = dayInlmtCnt;
    }

    public BigDecimal getDayPaylmtAmt() {
        return dayPaylmtAmt;
    }

    public void setDayPaylmtAmt(BigDecimal dayPaylmtAmt) {
        this.dayPaylmtAmt = dayPaylmtAmt;
    }

    public Integer getDayPaylmtCnt() {
        return dayPaylmtCnt;
    }

    public void setDayPaylmtCnt(Integer dayPaylmtCnt) {
        this.dayPaylmtCnt = dayPaylmtCnt;
    }

    public BigDecimal getMonInlmtAmt() {
        return monInlmtAmt;
    }

    public void setMonInlmtAmt(BigDecimal monInlmtAmt) {
        this.monInlmtAmt = monInlmtAmt;
    }

    public Integer getMonInlmtCnt() {
        return monInlmtCnt;
    }

    public void setMonInlmtCnt(Integer monInlmtCnt) {
        this.monInlmtCnt = monInlmtCnt;
    }

    public BigDecimal getMonPaylmtAmt() {
        return monPaylmtAmt;
    }

    public void setMonPaylmtAmt(BigDecimal monPaylmtAmt) {
        this.monPaylmtAmt = monPaylmtAmt;
    }

    public Integer getMonPaylmtCnt() {
        return monPaylmtCnt;
    }

    public void setMonPaylmtCnt(Integer monPaylmtCnt) {
        this.monPaylmtCnt = monPaylmtCnt;
    }

    public BigDecimal getDayNopswdlmtAmt() {
        return dayNopswdlmtAmt;
    }

    public void setDayNopswdlmtAmt(BigDecimal dayNopswdlmtAmt) {
        this.dayNopswdlmtAmt = dayNopswdlmtAmt;
    }

    public Integer getDayNopswdlmtCnt() {
        return dayNopswdlmtCnt;
    }

    public void setDayNopswdlmtCnt(Integer dayNopswdlmtCnt) {
        this.dayNopswdlmtCnt = dayNopswdlmtCnt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}