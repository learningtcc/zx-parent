package com.ink.trade.service.route.chain.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileBankRuntimeManager;
import com.ink.asile.core.po.AsileBankRuntime;
import com.ink.asile.core.po.AsileInfo;
import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.utils.CollectionUtils;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.service.route.chain.IRouteRule;

/**
 * 服务时间路由规则
 * Created by huohb on 2016/6/1.
 */
@Service("serviceTimeRule")
public class ServiceTimeRule implements IRouteRule{

    private static final YinkerLogger logger = YinkerLogger.getLogger(ServiceTimeRule.class);
    // 通道银行暂时不可用银行列表
    @Autowired
    private IAsileBankRuntimeManager asileBankRuntimeManager;
    @Override
    public void doRoute(List<AsileInfo> options, AsileRouteInput condition) {
        // 查询服务不可用通道
        AsileBankRuntime queryParam = new AsileBankRuntime();
        queryParam.setBankCode(condition.getBankShort());// 银行编码
        queryParam.setCreateTime(condition.getTradeDate());// 交易时间
        List<AsileBankRuntime> notAvailableList = asileBankRuntimeManager.getNotAvailableChannel(queryParam);

        if(!CollectionUtils.isEmpty(notAvailableList)){

            Set<String> notAvailableSet = new HashSet<String>();
            for(AsileBankRuntime asileBankRuntime : notAvailableList){
                notAvailableSet.add(asileBankRuntime.getAsileCode());
            }
            logger.info("服务不可用通道有{}",new Object[]{notAvailableSet.toArray().toString()});
            // 从通道集合中remove掉服务不可用的通道
            RuleListSubUtil.subList(options,notAvailableSet);

        }
    }

    @Override
    public int getRuleType() {
        return BUSINESS_RULE;
    }
}
