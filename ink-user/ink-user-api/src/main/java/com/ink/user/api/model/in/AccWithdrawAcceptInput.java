package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @Description: 账户提现受理
 * @author wanghao ^_^
 * @date 2016年6月12日 下午7:26:45
 * @version V1.0
 */
public class AccWithdrawAcceptInput implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1441962959646685763L;

	// 交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	// 商户编号（托管商户编号）
	@NotNull
	@Length(max = 24)
	private String mchId;
	@NotNull
	@Length(max = 64)
	private String ordId;
	// 订单日期(YYYYMMDDHHMMSS)
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	//原请求流水号
	@NotNull
	@Length(max = 64)
	private String oriOrdId;
	//原交易日期
	@NotNull
	@Length(max = 14)
	private String oriTradeDate;
	// 渠道机构在账户系统里分配的编号
	@NotNull
	@Length(max = 8)
	private String oriTxnCode;
	//渠道
//	@NotNull
	@Length(max = 24)
	private String channelId;

	@NotNull
	@Length(max = 16)
	private String amt;
	
	@Length(max = 16)
	private String custFee;

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
	public String getOriOrdId() {
		return oriOrdId;
	}
	public void setOriOrdId(String oriOrdId) {
		this.oriOrdId = oriOrdId;
	}
	public String getOriTradeDate() {
		return oriTradeDate;
	}
	public void setOriTradeDate(String oriTradeDate) {
		this.oriTradeDate = oriTradeDate;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
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
	
	public String getOriTxnCode() {
		return oriTxnCode;
	}
	public void setOriTxnCode(String oriTxnCode) {
		this.oriTxnCode = oriTxnCode;
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
		builder.append("AccWithdrawAcceptInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", oriOrdId=");
		builder.append(oriOrdId);
		builder.append(", oriTradeDate=");
		builder.append(oriTradeDate);
		builder.append(", oriTxnCode=");
		builder.append(oriTxnCode);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", amt=");
		builder.append(amt);
		builder.append(", custFee=");
		builder.append(custFee);
		builder.append(", mac=");
		builder.append(mac);
		builder.append("]");
		return builder.toString();
	}
	
}
