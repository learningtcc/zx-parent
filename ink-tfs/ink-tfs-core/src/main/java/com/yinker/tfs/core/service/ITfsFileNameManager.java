/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.tfs.core.service;

import com.yinker.tfs.core.po.TfsFileName;
import com.yinker.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface ITfsFileNameManager extends IBaseManager<TfsFileName, Long>{
    public TfsFileName findByFileName(String fileName, String suffix, String sourceCode, String moduleCode);
}
