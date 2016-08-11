package com.ink.user.core.service.redis;

import com.ink.user.core.po.TnsTxn;

/**
 * 交易信息缓存服务
 * @author yangchen
 * @date 2016年1月25日 上午10:48:39
 */
public interface TnsTxnCacheService {
	public TnsTxn getTnsTxnByTxnCode(String txnCode);
	
	public void setTnsTxnCache(TnsTxn tnsTxn);
	
	public void removeTnsTxnCache(String txnCode);
}
