package com.ink.asile.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import redis.BaseRedis;

import com.ink.asile.core.dao.IAsileResCodeDao;
import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.po.AsileResCode;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;

@Service
@Transactional
public class AsileResCodeManagerImpl extends BaseManager<AsileResCode, Long> implements IAsileResCodeManager {

	private YinkerLogger logger = YinkerLogger.getLogger(AsileResCodeManagerImpl.class);
	@Autowired
	private IAsileResCodeDao asileResCodeDao;
	@Autowired
	private Yedis baseRedis;

	@Override
	protected EntityDao<AsileResCode, Long> getEntityDao() {
		return asileResCodeDao;
	}

	@Override
	public int removeById(Long id) throws DataAccessException {
		AsileResCode asileResCode = this.getById(id);
		int count = super.removeById(id);
		baseRedis.deleteObject("AsileResCode-", asileResCode.getAsileCode() + asileResCode.getAsileResCode());
		return count;
	}

	@Override
	public int update(AsileResCode entity) throws DataAccessException {
		int count = super.update(entity);
		AsileResCode asileResCode = this.getById(entity.getId());
		baseRedis.cacheObject("AsileResCode-", asileResCode.getAsileCode() + asileResCode.getAsileResCode(), asileResCode);
		return count;
	}
	public int updateNotNull(AsileResCode entity){
		int count = asileResCodeDao.updateNotNull(entity);
		AsileResCode asileResCode = this.getById(entity.getId());
		baseRedis.cacheObject("AsileResCode-", asileResCode.getAsileCode() + asileResCode.getAsileResCode(), asileResCode);
		return count;
	}
	@Override
	public AsileResCode findByAsileCodeAndAsileResCode(final String AsileCode, final String AsileResCode) {
		// 先从redis中查询
		String key = AsileCode + AsileResCode;
		CacheObject cache = baseRedis.getObject("AsileResCode-", key, AsileResCode.class, new Reader<Object>() {
			@Override
			public Object readerFromDatabase() {
				return asileResCodeDao.getChanPlatResCodeRel(AsileCode, AsileResCode);
			}
		});
		AsileResCode asileResCode = (AsileResCode) cache.getValue();
		return asileResCode;
	}

}
