package com.ink.channel.core.minsheng.response;

import com.ink.channel.core.minsheng.request.CmbcBodyBean;

/**
 * 民生电商认证返回消息类
 * @author huohb
 *
 */
public class CmbcAuthResBean extends CmbcBodyBean {

	private static final long serialVersionUID = 1L;
	
	private String isBcnAndIdnConform;
	
	private String bankName;

	public String getIsBcnAndIdnConform() {
		return isBcnAndIdnConform;
	}

	public void setIsBcnAndIdnConform(String isBcnAndIdnConform) {
		this.isBcnAndIdnConform = isBcnAndIdnConform;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
