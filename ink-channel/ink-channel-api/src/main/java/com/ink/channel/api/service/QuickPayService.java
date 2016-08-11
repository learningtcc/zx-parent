package com.ink.channel.api.service;

import com.ink.channel.api.model.in.QuickPayInput;
import com.ink.channel.api.model.out.QuickPayOutput;
/**
 * 
 *<pre>
 *<b>类描述:</b>(快捷支付首次支付接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月29日 下午6:37:58
 *</pre>
 */
public interface QuickPayService {
	
	/**
	 * 快捷支付
	 * @param input
	 * @return
	 */
    public QuickPayOutput pay(QuickPayInput input);
}
