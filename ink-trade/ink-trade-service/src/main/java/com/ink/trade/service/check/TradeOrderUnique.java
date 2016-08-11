package com.ink.trade.service.check;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ink.trade.core.exception.orderfail.OrderUniqueException;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.po.TradeOrder;
import com.ink.trade.core.query.TradeOrderQuery;

/**
 * 
 *<pre>
 *<b>类描述:</b>(交易订单唯一性检查)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月18日 下午7:44:15
 *</pre>
 */
public class TradeOrderUnique extends TradeCheck {
    @Autowired
    private ITradeOrderManager tradeOrderManager;
    @Override
    public void operateCheck(Order order){
       this.unique(order);
       if(this.getOpt()!=null){
          this.getOpt().operateCheck(order);
       }
    }

    public void unique(Order order) {
        TradeOrderQuery query=new TradeOrderQuery();
        query.setOrderId(order.getOrderId());
        query.setMasterMark(true);// 查主库
       List<TradeOrder> tradeList=tradeOrderManager.find(query);
       if(tradeList!=null && !tradeList.isEmpty()){
           throw new OrderUniqueException();
       }
    }
   

}
