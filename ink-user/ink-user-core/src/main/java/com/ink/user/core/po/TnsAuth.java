package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TnsAuth implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5972971373612363117L;

	/** 数据库表主键 */
	private Long id;

	/** 创建时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date lastUpdateTime;

	/** 交易流水表主键 */
	private Long tnsLogId;

	/** 交易代码 */
	private String txnCode;

	/** 交易名称 */
	private String txnName;

	/** 记帐日期 */
	private Date accDate;

	/** 客户号, 填写手机号 */
	private String custId;

	/** 姓名 */
	private String custName;

	/** 客户类型 0-个人 1-商户 */
	private Integer custType;

	/** 子帐户号 */
	private String sacId;

	/** 交易币种 */
	private String cur;

	/** 授权金额 */
	private BigDecimal authAmt;

	/** 可授权余额 */
	private BigDecimal authBal;

	/** 授权状态 0-授权中 1-授权完成 */
	private Integer authStatus;

	/** 备注 */
	private String memo;

	/** 删除标识 0-正常 1-删除 */
	private Integer delFlag;

	/** 预留字段1 */
	private String filler1;

	/** 预留字段2 */
	private String filler2;

	/** 预留字段3 */
	private String filler3;

	/**  */
	private Integer version;

	/** 冲正标志 N-正常 Y-红字 B-蓝字 */
	private String revFlg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getTnsLogId() {
		return tnsLogId;
	}

	public void setTnsLogId(Long tnsLogId) {
		this.tnsLogId = tnsLogId;
	}

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode == null ? null : txnCode.trim();
	}

	public String getTxnName() {
		return txnName;
	}

	public void setTxnName(String txnName) {
		this.txnName = txnName == null ? null : txnName.trim();
	}

	public Date getAccDate() {
		return accDate;
	}

	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId == null ? null : custId.trim();
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName == null ? null : custName.trim();
	}

	public Integer getCustType() {
		return custType;
	}

	public void setCustType(Integer custType) {
		this.custType = custType;
	}

	public String getSacId() {
		return sacId;
	}

	public void setSacId(String sacId) {
		this.sacId = sacId == null ? null : sacId.trim();
	}

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur == null ? null : cur.trim();
	}

	public BigDecimal getAuthAmt() {
		return authAmt;
	}

	public void setAuthAmt(BigDecimal authAmt) {
		this.authAmt = authAmt;
	}

	public BigDecimal getAuthBal() {
		return authBal;
	}

	public void setAuthBal(BigDecimal authBal) {
		this.authBal = authBal;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
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

	public String getRevFlg() {
		return revFlg;
	}

	public void setRevFlg(String revFlg) {
		this.revFlg = revFlg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TnsAuth [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
		builder.append(", tnsLogId=");
		builder.append(tnsLogId);
		builder.append(", txnCode=");
		builder.append(txnCode);
		builder.append(", txnName=");
		builder.append(txnName);
		builder.append(", accDate=");
		builder.append(accDate);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", custType=");
		builder.append(custType);
		builder.append(", sacId=");
		builder.append(sacId);
		builder.append(", cur=");
		builder.append(cur);
		builder.append(", authAmt=");
		builder.append(authAmt);
		builder.append(", authBal=");
		builder.append(authBal);
		builder.append(", authStatus=");
		builder.append(authStatus);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append(", filler1=");
		builder.append(filler1);
		builder.append(", filler2=");
		builder.append(filler2);
		builder.append(", filler3=");
		builder.append(filler3);
		builder.append(", version=");
		builder.append(version);
		builder.append(", revFlg=");
		builder.append(revFlg);
		builder.append("]");
		return builder.toString();
	}

}