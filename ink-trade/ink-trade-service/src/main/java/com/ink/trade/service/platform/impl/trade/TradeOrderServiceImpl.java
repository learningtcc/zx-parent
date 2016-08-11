package com.ink.trade.service.platform.impl.trade;

import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.api.platform.trade.model.base.TradeOrderEntity;
import com.ink.trade.api.platform.trade.model.in.TradeOrderQueryInput;
import com.ink.trade.api.platform.trade.service.ITradeOrderService;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.po.TradeOrder;
import com.ink.trade.core.query.TradeOrderQuery;
@Service("tradeOrderService")
public class TradeOrderServiceImpl implements ITradeOrderService {

	YinkerLogger log = YinkerLogger.getLogger(TradeOrderServiceImpl.class);

	@Autowired
	private ITradeOrderManager tradeOrderManager;
	@Override
	public CommonResult<Page<TradeOrderEntity>> list(TradeOrderQueryInput input) {
		CommonResult<Page<TradeOrderEntity>> ret = new CommonResult<>();
		TradeOrderQuery query = BeanCopyConverter.converterClass(input, TradeOrderQuery.class);
		Page<TradeOrder> page ;
		try {
			page = tradeOrderManager.findPage(query);
		} catch (Exception e) {
			log.error("TradeOrderServiceImpl.pageQuery.ex:"+e);
			return new CommonResult<Page<TradeOrderEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception:"+e.getMessage(), new Page<TradeOrderEntity>());
		}
		List<TradeOrderEntity> converterClass = (List<TradeOrderEntity>) BeanCopyConverter.converterClass(page.getResult(), TradeOrderEntity.class);
		Page<TradeOrderEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

}
