/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.dao;

import com.ink.base.EntityDao;
import com.ink.trade.core.po.MchBank;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IMchBankDao extends EntityDao<MchBank, java.lang.Long>{
  MchBank getByMchIdBankShort(String mchId,String bankShort);
  public int updateNotNull(MchBank mchBank);
}