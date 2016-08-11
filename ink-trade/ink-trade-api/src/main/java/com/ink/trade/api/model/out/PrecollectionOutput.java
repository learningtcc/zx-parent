package com.ink.trade.api.model.out;

import java.math.BigDecimal;

import com.ink.trade.api.enums.TradeCurrency;

/**
 * 
 *<pre>
 *<b>类描述:</b>(充值输出)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月13日 下午1:58:39
 *</pre>
 */
public class PrecollectionOutput extends BaseTradeOutput{

    /**
     * 
     */
    private static final long serialVersionUID = -8271125705950690131L;
    /**交易金额**/
    private BigDecimal amt;
    /**交易请求流水号**/
    private String requestId;
    /**币种**/
    private TradeCurrency currency;
    /**用户id**/
    private String userId;
    /**交易时间**/
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
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PrecollectionOutput [amt=");
        builder.append(amt);
        builder.append(", requestId=");
        builder.append(requestId);
        builder.append(", currency=");
        builder.append(currency);
        builder.append(", userId=");
        builder.append(userId);
        builder.append("]");
        return builder.toString();
    }
    
}
