package com.ink.trade.api.service;

import com.ink.trade.api.model.in.TradeQuickPayInput;
import com.ink.trade.api.model.out.TradeQuickPayOutput;

/**
 * 
 * <pre>
 * <b>类描述:</b>(快捷支付)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年5月16日 下午5:26:21
 * </pre>
 */
public interface ITradeQuickPayService {
    /**
     * 快捷支付
     * 
     * @param quickPayInput
     * @return
     */
    TradeQuickPayOutput quickPay(TradeQuickPayInput quickPayInput);
}
