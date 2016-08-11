package com.ink.asile.core.manager.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import redis.BaseRedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ink.asile.core.dao.IAsileBankRuntimeDao;
import com.ink.asile.core.manager.IAsileBankRuntimeManager;
import com.ink.asile.core.po.AsileBankRuntime;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.trade.cache.SimpleCache;

@Service("asileBankRunitmeManager")
@Transactional
public class AsileBankRuntimeManagerImpl extends BaseManager<AsileBankRuntime, Long> implements IAsileBankRuntimeManager {
    private static final YinkerLogger logger = YinkerLogger.getLogger(AsileBankRuntimeManagerImpl.class);
    @Autowired
    private IAsileBankRuntimeDao asileBankRuntimeDao;
    @Autowired
    private Yedis redisClient;

    private static SimpleCache<String, List<AsileBankRuntime>> localCache = new SimpleCache<String, List<AsileBankRuntime>>(16);

    private static final String cacheKey = "route.runtime.all";

    @Override
    public List<AsileBankRuntime> findAsileBankRuntimes(AsileBankRuntime record) {
        return asileBankRuntimeDao.findAsileBankRuntimes(record);
    }

    @Override
    public List<AsileBankRuntime> getNotAvailableChannel(AsileBankRuntime queryParam) {
        CacheObject cache = redisClient.getObject("AsileBankRunTime-", cacheKey, String.class, new Reader<Object>() {

            @Override
            public String readerFromDatabase() {
                return JSONObject.toJSONString(asileBankRuntimeDao.getAll());
            }
        });
        List<AsileBankRuntime> results = JSON.parseArray(cache.getValue().toString(), AsileBankRuntime.class);
        if (CollectionUtils.isEmpty(results)){
            return null;
        }
        Iterator<AsileBankRuntime> iterator = results.iterator();
        while (iterator.hasNext()) {
            AsileBankRuntime runtime = iterator.next();
            if (!(queryParam.getBankCode().equals(runtime.getBankCode()) && queryParam.getCreateTime().compareTo(runtime.getInactivaStartTime()) != -1 && queryParam.getCreateTime().compareTo(runtime.getInactivaEndTime()) != 1)) {
                iterator.remove();
            }
        }
        return results;
    }

    private List<AsileBankRuntime> getFromDB() {
        List<AsileBankRuntime> list = asileBankRuntimeDao.getAll();
        redisClient.cacheObject("AsileBankRuntime-", cacheKey, JSONObject.toJSONString(list));
        return list;
    }

    @Override
    public int update(AsileBankRuntime entity) throws DataAccessException {
        int affectRow = super.update(entity);
        getFromDB();
        return affectRow;
    }
    @Override
	public int updateNotNull(AsileBankRuntime asileBankRuntime){
    	 int affectRow= asileBankRuntimeDao.updateNotNull(asileBankRuntime);
		 getFromDB();
		 return affectRow;
	}
    @Override
    public int save(AsileBankRuntime entity) throws DataAccessException {
        int affectRow = super.save(entity);
        getFromDB();
        return affectRow;
    }

    @Override
    public int saveBatch(List<AsileBankRuntime> list) throws DataAccessException {
        int affectRow = super.saveBatch(list);
        getFromDB();
        return affectRow;
    }

    @Override
    public void deleteByEntity(AsileBankRuntime entity) throws DataAccessException {
        super.deleteByEntity(entity);
        getFromDB();
    }

    @Override
    protected EntityDao<AsileBankRuntime, Long> getEntityDao() {
        return asileBankRuntimeDao;
    }
    

}
