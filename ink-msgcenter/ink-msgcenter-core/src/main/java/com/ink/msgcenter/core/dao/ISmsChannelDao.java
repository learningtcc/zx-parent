/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao;

import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.SmsChannel;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface ISmsChannelDao extends EntityDao<SmsChannel, Long>{

    /**
     * 更新信息
     * @return 更新行数
     * @throws Exception
     */
    int updateInfo(SmsChannel smsChannel)throws Exception;

    /**
     * 重置邮件通道优先级
     * 在原有优先级的基础上，重新排序，有1开始自然排序。去除status为3的数据
     * @return
     * @throws Exception
     */
    int resetUpdatePriorityLevel()throws Exception;

    Map<String,Long> getMaxSmsChannelCode();

    List findSmsChannelTree();

	List<SmsChannel> findSmsChannels(SmsChannel record);
}