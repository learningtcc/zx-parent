/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.dao;

import com.ink.asile.core.po.AuthChannelPriority;
import com.ink.base.EntityDao;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IAuthChannelPriorityDao extends EntityDao<AuthChannelPriority, java.lang.Long>{

    /**
     * 获取所有四要素认证通道
     * @return
     */
    List<AuthChannelPriority> getAll();
    
    public int updateNotNull(AuthChannelPriority authChannelPriority);
}