package com.ink.channel.api.service;

import com.ink.channel.api.model.in.OrderCancelIn;
import com.ink.channel.api.model.out.OrderCancelOut;

/**
 * 取消订单(仅限当日)
 *<pre>
 *<b>类描述:</b>(这里用一句话描述这个类的作用)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月29日 下午6:32:37
 *</pre>
 */
public interface OrderCancelService {
	OrderCancelOut orderCancel(OrderCancelIn orderCancelIn);
}
