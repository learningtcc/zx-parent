package com.ink.user.service.check.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象检查器，写了一些公用方法
 * @author yangchen
 * @date 2016年2月24日 下午3:50:48
 */
public abstract class AbstractCheck {
	
	
	protected Logger logger = LoggerFactory.getLogger(AbstractCheck.class);
	
	
	protected Map<String, Object> putMap(Map<String, Object> map, String key, Object obj) {
		map.put(key, obj);
		return map;
	}
}
