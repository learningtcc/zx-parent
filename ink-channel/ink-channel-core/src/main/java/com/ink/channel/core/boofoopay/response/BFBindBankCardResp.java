package com.ink.channel.core.boofoopay.response;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BFBindBankCardResp extends RootCollection {

	/**
	 * 
	 */
	private static final long serialVersionUID = -20241057504628100L;
	private String trade_date ;//订单发送时间
	private String trans_serial_no;//商户流水号
	private String trans_id;//商户订单号
	private String bind_id;//绑定标识号
	
	public String getBind_id() {
		return bind_id;
	}
	public void setBind_id(String bind_id) {
		this.bind_id = bind_id;
	}
	public String getTrade_date() {
		return trade_date;
	}
	public void setTrade_date(String trade_date) {
		this.trade_date = trade_date;
	}
	public String getTrans_serial_no() {
		return trans_serial_no;
	}
	public void setTrans_serial_no(String trans_serial_no) {
		this.trans_serial_no = trans_serial_no;
	}
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
