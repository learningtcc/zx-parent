/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao;

import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.EmailChannel;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IEmailChannelDao extends EntityDao<EmailChannel, Long>{

    /**
     * 更新信息
     * @return 更新行数
     * @throws Exception
     */
    int updateInfo(EmailChannel emailChannel)throws Exception;

    /**
     * 重置邮件通道优先级
     * 在原有优先级的基础上，重新排序，有1开始自然排序。去除status为3的数据
     * @return
     * @throws Exception
     */
    int resetUpdatePriorityLevel()throws Exception;

    Map<String,Long> getMaxEmailChannelCode();

    List findEmailChannelTree();

}