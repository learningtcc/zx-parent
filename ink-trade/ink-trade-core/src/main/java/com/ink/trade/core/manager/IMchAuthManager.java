/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.manager;

import com.ink.base.IBaseManager;
import com.ink.trade.core.po.MchAuth;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IMchAuthManager extends IBaseManager<MchAuth, java.lang.Long>{
     /**
      * 根据商户号及支付类型查询商户权限
      * @param mchId
      * @param payType
      * @return
      */
     MchAuth getByMchIdPayType(String mchId, String payType);
     
     public int updateNotNull(MchAuth mchAuth);
}
