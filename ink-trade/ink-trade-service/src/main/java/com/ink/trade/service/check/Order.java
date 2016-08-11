package com.ink.trade.service.check;

import java.math.BigDecimal;

/**
 * 
 *<pre>
 *<b>类描述:</b>(订单)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月14日 下午5:37:15
 *</pre>
 */
public class Order {
    /**订单号**/
    private String orderId;
    /**用户号**/
    private String userId;
    /**商户号**/
    private String merchantId;
    /**交易码**/
    private String tradeCode;
    /**支付类型**/
    private String payType;
    /**卡号**/
    private String cardNo;
    /**交易金额**/
    private BigDecimal amt;
    /** 姓名**/
    private String custName;
    /** 证件类型 01-身份证**/
    private String idType;
    /** 证件号码**/
    private String idNo;
    /** 银行卡类型**/
    private String cardType;
    /** 银行预留手机号**/
    private String bankMblNo;
    
    
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
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public BigDecimal getAmt() {
        return amt;
    }
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getBankMblNo() {
		return bankMblNo;
	}
	public void setBankMblNo(String bankMblNo) {
		this.bankMblNo = bankMblNo;
	}
    

}
