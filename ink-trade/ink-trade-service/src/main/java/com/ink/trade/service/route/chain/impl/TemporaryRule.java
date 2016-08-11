package com.ink.trade.service.route.chain.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileBankTempManager;
import com.ink.asile.core.po.AsileBankTemp;
import com.ink.asile.core.po.AsileInfo;
import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.utils.CollectionUtils;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.service.route.chain.IRouteRule;

/**
 * 临时配置路由规则
 * Created by huohb on 2016/6/1.
 */
@Service("temporaryRule")
public class TemporaryRule implements IRouteRule{

    private static final YinkerLogger logger = YinkerLogger.getLogger(TemporaryRule.class);

    @Autowired
    private IAsileBankTempManager asileBankTempManager;// 临时指定通道Manager

    @Override
    public void doRoute(List<AsileInfo> options, AsileRouteInput condition) {
        // 查询某个时间段后台配置的指定通道
        AsileBankTemp queryParam = new AsileBankTemp();
        queryParam.setAsilePayType(condition.getRouteBusinessType().getCode());// 支付方式
        queryParam.setAsileAmtStart(condition.getAmt());// 交易金额
        queryParam.setBankCode(condition.getBankShort());// 银行简码
        List<AsileBankTemp> temporaryList = asileBankTempManager.findBankByLimitAndCodeAndTime(queryParam,condition.getTradeDate());
        // 如果配置了指定通道，将options中不在配置列表的remove，否则不进行任何操作
        if(!CollectionUtils.isEmpty(temporaryList)){
            Set<String> temporarySet = new HashSet<String>();
            for(AsileBankTemp asileBankTemp : temporaryList){
                temporarySet.add(asileBankTemp.getAsileCode());
            }
            logger.info("后台配置临时指定通道为{}",new Object[]{temporarySet.toArray().toString()});
            // remove掉options中不在配置列表的通道元素
            RuleListSubUtil.remainsList(options,temporarySet);
        }
    }

    @Override
    public int getRuleType() {
        return OPTIMAL_RULE;
    }
}
