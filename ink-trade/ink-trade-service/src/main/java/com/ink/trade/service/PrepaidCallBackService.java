package com.ink.trade.service;

/**
 * 
 *<pre>
 *<b>类描述:</b>(代付回调)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年5月26日 下午6:48:37
 *</pre>
 */
import java.util.Map;

import com.ink.trade.core.po.TradeOrder;

public interface PrepaidCallBackService {
    boolean callBack(String orderNo, boolean orderStatus, String tranFlowNo);

//    void callBackSuccess(Pay pay, TradeOrder tradeOrder, UserBalance userBalance, String transNo);
//
//    void callBackFailer(Pay pay, TradeOrder tradeOrder, String transNo);

    void updateTradeOrder(TradeOrder order);

    void putOrder2ExceptionQueue(final String name, Object object);

    void freezeThaw(TradeOrder order);

    void pushToBalance(String adr, Map<String, String> params);
}
