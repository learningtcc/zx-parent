package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @Description: 账户提现冻结
 * @author wanghao ^_^
 * @date 2016年6月12日 下午6:13:29
 */
public class AccWithdrawFreezeInput implements Serializable {

	/**
	*/
	private static final long serialVersionUID = -4484607672441097954L;
	// 交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	// 商户编号（托管商户编号）
	@NotNull
	@Length(max = 24)
	private String mchId;
	// 客户编号
	@NotNull
	@Length(max = 40)
	private String custId;
	// 订单日期(YYYYMMDD)
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	//请求流水号
	@NotNull
	@Length(max = 64)
	private String ordId;
	//金额
	@NotNull
	@Length(max = 16)
	private String amt;
	// 手续费
	@Length(max = 16)
	private String custFee;
	// 用于充值的子账户种类 0001-个人现金账户
	@Length(max = 4)
	private String accountType;
	// 银行卡号
	@NotNull
	@Length(max = 32)
	private String cardNo;
	// 渠道机构在账户系统里分配的编号
//	@NotNull
	@Length(max = 24)
	private String channelId;

	// mac值，用于接口合法性校验
	@NotNull
	private String mac;
	
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccWithdrawFreezeInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", amt=");
		builder.append(amt);
		builder.append(", custFee=");
		builder.append(custFee);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", mac=");
		builder.append(mac);
		builder.append("]");
		return builder.toString();
	}

}
