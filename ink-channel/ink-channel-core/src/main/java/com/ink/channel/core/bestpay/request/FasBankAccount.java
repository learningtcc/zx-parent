package com.ink.channel.core.bestpay.request;

import java.io.Serializable;

/**
 * 翼支付签约请求消息-银行账户信息
 * 
 * @author huohb
 *
 */
public class FasBankAccount implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String bankCode;// 银行编码
	
	private String cardType; // 银行卡卡类型(1:借记卡)
	
	private String accountCode; // 银行卡卡号
	
	private String bankCardName; // 银行卡户名
	
	private String certNo; // 证件号码
	
	private String certType; // 证件类型(00:身份证)
	
	private String mobile; // 联系方式(手机号码)
	
	private String areaCode; // 银行区域码6位固定长度(翼支付不验证)
	
	private String perEntFlag; // 对公对私标识(0:对公；1:对私)

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getBankCardName() {
		return bankCardName;
	}

	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPerEntFlag() {
		return perEntFlag;
	}

	public void setPerEntFlag(String perEntFlag) {
		this.perEntFlag = perEntFlag;
	}

}
