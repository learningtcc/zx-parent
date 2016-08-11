package com.ink.trade.api.model.out;

import com.ink.trade.api.model.BaseTrade;

public class BaseTradeOutput extends BaseTrade{
    /**
     * 
     */
    private static final long serialVersionUID = -3793873114362776799L;
    /**响应码**/
    private String reponseCode;
    /**响应详细信息**/
    private String reponseMsg;
    /**交易状态**/
    private String tradeStatus;
    public String getReponseCode() {
        return reponseCode;
    }
    public void setReponseCode(String reponseCode) {
        this.reponseCode = reponseCode;
    }
    public String getReponseMsg() {
        return reponseMsg;
    }
    public void setReponseMsg(String reponseMsg) {
        this.reponseMsg = reponseMsg;
    }
    public String getTradeStatus() {
        return tradeStatus;
    }
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BaseTradeOutput [reponseCode=");
        builder.append(reponseCode);
        builder.append(", reponseMsg=");
        builder.append(reponseMsg);
        builder.append(", tradeStatus=");
        builder.append(tradeStatus);
        builder.append("]");
        return builder.toString();
    }
    
}
