/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.ISmsChannelDao;
import com.ink.msgcenter.core.po.SmsChannel;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("smsChannelDao")
public class SmsChannelDaoImpl extends BaseIbatisDao<SmsChannel,Long> implements ISmsChannelDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "SmsChannel";
	}
	
	@Override
	protected void prepareObjectForSave(SmsChannel entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	/**
	 * 更新信息
	 *
	 * @param smsChannel
	 * @return 更新行数
	 * @throws Exception
	 */
	@Override
	public int updateInfo(SmsChannel smsChannel) throws Exception {
		return this.getSqlSession().update(getIbatisSqlMapNamespace()+".updateInfo",smsChannel);
	}

	/**
	 * 重置邮件通道优先级
	 * 在原有优先级的基础上，重新排序，有1开始自然排序。去除status为3的数据
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public int resetUpdatePriorityLevel() throws Exception {
		return this.getSqlSession().update(getIbatisSqlMapNamespace()+".resetUpdatePriorityLevel");
	}

	@Override
	public Map<String,Long> getMaxSmsChannelCode() {
		return this.getSqlSession().selectOne(getIbatisSqlMapNamespace() + ".getMaxSmsChannelCode");
	}

	@Override
	public List findSmsChannelTree() {
		return this.getSqlSession().selectList(getIbatisSqlMapNamespace() + ".findSmsChannelTree");
	}

	@Override
	public List<SmsChannel> findSmsChannels(SmsChannel record) {
		return getSqlSession().selectList(getIbatisSqlMapNamespace()+".findSmsChannels",record);
	}
}
