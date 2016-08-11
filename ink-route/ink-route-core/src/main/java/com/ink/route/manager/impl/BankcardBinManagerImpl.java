/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.manager.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.route.api.constants.RouteRedisConstants;
import com.ink.route.api.model.po.BankcardBin;
import com.ink.route.dao.IBankcardBinDao;
import com.ink.route.manager.IBankcardBinManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
/**
 * 
 * @Description cardBin业务类
 * @author xuguoqi
 * @date 2016年5月6日 下午4:39:34
 */
@Service("bankcardBinManager")
@Transactional
public class BankcardBinManagerImpl extends BaseManager<BankcardBin,Long> implements IBankcardBinManager{

	YinkerLogger log = YinkerLogger.getLogger(BankcardBinManagerImpl.class);
	
	@Autowired
	private IBankcardBinDao bankcardBinDao;
	
	@Autowired
    private Yedis redisClient;
	
	private final int [] cardBinLenArr = {1,2,3,4,5,6,7,8,9,10};//cardbin长度集合
	
	public EntityDao<BankcardBin, Long> getEntityDao() {
		return this.bankcardBinDao;
	}
	
	/**
	 * 
	 * @Description 根据银行卡号获取cardBin信息
	 * @author xuguoqi
	 * @date 2016年5月5日 上午11:17:19
	 * @param BankNo
	 * @return
	 */
	@Transactional(readOnly=true)
	public BankcardBin getByCardBin(final String bankNo) {
		long startTime = System.currentTimeMillis();
		log.info("cardbin查询开始---------:");
		for (int cardBinLen : cardBinLenArr) {
			final String cardBin = bankNo.substring(0, cardBinLen);
			String cardBinRedisKey = this.getCardBinRedisKey(bankNo.length(), cardBinLen, cardBin);
			CacheObject cacheObject = redisClient.getObject("BankcardBin-", cardBinRedisKey, String.class, new Reader<Object>() {
				
				@Override
				public Object readerFromDatabase() {
					BankcardBin entity = new BankcardBin();
					entity.setCardNoLen(bankNo.length());
					entity.setCardBin(cardBin);
					BankcardBin byCardBin = bankcardBinDao.getByCardBinByCardBinLen(entity);
					if(null!=byCardBin){
						return JSONObject.toJSONString(byCardBin);
					}
					return null;
				}
			});
			
			
			if(cacheObject.getValue()!=null && StringUtils.isNotBlank(cacheObject.getValue().toString())){
				log.info("最终查询cardbin 共耗时:"+(System.currentTimeMillis()-startTime)+"ms");
				return JSONObject.parseObject(cacheObject.getValue().toString(), BankcardBin.class);
			}
			continue;
		}
		
		throw new RuntimeException("cardBin校验失败，请检查卡号是否正确！");
		
	}
	
	/**
	 * @Description 保存
	 * @author xuguoqi
	 * @date 2016年7月18日 下午5:27:10
	 * @param entity
	 * @return
	 * @throws DataAccessException  
	 */
	@Override
	public int save(BankcardBin entity) {
		int save = super.save(entity);
		BankcardBin bankcardBin = this.getById(Long.parseLong(String.valueOf(save)));
		//刷新缓存
		String cardBinRedisKey = this.getCardBinRedisKey(bankcardBin.getCardNoLen(), bankcardBin.getCardBinLen(), bankcardBin.getCardBin());
		redisClient.cacheObject("BankcardBin-", cardBinRedisKey, JSONObject.toJSONString(entity));
		return save;
	}
	
	/**
	 * @Description 删除
	 * @author xuguoqi
	 * @date 2016年7月18日 下午5:27:35
	 * @param id
	 * @return
	 * @throws DataAccessException  
	 */
	@Override
	public int removeById(Long id) {
		BankcardBin bankcardBin = super.getById(id);
		int removeById = super.removeById(id);
		//删除缓存
		String cardBinRedisKey = this.getCardBinRedisKey(bankcardBin.getCardNoLen(), bankcardBin.getCardBinLen(), bankcardBin.getCardBin());
		redisClient.deleteObject("BankcardBin-", cardBinRedisKey);
		return removeById;
	}
	
	/**
	 * @Description 更新
	 * @author xuguoqi
	 * @date 2016年7月18日 下午5:27:46
	 * @param entity
	 * @return
	 * @throws DataAccessException  
	 */
	@Override
	public int update(BankcardBin entity){
		BankcardBin bankcardBinBefore = super.getById(entity.getId());
		//删除缓存
		String cardBinRedisKeyBefore = this.getCardBinRedisKey(bankcardBinBefore.getCardNoLen(), bankcardBinBefore.getCardBinLen(), bankcardBinBefore.getCardBin());
		redisClient.deleteObject("BankcardBin-", cardBinRedisKeyBefore);
		int update = super.update(entity);
		BankcardBin bankcardBinAfter = super.getById(entity.getId());
		//刷新缓存
		String cardBinRedisKeyAfter = this.getCardBinRedisKey(bankcardBinAfter.getCardNoLen(), bankcardBinAfter.getCardBinLen(), bankcardBinAfter.getCardBin());
		redisClient.cacheObject("BankcardBin-", cardBinRedisKeyAfter, JSONObject.toJSONString(bankcardBinAfter));
		return update;
	}
	@Override
	public int updateNotNull(BankcardBin entity){
		BankcardBin bankcardBinBefore = super.getById(entity.getId());
		//删除缓存
		String cardBinRedisKeyBefore = this.getCardBinRedisKey(bankcardBinBefore.getCardNoLen(), bankcardBinBefore.getCardBinLen(), bankcardBinBefore.getCardBin());
		redisClient.deleteObject("BankcardBin-", cardBinRedisKeyBefore);
		
		int update = bankcardBinDao.updateNotNull(entity);
		
		BankcardBin bankcardBinAfter = super.getById(entity.getId());
		//刷新缓存
		String cardBinRedisKeyAfter = this.getCardBinRedisKey(bankcardBinAfter.getCardNoLen(), bankcardBinAfter.getCardBinLen(), bankcardBinAfter.getCardBin());
		redisClient.cacheObject("BankcardBin-", cardBinRedisKeyAfter, JSONObject.toJSONString(bankcardBinAfter));
		return update;
	}
	
	/**
	 * 
	 * @Description 获取carBin的redis缓存key值
	 * @author xuguoqi
	 * @date 2016年5月5日 下午7:23:49
	 * @return
	 */
	private String getCardBinRedisKey(int cardNoLen,int cardBinLen,String cardBin){
		return RouteRedisConstants.KEY_PROJECT+"-"+RouteRedisConstants.KEY_MODULE_BASIC+"-"+RouteRedisConstants.KEY_BUSINESS_CARDBIN+"-"+cardNoLen+"-"+cardBinLen+"-"+cardBin;
	}
	
}
