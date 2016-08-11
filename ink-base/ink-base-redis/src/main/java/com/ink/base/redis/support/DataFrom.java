package com.ink.base.redis.support;

/**
 * 数据来源
 * @author zongtt
 * 2016年7月18日16:17:39
 */
public enum DataFrom {
	DB("数据库",1), COMPUTE("计算结果",2),OTHER("其他",3);

	DataFrom(String name, int ordinal) {
	}
}
