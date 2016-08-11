package com.ink.channel.api.service;

import com.ink.channel.api.model.in.QuickAuthInput;
import com.ink.channel.api.model.out.QuickAuthOutput;
/**
 * 
 *<pre>
 *<b>类描述:</b>(快捷支付鉴权)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月29日 下午6:37:02
 *</pre>
 */
public interface QuickAuthService {
	/**
	 * 快捷支付鉴权
	 * @param input
	 * @return
	 */
    public QuickAuthOutput quickAuth(QuickAuthInput input);
}
