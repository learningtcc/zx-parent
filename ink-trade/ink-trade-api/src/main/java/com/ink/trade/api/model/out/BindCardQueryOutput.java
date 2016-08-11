package com.ink.trade.api.model.out;

import java.io.Serializable;
import java.util.List;

import com.ink.trade.api.model.BindCard;

public class BindCardQueryOutput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7849168798404475595L;
    private String version;
    private String merchantId;
    private String userId;
    private String tradeDate;
    private List<BindCard>  bindCardList;
    private String reponseCode;
    private String reponseMsg;
    
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
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTradeDate() {
        return tradeDate;
    }
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
    public List<BindCard> getBindCardList() {
        return bindCardList;
    }
    public void setBindCardList(List<BindCard> bindCardList) {
        this.bindCardList = bindCardList;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BindCardOutput [version=");
        builder.append(version);
        builder.append(", merchantId=");
        builder.append(merchantId);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", tradeDate=");
        builder.append(tradeDate);
        builder.append(", bindCardList=");
        builder.append(bindCardList);
        builder.append(", reponseCode=");
        builder.append(reponseCode);
        builder.append(", reponseMsg=");
        builder.append(reponseMsg);
        builder.append("]");
        return builder.toString();
    }
    
    
}
