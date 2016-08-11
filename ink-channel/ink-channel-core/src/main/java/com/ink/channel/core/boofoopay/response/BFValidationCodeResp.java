package com.ink.channel.core.boofoopay.response;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BFValidationCodeResp extends RootCollection{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6714032404972892561L;

	private String  trade_date;//订单发送时间
	private String trans_serial_no;//商户流水号
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
