package com.ink.trade.service.route.chain.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileBankManager;
import com.ink.asile.core.po.AsileBank;
import com.ink.asile.core.po.AsileInfo;
import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.utils.CollectionUtils;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.service.route.chain.IRouteRule;


/**
 * 支持银行列表通道路由规则
 * Created by huohb on 2016/6/1.
 */
@Service("bankSupportRule")
public class BankSupportRule implements IRouteRule {

    private static final YinkerLogger logger = YinkerLogger.getLogger(BankSupportRule.class);

    // 通道支持银行列表Mananger
    @Autowired
    private IAsileBankManager asileBankManager;

    @Override
    public void doRoute(List<AsileInfo> options, AsileRouteInput condition) {

        // 查询支持此笔交易的通道列表
        AsileBank queryParam = new AsileBank();
        queryParam.setAsileCrashLimit(condition.getAmt());// 交易金额
        queryParam.setBankShort(condition.getBankShort());// 银行编码
        queryParam.setCreateTime(condition.getTradeDate());// 交易日期
        queryParam.setAsilePayType(condition.getRouteBusinessType().getCode());
        List<AsileBank> supportList = asileBankManager.getAsileBankByBankShortAndAmtLimit(queryParam);
        // 所有通道都不支持此笔交易，清空集合
        if (CollectionUtils.isEmpty(supportList)) {
            options.clear();
            logger.error("支持银行列表路由-没有支持此银行" + condition.getBankShort() + "的通道");
            return;
        }
        // 支持的通道Set
        Set<String> supportSet = new HashSet<String>();
        // 用来优先级排序
        Map<String, AsileBank> supportMap = new HashMap<String, AsileBank>();
        for (AsileBank asileBank : supportList) {
            supportSet.add(asileBank.getAsileCode());
            supportMap.put(asileBank.getAsileCode(), asileBank);
        }

        // 移除掉不支持的通道
        RuleListSubUtil.remainsList(options, supportSet);

        // 按照优先级排序
        Collections.sort(options, new PriorityComparator(supportMap));
    }

    @Override
    public int getRuleType() {
        return BUSINESS_RULE;
    }

    class PriorityComparator implements Comparator<AsileInfo> {

        private Map<String, AsileBank> supportMap = null;

        public PriorityComparator(Map<String, AsileBank> supportMap) {
            this.supportMap = supportMap;
        }

        @Override
        public int compare(AsileInfo o1, AsileInfo o2) {

            int priority1 = supportMap.get(o1.getAsileCode()).getPriority();
            int priority2 = supportMap.get(o2.getAsileCode()).getPriority();

            return priority1 < priority2 ? -1 : (priority1 == priority2) ? 0 : 1;
        }
    }
}
