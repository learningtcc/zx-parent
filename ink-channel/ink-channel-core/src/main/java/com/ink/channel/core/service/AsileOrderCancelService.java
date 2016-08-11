package com.ink.channel.core.service;

import com.ink.channel.core.model.in.AsileOrderCancelIn;
import com.ink.channel.core.model.out.AsileOrderCancelOut;

/**
 * 
 *<pre>
 *<b>类描述:</b>(渠道取消订单接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月30日 上午10:09:30
 *</pre>
 */
public interface AsileOrderCancelService {
	AsileOrderCancelOut orderCancel(AsileOrderCancelIn asileOrderCancelIn);
}
