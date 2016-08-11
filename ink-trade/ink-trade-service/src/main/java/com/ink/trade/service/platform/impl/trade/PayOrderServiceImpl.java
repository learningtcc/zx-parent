package com.ink.trade.service.platform.impl.trade;

import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.api.platform.trade.model.base.PayOrderEntity;
import com.ink.trade.api.platform.trade.service.IPayOrderService;
import com.ink.trade.core.manager.IPayManager;
import com.ink.trade.core.po.Pay;
import com.ink.trade.core.query.PayQuery;
@Service("payOrderService")
public class PayOrderServiceImpl implements IPayOrderService{

	YinkerLogger log = YinkerLogger.getLogger(PayOrderServiceImpl.class);

	@Autowired
	private IPayManager payManager;


	@Override
	public CommonResult<Page<PayOrderEntity>> list(PayOrderEntity input) {
		CommonResult<Page<PayOrderEntity>> ret = new CommonResult<>();
		PayQuery query = BeanCopyConverter.converterClass(input, PayQuery.class);
		Page<Pay> page ;
		try {
			page = payManager.findPage(query);
		} catch (Exception e) {
			log.error("PayOrderServiceImpl.pageQuery.ex: "+e);
			return new CommonResult<Page<PayOrderEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception:"+e.getMessage(), new Page<PayOrderEntity>());
		}
		List<PayOrderEntity> converterClass = (List<PayOrderEntity>) BeanCopyConverter.converterClass(page.getResult(), PayOrderEntity.class);
		Page<PayOrderEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}
}
