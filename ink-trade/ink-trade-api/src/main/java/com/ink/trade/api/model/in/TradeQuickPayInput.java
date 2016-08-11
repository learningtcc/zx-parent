package com.ink.trade.api.model.in;

import javax.validation.constraints.NotNull;

import com.ink.trade.api.model.BaseTrade;

/**
 * 
 * <pre>
 * <b>类描述:</b>(快捷支付输入)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年5月16日 下午5:27:48
 * </pre>
 */
public class TradeQuickPayInput extends BaseTrade {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 银行卡号 **/
    @NotNull(message="银行卡号不可为空")
    private String cardNo;
    /** 用户id **/
    @NotNull(message="用户号不可为空")
    private String userId;
    /** 订单号 **/
    @NotNull(message="订单号不可为空")
    private String orderId;
    /**短信验证码**/
    @NotNull(message="验证码不可为空")
    private String validCode;


	/**账户类型**/
    @NotNull(message = "账户类型不可为空")
    private String accountType;
    
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    public void setOrderId(String signOrderId) {
        this.orderId = signOrderId;
    }


    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeQuickPayInput [cardNo=");
		builder.append(cardNo);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", validCode=");
		builder.append(validCode);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append("]");
		return builder.toString();
	}
     
}
