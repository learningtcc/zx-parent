package com.ink.channel.api.service;

import com.ink.channel.api.model.in.QueryPayAccountIn;
import com.ink.channel.api.model.out.QueryPayAccountOut;
/**
 * 
 *<pre>
 *<b>类描述:</b>(代收查询接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月29日 下午6:32:00
 *</pre>
 */
public interface Pay2AccountQueryService {
    QueryPayAccountOut accountQuery(QueryPayAccountIn order);
}
