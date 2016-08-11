package com.ink.trade.api.model.in;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.ink.trade.api.model.BaseTrade;

public class CertifiedPayInput extends BaseTrade{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2310693881535668574L;
	@NotNull(message="订单号不可为空")
	private String orderId;
	@NotNull(message="用户号不可为空")
    private String userId;
	@NotNull(message="银行卡号不可为空")
    private String cardNo;
	@NotNull(message="交易金额不可为空")
	@DecimalMin(value="0.01",message="交易金额不小于0.01")
    private BigDecimal amt;
	@NotNull(message="短信验证码不可为空")
    private String validCode;
    private String noticeUrl;
    
    public String getNoticeUrl() {
		return noticeUrl;
	}
	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}
	public BigDecimal getAmt() {
        return amt;
    }
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
    public String getValidCode() {
        return validCode;
    }
    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CertifiedPayInput [orderId=");
		builder.append(orderId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", amt=");
		builder.append(amt);
		builder.append(", validCode=");
		builder.append(validCode);
		builder.append(", noticeUrl=");
		builder.append(noticeUrl);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
    
}
