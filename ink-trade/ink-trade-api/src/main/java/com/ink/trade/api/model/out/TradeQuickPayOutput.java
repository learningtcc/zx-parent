package com.ink.trade.api.model.out;


/**
 * 快捷支付输出
 *<pre>
 *<b>类描述:</b>()
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年5月16日 下午5:29:02
 *</pre>
 */
public class TradeQuickPayOutput extends BaseTradeOutput {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String userId;
    
    private String requestId;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
    
}
