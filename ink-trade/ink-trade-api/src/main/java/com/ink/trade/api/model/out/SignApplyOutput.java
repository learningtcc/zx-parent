package com.ink.trade.api.model.out;


public class SignApplyOutput extends BaseTradeOutput {

    /**
     * 
     */
    private static final long serialVersionUID = 8730453471717697861L;
    private String userId;
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
        builder.append("SignApplyOutput [userId=");
        builder.append(userId);
        builder.append(", signOrderId=");
        builder.append(signOrderId);
        builder.append("]");
        return builder.toString();
    }
    
}
