/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.manager;

import com.ink.base.IBaseManager;
import com.ink.trade.core.po.UnknownOrder;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IUnknownOrderManager extends IBaseManager<UnknownOrder, Long>{
    /**
     * 标记任务
     * @param param
     * @return
     */
    int remarkOrder(Map<String,Object> param);

    /**
     * 查询待处理任务
     * @param param
     * @return
     */
    List<UnknownOrder> queryTask(Map<String,Object> param);
    
    public int updateNotNull(UnknownOrder unknownOrder);
}
