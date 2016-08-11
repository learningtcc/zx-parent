package com.ink.channel.api.service;

import com.ink.channel.api.model.in.PayAccountIn;
import com.ink.channel.api.model.out.PayAccountOut;
/**
 * 
 *<pre>
 *<b>类描述:</b>(代收接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月29日 下午6:33:22
 *</pre>
 */
public interface Pay2AccountService {
  public PayAccountOut pay(PayAccountIn order);
}
