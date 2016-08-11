package com.ink.user.api.model.out;

import java.io.Serializable;

/**
 * @Description: 交易结果查询返回信息
 * @author wanghao^_^
 * @date 2016年6月13日 上午11:14:21
 * @version V1.0
 */
public class QueryTransResultOutput extends RetOutput implements Serializable{

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -8751993847784194654L;
	private String txnCode;
	private String oriRetMsg;
	private String oriRetCode;
	private String tradeInfo;
	
	public String getTxnCode() {
		return txnCode;
	}
	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}
	public String getOriRetMsg() {
		return oriRetMsg;
	}
	public void setOriRetMsg(String oriRetMsg) {
		this.oriRetMsg = oriRetMsg;
	}
	public String getOriRetCode() {
		return oriRetCode;
	}
	public void setOriRetCode(String oriRetCode) {
		this.oriRetCode = oriRetCode;
	}
	public String getTradeInfo() {
		return tradeInfo;
	}
	public void setTradeInfo(String tradeInfo) {
		this.tradeInfo = tradeInfo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryTransResultOutput [txnCode=");
		builder.append(txnCode);
		builder.append(", oriRetMsg=");
		builder.append(oriRetMsg);
		builder.append(", oriRetCode=");
		builder.append(oriRetCode);
		builder.append(", tradeInfo=");
		builder.append(tradeInfo);
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
