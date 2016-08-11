package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ink.base.util.DateConvertUtils;
import com.ink.user.common.constant.PatsAtpConstant;

public class AccPac implements Serializable{
    /** 
	 * @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8619253825305519553L;
	
	// date formats
	public static final String FORMAT_CREATE_TIME = PatsAtpConstant.DATE_TIME_FORMAT;
	public static final String FORMAT_LAST_UPDATE_TIME = PatsAtpConstant.DATE_TIME_FORMAT;
	public static final String FORMAT_OPEN_DATE = PatsAtpConstant.DATE_TIME_FORMAT;
	public static final String FORMAT_CLOSE_DATE = PatsAtpConstant.DATE_TIME_FORMAT;

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

    /** 主账号 */
    private String pacId;

    /** 商户信息表主键 */
    private Long accProdId;

    /** 产品编号(帐户级别) 0001-未实名账户 0002-已实名账户 0003-VIP帐户 0004-公司账户 */
    private String prodId;

    /** 客户类型 0-个人 1-单位 */
    private Integer custType;

    /** 日收入金额累计 */
    private BigDecimal dayInAmt;

    /** 日收入笔数累计 */
    private Integer dayInCnt;

    /** 日支出金额累计 */
    private BigDecimal dayPayAmt;

    /** 日支出笔数累计 */
    private Integer dayPayCnt;

    /** 月收入金额累计 */
    private BigDecimal monInAmt;

    /** 月收入笔数累计 */
    private Integer monInCnt;

    /** 月支出金额累计 */
    private BigDecimal monPayAmt;

    /** 月支出笔数累计 */
    private Integer monPayCnt;

    /** 日免签支付金额累计 */
    private BigDecimal dayNopswdAmt;

    /** 日免签支付笔数累计 */
    private Integer dayNopswdCnt;

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

    /** 开户日期 */
    private Date openDate;

    /** 销户日期 */
    private Date closeDate;

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

    public String getPacId() {
        return pacId;
    }

    public void setPacId(String pacId) {
        this.pacId = pacId == null ? null : pacId.trim();
    }

    public Long getAccProdId() {
        return accProdId;
    }

    public void setAccProdId(Long accProdId) {
        this.accProdId = accProdId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public Integer getCustType() {
        return custType;
    }

    public void setCustType(Integer custType) {
        this.custType = custType;
    }

    public BigDecimal getDayInAmt() {
        return dayInAmt;
    }

    public void setDayInAmt(BigDecimal dayInAmt) {
        this.dayInAmt = dayInAmt;
    }

    public Integer getDayInCnt() {
        return dayInCnt;
    }

    public void setDayInCnt(Integer dayInCnt) {
        this.dayInCnt = dayInCnt;
    }

    public BigDecimal getDayPayAmt() {
        return dayPayAmt;
    }

    public void setDayPayAmt(BigDecimal dayPayAmt) {
        this.dayPayAmt = dayPayAmt;
    }

    public Integer getDayPayCnt() {
        return dayPayCnt;
    }

    public void setDayPayCnt(Integer dayPayCnt) {
        this.dayPayCnt = dayPayCnt;
    }

    public BigDecimal getMonInAmt() {
        return monInAmt;
    }

    public void setMonInAmt(BigDecimal monInAmt) {
        this.monInAmt = monInAmt;
    }

    public Integer getMonInCnt() {
        return monInCnt;
    }

    public void setMonInCnt(Integer monInCnt) {
        this.monInCnt = monInCnt;
    }

    public BigDecimal getMonPayAmt() {
        return monPayAmt;
    }

    public void setMonPayAmt(BigDecimal monPayAmt) {
        this.monPayAmt = monPayAmt;
    }

    public Integer getMonPayCnt() {
        return monPayCnt;
    }

    public void setMonPayCnt(Integer monPayCnt) {
        this.monPayCnt = monPayCnt;
    }

    public BigDecimal getDayNopswdAmt() {
        return dayNopswdAmt;
    }

    public void setDayNopswdAmt(BigDecimal dayNopswdAmt) {
        this.dayNopswdAmt = dayNopswdAmt;
    }

    public Integer getDayNopswdCnt() {
        return dayNopswdCnt;
    }

    public void setDayNopswdCnt(Integer dayNopswdCnt) {
        this.dayNopswdCnt = dayNopswdCnt;
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

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
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
    public String getCloseDateString() {
		return DateConvertUtils.format(getCloseDate(), FORMAT_CLOSE_DATE);
	}
	public void setCloseDateString(String value) {
		setCloseDate(DateConvertUtils.parse(value, FORMAT_CLOSE_DATE,java.util.Date.class));
	}
	public String getOpenDateString() {
		return DateConvertUtils.format(getOpenDate(), FORMAT_OPEN_DATE);
	}
	public void setOpenDateString(String value) {
		setOpenDate(DateConvertUtils.parse(value, FORMAT_OPEN_DATE,java.util.Date.class));
	}
	public String getLastUpdateTimeString() {
		return DateConvertUtils.format(getLastUpdateTime(), FORMAT_LAST_UPDATE_TIME);
	}
	public void setLastUpdateTimeString(String value) {
		setLastUpdateTime(DateConvertUtils.parse(value, FORMAT_LAST_UPDATE_TIME,java.util.Date.class));
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccPac [id=");
		builder.append(id);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", ownerGroup=");
		builder.append(ownerGroup);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
		builder.append(", pacId=");
		builder.append(pacId);
		builder.append(", accProdId=");
		builder.append(accProdId);
		builder.append(", prodId=");
		builder.append(prodId);
		builder.append(", custType=");
		builder.append(custType);
		builder.append(", dayInAmt=");
		builder.append(dayInAmt);
		builder.append(", dayInCnt=");
		builder.append(dayInCnt);
		builder.append(", dayPayAmt=");
		builder.append(dayPayAmt);
		builder.append(", dayPayCnt=");
		builder.append(dayPayCnt);
		builder.append(", monInAmt=");
		builder.append(monInAmt);
		builder.append(", monInCnt=");
		builder.append(monInCnt);
		builder.append(", monPayAmt=");
		builder.append(monPayAmt);
		builder.append(", monPayCnt=");
		builder.append(monPayCnt);
		builder.append(", dayNopswdAmt=");
		builder.append(dayNopswdAmt);
		builder.append(", dayNopswdCnt=");
		builder.append(dayNopswdCnt);
		builder.append(", dayInlmtAmt=");
		builder.append(dayInlmtAmt);
		builder.append(", dayInlmtCnt=");
		builder.append(dayInlmtCnt);
		builder.append(", dayPaylmtAmt=");
		builder.append(dayPaylmtAmt);
		builder.append(", dayPaylmtCnt=");
		builder.append(dayPaylmtCnt);
		builder.append(", monInlmtAmt=");
		builder.append(monInlmtAmt);
		builder.append(", monInlmtCnt=");
		builder.append(monInlmtCnt);
		builder.append(", monPaylmtAmt=");
		builder.append(monPaylmtAmt);
		builder.append(", monPaylmtCnt=");
		builder.append(monPaylmtCnt);
		builder.append(", dayNopswdlmtAmt=");
		builder.append(dayNopswdlmtAmt);
		builder.append(", dayNopswdlmtCnt=");
		builder.append(dayNopswdlmtCnt);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", closeDate=");
		builder.append(closeDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
	
}