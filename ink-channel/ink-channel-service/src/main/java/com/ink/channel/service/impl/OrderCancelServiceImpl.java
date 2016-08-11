package com.yinker.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinker.base.utils.BeanCopyConverter;
import com.yinker.channel.api.model.in.OrderCancelIn;
import com.yinker.channel.api.model.out.OrderCancelOut;
import com.yinker.channel.api.service.OrderCancelService;
import com.yinker.channel.core.model.in.AsileOrderCancelIn;
import com.yinker.channel.core.model.out.AsileOrderCancelOut;
import com.yinker.channel.core.service.AsileOrderCancelService;
/**
 * 取消订单
 * @author ZYC7-DZ-057
 *
 */
@Service
public class OrderCancelServiceImpl implements OrderCancelService {
	
	@Autowired
    private Map<String,AsileOrderCancelService> asileOrderCancelContainer;
	
	@Override
	public OrderCancelOut orderCancel(OrderCancelIn orderCancelIn) {
		String key=getKey(orderCancelIn.getChannelId());
		AsileOrderCancelIn asileOrderCancelIn= BeanCopyConverter.converterClass(orderCancelIn, AsileOrderCancelIn.class);
		AsileOrderCancelOut asilePay2AccountOut = asileOrderCancelContainer.get(key).orderCancel(asileOrderCancelIn);
		OrderCancelOut out = BeanCopyConverter.converterClass(asilePay2AccountOut,OrderCancelOut.class);
        return out;
	}
	
	public String getKey(String channelId){
    	return channelId;
    }
}
