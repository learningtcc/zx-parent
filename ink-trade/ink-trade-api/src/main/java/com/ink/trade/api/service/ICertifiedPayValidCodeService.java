package com.ink.trade.api.service;

import com.ink.trade.api.model.in.CertifiedPayValidCodeInput;
import com.ink.trade.api.model.out.CertifiedPayValidCodeOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(认证签首次支付获取短信验证码接口-短验接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年6月20日 上午10:44:00
 *</pre>
 */
public interface ICertifiedPayValidCodeService {

   CertifiedPayValidCodeOutput validCode(CertifiedPayValidCodeInput input);
}
