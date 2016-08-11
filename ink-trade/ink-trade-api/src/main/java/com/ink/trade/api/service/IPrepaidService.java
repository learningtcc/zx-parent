package com.ink.trade.api.service;

import com.ink.trade.api.model.in.PrepaidInput;
import com.ink.trade.api.model.out.PrepaidOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(提现服务接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月13日 上午9:59:45
 *</pre>
 */
public interface IPrepaidService {
       public PrepaidOutput withdraw(PrepaidInput withDrawInput);
}
