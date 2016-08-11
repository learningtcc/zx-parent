package com.ink.channel.api.service;

import com.ink.channel.api.model.in.RefundInput;
import com.ink.channel.api.model.out.RefundOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(退款退货)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月29日 下午6:28:42
 *</pre>
 */
public interface RefundService {
	/**
	 * 退款
	 * @param input
	 * @return
	 */
	public RefundOutput refund(RefundInput input);

}
