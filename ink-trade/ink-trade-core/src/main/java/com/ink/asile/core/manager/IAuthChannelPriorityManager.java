/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.manager;

import com.ink.asile.core.po.AuthChannelPriority;
import com.ink.base.IBaseManager;
import com.ink.trade.core.po.Auth;

import java.util.List;

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
