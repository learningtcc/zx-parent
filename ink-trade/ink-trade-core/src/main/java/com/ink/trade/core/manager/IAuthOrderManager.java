package com.ink.trade.core.manager;

import com.ink.base.IBaseManager;
import com.ink.trade.core.po.AuthOrder;
/**
 * 
 *<pre>
 *<b>类描述:</b>()
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月22日 下午6:39:56
 *</pre>
 */
public interface IAuthOrderManager extends IBaseManager<AuthOrder, Long>{
    /**
     * 根据订单号查询减去订单
     * @param authOrderId
     * @return
     */
    public AuthOrder getOrderByOrderId(String authOrderId);
    public int updateNotNull(AuthOrder authOrder);
}
