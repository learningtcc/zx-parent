package com.ink.user.api.model.out;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @ClassName: Acc30010RetDTO
 * @Description: 充值返回信息
 * @author guojie.gao
 * @date 2015年10月15日 下午6:24:55
 *
 */
public class RetOutput implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 7941174214710240014L;

	@NotNull
	@Length(max = 8)
	private String retCode;
	@Length(max = 40)
	private String retMsg;
	@Length(max = 64)
	private String ordId;
	@Length(max = 24)
	private String accTnsId;
	@Length(max = 14)
	private String tradeDate;
	
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	public String getAccTnsId() {
		return accTnsId;
	}
	public void setAccTnsId(String accTnsId) {
		this.accTnsId = accTnsId;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RetOutput [retCode=");
		builder.append(retCode);
		builder.append(", retMsg=");
		builder.append(retMsg);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", accTnsId=");
		builder.append(accTnsId);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append("]");
		return builder.toString();
	}
}
