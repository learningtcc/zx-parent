package com.ink.channel.api.model.out;

import java.io.Serializable;
/**
 * 快捷支付鉴权输出参数类
 * @author huohb
 *
 */
public class QuickAuthOutput implements Serializable {

	private static final long serialVersionUID = -8174781399921971585L;
	
	private String resCode;// 返回码
	
	private String resMsg;// 返回信息
	
	private String token;// 令牌
	
	private String orderNo;//订单号
	
	private String orgTranFlow;//流水号
	
	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOrgTranFlow() {
		return orgTranFlow;
	}

	public void setOrgTranFlow(String orgTranFlow) {
		this.orgTranFlow = orgTranFlow;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuickAuthOutput [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", token=");
		builder.append(token);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", orgTranFlow=");
		builder.append(orgTranFlow);
		builder.append("]");
		return builder.toString();
	}
	
}
