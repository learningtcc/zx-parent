package com.ink.channel.api.service;

import com.ink.channel.api.model.in.Pay2CardInput;
import com.ink.channel.api.model.out.Pay2CardOutput;
/**
 * 
 *<pre>
 *<b>类描述:</b>(代付接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月29日 下午6:36:21
 *</pre>
 */
public interface Pay2CardService {
	/**
	 * 代付
	 * @param input
	 * @return
	 */
    public Pay2CardOutput pay(Pay2CardInput input);
}
