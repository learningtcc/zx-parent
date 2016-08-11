package com.ink.user.api.model.out;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author 作者 :bo.chen
 * @version 创建时间：2015年10月21日 下午8:14:59
 * @description 描述：个人账户开户（ACC38040）返回报文
 * 
 */
public class OpenAccountOutput extends RetOutput implements Serializable {

	private static final long serialVersionUID = -4558876992672543822L;

	@Length(max=24)
	private String uid;
	@Length(max=24)
	private String accountId;
	@NotNull
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OpenAccountOutput [uid=");
		builder.append(uid);
		builder.append(", accountId=");
		builder.append(accountId);
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
