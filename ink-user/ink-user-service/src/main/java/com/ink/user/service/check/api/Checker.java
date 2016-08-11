package com.ink.user.service.check.api;

import java.util.Map;

/**
 * 检查器
 * @author yangchen
 * @date 2016年2月23日 下午4:18:26
 */
public interface Checker {
	
	public Map<String, Object> check(String txnCode, Map<String,String> dtoMap, Map<String, Object> map) throws Exception;
	
}
