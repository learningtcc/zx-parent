package com.ink.trade.api.platform.trade.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.api.platform.trade.model.base.TradeOrderEntity;
import com.ink.trade.api.platform.trade.model.in.TradeOrderQueryInput;

public interface ITradeOrderService {
	CommonResult<Page<TradeOrderEntity>> list(TradeOrderQueryInput input);
}
