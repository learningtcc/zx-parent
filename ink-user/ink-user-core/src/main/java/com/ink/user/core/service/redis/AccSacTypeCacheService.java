package com.ink.user.core.service.redis;

import com.ink.user.core.po.AccSacType;

/**
 * 账户类型缓存服务
 * @author yangchen
 * @date 2016年5月17日 下午3:15:19
 */
public interface AccSacTypeCacheService {

	public AccSacType getAccSacTypeCache(String sacType );
	
	public void setAccSacTypeCache(AccSacType accSacType);
	
	public void removeAccSacTypeCache(String sacType);
}
