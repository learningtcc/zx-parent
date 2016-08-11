/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.manager.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ink.asile.core.dao.IAsileBusinessSupportDao;
import com.ink.asile.core.manager.IAsileBusinessSupportManager;
import com.ink.asile.core.po.AsileBusinessSupport;
import com.ink.asile.core.query.AsileBusinessSupportQuery;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("asileBusinessSupportManager")
@Transactional
public class AsileBusinessSupportManagerImpl extends BaseManager<AsileBusinessSupport,java.lang.Long> implements IAsileBusinessSupportManager {

	private static final YinkerLogger logger = YinkerLogger.getLogger(AsileBusinessSupportManagerImpl.class);

	private static final String cacheKey= "asile.business.support.";

	@Autowired
	private IAsileBusinessSupportDao asileBusinessSupportDao;
	@Autowired
	private Yedis redisClient;

	public EntityDao<AsileBusinessSupport, java.lang.Long> getEntityDao() {
		return this.asileBusinessSupportDao;
	}

	@Override
	public List<AsileBusinessSupport> getByMerNo(String merNo) {

		CacheObject cache=redisClient.getObject("AsileBusinessSupport-", cacheKey, String.class, new Reader<Object>() {
			
			@Override
			public Object readerFromDatabase() {
				return JSONObject.toJSONString(asileBusinessSupportDao.find(new AsileBusinessSupportQuery()));
			}
		});
		List<AsileBusinessSupport> result=JSON.parseArray(cache.getValue().toString(),AsileBusinessSupport.class);
		if(CollectionUtils.isEmpty(result)){
			return null;
		}
		Iterator<AsileBusinessSupport> iterator=result.iterator();
		while(iterator.hasNext()){
			AsileBusinessSupport asileBusinessSupport=iterator.next();
			if(!asileBusinessSupport.getMchId().equals(merNo)){
				iterator.remove();
			}
		}
		return result;
	}

	@Override
	public int save(AsileBusinessSupport entity) throws DataAccessException {
		int count= super.save(entity);
		getFromDB();
		return count;
	}

	@Override
	public int saveBatch(List<AsileBusinessSupport> list) throws DataAccessException {
		int count= super.saveBatch(list);
		getFromDB();
		return count;
	}

	@Override
	public int removeById(Long id) throws DataAccessException {
		int count= super.removeById(id);
		getFromDB();
		return count;
	}

	@Override
	public int update(AsileBusinessSupport entity) throws DataAccessException {
		int count= super.update(entity);
		getFromDB();
		return count;
	}
	@Override
	public int updateNotNull(AsileBusinessSupport asileBusinessSupport){
		int count= asileBusinessSupportDao.updateNotNull(asileBusinessSupport);
		getFromDB();
		return count;
	}
	@Override
	public void deleteByEntity(AsileBusinessSupport entity) throws DataAccessException {
		super.deleteByEntity(entity);
		getFromDB();
	}
	public List<AsileBusinessSupport> getFromDB(){
		List<AsileBusinessSupport> list=asileBusinessSupportDao.find(new AsileBusinessSupportQuery());
		redisClient.cacheObject("AsileBusinessSupport-", cacheKey, JSONObject.toJSONString(list));
		return list;
	}
}
