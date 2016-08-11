package com.ink.channel.core.boofoopay.response;

public class BFUnbindBankCardResp extends RootCollection {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5033230557207980512L;
	private String resp_code;//应答码
	private String resp_msg;//应答信息
	private String member_id;//商户号
	private String terminal_id;//终端号
	private String trade_date ;//订单发送时间
	private String trans_serial_no;//商户流水号
	public String getResp_code() {
		return resp_code;
	}
	public void setResp_code(String resp_code) {
		this.resp_code = resp_code;
	}
	public String getResp_msg() {
		return resp_msg;
	}
	public void setResp_msg(String resp_msg) {
		this.resp_msg = resp_msg;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getTerminal_id() {
		return terminal_id;
	}
	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
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
	
}
