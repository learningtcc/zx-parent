//package com.ink.user.core.po;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
//public class AccMcc implements Serializable{
//    /** 
//	 * @Fields serialVersionUID : TODO
//	*/ 
//	private static final long serialVersionUID = 8711212614773988999L;
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
//    /** 商户类别 */
//    private String mcc;
//
//    /** 适用范围 */
//    private String mccRange;
//
//    /** 大类 */
//    private String masterType;
//
//    /** 细类 */
//    private String detailType;
//
//    /** 手续费收取模式 1-固定金额收取 2-按比率收取 */
//    private Integer feeMode;
//
//    /** 固定手续费金额 */
//    private BigDecimal feeFixAmt;
//
//    /** 固定手续费费率, 单位为% */
//    private BigDecimal feeRate;
//
//    /** 手续费收取最高金额 */
//    private BigDecimal feeMaxAmt;
//
//    /** 备注 */
//    private String memo;
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
//    public String getMcc() {
//        return mcc;
//    }
//
//    public void setMcc(String mcc) {
//        this.mcc = mcc == null ? null : mcc.trim();
//    }
//
//    public String getMccRange() {
//        return mccRange;
//    }
//
//    public void setMccRange(String mccRange) {
//        this.mccRange = mccRange == null ? null : mccRange.trim();
//    }
//
//    public String getMasterType() {
//        return masterType;
//    }
//
//    public void setMasterType(String masterType) {
//        this.masterType = masterType == null ? null : masterType.trim();
//    }
//
//    public String getDetailType() {
//        return detailType;
//    }
//
//    public void setDetailType(String detailType) {
//        this.detailType = detailType == null ? null : detailType.trim();
//    }
//
//    public Integer getFeeMode() {
//        return feeMode;
//    }
//
//    public void setFeeMode(Integer feeMode) {
//        this.feeMode = feeMode;
//    }
//
//    public BigDecimal getFeeFixAmt() {
//        return feeFixAmt;
//    }
//
//    public void setFeeFixAmt(BigDecimal feeFixAmt) {
//        this.feeFixAmt = feeFixAmt;
//    }
//
//    public BigDecimal getFeeRate() {
//        return feeRate;
//    }
//
//    public void setFeeRate(BigDecimal feeRate) {
//        this.feeRate = feeRate;
//    }
//
//    public BigDecimal getFeeMaxAmt() {
//        return feeMaxAmt;
//    }
//
//    public void setFeeMaxAmt(BigDecimal feeMaxAmt) {
//        this.feeMaxAmt = feeMaxAmt;
//    }
//
//    public String getMemo() {
//        return memo;
//    }
//
//    public void setMemo(String memo) {
//        this.memo = memo == null ? null : memo.trim();
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