/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class TnsLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private java.lang.Long id;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后修改时间 */
	private java.util.Date lastUpdateTimeBegin;
	private java.util.Date lastUpdateTimeEnd;
	/** 前端请求流水 */
	private java.lang.String reqId;
	/** 商户编号 */
	private java.lang.Long mchId;
	/** 订单编号 */
	private java.lang.String ordId;
	/** 订单日期 */
	private java.util.Date ordDateBegin;
	private java.util.Date ordDateEnd;
	/** 订单时间 */
	private java.util.Date ordTimeBegin;
	private java.util.Date ordTimeEnd;
	/** 交易代码 */
	private java.lang.String txnCode;
	/** 交易名称 */
	private java.lang.String txnName;
	/** 交易性质 1-充值 2-消费 3-资金转入 4-资金转出 5-汇入 6-汇出 7-手续费 8-取现 9-其他 */
	private Integer txnNature;
	/** 运行级别 1-联机 2-批处理 */
	private java.lang.Boolean runLv;
	/** 交易金额方向 D-借 C-贷 O-其他 */
	private java.lang.String dir;
	/** 客户号, 填写手机号 */
	private java.lang.String custId;
	/** 机构编号 */
	private java.lang.Long orgId;
	/** 订单金额 */
	private BigDecimal ordAmt;
	/** 联机交易代码表主键 */
	private java.lang.Long accTxnId;
	/** 记帐日期 */
	private java.util.Date accDateBegin;
	private java.util.Date accDateEnd;
	/** 交易渠道 */
	private java.lang.String channel;
	/** 授权编号 预授权、授权、汇款交易的授权号 */
	private java.lang.Long authId;
	/** 交易卡号 */
	private java.lang.String cardNo;
	/** 支付订单编号 */
	private java.lang.String payId;
	/** 原始订单编号 */
	private java.lang.String oriOrdId;
	/** 原始订单日期 */
	private java.util.Date oriOrdDateBegin;
	private java.util.Date oriOrdDateEnd;
	/** 原始交易代码 */
	private java.lang.String oriTxnCode;
	/** 开销户标识 N-无开销户 O-开户 C-销户 */
	private java.lang.String openCloseFlg;
	/** 账户类型 0-客户账户 1-商户账户 2-机构账户 3-科目账户 */
	private java.lang.Boolean accType;
	/** 账户号 客户账户填写客户号, 商户账户填写商户编号, 机构账户填写机构编号, 科目账户填写科目号 */
	private java.lang.Long accId;
	/** 账户名称 客户账户填写姓名, 商户账户填写商户名称, 机构账户填写机构名称, 科目账户填写科目名称 */
	private java.lang.String accName;
	/** 账户资金号 客户账户填写主账号，商户账户、机构账户和科目账户填写内部账户号 */
	private java.lang.Long accAmtId;
	/** 账户号的子帐户类型, 记录该交易访问了哪些子帐户, 每4位表示一种子帐户类型, 按借贷子帐户顺序记录 */
	private java.lang.String accSubType;
	/** 账户币种 */
	private java.lang.String accCur;
	/** 对方账户类型 0-客户账户 1-商户账户 2-机构账户 3-科目账户 */
	private java.lang.Integer agaType;
	/** 对方账户号 客户账户填写客户号, 商户账户填写商户编号, 机构账户填写机构编号, 科目账户填写科目号 */
	private java.lang.Long agaId;
	/** 对方账户名称 客户账户填写姓名, 商户账户填写商户名称, 机构账户填写机构名称, 科目账户填写科目名称 */
	private java.lang.String agaName;
	/** 对方账户资金号 客户账户填写主账号，商户账户、机构账户和科目账户填写内部账户号 */
	private java.lang.Long agaAmtId;
	/** 对方账户号的子帐户类型, 记录该交易访问了哪些子帐户, 每4位表示一种子帐户类型, 按借贷子帐户顺序记录 */
	private java.lang.String agaSubType;
	/** 对方账户币种 */
	private java.lang.String agaCur;
	/** 交易金额 不包含手续费的交易金额 */
	private BigDecimal amt;
	/** 客户手续费 */
	private BigDecimal custFee;
	/** 余额 交易后的账户余额 */
	private BigDecimal bal;
	/** 冲正标识 0-不允许 1-允许 */
	private java.lang.Boolean revFlg;
	/** 返回码 */
	private java.lang.String retCode;
	/** 返回描述 */
	private java.lang.String retMsg;
	/** 备注 */
	private java.lang.String memo;
	/** 删除标识 0-正常 1-删除 */
	private java.lang.Boolean delFlag;
	/** 预留字段1 */
	private java.lang.String filler1;
	/** 预留字段2 */
	private java.lang.String filler2;
	/** 预留字段3 */
	private java.lang.String filler3;
	/** version */
	private java.lang.Integer version;
	/** 平台标识号 */
	private java.lang.Long uid;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}	
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	
	public java.util.Date getLastUpdateTimeBegin() {
		return this.lastUpdateTimeBegin;
	}
	
	public void setLastUpdateTimeBegin(java.util.Date value) {
		this.lastUpdateTimeBegin = value;
	}	
	
	public java.util.Date getLastUpdateTimeEnd() {
		return this.lastUpdateTimeEnd;
	}
	
	public void setLastUpdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastUpdateTimeEnd = calendar.getTime();
		}else {
			this.lastUpdateTimeEnd = value;
		}
	}
	
	public java.lang.String getReqId() {
		return this.reqId;
	}
	
	public void setReqId(java.lang.String value) {
		this.reqId = value;
	}
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.String getOrdId() {
		return this.ordId;
	}
	
	public void setOrdId(java.lang.String value) {
		this.ordId = value;
	}
	
	public java.util.Date getOrdDateBegin() {
		return this.ordDateBegin;
	}
	
	public void setOrdDateBegin(java.util.Date value) {
		this.ordDateBegin = value;
	}	
	
	public java.util.Date getOrdDateEnd() {
		return this.ordDateEnd;
	}
	
	public void setOrdDateEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.ordDateEnd = calendar.getTime();
		}else {
			this.ordDateEnd = value;
		}
	}
	
	public java.util.Date getOrdTimeBegin() {
		return this.ordTimeBegin;
	}
	
	public void setOrdTimeBegin(java.util.Date value) {
		this.ordTimeBegin = value;
	}	
	
	public java.util.Date getOrdTimeEnd() {
		return this.ordTimeEnd;
	}
	
	public void setOrdTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.ordTimeEnd = calendar.getTime();
		}else {
			this.ordTimeEnd = value;
		}
	}
	
	public java.lang.String getTxnCode() {
		return this.txnCode;
	}
	
	public void setTxnCode(java.lang.String value) {
		this.txnCode = value;
	}
	
	public java.lang.String getTxnName() {
		return this.txnName;
	}
	
	public void setTxnName(java.lang.String value) {
		this.txnName = value;
	}
	
	public Integer getTxnNature() {
		return this.txnNature;
	}
	
	public void setTxnNature(Integer value) {
		this.txnNature = value;
	}
	
	public java.lang.Boolean getRunLv() {
		return this.runLv;
	}
	
	public void setRunLv(java.lang.Boolean value) {
		this.runLv = value;
	}
	
	public java.lang.String getDir() {
		return this.dir;
	}
	
	public void setDir(java.lang.String value) {
		this.dir = value;
	}
	
	public java.lang.String getCustId() {
		return this.custId;
	}
	
	public void setCustId(java.lang.String value) {
		this.custId = value;
	}
	
	public java.lang.Long getOrgId() {
		return this.orgId;
	}
	
	public void setOrgId(java.lang.Long value) {
		this.orgId = value;
	}
	
	public BigDecimal getOrdAmt() {
		return this.ordAmt;
	}
	
	public void setOrdAmt(BigDecimal value) {
		this.ordAmt = value;
	}
	
	public java.lang.Long getAccTxnId() {
		return this.accTxnId;
	}
	
	public void setAccTxnId(java.lang.Long value) {
		this.accTxnId = value;
	}
	
	public java.util.Date getAccDateBegin() {
		return this.accDateBegin;
	}
	
	public void setAccDateBegin(java.util.Date value) {
		this.accDateBegin = value;
	}	
	
	public java.util.Date getAccDateEnd() {
		return this.accDateEnd;
	}
	
	public void setAccDateEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.accDateEnd = calendar.getTime();
		}else {
			this.accDateEnd = value;
		}
	}
	
	public java.lang.String getChannel() {
		return this.channel;
	}
	
	public void setChannel(java.lang.String value) {
		this.channel = value;
	}
	
	public java.lang.Long getAuthId() {
		return this.authId;
	}
	
	public void setAuthId(java.lang.Long value) {
		this.authId = value;
	}
	
	public java.lang.String getCardNo() {
		return this.cardNo;
	}
	
	public void setCardNo(java.lang.String value) {
		this.cardNo = value;
	}
	
	public java.lang.String getPayId() {
		return this.payId;
	}
	
	public void setPayId(java.lang.String value) {
		this.payId = value;
	}
	
	public java.lang.String getOriOrdId() {
		return this.oriOrdId;
	}
	
	public void setOriOrdId(java.lang.String value) {
		this.oriOrdId = value;
	}
	
	public java.util.Date getOriOrdDateBegin() {
		return this.oriOrdDateBegin;
	}
	
	public void setOriOrdDateBegin(java.util.Date value) {
		this.oriOrdDateBegin = value;
	}	
	
	public java.util.Date getOriOrdDateEnd() {
		return this.oriOrdDateEnd;
	}
	
	public void setOriOrdDateEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.oriOrdDateEnd = calendar.getTime();
		}else {
			this.oriOrdDateEnd = value;
		}
	}
	
	public java.lang.String getOriTxnCode() {
		return this.oriTxnCode;
	}
	
	public void setOriTxnCode(java.lang.String value) {
		this.oriTxnCode = value;
	}
	
	public java.lang.String getOpenCloseFlg() {
		return this.openCloseFlg;
	}
	
	public void setOpenCloseFlg(java.lang.String value) {
		this.openCloseFlg = value;
	}
	
	public java.lang.Boolean getAccType() {
		return this.accType;
	}
	
	public void setAccType(java.lang.Boolean value) {
		this.accType = value;
	}
	
	public java.lang.Long getAccId() {
		return this.accId;
	}
	
	public void setAccId(java.lang.Long value) {
		this.accId = value;
	}
	
	public java.lang.String getAccName() {
		return this.accName;
	}
	
	public void setAccName(java.lang.String value) {
		this.accName = value;
	}
	
	public java.lang.Long getAccAmtId() {
		return this.accAmtId;
	}
	
	public void setAccAmtId(java.lang.Long value) {
		this.accAmtId = value;
	}
	
	public java.lang.String getAccSubType() {
		return this.accSubType;
	}
	
	public void setAccSubType(java.lang.String value) {
		this.accSubType = value;
	}
	
	public java.lang.String getAccCur() {
		return this.accCur;
	}
	
	public void setAccCur(java.lang.String value) {
		this.accCur = value;
	}
	
	public java.lang.Integer getAgaType() {
		return this.agaType;
	}
	
	public void setAgaType(java.lang.Integer value) {
		this.agaType = value;
	}
	
	public java.lang.Long getAgaId() {
		return this.agaId;
	}
	
	public void setAgaId(java.lang.Long value) {
		this.agaId = value;
	}
	
	public java.lang.String getAgaName() {
		return this.agaName;
	}
	
	public void setAgaName(java.lang.String value) {
		this.agaName = value;
	}
	
	public java.lang.Long getAgaAmtId() {
		return this.agaAmtId;
	}
	
	public void setAgaAmtId(java.lang.Long value) {
		this.agaAmtId = value;
	}
	
	public java.lang.String getAgaSubType() {
		return this.agaSubType;
	}
	
	public void setAgaSubType(java.lang.String value) {
		this.agaSubType = value;
	}
	
	public java.lang.String getAgaCur() {
		return this.agaCur;
	}
	
	public void setAgaCur(java.lang.String value) {
		this.agaCur = value;
	}
	
	public BigDecimal getAmt() {
		return this.amt;
	}
	
	public void setAmt(BigDecimal value) {
		this.amt = value;
	}
	
	public BigDecimal getCustFee() {
		return this.custFee;
	}
	
	public void setCustFee(BigDecimal value) {
		this.custFee = value;
	}
	
	public BigDecimal getBal() {
		return this.bal;
	}
	
	public void setBal(BigDecimal value) {
		this.bal = value;
	}
	
	public java.lang.Boolean getRevFlg() {
		return this.revFlg;
	}
	
	public void setRevFlg(java.lang.Boolean value) {
		this.revFlg = value;
	}
	
	public java.lang.String getRetCode() {
		return this.retCode;
	}
	
	public void setRetCode(java.lang.String value) {
		this.retCode = value;
	}
	
	public java.lang.String getRetMsg() {
		return this.retMsg;
	}
	
	public void setRetMsg(java.lang.String value) {
		this.retMsg = value;
	}
	
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	
	public java.lang.Boolean getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(java.lang.Boolean value) {
		this.delFlag = value;
	}
	
	public java.lang.String getFiller1() {
		return this.filler1;
	}
	
	public void setFiller1(java.lang.String value) {
		this.filler1 = value;
	}
	
	public java.lang.String getFiller2() {
		return this.filler2;
	}
	
	public void setFiller2(java.lang.String value) {
		this.filler2 = value;
	}
	
	public java.lang.String getFiller3() {
		return this.filler3;
	}
	
	public void setFiller3(java.lang.String value) {
		this.filler3 = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.lang.Long getUid() {
		return this.uid;
	}
	
	public void setUid(java.lang.Long value) {
		this.uid = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TnsLogQuery [id=");
		builder.append(id);
		builder.append(", createTimeBegin=");
		builder.append(createTimeBegin);
		builder.append(", createTimeEnd=");
		builder.append(createTimeEnd);
		builder.append(", lastUpdateTimeBegin=");
		builder.append(lastUpdateTimeBegin);
		builder.append(", lastUpdateTimeEnd=");
		builder.append(lastUpdateTimeEnd);
		builder.append(", reqId=");
		builder.append(reqId);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", ordDateBegin=");
		builder.append(ordDateBegin);
		builder.append(", ordDateEnd=");
		builder.append(ordDateEnd);
		builder.append(", ordTimeBegin=");
		builder.append(ordTimeBegin);
		builder.append(", ordTimeEnd=");
		builder.append(ordTimeEnd);
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
		builder.append(", accDateBegin=");
		builder.append(accDateBegin);
		builder.append(", accDateEnd=");
		builder.append(accDateEnd);
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
		builder.append(", oriOrdDateBegin=");
		builder.append(oriOrdDateBegin);
		builder.append(", oriOrdDateEnd=");
		builder.append(oriOrdDateEnd);
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

