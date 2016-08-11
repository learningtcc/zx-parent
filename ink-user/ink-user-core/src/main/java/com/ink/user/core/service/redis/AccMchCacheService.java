package com.ink.user.core.service.redis;

import com.ink.user.core.po.AccMch;

/**
 * 商户缓存服务
 * @author yangchen
 * @date 2016年1月25日 上午10:55:17
 */
public interface AccMchCacheService {
	
	public AccMch getAccMchCacheByMchId(Long mchId);
	
	public void setAccMchCache(AccMch accMch);
	
	public void removeAccMchCache(Long mchId);
}
