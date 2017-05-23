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
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.route.api.model.po.AsileBusiness;
import com.ink.route.api.model.query.AsileBusinessQuery;
import com.ink.route.dao.IAsileBusinessDao;
import com.ink.route.manager.IAsileBusinessManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Service("asileBusinessManager")
@Transactional
public class AsileBusinessManagerImpl extends BaseManager<AsileBusiness, java.lang.Long> implements IAsileBusinessManager {
	private static final String cacheKey = "trade.asile.business";
//	@Autowired
	private Yedis redisClient;
	@Autowired
	private IAsileBusinessDao asileBusinessDao;

	public EntityDao<AsileBusiness, java.lang.Long> getEntityDao() {
		return this.asileBusinessDao;
	}

	public boolean IsSynchronized(String asileCode, String businessCode) {
		boolean flag = true;
		CacheObject cache=redisClient.getObject("AsileBusiness-", cacheKey, String.class, new Reader<Object>() {
			
			@Override
			public Object readerFromDatabase() {
				return JSONObject.toJSONString(asileBusinessDao.find(new AsileBusinessQuery()));
			}
		});
		List<AsileBusiness> asileBusinessList=JSON.parseArray(cache.getValue().toString(),AsileBusiness.class);
		for(AsileBusiness asileBusiness:asileBusinessList){
			if(asileBusiness.getAsileCode().equals(asileCode)&&asileBusiness.getBusinessCode().equals(businessCode)&&asileBusiness.getIsSyn().equals("0")){
				flag = false;
				return flag;
			}
		}
		return flag;
	}

	@Override
	public int save(AsileBusiness entity) throws DataAccessException {
		int count= super.save(entity);
		this.getFromDB();
		return count;
	}

	@Override
	public int saveBatch(List<AsileBusiness> list) throws DataAccessException {
		int count= super.saveBatch(list);
		this.getFromDB();
		return count;
	}

	@Override
	public int removeById(Long id) throws DataAccessException {
		int count= super.removeById(id);
		this.getFromDB();
		return count;
	}

	@Override
	public int update(AsileBusiness entity) throws DataAccessException {
		int count= super.update(entity);
		this.getFromDB();
		return count;
	}
	@Override
	public int updateNotNull(AsileBusiness asileBusiness){
		int count=  asileBusinessDao.updateNotNull(asileBusiness);
		this.getFromDB();
		return count;
	}
	@Override
	public void deleteByEntity(AsileBusiness entity) throws DataAccessException {
		super.deleteByEntity(entity);
		this.getFromDB();
	}


	public List<AsileBusiness> getFromDB(){
		List<AsileBusiness> asileBusiness=asileBusinessDao.find(new AsileBusinessQuery());
		redisClient.cacheObject("AsileBusiness-", cacheKey, JSONObject.toJSONString(asileBusiness));
		return asileBusiness;
	}
}
