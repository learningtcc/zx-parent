package com.ink.base.redis.client;

/**
 * 缓存Reader
 * @author zongtt
 * 2016年7月13日13:29:08
 * @param <T>
 */
public interface Reader<T> {
	
	/**
	 * 用于缓存中获取不到时读取数据库的方法
	 * 注意：当数据库中查不到数据时，请返回NULL
	 * @return
	 */
	public T readerFromDatabase();

}
