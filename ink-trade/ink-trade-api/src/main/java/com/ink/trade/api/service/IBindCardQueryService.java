package com.ink.trade.api.service;

import com.ink.trade.api.model.in.BindCardQueryInput;
import com.ink.trade.api.model.out.BindCardQueryOutput;
/**
 * 
 *<pre>
 *<b>类描述:</b>(绑卡查询接口)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年5月5日 下午3:16:14
 *</pre>
 */
public interface IBindCardQueryService {
    /**
     * 根据商户号、用户号查询绑卡列表
     * @param bindCardInput
     * @return
     */
     public BindCardQueryOutput bindCardQuery(BindCardQueryInput bindCardInput);
}
