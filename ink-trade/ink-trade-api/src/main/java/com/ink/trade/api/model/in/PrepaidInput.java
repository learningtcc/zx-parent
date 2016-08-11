package com.ink.trade.api.model.in;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.ink.trade.api.enums.TradeCurrency;
import com.ink.trade.api.model.BaseTrade;

/**
 * 
 * <pre>
 * <b>类描述:</b>(提现输入)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年4月13日 下午2:07:51
 * </pre>
 */
public class PrepaidInput extends BaseTrade {
	/**
     * 
     */
	private static final long serialVersionUID = 8196500680052042084L;
	/** 银行简码 **/
	private String bankShort;
	/** 银行卡号 **/
	@NotNull(message="卡号不可为空")
	private String cardNo;
	/** 真实姓名 **/
	private String realName;
	/** 证件号 **/
	private String idNo;
	/** 手机号 **/
	private String phoneNo;
	/** 订单号 **/
	@NotNull(message="订单号不可为空")
	private String orderId;
	/** 交易金额 **/
	@NotNull(message="提现本金不可为空")
	private BigDecimal amt;
	/** 币种 **/
	private TradeCurrency currency;
	/** 用户id **/
	@NotNull(message="用户标识不可为空")
	private String userId;
	/** 账户类型 **/
	@NotNull(message="账户类型不可为空")
	@DecimalMin(value="0.01",message="交易金额不小于0.01")
	private String accountType;
	/** 通知地址 **/
	private String noticeUrl;
	public String getBankShort() {
		return bankShort;
	}

	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

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

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}

//	public BigDecimal getInterestAmt() {
//		return interestAmt;
//	}
//
//	public void setInterestAmt(BigDecimal interestAmt) {
//		this.interestAmt = interestAmt;
//	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PrepaidInput [bankShort=");
        builder.append(bankShort);
        builder.append(", cardNo=");
        builder.append(cardNo);
        builder.append(", realName=");
        builder.append(realName);
        builder.append(", idNo=");
        builder.append(idNo);
        builder.append(", phoneNo=");
        builder.append(phoneNo);
        builder.append(", orderId=");
        builder.append(orderId);
        builder.append(", amt=");
        builder.append(amt);
        builder.append(", currency=");
        builder.append(currency);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", accountType=");
        builder.append(accountType);
        builder.append(", noticeUrl=");
        builder.append(noticeUrl);
        builder.append(", interestAmt=");
        builder.append("]");
        return builder.toString();
    }

}
