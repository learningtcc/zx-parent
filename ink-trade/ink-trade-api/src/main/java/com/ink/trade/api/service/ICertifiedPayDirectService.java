package com.ink.trade.api.service;

import com.ink.trade.api.model.in.PayDirectInput;
import com.ink.trade.api.model.out.PayDirectOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(认证支付直接支付接口-无需短信验证码)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年6月20日 上午10:41:02
 *</pre>
 */
public interface ICertifiedPayDirectService {
  PayDirectOutput pay(PayDirectInput input);
}
