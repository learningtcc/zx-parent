package com.ink.trade.service.route.chain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileInfoManager;
import com.ink.asile.core.po.AsileInfo;
import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.utils.CollectionUtils;
import com.ink.trade.api.model.in.AsileRouteInput;

@Service("routeChainExecutor")
public class RouteChainExecutor {

    @Autowired
    private Map<String,IRouteRule> routeRules;
    @Autowired
    private IAsileInfoManager asileManager;

    private static YinkerLogger logger = YinkerLogger.getLogger(RouteChainExecutor.class);

    // 根据限额、银行Id、服务时间查询
    public AsileInfo execute(AsileRouteInput condition) {

        AsileInfo asileInfo = new AsileInfo();
        asileInfo.setAsileStatus("1");// 1表正常开放中
        long asileInfoStart = System.currentTimeMillis();
        List<AsileInfo> asileInfos = asileManager.findAsileInfos(asileInfo);
        long asileInfoEnd = System.currentTimeMillis();
        logger.info("路由获取通道耗时{}ms",new Object[]{asileInfoEnd - asileInfoStart});
        logger.info("交易路由选择,通道数量为{},路由规则检查begin...", asileInfos.size());
        if (CollectionUtils.isEmpty(asileInfos)) {
            logger.error("未查询到通道列表");
            return null;
        }
        logger.info("获取到路由规则链{}条", new Object[]{routeRules.size()});

        List<AsileInfo> lastRouteResult = null;

        Iterator<Map.Entry<String, IRouteRule>> routeRuleIterator = routeRules.entrySet().iterator();
        // 执行路由
        while (routeRuleIterator.hasNext()) {

            IRouteRule rule = routeRuleIterator.next().getValue();
            // 用来存放上一次路由结果
            lastRouteResult = new ArrayList<AsileInfo>(asileInfos);
            logger.info("开始{}路由，路由前通道为{}", new Object[]{getRuleName(rule), convertList2String(asileInfos)});
            // 规则过滤
            long routeStart = System.currentTimeMillis();
            rule.doRoute(asileInfos, condition);
            long routeEnd = System.currentTimeMillis();
            logger.info("路由{}耗时{}ms",new Object[]{getRuleName(rule),(routeEnd - routeStart)});

            // 如果是最优路由规则的话，如果路由出来为0个的话，需要把上次路由出的集合进行返回
            if (CollectionUtils.isEmpty(asileInfos) && IRouteRule.OPTIMAL_RULE == rule.getRuleType()) {
                asileInfos = lastRouteResult;
                logger.info("结束{}最优规则路由，路由后无可用通道，将上一路由结果{}返回", new Object[]{getRuleName(rule), convertList2String(asileInfos)});
            } else {
                logger.info("结束{}路由，路由后通道为{}", new Object[]{getRuleName(rule), convertList2String(asileInfos)});
            }

            // 如果集合为空，返回null
            if (CollectionUtils.isEmpty(asileInfos)) {
                return null;
            }
        }

        return asileInfos.get(0);
    }

    /**
     * 将List转换为String，主要用拼接渠道号与渠道名称，如10001-翼支付
     *
     * @param list
     * @return
     */
    private String convertList2String(List<AsileInfo> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0, j = list.size(); i < j; i++) {
            String str = list.get(i).getAsileCode() + "-" + list.get(i).getAsileName();

            strBuf.append((i == (j - 1)) ? str + "," : str);
        }
        return strBuf.toString();
    }

    /**
     * 获取规则类名
     *
     * @param rule
     * @return
     */
    private String getRuleName(IRouteRule rule) {
        return rule.getClass().getSimpleName();
    }

}
