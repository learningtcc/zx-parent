package com.ink.channel.core.bestpay.request;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ink.channel.core.utils.MyJaxbDateAdapter;

/**
 * 翼支付付款到银行卡账户请求消息类
 * 
 * @author huohb
 *
 */
@XmlRootElement(name = "data")
public class EPayToCardRequestBean extends EPayBaseRequestBean {

	private static final long serialVersionUID = 1L;

	private Date requestTime = new Date();// 商户端请求时间（格式：yyyyMMddHHmmss）
	
	private String accountCode;// 转出方账户号
	
	private String accountName;// 转出方账户名
	
	private String externalId;//外部订单号
	
	private String currencyCode;// 币种
	
	private String transactionAmount;// 交易金额
	
	private PayeeBankAccount payeeBankAccount;// 收款方银行账户信息
	

	public PayeeBankAccount getPayeeBankAccount() {
		return payeeBankAccount;
	}

	public void setPayeeBankAccount(PayeeBankAccount payeeBankAccount) {
		this.payeeBankAccount = payeeBankAccount;
	}

	@XmlJavaTypeAdapter(value = MyJaxbDateAdapter.class)
	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
}
