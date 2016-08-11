package com.ink.trade.service.route.chain;

import java.util.List;

import com.ink.asile.core.po.AsileInfo;
import com.ink.trade.api.model.in.AsileRouteInput;

/**
 * 路由规则过滤器
 * 
 * @author yangchen
 * @date 2016年4月7日 上午10:29:58
 */
public interface IRouteRule {
	/**
	 * 业务规则
	 * 业务规则路由的话，必须符合规则，不符合的话，直接返回null
	 */
	int BUSINESS_RULE = 1;
	/**
	 * 最优规则
	 * 最优规则路由的话，如果没有最优，需要返回上次路由结果集合的结果给业务端
	 */
	int OPTIMAL_RULE = 2;

	/**
	 * 路由规则筛选
	 * @param options
	 * @param condition
     */
	void doRoute(List<AsileInfo> options,AsileRouteInput condition);

	/**
	 * 获取路由规则类型（BUSINESS_RULE|OPTIMAL_RULE）
	 * @return
     */
	int getRuleType();
}
