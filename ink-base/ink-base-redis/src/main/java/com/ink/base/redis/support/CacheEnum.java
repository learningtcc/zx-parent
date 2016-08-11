package com.ink.base.redis.support;

public enum CacheEnum {
	
	REDIS("Redis",1), LOCAL("Ehcache",2);
	
	private String name;

	CacheEnum(String name, int ordinal) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}