package com.ink.user.api.model.out;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author 作者 :bo.chen
 * @version 创建时间：2015年10月21日 下午8:14:59
 * @description 描述：个人账户银行卡解绑（UnbundCard）返回报文
 * 
 */
public class UnbundCardOutput extends RetOutput implements Serializable {

	
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 2778763115853753148L;
	@Length(max=32)
	private String cardNo;
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnbundCardOutput [cardNo=");
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
