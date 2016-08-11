package com.ink.channel.core.boofoopay.response;

public class BFAuthenPayResp extends RootCollection{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5617150359599512202L;
	private String resp_code;//应答码
	private String resp_msg;//应答信息
	private String member_id;//商户号
	private String terminal_id;//终端号
	private String trade_date ;//订单发送时间
	private String trans_serial_no;//商户流水号
	private String trans_id;//商户订单号
	private String trans_no;//宝付交易号   由宝付返回， 用于在后续类交易中唯一标识一笔交易
	private String bind_id;//绑定标识号
	private String succ_amt;//成功金额
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
	public String getBind_id() {
		return bind_id;
	}
	public void setBind_id(String bind_id) {
		this.bind_id = bind_id;
	}
	public String getSucc_amt() {
		return succ_amt;
	}
	public void setSucc_amt(String succ_amt) {
		this.succ_amt = succ_amt;
	}
}
