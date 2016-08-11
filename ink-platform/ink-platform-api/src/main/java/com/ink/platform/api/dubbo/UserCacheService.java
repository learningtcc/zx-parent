package com.ink.platform.api.dubbo;

import com.ink.platform.api.model.SecUser;

/**
 * 用户缓存信息抽取接口
 * @author lww
 *
 */
public interface UserCacheService {
	/**
	 * 获取缓存用户
	 * @param loginName
	 * @return
	 */
	public SecUser getCacheUser(String loginName);
	/**
	 * 缓存用户
	 * @param loginName
	 */
	public void  setCacheUser(String loginName);
	/**
	 * 删除用户缓存
	 * @param loginName
	 */
	public void  removeCacheUser(String loginName);
}
