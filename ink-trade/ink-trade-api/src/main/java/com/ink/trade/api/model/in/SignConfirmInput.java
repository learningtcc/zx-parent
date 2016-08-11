package com.ink.trade.api.model.in;

import javax.validation.constraints.NotNull;

import com.ink.trade.api.model.BaseTrade;
/**
 * 
 *<pre>
 *<b>类描述:</b>(签约确认输入)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月13日 下午1:28:01
 *</pre>
 */
/**
 * 
 *<pre>
 *<b>类描述:</b>(签约确认输入)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月13日 下午1:28:01
 *</pre>
 */
public class SignConfirmInput extends BaseTrade{

    /**
     * 
     */
    private static final long serialVersionUID = 8739075589236909565L;
    /**用户id**/
    @NotNull(message="用户号不可为空")
    private String userId;
    /**签约订单号**/
    @NotNull(message="签约订单号不可为空")
    private String signOrderId;
    @NotNull(message="短信验证码不可为空！")
    private String validMessage;
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
    public String getValidMessage() {
        return validMessage;
    }
    public void setValidMessage(String validMessage) {
        this.validMessage = validMessage;
    }
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SignConfirmInput [userId=");
		builder.append(userId);
		builder.append(", signOrderId=");
		builder.append(signOrderId);
		builder.append(", validMessage=");
		builder.append(validMessage);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

    
}
