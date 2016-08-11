/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.scheduler.core.dao;

import com.ink.base.EntityDao;
import com.ink.scheduler.core.po.TimerUser;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface ITimerUserDao extends EntityDao<TimerUser, Integer> {
    public TimerUser getByUserName(String userName);
}