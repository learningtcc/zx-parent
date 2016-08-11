package com.ink.trade.api.model.out;
/**
 * 
 *<pre>
 *<b>类描述:</b>(签约确认输出)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月13日 下午1:35:58
 *</pre>
 */
public class SignConfirmOutput extends BaseTradeOutput {

    /**
     * 
     */
    private static final long serialVersionUID = 5135818266052302159L;
    /**用户id**/
    private String userId;
    /**签约订单号**/
    private String signOrderId;
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
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SignConfirmOutput [userId=");
        builder.append(userId);
        builder.append(", signOrderId=");
        builder.append(signOrderId);
        builder.append("]");
        return builder.toString();
    }
    
}
