package com.ink.trade.api.platform.trade.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.api.platform.trade.model.base.PayOrderEntity;

public interface IPayOrderService {
	CommonResult<Page<PayOrderEntity>> list(PayOrderEntity input);
}
