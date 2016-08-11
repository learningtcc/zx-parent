package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsilePay2CardInput;
import com.ink.channel.core.model.out.AsilePay2CardOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(渠道代收接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月30日 上午10:12:01
 *</pre>
 */
public interface AsilePay2CardService {
	/**
	 * 代付
	 * @param input
	 * @return
	 */
	public AsilePay2CardOutput pay(AsilePay2CardInput input);

}
