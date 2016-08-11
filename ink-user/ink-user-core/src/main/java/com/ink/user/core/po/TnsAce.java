package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TnsAce implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 4778927028782847040L;

	/** 数据库表主键 */
	private String id;

	/** 创建人 */
	private String owner;

	/** 创建人所属机构 */
	private String ownerGroup;

	/** 创建时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date lastUpdateTime;

	/** 交易流水表主键 */
	private String tnsLogId;

	/** 联机交易代码表主键 */
	private Long tnsTxnId;

	/** 交易代码 */
	private String txnCode;

	/** 分录组号 */
	private String aceGroup;

	/** 交易金额方向 D-借 C-贷 */
	private String dir;

	/** 记帐日期 */
	private Date accDate;

	/** 交易渠道 */
	private String channel;

	/** 开销户标识 N-无开销户 O-开户 C-销户 */
	private String openCloseFlg;

	/** 账户类型 0-客户账户 1-商户账户 2-机构账户 3-科目账户 */
	private Integer accType;

	/** 账户号 客户账户填写客户号, 商户账户填写商户编号, 机构账户填写机构编号, 科目账户填写科目号 */
	private Long accId;

	/** 账户名称 客户账户填写姓名, 商户账户填写商户名称, 机构账户填写机构名称, 科目账户填写科目名称 */
	private String accName;

	/** 账户资金号 客户账户填写主账号，商户账户、机构账户和科目账户填写内部账户号 */
	private Long accAmtId;

	/** 账户子资金号 */
	private Long accSubAmtId;

	/** 账户币种 */
	private String accCur;

	/** 账户上级科目号 */
	private String accUpItemId;

	/** 对方账户类型 0-客户账户 1-商户账户 2-机构账户 3-科目账户 */
	private Integer agaType;

	/** 对方账户号 客户账户填写客户号, 商户账户填写商户编号, 机构账户填写机构编号, 科目账户填写科目号 */
	private Long agaId;

	/** 对方账户名称 客户账户填写姓名, 商户账户填写商户名称, 机构账户填写机构名称, 科目账户填写科目名称 */
	private String agaName;

	/** 对方账户资金号 客户账户填写主账号，商户账户、机构账户和科目账户填写内部账户号 */
	private Long agaAmtId;

	/** 对方账户子资金号 */
	private Long agaSubAmtId;

	/** 对方账户币种 */
	private String agaCur;

	/** 对方账户上级科目号 */
	private String agaUpItemId;

	/** 交易金额 */
	private BigDecimal amt;

	/** 余额 交易后的账户余额 */
	private BigDecimal bal;

	/** 实时标志 0-实时入账 1-异步入账 */
	private Integer runtimeFlg;

	/** 入账标志 0-待入账 1-已入账 */
	private Integer inFlg;

	/** 入账交易代码 */
	private String inTxnCode;

	/** 入账交易时间 */
	private Date inTxnTime;

	/** 冲正标志 N-正常 Y-红字 B-蓝字 */
	private String revFlg;

	/** 备注 */
	private String memo;

	/** 删除标识 0-正常 1-删除 */
	private Boolean delFlag;

	/** 预留字段1 */
	private String filler1;

	/** 预留字段2 */
	private String filler2;

	/** 预留字段3 */
	private String filler3;

	/**  */
	private Integer version;

	/** 分录认证信息 实时入账时不记录, 异步入账时登记 */
	private String aceMac;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getTnsLogId() {
		return tnsLogId;
	}

	public void setTnsLogId(String tnsLogId) {
		this.tnsLogId = tnsLogId;
	}

	public Long getTnsTxnId() {
		return tnsTxnId;
	}

	public void setTnsTxnId(Long tnsTxnId) {
		this.tnsTxnId = tnsTxnId;
	}

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode == null ? null : txnCode.trim();
	}

	public String getAceGroup() {
		return aceGroup;
	}

	public void setAceGroup(String aceGroup) {
		this.aceGroup = aceGroup == null ? null : aceGroup.trim();
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir == null ? null : dir.trim();
	}

	public Date getAccDate() {
		return accDate;
	}

	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel == null ? null : channel.trim();
	}

	public String getOpenCloseFlg() {
		return openCloseFlg;
	}

	public void setOpenCloseFlg(String openCloseFlg) {
		this.openCloseFlg = openCloseFlg == null ? null : openCloseFlg.trim();
	}

	public Integer getAccType() {
		return accType;
	}

	public void setAccType(Integer accType) {
		this.accType = accType;
	}


	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName == null ? null : accName.trim();
	}


	public String getAccCur() {
		return accCur;
	}

	public void setAccCur(String accCur) {
		this.accCur = accCur == null ? null : accCur.trim();
	}

	public String getAccUpItemId() {
		return accUpItemId;
	}

	public void setAccUpItemId(String accUpItemId) {
		this.accUpItemId = accUpItemId == null ? null : accUpItemId.trim();
	}

	public Integer getAgaType() {
		return agaType;
	}

	public void setAgaType(Integer agaType) {
		this.agaType = agaType;
	}


	public String getAgaName() {
		return agaName;
	}

	public void setAgaName(String agaName) {
		this.agaName = agaName == null ? null : agaName.trim();
	}

	public Long getAgaSubAmtId() {
		return agaSubAmtId;
	}

	public void setAgaSubAmtId(Long agaSubAmtId) {
		this.agaSubAmtId = agaSubAmtId;
	}

	public String getAgaCur() {
		return agaCur;
	}

	public void setAgaCur(String agaCur) {
		this.agaCur = agaCur == null ? null : agaCur.trim();
	}

	public String getAgaUpItemId() {
		return agaUpItemId;
	}

	public void setAgaUpItemId(String agaUpItemId) {
		this.agaUpItemId = agaUpItemId == null ? null : agaUpItemId.trim();
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getBal() {
		return bal;
	}

	public void setBal(BigDecimal bal) {
		this.bal = bal;
	}

	public Integer getRuntimeFlg() {
		return runtimeFlg;
	}

	public void setRuntimeFlg(Integer runtimeFlg) {
		this.runtimeFlg = runtimeFlg;
	}

	public Integer getInFlg() {
        return inFlg;
    }

    public void setInFlg(Integer inFlg) {
        this.inFlg = inFlg;
    }

    public String getInTxnCode() {
		return inTxnCode;
	}

	public void setInTxnCode(String inTxnCode) {
		this.inTxnCode = inTxnCode == null ? null : inTxnCode.trim();
	}

	public Date getInTxnTime() {
		return inTxnTime;
	}

	public void setInTxnTime(Date inTxnTime) {
		this.inTxnTime = inTxnTime;
	}

	public String getRevFlg() {
		return revFlg;
	}

	public void setRevFlg(String revFlg) {
		this.revFlg = revFlg == null ? null : revFlg.trim();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1 == null ? null : filler1.trim();
	}

	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2 == null ? null : filler2.trim();
	}

	public String getFiller3() {
		return filler3;
	}

	public void setFiller3(String filler3) {
		this.filler3 = filler3 == null ? null : filler3.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAceMac() {
		return aceMac;
	}

	public void setAceMac(String aceMac) {
		this.aceMac = aceMac == null ? null : aceMac.trim();
	}
	
	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	public Long getAccAmtId() {
		return accAmtId;
	}

	public void setAccAmtId(Long accAmtId) {
		this.accAmtId = accAmtId;
	}

	public Long getAccSubAmtId() {
		return accSubAmtId;
	}

	public void setAccSubAmtId(Long accSubAmtId) {
		this.accSubAmtId = accSubAmtId;
	}

	public Long getAgaId() {
		return agaId;
	}

	public void setAgaId(Long agaId) {
		this.agaId = agaId;
	}

	public Long getAgaAmtId() {
		return agaAmtId;
	}

	public void setAgaAmtId(Long agaAmtId) {
		this.agaAmtId = agaAmtId;
	}

	@Override
	public String toString() {
		return "TnsAce [owner=" + owner + ", ownerGroup=" + ownerGroup
				+ ", createTime=" + createTime + ", lastUpdateTime="
				+ lastUpdateTime + ", tnsLogId=" + tnsLogId + ", tnsTxnId="
				+ tnsTxnId + ", txnCode=" + txnCode + ", aceGroup=" + aceGroup
				+ ", dir=" + dir + ", accDate=" + accDate + ", channel="
				+ channel + ", openCloseFlg=" + openCloseFlg + ", accType="
				+ accType + ", accId=" + accId + ", accName=" + accName
				+ ", accAmtId=" + accAmtId + ", accSubAmtId=" + accSubAmtId
				+ ", accCur=" + accCur + ", accUpItemId=" + accUpItemId
				+ ", agaType=" + agaType + ", agaId=" + agaId + ", agaName="
				+ agaName + ", agaAmtId=" + agaAmtId + ", agaSubAmtId="
				+ agaSubAmtId + ", agaCur=" + agaCur + ", agaUpItemId="
				+ agaUpItemId + ", amt=" + amt + ", bal=" + bal
				+ ", runtimeFlg=" + runtimeFlg + ", inFlg=" + inFlg
				+ ", inTxnCode=" + inTxnCode + ", inTxnTime=" + inTxnTime + "]";
	}
}