/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.manager;

import com.ink.asile.core.po.AsileBusinessSupport;
import com.ink.base.IBaseManager;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface IAsileBusinessSupportManager extends IBaseManager<AsileBusinessSupport, java.lang.Long> {
    /**
     * 根据商户号查询
     * @param merNo
     * @return
     */
    List<AsileBusinessSupport> getByMerNo(String merNo);

    public int updateNotNull(AsileBusinessSupport asileBusinessSupport);
}
