package com.ink.route.chain.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.route.api.model.in.AsileRouteInput;
import com.ink.route.api.model.po.AsileInfo;
import com.ink.route.chain.service.IRouteRule;

/**
 * 优先级路由
 * 
 * @author yangchen
 * @date 2016年4月7日 下午2:17:46
 */
@Service("priorityRouteRule")
public class PriorityRouteRule implements IRouteRule {

	private static YinkerLogger logger = YinkerLogger.getLogger(PriorityRouteRule.class);

	@Override
	public void doRoute(List<AsileInfo> options, AsileRouteInput condition) {

		// 按照优先级进行排序，选择优先级最高的通道返回
	}

	@Override
	public int getRuleType() {
		return OPTIMAL_RULE;
	}


}
