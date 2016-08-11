package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsileQuickPayInput;
import com.ink.channel.core.model.out.AsileQuickPayOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(渠道快捷支付接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月30日 上午10:13:12
 *</pre>
 */
public interface AsileQuickPayService {
	
	/**
	 * 快捷支付
	 * @param input
	 * @return
	 */
	public AsileQuickPayOutput pay(AsileQuickPayInput input);

}
