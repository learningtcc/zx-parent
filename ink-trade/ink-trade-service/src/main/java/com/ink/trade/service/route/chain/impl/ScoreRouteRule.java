package com.ink.trade.service.route.chain.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ink.asile.core.po.AsileInfo;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.service.route.chain.IRouteRule;

@Service("scoreRouteRule")
public class ScoreRouteRule implements IRouteRule{


	@Override
	public void doRoute(List<AsileInfo> options, AsileRouteInput condition) {
		// 评分机制先不考虑，所以空实现
	}

	@Override
	public int getRuleType() {
		return OPTIMAL_RULE;
	}
}
