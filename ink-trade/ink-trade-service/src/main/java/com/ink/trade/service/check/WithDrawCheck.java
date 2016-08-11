package com.ink.trade.service.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.exception.orderfail.OrderUniqueException;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.po.TradeOrder;

/**
 * 
 *<pre>
 *<b>类描述:</b>(账户提现检查)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月19日 下午2:29:57
 *</pre>
 */
@Service
public class WithDrawCheck extends TradeCheck{
    
	private YinkerLogger logger=YinkerLogger.getLogger(WithDrawCheck.class);
	@Autowired
	private ITradeOrderManager tradeOrderManager;

    @Override
    public void operateCheck(Order order) {
    	
     // 校验订单唯一性
        TradeOrder param = new TradeOrder();
        param.setMchId(order.getMerchantId());
        param.setOrderId(order.getOrderId());
        TradeOrder tradeOrder = tradeOrderManager.getByMerNoAndMerOrderNo(param);
        if (tradeOrder != null) {
            logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "用户" + order.getUserId() + "请求订单" + order.getOrderId() + "重复", order.getOrderId());
            throw new OrderUniqueException();
        }
    	
    	//提现校验
        if(null!=this.getOpt()){
        	this.getOpt().operateCheck(order);
        }
    }

}
