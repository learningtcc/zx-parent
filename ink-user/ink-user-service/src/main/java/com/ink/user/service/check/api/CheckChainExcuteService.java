package com.ink.user.service.check.api;

import java.util.Map;

public interface CheckChainExcuteService {


	/**
	 * 执行检查
	 * @param container 第一个检查器的容器
	 * @param dtoMap 接口dto转换成的参数map
	 * @param map 返回的map
	 * @param checkers 各个业务自定义检查器，可以做额外检查
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> executeCheck(CheckerContainer container , String txnCode, Map<String, String> dtoMap, Map<String, Object> map, Checker[] checkers ) throws Exception;
	
	/**
	 * 执行检查
	 * @param container 第一个检查器的容器
	 * @param dtoMap 接口dto转换成的参数map
	 * @param map 返回的map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> executeCheck(CheckerContainer container , String txnCode, Map<String, String> dtoMap, Map<String, Object> map) throws Exception;
}
