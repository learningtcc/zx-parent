package com.ink.trade.api.model;

import java.io.Serializable;

public class BindCard implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1442960176146880852L;
	/**银行卡号**/
	private String cardNo;
	/**银行名称**/
    private String bankName;
    /**银行简码**/
    private String bankCode;
    /**银行预留手机号**/
    private String phone;
    
    public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getBankCode() {
        return bankCode;
    }
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
