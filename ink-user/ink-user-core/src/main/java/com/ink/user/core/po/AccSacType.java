package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ink.base.util.DateConvertUtils;
import com.ink.user.common.constant.PatsAtpConstant;

public class AccSacType implements Serializable{
    /** 
	 * @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7937848038429105362L;
	
	// date formats
	public static final String FORMAT_CREATE_TIME = PatsAtpConstant.DATE_TIME_FORMAT;
	public static final String FORMAT_LAST_UPDATE_TIME = PatsAtpConstant.DATE_TIME_FORMAT;
	public static final String FORMAT_BIRTHDAY = PatsAtpConstant.DATE_FORMAT;

	/** 数据库表主键 */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date lastUpdateTime;

    /** 子帐户种类 */
    private String sacType;

    /** 子帐户名称 */
    private String sacName;

    /** 币种 */
    private String cur;

    /** 日充值金额上限 */
    private BigDecimal dayVarchargelmtAmt;

    /** 日充值笔数上限 */
    private Integer dayVarchargelmtCnt;

    /** 日消费金额上限 */
    private BigDecimal dayPaylmtAmt;

    /** 日消费笔数上限 */
    private Integer dayPaylmtCnt;

    /** 日转入金额上限 */
    private BigDecimal dayInlmtAmt;

    /** 日转入笔数上限 */
    private Integer dayInlmtCnt;

    /** 日转出金额上限 */
    private BigDecimal dayOutlmtAmt;

    /** 日转出笔数上限 */
    private Integer dayOutlmtCnt;

    /** 日提现金额上限 */
    private BigDecimal dayCashlmtAmt;

    /** 日提现笔数上限 */
    private Integer dayCashlmtCnt;

    /** 月充值金额上限 */
    private BigDecimal monVarchargelmtAmt;

    /** 月充值笔数上限 */
    private Integer monVarchargelmtCnt;

    /** 月消费金额上限 */
    private BigDecimal monPaylmtAmt;

    /** 月消费笔数上限 */
    private Integer monPaylmtCnt;

    /** 月转入金额上限 */
    private BigDecimal monInlmtAmt;

    /** 月转入笔数上限 */
    private Integer monInlmtCnt;

    /** 月转出金额上限 */
    private BigDecimal monOutlmtAmt;

    /** 月转出笔数上限 */
    private Integer monOutlmtCnt;

    /** 月提现金额上限 */
    private BigDecimal monCashlmtAmt;

    /** 月提现笔数上限 */
    private Integer monCashlmtCnt;

    /** 状态 1-启用 2-停用 9-注销 */
    private Integer status;

    /**  */
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSacType() {
        return sacType;
    }

    public void setSacType(String sacType) {
        this.sacType = sacType == null ? null : sacType.trim();
    }

    public String getSacName() {
        return sacName;
    }

    public void setSacName(String sacName) {
        this.sacName = sacName == null ? null : sacName.trim();
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur == null ? null : cur.trim();
    }

    public BigDecimal getDayVarchargelmtAmt() {
        return dayVarchargelmtAmt;
    }

    public void setDayVarchargelmtAmt(BigDecimal dayVarchargelmtAmt) {
        this.dayVarchargelmtAmt = dayVarchargelmtAmt;
    }

    public Integer getDayVarchargelmtCnt() {
        return dayVarchargelmtCnt;
    }

    public void setDayVarchargelmtCnt(Integer dayVarchargelmtCnt) {
        this.dayVarchargelmtCnt = dayVarchargelmtCnt;
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

    public BigDecimal getDayOutlmtAmt() {
        return dayOutlmtAmt;
    }

    public void setDayOutlmtAmt(BigDecimal dayOutlmtAmt) {
        this.dayOutlmtAmt = dayOutlmtAmt;
    }

    public Integer getDayOutlmtCnt() {
        return dayOutlmtCnt;
    }

    public void setDayOutlmtCnt(Integer dayOutlmtCnt) {
        this.dayOutlmtCnt = dayOutlmtCnt;
    }

    public BigDecimal getDayCashlmtAmt() {
        return dayCashlmtAmt;
    }

    public void setDayCashlmtAmt(BigDecimal dayCashlmtAmt) {
        this.dayCashlmtAmt = dayCashlmtAmt;
    }

    public Integer getDayCashlmtCnt() {
        return dayCashlmtCnt;
    }

    public void setDayCashlmtCnt(Integer dayCashlmtCnt) {
        this.dayCashlmtCnt = dayCashlmtCnt;
    }

    public BigDecimal getMonVarchargelmtAmt() {
        return monVarchargelmtAmt;
    }

    public void setMonVarchargelmtAmt(BigDecimal monVarchargelmtAmt) {
        this.monVarchargelmtAmt = monVarchargelmtAmt;
    }

    public Integer getMonVarchargelmtCnt() {
        return monVarchargelmtCnt;
    }

    public void setMonVarchargelmtCnt(Integer monVarchargelmtCnt) {
        this.monVarchargelmtCnt = monVarchargelmtCnt;
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

    public BigDecimal getMonOutlmtAmt() {
        return monOutlmtAmt;
    }

    public void setMonOutlmtAmt(BigDecimal monOutlmtAmt) {
        this.monOutlmtAmt = monOutlmtAmt;
    }

    public Integer getMonOutlmtCnt() {
        return monOutlmtCnt;
    }

    public void setMonOutlmtCnt(Integer monOutlmtCnt) {
        this.monOutlmtCnt = monOutlmtCnt;
    }

    public BigDecimal getMonCashlmtAmt() {
        return monCashlmtAmt;
    }

    public void setMonCashlmtAmt(BigDecimal monCashlmtAmt) {
        this.monCashlmtAmt = monCashlmtAmt;
    }

    public Integer getMonCashlmtCnt() {
        return monCashlmtCnt;
    }

    public void setMonCashlmtCnt(Integer monCashlmtCnt) {
        this.monCashlmtCnt = monCashlmtCnt;
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
		builder.append("AccSacType [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
		builder.append(", sacType=");
		builder.append(sacType);
		builder.append(", sacName=");
		builder.append(sacName);
		builder.append(", cur=");
		builder.append(cur);
		builder.append(", dayVarchargelmtAmt=");
		builder.append(dayVarchargelmtAmt);
		builder.append(", dayVarchargelmtCnt=");
		builder.append(dayVarchargelmtCnt);
		builder.append(", dayPaylmtAmt=");
		builder.append(dayPaylmtAmt);
		builder.append(", dayPaylmtCnt=");
		builder.append(dayPaylmtCnt);
		builder.append(", dayInlmtAmt=");
		builder.append(dayInlmtAmt);
		builder.append(", dayInlmtCnt=");
		builder.append(dayInlmtCnt);
		builder.append(", dayOutlmtAmt=");
		builder.append(dayOutlmtAmt);
		builder.append(", dayOutlmtCnt=");
		builder.append(dayOutlmtCnt);
		builder.append(", dayCashlmtAmt=");
		builder.append(dayCashlmtAmt);
		builder.append(", dayCashlmtCnt=");
		builder.append(dayCashlmtCnt);
		builder.append(", monVarchargelmtAmt=");
		builder.append(monVarchargelmtAmt);
		builder.append(", monVarchargelmtCnt=");
		builder.append(monVarchargelmtCnt);
		builder.append(", monPaylmtAmt=");
		builder.append(monPaylmtAmt);
		builder.append(", monPaylmtCnt=");
		builder.append(monPaylmtCnt);
		builder.append(", monInlmtAmt=");
		builder.append(monInlmtAmt);
		builder.append(", monInlmtCnt=");
		builder.append(monInlmtCnt);
		builder.append(", monOutlmtAmt=");
		builder.append(monOutlmtAmt);
		builder.append(", monOutlmtCnt=");
		builder.append(monOutlmtCnt);
		builder.append(", monCashlmtAmt=");
		builder.append(monCashlmtAmt);
		builder.append(", monCashlmtCnt=");
		builder.append(monCashlmtCnt);
		builder.append(", status=");
		builder.append(status);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
	
}