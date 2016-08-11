package com.ink.user.service.check.impl;

import java.util.Map;

import com.ink.user.service.check.api.CheckChainExcuteService;
import com.ink.user.service.check.api.Checker;
import com.ink.user.service.check.api.CheckerContainer;
import org.springframework.stereotype.Service;

/**
 * 检查链的执行器
 * @author yangchen
 * @date 2016年2月23日 下午7:36:53
 */
@Service("checkChainExcuteService")
public class CheckChainExcutor implements CheckChainExcuteService {

	/**
	 * 执行检查
	 * @param container 第一个检查器的容器
	 * @param dtoMap 接口dto转换成的参数map
	 * @param map 返回的map
	 * @param checkers 各个业务自定义检查器，可以做额外检查
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> executeCheck(CheckerContainer container, String txnCode, Map<String, String> dtoMap, Map<String, Object> map, Checker[] checkers ) throws Exception{
		map = executeCheck(container, txnCode, dtoMap, map);
		if(checkers != null && checkers.length > 0){
			for(Checker checker : checkers){
				map = checker.check(txnCode, dtoMap, map);
			}
		}
		return map;
	}
	
	/**
	 * 执行检查
	 * @param container 第一个检查器的容器
	 * @param dtoMap 接口dto转换成的参数map
	 * @param map 返回的map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> executeCheck(CheckerContainer container, String txnCode,Map<String, String> dtoMap, Map<String, Object> map) throws Exception{
		while(container.next() != null){
			Checker checker = container.getChecker();
			map = checker.check(txnCode, dtoMap, map);
			container = container.next();
		}
		return map;
	}
}
