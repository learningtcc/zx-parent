package com.ink.user.api.model.in;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 转账
 * @author wanghao^_^
 * @date 2016年6月13日 上午10:27:23
 * @version V1.0
 */
public class AccTransferInput implements Serializable{

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 3823650834274868558L;
	//交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	//商户号
	@NotNull
	@Length(max = 24)
	private String mchId;
	//填写手机号
	@NotNull
	@Length(max = 40)
	private String custId;
	//订单号
	@NotNull
	@Length(max = 64)
	private String ordId;
	//订单日期(YYYYMMDDHHMMSS)
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	//实际记账金额，以元为单位，精确到小数点后2位。
	@NotNull
	@Length(max = 16)
	private String amt;
	//手续费
	@Length(max = 16)
	private String custFee;
	//用于充值的子账户种类	0001-个人现金账户
	@NotNull
	@Length(max = 4)
	private String accountType;
	//目标子账户种类
	@NotNull
	@Length(max = 4)
	private String agaSacType;
	//备注
	//private String memo;
	
	public String getTxnCode() {
		return txnCode;
	}
	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getCustFee() {
		return custFee;
	}
	public void setCustFee(String custFee) {
		this.custFee = custFee;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAgaSacType() {
		return agaSacType;
	}
	public void setAgaSacType(String agaSacType) {
		this.agaSacType = agaSacType;
	}
//	public String getMemo() {
//		return memo;
//	}
//	public void setMemo(String memo) {
//		this.memo = memo;
//	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccTransferInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", amt=");
		builder.append(amt);
		builder.append(", custFee=");
		builder.append(custFee);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", agaSacType=");
		builder.append(agaSacType);
		//builder.append(", memo=");
		//builder.append(memo);
		builder.append("]");
		return builder.toString();
	}
	
}
