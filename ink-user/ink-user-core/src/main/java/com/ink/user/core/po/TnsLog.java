package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TnsLog implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1485254594024228184L;

	/** 数据库表主键 */
	private String id;

	/** 创建时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date lastUpdateTime;

	/** 前端请求流水 */
	private String reqId;

	/** 商户编号 */
	private Long mchId;

	/** 订单编号 */
	private String ordId;

	/** 订单日期 */
	private Date ordDate;

	/** 订单时间 */
	private Date ordTime;

	/** 交易代码 */
	private String txnCode;

	/** 交易名称 */
	private String txnName;

	/** 交易性质 1-充值 2-消费 3-资金转入 4-资金转出 5-汇入 6-汇出 7-手续费 8-取现 9-其他 */
	private Integer txnNature;

	/** 运行级别 1-联机 2-批处理 */
	private Integer runLv;

	/** 交易金额方向 D-借 C-贷 O-其他 */
	private String dir;

	/** 客户号, 填写手机号 */
	private String custId;

	/** 机构编号 */
	private String orgId;

	/** 订单金额 */
	private BigDecimal ordAmt;

	/** 联机交易代码表主键 */
	private Long accTxnId;

	/** 记帐日期 */
	private Date accDate;

	/** 交易渠道 */
	private String channel;

	/** 授权编号 预授权、授权、汇款交易的授权号 */
	private Long authId;

	/** 交易卡号 */
	private String cardNo;

	/** 支付订单编号 */
	private String payId;

	/** 原始订单编号 */
	private String oriOrdId;

	/** 原始订单日期 */
	private Date oriOrdDate;

	/** 原始交易代码 */
	private String oriTxnCode;

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

	/** 账户号的子帐户类型, 记录该交易访问了哪些子帐户, 每4位表示一种子帐户类型, 按借贷子帐户顺序记录 */
	private String accSubType;

	/** 账户币种 */
	private String accCur;

	/** 对方账户类型 0-客户账户 1-商户账户 2-机构账户 3-科目账户 */
	private Integer agaType;

	/** 对方账户号 客户账户填写客户号, 商户账户填写商户编号, 机构账户填写机构编号, 科目账户填写科目号 */
	private Long agaId;

	/** 对方账户名称 客户账户填写姓名, 商户账户填写商户名称, 机构账户填写机构名称, 科目账户填写科目名称 */
	private String agaName;

	/** 对方账户资金号 客户账户填写主账号，商户账户、机构账户和科目账户填写内部账户号 */
	private Long agaAmtId;

	/** 对方账户号的子帐户类型, 记录该交易访问了哪些子帐户, 每4位表示一种子帐户类型, 按借贷子帐户顺序记录 */
	private String agaSubType;

	/** 对方账户币种 */
	private String agaCur;

	/** 交易金额 不包含手续费的交易金额 */
	private BigDecimal amt;

	/** 客户手续费 */
	private BigDecimal custFee;

	/** 余额 交易后的账户余额 */
	private BigDecimal bal;

	/** 冲正标识 0-不允许 1-允许 */
	private Integer revFlg;

	/** 返回码 */
	private String retCode;

	/** 返回描述 */
	private String retMsg;

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

	/** 平台标识号 */
	private Long uid;

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

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId == null ? null : reqId.trim();
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId == null ? null : ordId.trim();
	}

	public Date getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}

	public Date getOrdTime() {
		return ordTime;
	}

	public void setOrdTime(Date ordTime) {
		this.ordTime = ordTime;
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

	public Integer getTxnNature() {
		return txnNature;
	}

	public void setTxnNature(Integer txnNature) {
		this.txnNature = txnNature;
	}

	public Integer getRunLv() {
		return runLv;
	}

	public void setRunLv(Integer runLv) {
		this.runLv = runLv;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir == null ? null : dir.trim();
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId == null ? null : custId.trim();
	}

	public BigDecimal getOrdAmt() {
		return ordAmt;
	}

	public void setOrdAmt(BigDecimal ordAmt) {
		this.ordAmt = ordAmt;
	}

	public Long getAccTxnId() {
		return accTxnId;
	}

	public void setAccTxnId(Long accTxnId) {
		this.accTxnId = accTxnId;
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo == null ? null : cardNo.trim();
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId == null ? null : payId.trim();
	}

	public String getOriOrdId() {
		return oriOrdId;
	}

	public void setOriOrdId(String oriOrdId) {
		this.oriOrdId = oriOrdId == null ? null : oriOrdId.trim();
	}

	public Date getOriOrdDate() {
		return oriOrdDate;
	}

	public void setOriOrdDate(Date oriOrdDate) {
		this.oriOrdDate = oriOrdDate;
	}

	public String getOriTxnCode() {
		return oriTxnCode;
	}

	public void setOriTxnCode(String oriTxnCode) {
		this.oriTxnCode = oriTxnCode == null ? null : oriTxnCode.trim();
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

	public String getAccSubType() {
		return accSubType;
	}

	public void setAccSubType(String accSubType) {
		this.accSubType = accSubType == null ? null : accSubType.trim();
	}

	public String getAccCur() {
		return accCur;
	}

	public void setAccCur(String accCur) {
		this.accCur = accCur == null ? null : accCur.trim();
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

	public String getAgaSubType() {
		return agaSubType;
	}

	public void setAgaSubType(String agaSubType) {
		this.agaSubType = agaSubType == null ? null : agaSubType.trim();
	}

	public String getAgaCur() {
		return agaCur;
	}

	public void setAgaCur(String agaCur) {
		this.agaCur = agaCur == null ? null : agaCur.trim();
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getCustFee() {
		return custFee;
	}

	public void setCustFee(BigDecimal custFee) {
		this.custFee = custFee;
	}

	public BigDecimal getBal() {
		return bal;
	}

	public void setBal(BigDecimal bal) {
		this.bal = bal;
	}

	public Integer getRevFlg() {
		return revFlg;
	}

	public void setRevFlg(Integer revFlg) {
		this.revFlg = revFlg;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode == null ? null : retCode.trim();
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg == null ? null : retMsg.trim();
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

	public Long getMchId() {
		return mchId;
	}

	public void setMchId(Long mchId) {
		this.mchId = mchId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
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

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TnsLog [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
		builder.append(", reqId=");
		builder.append(reqId);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", ordDate=");
		builder.append(ordDate);
		builder.append(", ordTime=");
		builder.append(ordTime);
		builder.append(", txnCode=");
		builder.append(txnCode);
		builder.append(", txnName=");
		builder.append(txnName);
		builder.append(", txnNature=");
		builder.append(txnNature);
		builder.append(", runLv=");
		builder.append(runLv);
		builder.append(", dir=");
		builder.append(dir);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", ordAmt=");
		builder.append(ordAmt);
		builder.append(", accTxnId=");
		builder.append(accTxnId);
		builder.append(", accDate=");
		builder.append(accDate);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", authId=");
		builder.append(authId);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", payId=");
		builder.append(payId);
		builder.append(", oriOrdId=");
		builder.append(oriOrdId);
		builder.append(", oriOrdDate=");
		builder.append(oriOrdDate);
		builder.append(", oriTxnCode=");
		builder.append(oriTxnCode);
		builder.append(", openCloseFlg=");
		builder.append(openCloseFlg);
		builder.append(", accType=");
		builder.append(accType);
		builder.append(", accId=");
		builder.append(accId);
		builder.append(", accName=");
		builder.append(accName);
		builder.append(", accAmtId=");
		builder.append(accAmtId);
		builder.append(", accSubType=");
		builder.append(accSubType);
		builder.append(", accCur=");
		builder.append(accCur);
		builder.append(", agaType=");
		builder.append(agaType);
		builder.append(", agaId=");
		builder.append(agaId);
		builder.append(", agaName=");
		builder.append(agaName);
		builder.append(", agaAmtId=");
		builder.append(agaAmtId);
		builder.append(", agaSubType=");
		builder.append(agaSubType);
		builder.append(", agaCur=");
		builder.append(agaCur);
		builder.append(", amt=");
		builder.append(amt);
		builder.append(", custFee=");
		builder.append(custFee);
		builder.append(", bal=");
		builder.append(bal);
		builder.append(", revFlg=");
		builder.append(revFlg);
		builder.append(", retCode=");
		builder.append(retCode);
		builder.append(", retMsg=");
		builder.append(retMsg);
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
		builder.append(", uid=");
		builder.append(uid);
		builder.append("]");
		return builder.toString();
	}

}