/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service;

import com.ink.msgcenter.core.po.EmailChannel;
import com.ink.base.IBaseManager;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface IEmailChannelManager extends IBaseManager<EmailChannel, Long> {

	/**
	 * 更新状态
	 * 
	 * @param id
	 *            主键id
	 * @param status
	 *            状态
	 * @return 更新行数
	 * @throws Exception
	 */
	int updateStatus(EmailChannel emailChannel) throws Exception;

	/**
	 * 获取最大邮件通道CODE
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<String, Long> getMaxEmailChannelCode() throws Exception;

	/**
	 * 获取邮件通道信息
	 * 
	 * @return
	 */
	List findEmailChannelTree() throws Exception;

	/**
	 * 保存优先级调整
	 * 
	 * @param emailChannel
	 * @param channelLevel
	 */
	int saveLevelOrder(EmailChannel emailChannel, String channelLevel) throws Exception;

}
