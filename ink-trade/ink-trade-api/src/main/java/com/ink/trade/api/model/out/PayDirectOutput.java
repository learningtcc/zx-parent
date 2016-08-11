package com.ink.trade.api.model.out;

import java.math.BigDecimal;

public class PayDirectOutput extends BaseTradeOutput {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8343806029760900634L;
	private BigDecimal amt;
    private String userId;
    private String requestId;
    
    public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
