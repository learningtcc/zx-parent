package com.ink.trade.api.model.in;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.ink.trade.api.model.BaseTrade;

/**
 * 
 * <pre>
 * <b>类描述:</b>(快捷签约输入)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年5月16日 下午5:27:33
 * </pre>
 */
public class TradeQuickAuthInput extends BaseTrade {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

//    /** 银行简码 **/
//    private String bankShort;
//    /** 银行卡类型 **/
//    @NotNull(message="卡类型不可为空")
//    private CardType cardType;
    /** 银行卡号 **/
    @NotNull(message="银行卡号不可为空")
    private String cardNo;
//    /** 真实姓名 **/
//    @NotNull(message="银行卡开户姓名不可为空")
//    private String realName;
//    /** 证件类型 **/
//    @NotNull(message="证件类型不可为空")
//    private IdType idType;
//    /** 证件号 **/
//    @NotNull(message="证件号不可为空")
//    private String idNo;
//    /** 手机号 **/
//    @NotNull(message="银行预留手机号不可为空")
//    private String phoneNo;
    /** 用户id **/
    @NotNull(message="用户号不可为空")
    private String userId;
    /** 签约订单号 **/
    @NotNull(message="订单号不可为空")
    private String signOrderId;
//    /** 银行卡有效日期 **/
//    private String expireDate;
//    /** 银行卡cvv2 **/
//    private String cvv2;
    /**交易金额**/
    @NotNull(message="交易金额不可为空")
    @DecimalMin(value="0.01",message="交易金额不小于0.01")
    private BigDecimal amt;
    /**账户类型**/
    @NotNull(message = "账户类型不可为空")
    private String accountType;
    /**通知URL**/
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

//    public String getBankShort() {
//        return bankShort;
//    }
//
//    public void setBankShort(String bankShort) {
//        this.bankShort = bankShort;
//    }
//
//    public CardType getCardType() {
//        return cardType;
//    }
//
//    public void setCardType(CardType cardType) {
//        this.cardType = cardType;
//    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

//    public String getRealName() {
//        return realName;
//    }
//
//    public void setRealName(String realName) {
//        this.realName = realName;
//    }
//
//    public IdType getIdType() {
//        return idType;
//    }
//
//    public void setIdType(IdType idType) {
//        this.idType = idType;
//    }
//
//    public String getIdNo() {
//        return idNo;
//    }
//
//    public void setIdNo(String idNo) {
//        this.idNo = idNo;
//    }

//    public String getPhoneNo() {
//        return phoneNo;
//    }
//
//    public void setPhoneNo(String phoneNo) {
//        this.phoneNo = phoneNo;
//    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSignOrderId() {
        return signOrderId;
    }

    public void setSignOrderId(String signOrderId) {
        this.signOrderId = signOrderId;
    }

    
//    public String getExpireDate() {
//        return expireDate;
//    }
//
//    public void setExpireDate(String expireDate) {
//        this.expireDate = expireDate;
//    }
//
//    public String getCvv2() {
//        return cvv2;
//    }
//
//    public void setCvv2(String cvv2) {
//        this.cvv2 = cvv2;
//    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TradeQuickAuthInput [bankShort=");
//        builder.append(bankShort);
//        builder.append(", cardType=");
//        builder.append(cardType);
        builder.append(", cardNo=");
        builder.append(cardNo);
//        builder.append(", realName=");
//        builder.append(realName);
//        builder.append(", idType=");
//        builder.append(idType);
//        builder.append(", idNo=");
//        builder.append(idNo);
//        builder.append(", phoneNo=");
//        builder.append(phoneNo);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", signOrderId=");
        builder.append(signOrderId);
//        builder.append(", expireDate=");
//        builder.append(expireDate);
//        builder.append(", cvv2=");
//        builder.append(cvv2);
        builder.append(", amt=");
        builder.append(amt);
        builder.append(", accountType=");
        builder.append(accountType);
        builder.append("]");
        return builder.toString();
    }

    

}
