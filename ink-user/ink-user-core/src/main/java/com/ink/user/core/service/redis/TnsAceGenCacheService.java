package com.ink.user.core.service.redis;

import java.util.List;

import com.ink.user.core.po.TnsAceGen;

/**
 * 会计分录配置信息缓存服务
 * @author yangchen
 * @date 2016年1月25日 上午10:46:45
 */
public interface TnsAceGenCacheService {
	public List<TnsAceGen> getTnsAceGenFromCache(String txnCode, String aceGroup);
	
	public void setTnsAceGenList(String txnCode,String aceGroup, List<TnsAceGen> list);
	
	public void removeTnsAceGenList(String txnCode, String aceGroup);
}
