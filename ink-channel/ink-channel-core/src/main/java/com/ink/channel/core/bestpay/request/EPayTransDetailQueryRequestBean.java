package com.ink.channel.core.bestpay.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 翼支付-交易明细查询请求消息类
 * @author huohb
 *
 */
@XmlRootElement(name = "data")
public class EPayTransDetailQueryRequestBean extends EPayBaseRequestBean{

	private static final long serialVersionUID = 1L;
	
	private String queryCondition;// 查询条件
	
	private String value;// 查询条件的值
	
	private String transPlatCode;// 交易平台号

	public String getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTransPlatCode() {
		return transPlatCode;
	}

	public void setTransPlatCode(String transPlatCode) {
		this.transPlatCode = transPlatCode;
	}
}
