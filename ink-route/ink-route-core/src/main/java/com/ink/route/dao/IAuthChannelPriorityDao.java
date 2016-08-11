/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.dao;

import java.util.List;

import com.ink.base.EntityDao;
import com.ink.route.api.model.po.AuthChannelPriority;

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