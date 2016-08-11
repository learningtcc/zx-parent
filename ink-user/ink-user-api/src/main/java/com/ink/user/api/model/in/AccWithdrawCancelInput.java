package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 提现撤回接口入参
 * 
 * @author yangchen
 * @date 2016年6月13日 下午3:30:00
 */
public class AccWithdrawCancelInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8199149619938590861L;
	// 交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	// 商户号
	@NotNull
	@Length(max = 24)
	private String mchId;
	// 订单号
	@NotNull
	@Length(max = 64)
	private String ordId;
	// 原交易代码
	@NotNull
	@Length(max = 8)
	private String oriTxnCode;
	// 原请求流水号
	@NotNull
	@Length(max = 64)
	private String oriOrdId;
	// 原交易时间
	@NotNull
	@Length(max = 14)
	private String oriTradeDate;
	// 订单日期(YYYYMMDDHHMMSS)
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	// 备注
	private String memo;

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

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
		builder.append("AccWithdrawCancelInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", oriTxnCode=");
		builder.append(oriTxnCode);
		builder.append(", oriOrdId=");
		builder.append(oriOrdId);
		builder.append(", oriTradeDate=");
		builder.append(oriTradeDate);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", mac=");
		builder.append(mac);
		builder.append("]");
		return builder.toString();
	}
	
}
