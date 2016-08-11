package com.ink.trade.service.check;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ink.trade.core.exception.orderfail.OrderUniqueException;
import com.ink.trade.core.manager.IAuthOrderManager;
import com.ink.trade.core.po.AuthOrder;
import com.ink.trade.core.query.AuthOrderQuery;
/***
 * 
 *<pre>
 *<b>类描述:</b>(签约订单唯一性检查)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月18日 下午7:43:52
 *</pre>
 */
public class SignOrderUnique extends TradeCheck  {
    @Autowired
    private IAuthOrderManager authorderManager;

    public void unique(Order order) {
      AuthOrderQuery query=new AuthOrderQuery();
      query.setOrderId(order.getOrderId());
      query.setMchId(order.getMerchantId());
        query.setMasterMark(true);// 查主库
      List<AuthOrder> authOrderList= authorderManager.find(query);
      if(authOrderList!=null&&!authOrderList.isEmpty()){
              throw new OrderUniqueException();
      }
    }

    @Override
    public void operateCheck(Order order) {
        this.unique(order);
        if (this.getOpt() != null) {
            this.getOpt().operateCheck(order);
        }
    }

}
