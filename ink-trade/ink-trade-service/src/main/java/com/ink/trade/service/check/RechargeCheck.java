package com.ink.trade.service.check;


/**
 * 
 * <pre>
 * <b>类描述:</b>(充值检查)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年4月18日 下午7:37:23
 * </pre>
 */
public class RechargeCheck extends TradeCheck {

    @Override
    public void operateCheck(Order order) {

        if (this.getOpt() != null) {
            this.getOpt().operateCheck(order);
        }
    }

}
