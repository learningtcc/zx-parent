package com.ink.trade.service.route.chain.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ink.asile.core.manager.IAsileBusinessSupportManager;
import com.ink.asile.core.po.AsileBusinessSupport;
import com.ink.asile.core.po.AsileInfo;
import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.service.route.chain.IRouteRule;

/**
 * 商户渠道业务支持路由规则
 * 主要包括：商户开通哪些渠道
 * 商户开通哪些渠道的哪个业务
 * Created by huohb on 2016/6/20.
 */
@Service("merChannelBizSupportRule")
public class MerChannelBizSupportRule implements IRouteRule {

    private static final YinkerLogger logger = YinkerLogger.getLogger(MerChannelBizSupportRule.class);
    @Autowired
    private IAsileBusinessSupportManager asileBusinessSupportManager;

    @Override
    public void doRoute(List<AsileInfo> options, AsileRouteInput condition) {
        // 获取商户开通的渠道业务
        List<AsileBusinessSupport> supports = asileBusinessSupportManager.getByMerNo(condition.getMchId());

        if (CollectionUtils.isEmpty(supports)) {
            logger.info("商户{}未开通任何渠道业务", new Object[]{condition.getMchId()});
            options.clear();
            return;
        }

        // 判断业务支持度
        Set<String> supportSet = new HashSet<String>();

        for(AsileBusinessSupport asileBusinessSupport : supports){
            // 支持此业务，添加到Set
            if (isHadPermission(condition.getRouteBusinessType().getIndex(), asileBusinessSupport.getPayType())) {
                supportSet.add(asileBusinessSupport.getChannelNo());
            }
        }
        if(supportSet.isEmpty()){
            logger.info("商户{}开通的渠道都不支持{}业务",new Object[]{condition.getMchId(),condition.getRouteBusinessType().getValue()});
            options.clear();
            return;
        }
        // 将渠道号不在Set中的渠道移出options
        RuleListSubUtil.remainsList(options,supportSet);

    }

    /**
     * 判断是否有指定业务的权限
     * 此处用二进制进行&操作进行判断权限
     *
     * @param index
     * @param state
     * @return
     */
    private boolean isHadPermission(int index, int state) {
        int tmp = 1 << index;
        int num = state & tmp;
        return num > 0;
    }

    @Override
    public int getRuleType() {
        return BUSINESS_RULE;
    }
}
