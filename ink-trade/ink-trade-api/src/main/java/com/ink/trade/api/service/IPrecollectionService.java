package com.ink.trade.api.service;

import com.ink.trade.api.model.in.PrecollenctionInput;
import com.ink.trade.api.model.out.PrecollectionOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(充值服务接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月13日 上午9:58:54
 *</pre>
 */
public interface IPrecollectionService {
  public PrecollectionOutput recharge(PrecollenctionInput rechargeInput);
}
