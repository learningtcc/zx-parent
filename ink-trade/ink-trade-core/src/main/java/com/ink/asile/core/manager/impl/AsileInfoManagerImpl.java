package com.ink.asile.core.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ink.asile.core.dao.IAsileInfoDao;
import com.ink.asile.core.manager.IAsileInfoManager;
import com.ink.asile.core.po.AsileInfo;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;

@Service("asileInfoManager")
public class AsileInfoManagerImpl extends BaseManager<AsileInfo, Long>
        implements IAsileInfoManager {

    private static final YinkerLogger logger = YinkerLogger.getLogger(AsileInfoManagerImpl.class);

    private static final String cacheKey = "route.asileInfo.all";

    @Autowired
    private IAsileInfoDao asileInfoDao;
    @Autowired
    private Yedis redisClient;

    @Override
    protected EntityDao<AsileInfo, Long> getEntityDao() {
        return asileInfoDao;
    }

    @Override
    public List<AsileInfo> findAsileInfos(AsileInfo record) {

        List<AsileInfo> asileInfos = null;
        CacheObject cache=redisClient.getObject("AsileInfo-", cacheKey,String.class,new Reader<Object>() {
			
			@Override
			public Object readerFromDatabase() {
				AsileInfo record = new AsileInfo();
		        record.setAsileStatus("1");
				return JSONObject.toJSONString(asileInfoDao.findAsileInfos(record));
			}
		});
        asileInfos=JSON.parseArray(cache.getValue().toString(),AsileInfo.class);
        return asileInfos;
    }

    @Override
    public AsileInfo findAsileInfoByName(String name) {
        return asileInfoDao.findAsileInfoByName(name);
    }


    private List<AsileInfo> getFromDB(AsileInfo record) {

        List<AsileInfo> asileInfos = asileInfoDao.findAsileInfos(record);
        // 将数据缓存到Redis和LocalCache
        refreshCache(asileInfos);
        return asileInfos;
    }

    private void refreshCache(List<AsileInfo> asileInfos) {
    	redisClient.cacheObject("AsileInfo-", cacheKey, JSONObject.toJSONString(asileInfos));

    }

    private void updateCache() {
        AsileInfo record = new AsileInfo();
        record.setAsileStatus("1");
        getFromDB(record);
    }

    @Override
    public int save(AsileInfo entity) throws DataAccessException {
        int affectRow = super.save(entity);
        updateCache();
        return affectRow;
    }

    @Override
    public int saveBatch(List<AsileInfo> list) throws DataAccessException {
        int affectRow = super.saveBatch(list);
        updateCache();
        return affectRow;
    }
    @Override
    public int update(AsileInfo entity) throws DataAccessException {
        int affectRow = super.update(entity);
        updateCache();
        return affectRow;
    }
    @Override
    public int updateNotNull(AsileInfo asileInfo){
    	 int affectRow =  asileInfoDao.updateNotNull(asileInfo);
		 updateCache();
	     return affectRow;
	}
    @Override
    public int removeById(Long id) throws DataAccessException {
        int affectRow = super.removeById(id);
        updateCache();
        return affectRow;
    }

    @Override
    public void deleteByEntity(AsileInfo entity) throws DataAccessException {
        super.deleteByEntity(entity);
        updateCache();
    }
}
