package com.ink.trade.api.service;

import com.ink.trade.api.model.in.CertifiedPayInput;
import com.ink.trade.api.model.out.CertifiedPayOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(认证支付首次支付接口-短验接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年6月20日 上午10:43:31
 *</pre>
 */
public interface ICertifiedPayService {
     CertifiedPayOutput pay(CertifiedPayInput input);
}
