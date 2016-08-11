package com.ink.trade.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 
 * <pre>
 * <b>类描述:</b>(交易订单查询输入)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年5月5日 下午3:19:25
 * </pre>
 */
public class OrderQueryInput implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 2212939619404045686L;

	@NotNull(message = "商户号不可为空")
	private String merchantId;// 商户号
	@NotNull(message = "交易码不可为空")
	private String tradeCode;// 交易码
	@NotNull(message = "用户号不可为空")
	private String userId;// 用户号
	@NotNull(message = "订单号不可为空")
	private String orderId;// 订单号

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderQueryInput [version=");
		builder.append(", merchantId=");
		builder.append(merchantId);
		builder.append(", tradeCode=");
		builder.append(tradeCode);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append("]");
		return builder.toString();
	}

}
