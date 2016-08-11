//package com.ink.user.core.po;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
//public class AccIac implements Serializable{
//    /** 
//     * @Fields serialVersionUID : TODO
//    */ 
//    private static final long serialVersionUID = -3493108039919345213L;
//
//    
//    /** 数据库表主键 */
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
//    /** 内部账户号 */
//    private String iacId;
//
//    /** 币种 */
//    private String cur;
//
//    /** 上级科目号 */
//    private String upItemId;
//
//    /** 内部账户类型 1-商户账户 2-机构账户 3-科目账户 */
//    private Integer iacType;
//
//    /** 昨日余额 日终批处理更新 */
//    private BigDecimal lstBal;
//
//    /** 当前余额 */
//    private BigDecimal curBal;
//
//    /** 临时余额 日终使用 */
//    private BigDecimal tmpBal;
//
//    /** 最低余额 */
//    private BigDecimal minBal;
//
//    /** 余额方向 */
//    private String balDir;
//
//    /** 交易方向 */
//    private Integer txnDir;
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
//    /** 删除标识 0-正常 1-删除 */
//    private Integer delFlag;
//
//    /** 预留字段1 */
//    private String filler1;
//
//    /** 预留字段2 */
//    private String filler2;
//
//    /** 预留字段3 */
//    private String filler3;
//
//    /**  */
//    private Integer version;
//
//    /** 内部账户认证信息 */
//    private String iacMac;
//    
//    /** 商户编号 */
//    private Long mchId;
//    
//    /** 机构编号 */
//    private String orgId;
//    
//    private String accDepositType;
//    
//
//    @Override
//	public String toString() {
//		return "AccIac [id=" + id + ", owner=" + owner + ", ownerGroup="
//				+ ownerGroup + ", createTime=" + createTime
//				+ ", lastUpdateTime=" + lastUpdateTime + ", iacId=" + iacId
//				+ ", cur=" + cur + ", upItemId=" + upItemId + ", iacType="
//				+ iacType + ", lstBal=" + lstBal + ", curBal=" + curBal
//				+ ", tmpBal=" + tmpBal + ", minBal=" + minBal + ", balDir="
//				+ balDir + ", txnDir=" + txnDir + ", openDate=" + openDate
//				+ ", closeDate=" + closeDate + ", status=" + status
//				+ ", delFlag=" + delFlag + ", filler1=" + filler1
//				+ ", filler2=" + filler2 + ", filler3=" + filler3
//				+ ", version=" + version + ", iacMac=" + iacMac + ", mchId="
//				+ mchId + ", orgId=" + orgId + ", accDepositType="
//				+ accDepositType + "]";
//	}
//
//	public Long getId() {
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
//
//    public String getCur() {
//        return cur;
//    }
//
//    public void setCur(String cur) {
//        this.cur = cur == null ? null : cur.trim();
//    }
//
//    public String getUpItemId() {
//        return upItemId;
//    }
//
//    public void setUpItemId(String upItemId) {
//        this.upItemId = upItemId == null ? null : upItemId.trim();
//    }
//
//    public Integer getIacType() {
//        return iacType;
//    }
//
//    public void setIacType(Integer iacType) {
//        this.iacType = iacType;
//    }
//
//    public BigDecimal getLstBal() {
//        return lstBal;
//    }
//
//    public void setLstBal(BigDecimal lstBal) {
//        this.lstBal = lstBal;
//    }
//
//    public BigDecimal getCurBal() {
//        return curBal;
//    }
//
//    public void setCurBal(BigDecimal curBal) {
//        this.curBal = curBal;
//    }
//
//    public BigDecimal getTmpBal() {
//        return tmpBal;
//    }
//
//    public void setTmpBal(BigDecimal tmpBal) {
//        this.tmpBal = tmpBal;
//    }
//
//    public BigDecimal getMinBal() {
//        return minBal;
//    }
//
//    public void setMinBal(BigDecimal minBal) {
//        this.minBal = minBal;
//    }
//
//    public String getBalDir() {
//        return balDir;
//    }
//
//    public void setBalDir(String balDir) {
//        this.balDir = balDir;
//    }
//
//    public Integer getTxnDir() {
//        return txnDir;
//    }
//
//    public void setTxnDir(Integer txnDir) {
//        this.txnDir = txnDir;
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
//    public Integer getDelFlag() {
//		return delFlag;
//	}
//
//	public void setDelFlag(Integer delFlag) {
//		this.delFlag = delFlag;
//	}
//
//	public String getFiller1() {
//        return filler1;
//    }
//
//    public void setFiller1(String filler1) {
//        this.filler1 = filler1 == null ? null : filler1.trim();
//    }
//
//    public String getFiller2() {
//        return filler2;
//    }
//
//    public void setFiller2(String filler2) {
//        this.filler2 = filler2 == null ? null : filler2.trim();
//    }
//
//    public String getFiller3() {
//        return filler3;
//    }
//
//    public void setFiller3(String filler3) {
//        this.filler3 = filler3 == null ? null : filler3.trim();
//    }
//
//    public Integer getVersion() {
//        return version;
//    }
//
//    public void setVersion(Integer version) {
//        this.version = version;
//    }
//
//    public String getIacMac() {
//        return iacMac;
//    }
//
//    public void setIacMac(String iacMac) {
//        this.iacMac = iacMac == null ? null : iacMac.trim();
//    }
//
//	public String getIacId() {
//		return iacId;
//	}
//
//	public void setIacId(String iacId) {
//		this.iacId = iacId;
//	}
//
//	public Long getMchId() {
//		return mchId;
//	}
//
//	public void setMchId(Long mchId) {
//		this.mchId = mchId;
//	}
//
//	public String getOrgId() {
//		return orgId;
//	}
//
//	public void setOrgId(String orgId) {
//		this.orgId = orgId;
//	}
//
//	public String getAccDepositType() {
//		return accDepositType;
//	}
//
//	public void setAccDepositType(String accDepositType) {
//		this.accDepositType = accDepositType;
//	}
//}