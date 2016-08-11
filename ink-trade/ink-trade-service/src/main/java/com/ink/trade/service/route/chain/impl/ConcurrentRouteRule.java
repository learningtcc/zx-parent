package com.ink.trade.service.route.chain.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ink.asile.core.po.AsileInfo;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.service.route.chain.IRouteRule;

/**
 * 通道并发数控制
 * @author yangchen
 * @date 2016年4月7日 下午2:13:39
 */
@Service("concurrentRouteRule")
public class ConcurrentRouteRule implements IRouteRule{

	@Override
	public void doRoute(List<AsileInfo> options, AsileRouteInput condition) {
		// 因没有实际运行数据先不考虑，如果实现可考虑采用redis做计数器
	}

	@Override
	public int getRuleType() {
		return OPTIMAL_RULE;
	}
}
