package com.ink.user.api.model.out;

import java.io.Serializable;

/**
 * 
 * @author 作者 :bo.chen
 * @version 创建时间：2015年10月21日 下午8:14:59
 * @description 描述：个人账户信息查询（ACC38050）返回报文
 * 
 */
public class QueryCustAccOutput extends RetOutput implements Serializable {

	private static final long serialVersionUID = 3632583169472041098L;
	
	private String accountId;
	private String openDate;
	private String closeDate;
	private String sacType;
	private String status;
//	private String accTnsId;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getSacType() {
		return sacType;
	}
	public void setSacType(String sacType) {
		this.sacType = sacType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryCustAccOutput [accountId=");
		builder.append(accountId);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", closeDate=");
		builder.append(closeDate);
		builder.append(", sacType=");
		builder.append(sacType);
		builder.append(", status=");
		builder.append(status);
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
