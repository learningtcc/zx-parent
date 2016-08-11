package com.ink.asile.core.manager.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.ink.asile.core.dao.IAsileBankTempDao;
import com.ink.asile.core.manager.IAsileBankTempManager;
import com.ink.asile.core.po.AsileBankTemp;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.trade.cache.SimpleCache;

@Service("asileBankTempManager")
@Transactional
public class AsileBankTempManagerImpl extends BaseManager<AsileBankTemp, Long>
        implements IAsileBankTempManager {

    private static final YinkerLogger logger = YinkerLogger.getLogger(AsileBankTempManagerImpl.class);
    @Autowired
    private Yedis redisClient;
    @Autowired
    private IAsileBankTempDao asileBankTempDao;

    private static SimpleCache<String, List<AsileBankTemp>> localCache = new SimpleCache<String, List<AsileBankTemp>>(16);

    private static final String cacheKey = "route.asileTemp.key";

    @Override
    protected EntityDao<AsileBankTemp, Long> getEntityDao() {
        return asileBankTempDao;
    }

    @Override
    public List<AsileBankTemp> findAsileBankTemps(AsileBankTemp record) {
        return asileBankTempDao.findAsileBankTemps(record);
    }

    @Override
    public List<AsileBankTemp> findBankByLimitAndCodeAndTime(AsileBankTemp record, Date tradeDate) {
        CacheObject cache = redisClient.getObject("AsileBankTemp-", cacheKey, String.class, new Reader<Object>() {

            @Override
            public String readerFromDatabase() {
                return JSONObject.toJSONString(asileBankTempDao.getAll());
            }
        });

        List<AsileBankTemp> results = JSON.parseArray(cache.getValue().toString(), AsileBankTemp.class);
        if(CollectionUtils.isEmpty(results)){
            return null;
        }
        Iterator<AsileBankTemp> iterator = results.iterator();
        /**
         * 路由策略，不符合以下条件的进行remove
         * 1、银行码相同
         * 2、支付方式相同
         * 3、金额在指定金额范围内
         * 4、时间在指定时间范围内
         */
        while (iterator.hasNext()) {
            AsileBankTemp temp = iterator.next();
            if (!(record.getBankCode().equals(temp.getBankCode())
                    && record.getAsilePayType().equals(temp.getAsilePayType())
                    && record.getAsileAmtStart().compareTo(temp.getAsileAmtStart()) != -1
                    && record.getAsileAmtStart().compareTo(temp.getAsileAmtEnd()) != 1
                    && tradeDate.compareTo(temp.getAsileServiceBeginTime()) != -1
                    && tradeDate.compareTo(temp.getAsileServiceEndTime()) != 1)) {
                iterator.remove();
            }
        }
        return results;
    }


    private List<AsileBankTemp> getFromDB() {
        List<AsileBankTemp> list = asileBankTempDao.getAll();
        redisClient.cacheObject("AsileBankTemp-", cacheKey, JSONObject.toJSONString(list));
        return list;
    }

    @Override
    public int saveBatch(List<AsileBankTemp> list) throws DataAccessException {
        int affectRow = super.saveBatch(list);
        getFromDB();
        return affectRow;
    }

    @Override
    public int save(AsileBankTemp entity) throws DataAccessException {
        int affectRow = super.save(entity);
        getFromDB();
        return affectRow;
    }

    @Override
    public int update(AsileBankTemp entity) throws DataAccessException {
        int affectRow = super.update(entity);
        getFromDB();
        return affectRow;
    }
    @Override
   	public int updateNotNull(AsileBankTemp asileBankTemp){
   		int affectRow = asileBankTempDao.updateNotNull(asileBankTemp);
   		getFromDB();
   		return affectRow;
   	}
    @Override
    public int removeById(Long id) throws DataAccessException {
        int affectRow = super.removeById(id);
        getFromDB();
        return affectRow;
    }

    @Override
    public void deleteByEntity(AsileBankTemp entity) throws DataAccessException {
        super.deleteByEntity(entity);
        getFromDB();
    }
}
