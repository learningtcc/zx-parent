package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TnsFee implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 4651033302560287148L;

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

	/** 联机交易代码表主键 */
	private Long tnsTxnId;

	/** 交易代码 */
	private String txnCode;

	/** 账户类型 0-客户账户 1-商户账户 2-机构账户 3-科目账户 */
	private Integer accType;

	/** 产品编号(帐户级别) 0001-未实名账户 0002-已实名账户 0003-VIP帐户 0004-公司账户 */
	private String prodId;

	/** 子帐户种类 */
	private String sacType;

	/** 商户类型 参考银联规范 */
	private String mchType;

	/** 币种 */
	private String cur;

	/** 手续费收取类型 0-不收取 1-实时单笔 2-周期单笔 3-周期汇总 */
	private Integer feeType;

	/** 手续费收取模式 1-固定金额收取 2-按比率收取 */
	private Integer feeMode;

	/** 手续费计算模式 0-标准 1-套档 2-分层 最多支持5层费率 */
	private Integer feeCalMode;

	/** 手续费收取起始金额 */
	private BigDecimal feeBegAmt;

	/** 手续费收取最低金额 */
	private BigDecimal feeMinAmt;

	/** 手续费收取最高金额 */
	private BigDecimal feeMaxAmt;

	/** 计费参考1, 本字段缺省为全9 */
	private BigDecimal feeRef1;

	/** 固定手续费金额1 */
	private BigDecimal feeFixAmt1;

	/** 固定手续费费率1, 单位为% */
	private BigDecimal feeRate1;

	/** 计费参考2, 本字段缺省为全9 */
	private BigDecimal feeRef2;

	/** 固定手续费金额2 */
	private BigDecimal feeFixAmt2;

	/** 固定手续费费率2, 单位为% */
	private BigDecimal feeRate2;

	/** 计费参考3, 本字段缺省为全9 */
	private BigDecimal feeRef3;

	/** 固定手续费金额3 */
	private BigDecimal feeFixAmt3;

	/** 固定手续费费率3, 单位为% */
	private BigDecimal feeRate3;

	/** 计费参考4, 本字段缺省为全9 */
	private BigDecimal feeRef4;

	/** 固定手续费金额4 */
	private BigDecimal feeFixAmt4;

	/** 固定手续费费率4, 单位为% */
	private BigDecimal feeRate4;

	/** 计费参考5, 本字段缺省为全9 */
	private BigDecimal feeRef5;

	/** 固定手续费金额5 */
	private BigDecimal feeFixAmt5;

	/** 固定手续费费率5, 单位为% */
	private BigDecimal feeRate5;

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

	public Integer getAccType() {
		return accType;
	}

	public void setAccType(Integer accType) {
		this.accType = accType;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId == null ? null : prodId.trim();
	}

	public String getSacType() {
		return sacType;
	}

	public void setSacType(String sacType) {
		this.sacType = sacType == null ? null : sacType.trim();
	}

	public String getMchType() {
		return mchType;
	}

	public void setMchType(String mchType) {
		this.mchType = mchType == null ? null : mchType.trim();
	}

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur == null ? null : cur.trim();
	}

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public Integer getFeeMode() {
		return feeMode;
	}

	public void setFeeMode(Integer feeMode) {
		this.feeMode = feeMode;
	}

	public Integer getFeeCalMode() {
		return feeCalMode;
	}

	public void setFeeCalMode(Integer feeCalMode) {
		this.feeCalMode = feeCalMode;
	}

	public BigDecimal getFeeBegAmt() {
		return feeBegAmt;
	}

	public void setFeeBegAmt(BigDecimal feeBegAmt) {
		this.feeBegAmt = feeBegAmt;
	}

	public BigDecimal getFeeMinAmt() {
		return feeMinAmt;
	}

	public void setFeeMinAmt(BigDecimal feeMinAmt) {
		this.feeMinAmt = feeMinAmt;
	}

	public BigDecimal getFeeMaxAmt() {
		return feeMaxAmt;
	}

	public void setFeeMaxAmt(BigDecimal feeMaxAmt) {
		this.feeMaxAmt = feeMaxAmt;
	}

	public BigDecimal getFeeRef1() {
		return feeRef1;
	}

	public void setFeeRef1(BigDecimal feeRef1) {
		this.feeRef1 = feeRef1;
	}

	public BigDecimal getFeeFixAmt1() {
		return feeFixAmt1;
	}

	public void setFeeFixAmt1(BigDecimal feeFixAmt1) {
		this.feeFixAmt1 = feeFixAmt1;
	}

	public BigDecimal getFeeRate1() {
		return feeRate1;
	}

	public void setFeeRate1(BigDecimal feeRate1) {
		this.feeRate1 = feeRate1;
	}

	public BigDecimal getFeeRef2() {
		return feeRef2;
	}

	public void setFeeRef2(BigDecimal feeRef2) {
		this.feeRef2 = feeRef2;
	}

	public BigDecimal getFeeFixAmt2() {
		return feeFixAmt2;
	}

	public void setFeeFixAmt2(BigDecimal feeFixAmt2) {
		this.feeFixAmt2 = feeFixAmt2;
	}

	public BigDecimal getFeeRate2() {
		return feeRate2;
	}

	public void setFeeRate2(BigDecimal feeRate2) {
		this.feeRate2 = feeRate2;
	}

	public BigDecimal getFeeRef3() {
		return feeRef3;
	}

	public void setFeeRef3(BigDecimal feeRef3) {
		this.feeRef3 = feeRef3;
	}

	public BigDecimal getFeeFixAmt3() {
		return feeFixAmt3;
	}

	public void setFeeFixAmt3(BigDecimal feeFixAmt3) {
		this.feeFixAmt3 = feeFixAmt3;
	}

	public BigDecimal getFeeRate3() {
		return feeRate3;
	}

	public void setFeeRate3(BigDecimal feeRate3) {
		this.feeRate3 = feeRate3;
	}

	public BigDecimal getFeeRef4() {
		return feeRef4;
	}

	public void setFeeRef4(BigDecimal feeRef4) {
		this.feeRef4 = feeRef4;
	}

	public BigDecimal getFeeFixAmt4() {
		return feeFixAmt4;
	}

	public void setFeeFixAmt4(BigDecimal feeFixAmt4) {
		this.feeFixAmt4 = feeFixAmt4;
	}

	public BigDecimal getFeeRate4() {
		return feeRate4;
	}

	public void setFeeRate4(BigDecimal feeRate4) {
		this.feeRate4 = feeRate4;
	}

	public BigDecimal getFeeRef5() {
		return feeRef5;
	}

	public void setFeeRef5(BigDecimal feeRef5) {
		this.feeRef5 = feeRef5;
	}

	public BigDecimal getFeeFixAmt5() {
		return feeFixAmt5;
	}

	public void setFeeFixAmt5(BigDecimal feeFixAmt5) {
		this.feeFixAmt5 = feeFixAmt5;
	}

	public BigDecimal getFeeRate5() {
		return feeRate5;
	}

	public void setFeeRate5(BigDecimal feeRate5) {
		this.feeRate5 = feeRate5;
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

	@Override
	public String toString() {
		return "TnsFee [id=" + id + ", owner=" + owner + ", ownerGroup="
				+ ownerGroup + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", tnsTxnId="
				+ tnsTxnId + ", txnCode=" + txnCode + ", accType=" + accType
				+ ", prodId=" + prodId + ", sacType=" + sacType + ", mchType="
				+ mchType + ", cur=" + cur + ", feeType=" + feeType
				+ ", feeMode=" + feeMode + ", feeCalMode=" + feeCalMode
				+ ", feeBegAmt=" + feeBegAmt + ", feeMinAmt=" + feeMinAmt
				+ ", feeMaxAmt=" + feeMaxAmt + ", feeRef1=" + feeRef1
				+ ", feeFixAmt1=" + feeFixAmt1 + ", feeRate1=" + feeRate1
				+ ", feeRef2=" + feeRef2 + ", feeFixAmt2=" + feeFixAmt2
				+ ", feeRate2=" + feeRate2 + ", feeRef3=" + feeRef3
				+ ", feeFixAmt3=" + feeFixAmt3 + ", feeRate3=" + feeRate3
				+ ", feeRef4=" + feeRef4 + ", feeFixAmt4=" + feeFixAmt4
				+ ", feeRate4=" + feeRate4 + ", feeRef5=" + feeRef5
				+ ", feeFixAmt5=" + feeFixAmt5 + ", feeRate5=" + feeRate5 + "]";
	}
}