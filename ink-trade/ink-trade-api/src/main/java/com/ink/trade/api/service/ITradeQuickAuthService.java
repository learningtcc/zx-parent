package com.ink.trade.api.service;

import com.ink.trade.api.model.in.TradeQuickAuthInput;
import com.ink.trade.api.model.out.TradeQuickAuthOutput;

/**
 * 
 * <pre>
 * <b>类描述:</b>(快捷动态鉴权)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年5月16日 下午5:26:02
 * </pre>
 */
public interface ITradeQuickAuthService {
    /**
     * 快捷动态鉴权
     * @param quickAuthInput
     * @return
     */
    TradeQuickAuthOutput quickAuth(TradeQuickAuthInput quickAuthInput);
}
