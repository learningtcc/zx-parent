package com.ink.channel.core.minsheng.request;

public class CmbcQueryPaymentReqBean extends CmbcBodyBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5153877296459401148L;
	private String orgTranFlow;//原交易流水号
	public String getOrgTranFlow() {
		return orgTranFlow;
	}
	public void setOrgTranFlow(String orgTranFlow) {
		this.orgTranFlow = orgTranFlow;
	}
	
}
