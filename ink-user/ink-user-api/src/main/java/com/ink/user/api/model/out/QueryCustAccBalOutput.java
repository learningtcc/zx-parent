package com.ink.user.api.model.out;

import java.io.Serializable;

/**
 * 
 * @author 作者 :bo.chen
 * @version 创建时间：2015年10月21日 下午8:14:59
 * @description 描述：个人账户余额查询（ACC38060）返回报文
 * 
 */
public class QueryCustAccBalOutput extends RetOutput implements Serializable {

	private static final long serialVersionUID = -3480810026862828978L;
	
	private String accountId;
	private String balDetail;
	// 账户余额
	private String accBal;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getBalDetail() {
		return balDetail;
	}
	public void setBalDetail(String balDetail) {
		this.balDetail = balDetail;
	}
	public String getAccBal() {
		return accBal;
	}
	public void setAccBal(String accBal) {
		this.accBal = accBal;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryCustAccBalOutput [accountId=");
		builder.append(accountId);
		builder.append(", balDetail=");
		builder.append(balDetail);
		builder.append(", accBal=");
		builder.append(accBal);
		builder.append(", getRetCode()=");
		builder.append(getRetCode());
		builder.append(", getRetMsg()=");
		builder.append(getRetMsg());
		builder.append(", getOrdId()=");
		builder.append(getOrdId());
		builder.append(", getAccTnsId()=");
		builder.append(getAccTnsId());
		builder.append(", getTradeDate()=");
		builder.append(getTradeDate());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}
	
}
