package com.ink.user.api.model.out;

import java.io.Serializable;

/**
 * @Description: 账户提现冻结返回参数
 * @author wanghao^_^
 * @date 2016年6月12日 下午6:17:12
 * @version V1.0
 */
public class AccWithdrawFreezeOutput extends RetOutput implements Serializable {

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 8517385461727378979L;
	//提现金额
	private String amt;
	private String custFee;
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	
	public String getCustFee() {
		return custFee;
	}
	public void setCustFee(String custFee) {
		this.custFee = custFee;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccWithdrawFreezeOutput [amt=");
		builder.append(amt);
		builder.append(", custFee=");
		builder.append(custFee);
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
