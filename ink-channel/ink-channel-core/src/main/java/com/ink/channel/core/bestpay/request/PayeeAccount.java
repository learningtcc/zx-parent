package com.ink.channel.core.bestpay.request;

import java.io.Serializable;

/**
 * 支付账户
 * @author huohb
 *
 */
public class PayeeAccount implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String accountCode;// 账户号
	
	private String accountName;// 账户名

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
}
