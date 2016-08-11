package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsileRefundInput;
import com.ink.channel.core.model.out.AsileRefundOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(渠道退款接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月30日 上午10:13:26
 *</pre>
 */
public interface AsileRefundService {
	/**
	 * 退款
	 * @param input
	 * @return
	 */
	public AsileRefundOutput refund(AsileRefundInput input);

}
