package com.ink.trade.api.service;

import com.ink.trade.api.model.in.OrderQueryInput;
import com.ink.trade.api.model.out.OrderQueryOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(交易订单查询)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年5月5日 下午3:18:15
 *</pre>
 */
public interface ITradeOrderQueryService {
     OrderQueryOutput tradeOrderQuery(OrderQueryInput orderQueryInput);
}
