//package com.ink.user.core.po;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
//public class AccSac implements Serializable{
//    /** 
//	 * @Fields serialVersionUID : TODO
//	*/ 
//	private static final long serialVersionUID = -2061829731043704335L;
//
//	/** 数据库表主键 */
//    private Long id;
//
//    /** 创建人 */
//    private String owner;
//
//    /** 创建人所属机构 */
//    private String ownerGroup;
//
//    /** 创建时间 */
//    private Date createTime;
//
//    /** 最后修改时间 */
//    private Date lastUpdateTime;
//
//    /** 子帐户号 */
//    private String sacId;
//
//    /** 主帐户控制表主键 */
//    private Long accPacId;
//
//    /** 主账号 */
//    private String pacId;
//
//    /** 子帐户类型表主键 */
//    private Long accSacTypeId;
//
//    /** 子帐户种类 */
//    private String sacType;
//
//    /** 日充值金额累计 */
//    private BigDecimal dayVarchargeAmt;
//
//    /** 日充值笔数累计 */
//    private Integer dayVarchargeCnt;
//
//    /** 日消费金额累计 */
//    private BigDecimal dayPayAmt;
//
//    /** 日消费笔数累计 */
//    private Integer dayPayCnt;
//
//    /** 日转入金额累计 */
//    private BigDecimal dayInAmt;
//
//    /** 日转入笔数累计 */
//    private Integer dayInCnt;
//
//    /** 日转出金额累计 */
//    private BigDecimal dayOutAmt;
//
//    /** 日转出笔数累计 */
//    private Integer dayOutCnt;
//
//    /** 日提现金额累计 */
//    private BigDecimal dayCashAmt;
//
//    /** 日提现笔数累计 */
//    private Integer dayCashCnt;
//
//    /** 月充值金额累计 */
//    private BigDecimal monVarchargeAmt;
//
//    /** 月充值笔数累计 */
//    private Integer monVarchargeCnt;
//
//    /** 月消费金额累计 */
//    private BigDecimal monPayAmt;
//
//    /** 月消费笔数累计 */
//    private Integer monPayCnt;
//
//    /** 月转入金额累计 */
//    private BigDecimal monInAmt;
//
//    /** 月转入笔数累计 */
//    private Integer monInCnt;
//
//    /** 月转出金额累计 */
//    private BigDecimal monOutAmt;
//
//    /** 月转出笔数累计 */
//    private Integer monOutCnt;
//
//    /** 月提现金额累计 */
//    private BigDecimal monCashAmt;
//
//    /** 月提现笔数累计 */
//    private Integer monCashCnt;
//
//    /** 日充值金额上限 */
//    private BigDecimal dayVarchargelmtAmt;
//
//    /** 日充值笔数上限 */
//    private Integer dayVarchargelmtCnt;
//
//    /** 日消费金额上限 */
//    private BigDecimal dayPaylmtAmt;
//
//    /** 日消费笔数上限 */
//    private Integer dayPaylmtCnt;
//
//    /** 日转入金额上限 */
//    private BigDecimal dayInlmtAmt;
//
//    /** 日转入笔数上限 */
//    private Integer dayInlmtCnt;
//
//    /** 日转出金额上限 */
//    private BigDecimal dayOutlmtAmt;
//
//    /** 日转出笔数上限 */
//    private Integer dayOutlmtCnt;
//
//    /** 日提现金额上限 */
//    private BigDecimal dayCashlmtAmt;
//
//    /** 日提现笔数上限 */
//    private Integer dayCashlmtCnt;
//
//    /** 月充值金额上限 */
//    private BigDecimal monVarchargelmtAmt;
//
//    /** 月充值笔数上限 */
//    private Integer monVarchargelmtCnt;
//
//    /** 月消费金额上限 */
//    private BigDecimal monPaylmtAmt;
//
//    /** 月消费笔数上限 */
//    private Integer monPaylmtCnt;
//
//    /** 月转入金额上限 */
//    private BigDecimal monInlmtAmt;
//
//    /** 月转入笔数上限 */
//    private Integer monInlmtCnt;
//
//    /** 月转出金额上限 */
//    private BigDecimal monOutlmtAmt;
//
//    /** 月转出笔数上限 */
//    private Integer monOutlmtCnt;
//
//    /** 月提现金额上限 */
//    private BigDecimal monCashlmtAmt;
//
//    /** 月提现笔数上限 */
//    private Integer monCashlmtCnt;
//
//    /** 开户日期 */
//    private Date openDate;
//
//    /** 销户日期 */
//    private Date closeDate;
//
//    /** 状态 1-启用 2-停用 9-注销 */
//    private Integer status;
//
//    /**  */
//    private Integer version;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getOwner() {
//        return owner;
//    }
//
//    public void setOwner(String owner) {
//        this.owner = owner == null ? null : owner.trim();
//    }
//
//    public String getOwnerGroup() {
//        return ownerGroup;
//    }
//
//    public void setOwnerGroup(String ownerGroup) {
//        this.ownerGroup = ownerGroup == null ? null : ownerGroup.trim();
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Date getLastUpdateTime() {
//        return lastUpdateTime;
//    }
//
//    public void setLastUpdateTime(Date lastUpdateTime) {
//        this.lastUpdateTime = lastUpdateTime;
//    }
//
//    public String getSacId() {
//        return sacId;
//    }
//
//    public void setSacId(String sacId) {
//        this.sacId = sacId == null ? null : sacId.trim();
//    }
//
//    public Long getAccPacId() {
//        return accPacId;
//    }
//
//    public void setAccPacId(Long accPacId) {
//        this.accPacId = accPacId;
//    }
//
//    public String getPacId() {
//        return pacId;
//    }
//
//    public void setPacId(String pacId) {
//        this.pacId = pacId == null ? null : pacId.trim();
//    }
//
//    public Long getAccSacTypeId() {
//        return accSacTypeId;
//    }
//
//    public void setAccSacTypeId(Long accSacTypeId) {
//        this.accSacTypeId = accSacTypeId;
//    }
//
//    public String getSacType() {
//        return sacType;
//    }
//
//    public void setSacType(String sacType) {
//        this.sacType = sacType == null ? null : sacType.trim();
//    }
//
//    public BigDecimal getDayVarchargeAmt() {
//        return dayVarchargeAmt;
//    }
//
//    public void setDayVarchargeAmt(BigDecimal dayVarchargeAmt) {
//        this.dayVarchargeAmt = dayVarchargeAmt;
//    }
//
//    public Integer getDayVarchargeCnt() {
//        return dayVarchargeCnt;
//    }
//
//    public void setDayVarchargeCnt(Integer dayVarchargeCnt) {
//        this.dayVarchargeCnt = dayVarchargeCnt;
//    }
//
//    public BigDecimal getDayPayAmt() {
//        return dayPayAmt;
//    }
//
//    public void setDayPayAmt(BigDecimal dayPayAmt) {
//        this.dayPayAmt = dayPayAmt;
//    }
//
//    public Integer getDayPayCnt() {
//        return dayPayCnt;
//    }
//
//    public void setDayPayCnt(Integer dayPayCnt) {
//        this.dayPayCnt = dayPayCnt;
//    }
//
//    public BigDecimal getDayInAmt() {
//        return dayInAmt;
//    }
//
//    public void setDayInAmt(BigDecimal dayInAmt) {
//        this.dayInAmt = dayInAmt;
//    }
//
//    public Integer getDayInCnt() {
//        return dayInCnt;
//    }
//
//    public void setDayInCnt(Integer dayInCnt) {
//        this.dayInCnt = dayInCnt;
//    }
//
//    public BigDecimal getDayOutAmt() {
//        return dayOutAmt;
//    }
//
//    public void setDayOutAmt(BigDecimal dayOutAmt) {
//        this.dayOutAmt = dayOutAmt;
//    }
//
//    public Integer getDayOutCnt() {
//        return dayOutCnt;
//    }
//
//    public void setDayOutCnt(Integer dayOutCnt) {
//        this.dayOutCnt = dayOutCnt;
//    }
//
//    public BigDecimal getDayCashAmt() {
//        return dayCashAmt;
//    }
//
//    public void setDayCashAmt(BigDecimal dayCashAmt) {
//        this.dayCashAmt = dayCashAmt;
//    }
//
//    public Integer getDayCashCnt() {
//        return dayCashCnt;
//    }
//
//    public void setDayCashCnt(Integer dayCashCnt) {
//        this.dayCashCnt = dayCashCnt;
//    }
//
//    public BigDecimal getMonVarchargeAmt() {
//        return monVarchargeAmt;
//    }
//
//    public void setMonVarchargeAmt(BigDecimal monVarchargeAmt) {
//        this.monVarchargeAmt = monVarchargeAmt;
//    }
//
//    public Integer getMonVarchargeCnt() {
//        return monVarchargeCnt;
//    }
//
//    public void setMonVarchargeCnt(Integer monVarchargeCnt) {
//        this.monVarchargeCnt = monVarchargeCnt;
//    }
//
//    public BigDecimal getMonPayAmt() {
//        return monPayAmt;
//    }
//
//    public void setMonPayAmt(BigDecimal monPayAmt) {
//        this.monPayAmt = monPayAmt;
//    }
//
//    public Integer getMonPayCnt() {
//        return monPayCnt;
//    }
//
//    public void setMonPayCnt(Integer monPayCnt) {
//        this.monPayCnt = monPayCnt;
//    }
//
//    public BigDecimal getMonInAmt() {
//        return monInAmt;
//    }
//
//    public void setMonInAmt(BigDecimal monInAmt) {
//        this.monInAmt = monInAmt;
//    }
//
//    public Integer getMonInCnt() {
//        return monInCnt;
//    }
//
//    public void setMonInCnt(Integer monInCnt) {
//        this.monInCnt = monInCnt;
//    }
//
//    public BigDecimal getMonOutAmt() {
//        return monOutAmt;
//    }
//
//    public void setMonOutAmt(BigDecimal monOutAmt) {
//        this.monOutAmt = monOutAmt;
//    }
//
//    public Integer getMonOutCnt() {
//        return monOutCnt;
//    }
//
//    public void setMonOutCnt(Integer monOutCnt) {
//        this.monOutCnt = monOutCnt;
//    }
//
//    public BigDecimal getMonCashAmt() {
//        return monCashAmt;
//    }
//
//    public void setMonCashAmt(BigDecimal monCashAmt) {
//        this.monCashAmt = monCashAmt;
//    }
//
//    public Integer getMonCashCnt() {
//        return monCashCnt;
//    }
//
//    public void setMonCashCnt(Integer monCashCnt) {
//        this.monCashCnt = monCashCnt;
//    }
//
//    public BigDecimal getDayVarchargelmtAmt() {
//        return dayVarchargelmtAmt;
//    }
//
//    public void setDayVarchargelmtAmt(BigDecimal dayVarchargelmtAmt) {
//        this.dayVarchargelmtAmt = dayVarchargelmtAmt;
//    }
//
//    public Integer getDayVarchargelmtCnt() {
//        return dayVarchargelmtCnt;
//    }
//
//    public void setDayVarchargelmtCnt(Integer dayVarchargelmtCnt) {
//        this.dayVarchargelmtCnt = dayVarchargelmtCnt;
//    }
//
//    public BigDecimal getDayPaylmtAmt() {
//        return dayPaylmtAmt;
//    }
//
//    public void setDayPaylmtAmt(BigDecimal dayPaylmtAmt) {
//        this.dayPaylmtAmt = dayPaylmtAmt;
//    }
//
//    public Integer getDayPaylmtCnt() {
//        return dayPaylmtCnt;
//    }
//
//    public void setDayPaylmtCnt(Integer dayPaylmtCnt) {
//        this.dayPaylmtCnt = dayPaylmtCnt;
//    }
//
//    public BigDecimal getDayInlmtAmt() {
//        return dayInlmtAmt;
//    }
//
//    public void setDayInlmtAmt(BigDecimal dayInlmtAmt) {
//        this.dayInlmtAmt = dayInlmtAmt;
//    }
//
//    public Integer getDayInlmtCnt() {
//        return dayInlmtCnt;
//    }
//
//    public void setDayInlmtCnt(Integer dayInlmtCnt) {
//        this.dayInlmtCnt = dayInlmtCnt;
//    }
//
//    public BigDecimal getDayOutlmtAmt() {
//        return dayOutlmtAmt;
//    }
//
//    public void setDayOutlmtAmt(BigDecimal dayOutlmtAmt) {
//        this.dayOutlmtAmt = dayOutlmtAmt;
//    }
//
//    public Integer getDayOutlmtCnt() {
//        return dayOutlmtCnt;
//    }
//
//    public void setDayOutlmtCnt(Integer dayOutlmtCnt) {
//        this.dayOutlmtCnt = dayOutlmtCnt;
//    }
//
//    public BigDecimal getDayCashlmtAmt() {
//        return dayCashlmtAmt;
//    }
//
//    public void setDayCashlmtAmt(BigDecimal dayCashlmtAmt) {
//        this.dayCashlmtAmt = dayCashlmtAmt;
//    }
//
//    public Integer getDayCashlmtCnt() {
//        return dayCashlmtCnt;
//    }
//
//    public void setDayCashlmtCnt(Integer dayCashlmtCnt) {
//        this.dayCashlmtCnt = dayCashlmtCnt;
//    }
//
//    public BigDecimal getMonVarchargelmtAmt() {
//        return monVarchargelmtAmt;
//    }
//
//    public void setMonVarchargelmtAmt(BigDecimal monVarchargelmtAmt) {
//        this.monVarchargelmtAmt = monVarchargelmtAmt;
//    }
//
//    public Integer getMonVarchargelmtCnt() {
//        return monVarchargelmtCnt;
//    }
//
//    public void setMonVarchargelmtCnt(Integer monVarchargelmtCnt) {
//        this.monVarchargelmtCnt = monVarchargelmtCnt;
//    }
//
//    public BigDecimal getMonPaylmtAmt() {
//        return monPaylmtAmt;
//    }
//
//    public void setMonPaylmtAmt(BigDecimal monPaylmtAmt) {
//        this.monPaylmtAmt = monPaylmtAmt;
//    }
//
//    public Integer getMonPaylmtCnt() {
//        return monPaylmtCnt;
//    }
//
//    public void setMonPaylmtCnt(Integer monPaylmtCnt) {
//        this.monPaylmtCnt = monPaylmtCnt;
//    }
//
//    public BigDecimal getMonInlmtAmt() {
//        return monInlmtAmt;
//    }
//
//    public void setMonInlmtAmt(BigDecimal monInlmtAmt) {
//        this.monInlmtAmt = monInlmtAmt;
//    }
//
//    public Integer getMonInlmtCnt() {
//        return monInlmtCnt;
//    }
//
//    public void setMonInlmtCnt(Integer monInlmtCnt) {
//        this.monInlmtCnt = monInlmtCnt;
//    }
//
//    public BigDecimal getMonOutlmtAmt() {
//        return monOutlmtAmt;
//    }
//
//    public void setMonOutlmtAmt(BigDecimal monOutlmtAmt) {
//        this.monOutlmtAmt = monOutlmtAmt;
//    }
//
//    public Integer getMonOutlmtCnt() {
//        return monOutlmtCnt;
//    }
//
//    public void setMonOutlmtCnt(Integer monOutlmtCnt) {
//        this.monOutlmtCnt = monOutlmtCnt;
//    }
//
//    public BigDecimal getMonCashlmtAmt() {
//        return monCashlmtAmt;
//    }
//
//    public void setMonCashlmtAmt(BigDecimal monCashlmtAmt) {
//        this.monCashlmtAmt = monCashlmtAmt;
//    }
//
//    public Integer getMonCashlmtCnt() {
//        return monCashlmtCnt;
//    }
//
//    public void setMonCashlmtCnt(Integer monCashlmtCnt) {
//        this.monCashlmtCnt = monCashlmtCnt;
//    }
//
//    public Date getOpenDate() {
//        return openDate;
//    }
//
//    public void setOpenDate(Date openDate) {
//        this.openDate = openDate;
//    }
//
//    public Date getCloseDate() {
//        return closeDate;
//    }
//
//    public void setCloseDate(Date closeDate) {
//        this.closeDate = closeDate;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public Integer getVersion() {
//        return version;
//    }
//
//    public void setVersion(Integer version) {
//        this.version = version;
//    }
//}