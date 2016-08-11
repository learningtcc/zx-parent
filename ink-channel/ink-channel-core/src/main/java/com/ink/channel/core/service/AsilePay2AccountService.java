package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsilePayAccountIn;
import com.ink.channel.core.model.out.AsilePayAccountOut;

/**
 * 
 *<pre>
 *<b>类描述:</b>(渠道代收接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月30日 上午10:10:43
 *</pre>
 */
public interface AsilePay2AccountService {
	AsilePayAccountOut payAccount(AsilePayAccountIn asilePayAccountIn);
}
