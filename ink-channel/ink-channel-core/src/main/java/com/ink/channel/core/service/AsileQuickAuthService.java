package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsileQuickAuthInput;
import com.ink.channel.core.model.out.AsileQuickAuthOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(渠道快捷鉴权接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月30日 上午10:12:22
 *</pre>
 */
public interface AsileQuickAuthService {
	
	/**
	 * 快捷支付鉴权
	 * @param input
	 * @return
	 */
	public AsileQuickAuthOutput quickAuth(AsileQuickAuthInput input);

}
