package com.ink.user.core.service.redis.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ink.user.core.dao.ITnsTxnDao;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.redis.TnsTxnCacheService;

@Service("tnsTxnCacheService")
public class TnsTxnCacheServiceImpl  extends BaseCacheServiceImpl implements TnsTxnCacheService {
//	private static final YinkerLoger log = YinkerLoger.getLogger(TnsTxnCacheServiceImpl.class);
	private static final String TnsTxnCacheKey = "TnsTxnCacheHashMap";
	@Autowired
	private ITnsTxnDao tnsTxnDao;
	@Override
	public TnsTxn getTnsTxnByTxnCode(String txnCode) {
		Object obj = getCache(TnsTxnCacheKey, txnCode, TnsTxn.class);
		if(obj == null){
			return null;
		}
		TnsTxn tnsTxn = (TnsTxn) obj;
		
//		log.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"从缓存中返回交易信息结构, tnsTxn : "+tnsTxn);
		return tnsTxn;
	}

	@Override
	public void setTnsTxnCache(TnsTxn tnsTxn) {
//		log.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"设置交易缓存信息， tnsTxn : "+tnsTxn);
		setCache( TnsTxnCacheKey, tnsTxn.getTxnCode(), tnsTxn);
	}

	@Override
	public void removeTnsTxnCache(String txnCode) {
//		log.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"移除交易缓存信息，txnCode : "+txnCode);
		removeCache(TnsTxnCacheKey, txnCode);
	}

	@Override
	protected Map<String, String> readFromDataBase() {
		Map<String, String> map = new HashMap<String, String>();
		List<TnsTxn> list = tnsTxnDao.selectAll();
		for(TnsTxn tnsTxn : list){
			map.put(tnsTxn.getTxnCode(), JSON.toJSONString(tnsTxn));
		}
		return map;
	}

}
