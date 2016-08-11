/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao;

import com.ink.monitor.core.po.SystermSource;
import com.ink.base.EntityDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface ISystermSourceDao extends EntityDao<SystermSource, Integer>{

    int updateInfoStatus(SystermSource systermSource);
}