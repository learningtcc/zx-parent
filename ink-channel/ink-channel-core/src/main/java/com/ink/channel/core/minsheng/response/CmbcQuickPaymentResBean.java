package com.ink.channel.core.minsheng.response;

import com.ink.channel.core.minsheng.request.CmbcBodyBean;

public class CmbcQuickPaymentResBean extends CmbcBodyBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3532515538726689819L;
	private String tranRespCode;//响应码
	private String tranRespMsg;//相应说明
	private String tranState;//响应状态
	public String getTranRespCode() {
		return tranRespCode;
	}
	public void setTranRespCode(String tranRespCode) {
		this.tranRespCode = tranRespCode;
	}
	public String getTranRespMsg() {
		return tranRespMsg;
	}
	public void setTranRespMsg(String tranRespMsg) {
		this.tranRespMsg = tranRespMsg;
	}
	public String getTranState() {
		return tranState;
	}
	public void setTranState(String tranState) {
		this.tranState = tranState;
	}
	
}
