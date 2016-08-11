package com.ink.trade.api.model.in;

import javax.validation.constraints.NotNull;

import com.ink.trade.api.enums.CardType;
import com.ink.trade.api.enums.IdType;
import com.ink.trade.api.model.BaseTrade;
/**
 * 
 *<pre>
 *<b>类描述:</b>(签约申请输入)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月13日 上午10:50:15
 *</pre>
 */
public class SignApplyInput extends BaseTrade {
     /**  
	 * @since JDK 1.7  
	 */  
	
	private static final long serialVersionUID = 601543675717106650L;
	/**银行简码**/
     private String bankShort;
     /**银行卡类型**/
     @NotNull(message="银卡类型不可为空")
     private CardType cardType;
     /**银行卡号**/
     @NotNull(message="银行卡号不可为空")
     private String cardNo;
     /**真实姓名**/
     @NotNull(message="银行卡开户姓名不可为空")
     private String realName;
     /**证件类型**/
     @NotNull(message="银行卡开户证件类型不可为空")
     private IdType idType;
     /**证件号**/
     @NotNull(message="银行卡开户证件号不可为空")
     private String idNo;
     /**手机号**/
     @NotNull(message="银行预留手机号不可为空")
     private String phoneNo;
     /**用户id**/
     @NotNull(message="用户标识不可为空")
     private String userId;
     /**签约订单号**/
     @NotNull(message="签约订单号不可为空")
     private String signOrderId;
     /**银行卡有效期**/
     private String expireDate;
     /**银行卡cvv2**/
     private String cvv2;
    public String getBankShort() {
        return bankShort;
    }
    public void setBankShort(String bankShort) {
        this.bankShort = bankShort;
    }
   
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public CardType getCardType() {
        return cardType;
    }
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
    public IdType getIdType() {
        return idType;
    }
    public void setIdType(IdType idType) {
        this.idType = idType;
    }
    public String getIdNo() {
        return idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
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
    
    public String getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
    public String getCvv2() {
        return cvv2;
    }
    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SignApplyInput [bankShort=");
        builder.append(bankShort);
        builder.append(", cardType=");
        builder.append(cardType);
        builder.append(", cardNo=");
        builder.append(cardNo);
        builder.append(", realName=");
        builder.append(realName);
        builder.append(", idType=");
        builder.append(idType);
        builder.append(", idNo=");
        builder.append(idNo);
        builder.append(", phoneNo=");
        builder.append(phoneNo);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", signOrderId=");
        builder.append(signOrderId);
        builder.append(", expireDate=");
        builder.append(expireDate);
        builder.append(", cvv2=");
        builder.append(cvv2);
        builder.append("]");
        return builder.toString();
    }
   
    
}
