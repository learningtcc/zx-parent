package com.ink.route.chain;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ink.base.log.util.YinkerLogger;
import com.ink.route.api.enums.RouteBusinessType;
import com.ink.route.api.model.in.AsileRouteInput;
import com.ink.route.api.model.po.AsileBusinessSupport;
import com.ink.route.api.model.po.AuthChannelPriority;
import com.ink.route.manager.IAsileBusinessSupportManager;
import com.ink.route.manager.IAuthChannelPriorityManager;

/**
 * 四要素认证路由执行器
 * Created by huohb on 2016/6/20.
 */
@Service("authRouteExecutor")
public class AuthRouteExecutor {

    private final YinkerLogger logger = YinkerLogger.getLogger(AuthRouteExecutor.class);

    @Autowired
    private IAuthChannelPriorityManager authChannelPriorityManager;

    @Autowired
    private IAsileBusinessSupportManager asileBusinessSupportManager;


    public String execute(AsileRouteInput input){
        List<AuthChannelPriority> authChannels = authChannelPriorityManager.getAuthChannels();

        if(CollectionUtils.isEmpty(authChannels)){
            logger.error("后台未配置四要素认证渠道，路由失败");
            return null;
        }

        // 获取商户开通的渠道业务
        List<AsileBusinessSupport> supports = asileBusinessSupportManager.getByMerNo(input.getMchId());

        if (CollectionUtils.isEmpty(supports)) {
            logger.info("商户{}未开通任何渠道业务", new Object[]{input.getMchId()});
            return null;
        }

        Set<String> supportSet = new HashSet<String>();
        for(AsileBusinessSupport asileBusinessSupport : supports){
            // 支持此业务，添加到Set
            if (isHadPermission(RouteBusinessType.AUTH.getIndex(), asileBusinessSupport.getPayType())) {
                supportSet.add(asileBusinessSupport.getChannelNo());
            }
        }

        if(supportSet.isEmpty()){
            logger.info("商户{}开通的渠道都不支持四要素认证业务",new Object[]{input.getMchId()});
            return null;
        }
        // 将渠道号不在Set中的渠道移出options
        Iterator<AuthChannelPriority> iterator = authChannels.iterator();
        while (iterator.hasNext()){
            if(!supportSet.contains(iterator.next().getChannelNo())){
                iterator.remove();
            }
        }

        if(CollectionUtils.isEmpty(authChannels)){
            return null;
        }

        // 按照优先级排序
        Collections.sort(authChannels, new Comparator<AuthChannelPriority>() {
            @Override
            public int compare(AuthChannelPriority o1, AuthChannelPriority o2) {
                return o1.getPriority() < o2.getPriority() ? -1 : (o1.getPriority() == o2.getPriority() ? 0 : 1);
            }
        });
        return authChannels.get(0).getChannelNo();
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

}
