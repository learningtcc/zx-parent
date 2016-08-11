package com.ink.user.api.model.out;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author 作者 :bo.chen
 * @version 创建时间：2015年10月21日 下午8:14:59
 * @description 描述：个人账户银行卡绑定（ACC38070）返回报文
 * 
 */
public class BindCardOutput extends RetOutput implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4063305259344696634L;
	@Length(max=24)
	private String bindCardId;
	@Length(max=32)
	private String cardNo;

	public String getBindCardId() {
		return bindCardId;
	}
	public void setBindCardId(String bindCardId) {
		this.bindCardId = bindCardId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BindCardOutput [bindCardId=");
		builder.append(bindCardId);
		builder.append(", cardNo=");
		builder.append(cardNo);
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