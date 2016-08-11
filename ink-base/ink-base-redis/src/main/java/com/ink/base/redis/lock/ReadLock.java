package com.ink.base.redis.lock;

/**
 * 数据库并发读锁对象
 * 过期时间设置为10分钟
 * @author zongtt
 * 2016年7月4日17:49:50
 */
public class ReadLock {
	
	/**
	 * 过期时间
	 */
	private long expireTime;

	public ReadLock() {
		expireTime = System.currentTimeMillis() + 600000;
	}

	public long getExpireTime() {
		return expireTime;
	}
}