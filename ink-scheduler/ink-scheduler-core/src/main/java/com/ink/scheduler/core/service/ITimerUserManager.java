/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.scheduler.core.service;

import com.ink.scheduler.core.po.TimerUser;
import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface ITimerUserManager extends IBaseManager<TimerUser, Integer>{
 public TimerUser getByUserName(String userName);
}
