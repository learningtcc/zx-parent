/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.dao;

import com.ink.base.EntityDao;
import com.ink.trade.core.po.MchAuth;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IMchAuthDao extends EntityDao<MchAuth, java.lang.Long>{
    MchAuth getByMchIdPayType(String mchId, String payType);
    public int updateNotNull(MchAuth mchAuth);
}