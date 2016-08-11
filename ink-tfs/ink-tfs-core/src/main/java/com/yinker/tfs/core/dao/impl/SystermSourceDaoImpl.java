/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.tfs.core.dao.impl;

import com.yinker.tfs.core.dao.ISystermSourceDao;
import com.yinker.tfs.core.po.SystermSource;
import com.yinker.tfs.core.po.TfsFileName;
import org.springframework.stereotype.Repository;

import com.yinker.base.dao.BaseIbatisDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("systermSourceDao")
public class SystermSourceDaoImpl extends BaseIbatisDao<SystermSource,Integer> implements ISystermSourceDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "SystermSource";
    }

}
