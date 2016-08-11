package com.ink.trade.api.model.out;


/**
 * 
 *<pre>
 *<b>类描述:</b>(快捷签约输出)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年5月16日 下午5:28:28
 *</pre>
 */
public class TradeQuickAuthOutput extends BaseTradeOutput {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
}
