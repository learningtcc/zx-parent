package com.ink.channel.core.boofoopay.response;

import org.apache.commons.lang.builder.ToStringBuilder;

public class QueryBFPay2AccountResp extends RootCollection  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4219637424673800463L;
	private String trade_date;//订单发送时间
	private String trans_serial_no;//商户流水号
	private String trans_id;//商户订单号
	private String trans_no;//宝付交易号
	private String succ_amt;//成功金额
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
	public String getTrans_no() {
		return trans_no;
	}
	public void setTrans_no(String trans_no) {
		this.trans_no = trans_no;
	}
	public String getSucc_amt() {
		return succ_amt;
	}
	public void setSucc_amt(String succ_amt) {
		this.succ_amt = succ_amt;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
