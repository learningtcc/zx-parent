/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao;

import com.ink.monitor.core.po.SystermModule;
import com.ink.base.EntityDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface ISystermModuleDao extends EntityDao<SystermModule, Integer>{

    int updateStatus(SystermModule systermModule);

    int updateInfoStatus(SystermModule systermModule);
}