/*
 * www.yinker.com	
 * 
 * wangxk
 */

package com.ink.trade.core.dao;


import com.ink.base.EntityDao;
import com.ink.trade.core.po.AuthOrder;

public interface IAuthOrderDao extends EntityDao<AuthOrder, Long>{
    /**
     * 根据订单id查询订单
     * @param authOrderId
     * @return
     */
    public AuthOrder getOrderByOrderId(String authOrderId);
    
    public int updateNotNull(AuthOrder authOrder);
}
