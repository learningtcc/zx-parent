/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.dao;

import com.ink.base.EntityDao;
import com.ink.user.core.po.AccTypeMchLimit;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IAccTypeMchLimitDao extends EntityDao<AccTypeMchLimit, java.lang.Long>{
	public AccTypeMchLimit getByMchIdAndSacType(Long mchId, String sacType) ;
}