/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.manager;

import java.util.List;

import com.ink.base.IBaseManager;
import com.ink.route.api.model.po.AuthChannelPriority;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface IAuthChannelPriorityManager extends IBaseManager<AuthChannelPriority, java.lang.Long> {
    /**
     * 获取所有四要素认证渠道
     * @return
     */
    List<AuthChannelPriority> getAuthChannels();

    public int updateNotNull(AuthChannelPriority authChannelPriority);
}
