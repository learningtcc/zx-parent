package com.ink.base.mybatis.interceptor.dialect;

public abstract class Dialect {
	
	public static enum Type {
		MYSQL, ORACLE
	}

	public abstract String getLimitString(String sql, int offset, int limit);

}
