/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.dao;

import com.ink.base.EntityDao;
import com.ink.route.api.model.po.AsileBusiness;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IAsileBusinessDao extends EntityDao<AsileBusiness, java.lang.Long>{
    AsileBusiness findByAsileCodeBusinessCode(String asileCode,String businessCode);
    public int updateNotNull(AsileBusiness asileBusiness);
}