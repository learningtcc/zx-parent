package com.ink.trade.api.model.out;

import java.math.BigDecimal;

import com.ink.trade.api.enums.TradeCurrency;

/**
 * 
 * <pre>
 * <b>类描述:</b>(提现输出)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年4月13日 下午2:08:04
 * </pre>
 */
public class PrepaidOutput extends BaseTradeOutput {

	/**
     * 
     */
	private static final long serialVersionUID = -7133574360412471244L;

	/** 交易订单号 **/
	private String orderId;
	/** 交易金额 **/
	private BigDecimal amt;
	/** 交易请求流水号 **/
	private String requestId;
	/** 币种 **/
	private TradeCurrency currency;
	/** 用户id **/
	private String userId;
	/** 账户类型 **/
	private String accountType;


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public TradeCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(TradeCurrency currency) {
		this.currency = currency;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WithdrawOutput [orderId=");
		builder.append(orderId);
		builder.append(", amt=");
		builder.append(amt);
		builder.append(", requestId=");
		builder.append(requestId);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", interestAmt=");
		builder.append("]");
		return builder.toString();
	}

}
