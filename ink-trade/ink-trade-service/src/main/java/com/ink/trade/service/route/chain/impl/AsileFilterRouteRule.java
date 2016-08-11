package com.ink.trade.service.route.chain.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ink.asile.core.po.AsileInfo;
import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.utils.CollectionUtils;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.service.route.chain.IRouteRule;

/**
 * @author wanghao
 * @ClassName: AsileFilterRouteRule
 * @Description: 过滤该请求中不允许的通道
 * @date 2016年4月8日
 */
@Service("asileFilterRouteRule")
public class AsileFilterRouteRule implements IRouteRule {

    private static YinkerLogger logger = YinkerLogger.getLogger(AsileFilterRouteRule.class);


    @Override
    public void doRoute(List<AsileInfo> options, AsileRouteInput condition) {

        if (!CollectionUtils.isEmpty(condition.getAsileCodeList())) {
            logger.info("业务端指定不路由通道为{}", new Object[]{condition.getAsileCodeList().toArray().toString()});

            Set<String> notAllowSet = new HashSet<String>();
            for (String key : condition.getAsileCodeList()) {
                notAllowSet.add(key);
            }
            // 遍历options，remove掉业务端指定不路由的通道
            RuleListSubUtil.subList(options,notAllowSet);
        }
    }

    @Override
    public int getRuleType() {
        return BUSINESS_RULE;
    }
}
