/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.route.api.model.po.AuthChannelPriority;
import com.ink.route.dao.IAuthChannelPriorityDao;
import com.ink.route.manager.IAuthChannelPriorityManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Service("authChannelPriorityManager")
@Transactional
public class AuthChannelPriorityManagerImpl extends BaseManager<AuthChannelPriority, java.lang.Long> implements IAuthChannelPriorityManager {

	private final YinkerLogger logger = YinkerLogger.getLogger(AuthChannelPriorityManagerImpl.class);

	private static final String cacheKey = "auth.channel.priority.all";
//	@Autowired
	private Yedis redisClient;
	@Autowired
	private IAuthChannelPriorityDao authChannelPriorityDao;

	public EntityDao<AuthChannelPriority, java.lang.Long> getEntityDao() {
		return this.authChannelPriorityDao;
	}

	@Override
	public List<AuthChannelPriority> getAuthChannels() {
		List<AuthChannelPriority> authChannels = null;
		CacheObject cache=redisClient.getObject("AuthChannelPriority-", cacheKey, String.class, new Reader<Object>() {

			@Override
			public Object readerFromDatabase() {
				return JSONObject.toJSONString(authChannelPriorityDao.getAll());
			}
		});
		authChannels=JSON.parseArray(cache.getValue().toString(),AuthChannelPriority.class);
		return authChannels;
	}

	@Override
	public int save(AuthChannelPriority entity) throws DataAccessException {
		int count= super.save(entity);
		getFromDB();
		return count;
	}

	@Override
	public int saveBatch(List<AuthChannelPriority> list) throws DataAccessException {
		int count= super.saveBatch(list);
		getFromDB();
		return count;
	}

	@Override
	public int removeById(Long id) throws DataAccessException {
		int count=super.removeById(id);
		getFromDB();
		return count;
	}

	@Override
	public int update(AuthChannelPriority entity) throws DataAccessException {
		int count= super.update(entity);
		getFromDB();
		return count;
	}
	@Override
	public int updateNotNull(AuthChannelPriority authChannelPriority){
		int count=  authChannelPriorityDao.updateNotNull(authChannelPriority);
		getFromDB();
		return count;
	}
	@Override
	public void deleteByEntity(AuthChannelPriority entity) throws DataAccessException {
		super.deleteByEntity(entity);
		getFromDB();
	}

	public List<AuthChannelPriority> getFromDB() {
		List<AuthChannelPriority> authChannels = authChannelPriorityDao.getAll();
		redisClient.cacheObject("AuthChannelPriority-", cacheKey, JSONObject.toJSONString(authChannels));
		return authChannels;
	}
}
