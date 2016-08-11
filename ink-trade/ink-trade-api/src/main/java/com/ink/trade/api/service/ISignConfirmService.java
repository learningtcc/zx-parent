package com.ink.trade.api.service;

import com.ink.trade.api.model.in.SignConfirmInput;
import com.ink.trade.api.model.out.SignConfirmOutput;

/**
 * 
 *<pre>
 *<b>类描述:</b>(签约确认服务接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月13日 上午9:57:30
 *</pre>
 */
public interface ISignConfirmService {
   public SignConfirmOutput signConfirm(SignConfirmInput signConfirmInput);
}
