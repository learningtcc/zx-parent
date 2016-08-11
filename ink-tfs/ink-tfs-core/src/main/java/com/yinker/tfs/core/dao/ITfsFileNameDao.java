/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.tfs.core.dao;

import com.yinker.base.EntityDao;
import com.yinker.tfs.core.po.TfsFileName;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface ITfsFileNameDao extends EntityDao<TfsFileName, Long>{
    public TfsFileName findByFileName(String fileName, String suffix, String sourceCode, String moduleCode);
}