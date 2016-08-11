package com.ink.route.chain.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.route.api.model.in.AsileRouteInput;
import com.ink.route.api.model.po.AsileInfo;
import com.ink.route.chain.service.IRouteRule;

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
