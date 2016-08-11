package com.ink.trade.api.model.in;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.ink.trade.api.model.BaseTrade;

public class PayDirectInput extends BaseTrade {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5691825778757564299L;
	@NotNull(message="用户编号不可为空")
    private String userId;
	@NotNull(message="银行卡号不可为空")
    private String cardNo;
    @NotNull(message="订单号不可为空")
    private String orderId;
    @NotNull(message="交易金额不可为空")
    @DecimalMin(value="0.01",message="交易金额不小于0.01")
    private BigDecimal amt;
    @NotNull(message="账户类型不可为空")
    private String accountType;
    /** 通知地址 **/
	private String noticeUrl;
	
    public String getNoticeUrl() {
		return noticeUrl;
	}
	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}
	public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public BigDecimal getAmt() {
        return amt;
    }
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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
		builder.append("PayDirectInput [userId=");
		builder.append(userId);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", amt=");
		builder.append(amt);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", noticeUrl=");
		builder.append(noticeUrl);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
    

}
