package com.ink.trade.api.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
/**
 * 
 *<pre>
 *<b>类描述:</b>()
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月13日 上午10:08:43
 *</pre>
 */
public class BaseTrade implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4285001486566400721L;
    /**商户号**/
    @NotNull(message="商户号不可为空")
    private String merchantId;
    /**交易码**/
    @NotNull(message="交易码不可为空")
    private String tradeCode;
    /**支付类型**/
    @NotNull(message="支付类型不可为空")
    private String payType;
   
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseTrade [merchantId=");
		builder.append(merchantId);
		builder.append(", tradeCode=");
		builder.append(tradeCode);
		builder.append(", payType=");
		builder.append(payType);
		builder.append("]");
		return builder.toString();
	}
    
}
