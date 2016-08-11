package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;

/**
 * 
 *<pre>
 *<b>类描述:</b>(渠道代收查询)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月30日 上午10:10:07
 *</pre>
 */
public interface AsilePay2AccountQueryService {
	AsileQueryPayAccountOut  queryPayOrder(AsileQueryPayAccountIn queryIn);
}
