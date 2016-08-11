/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月27日 下午3:58:21
 */
package com.ink.trade.service.platform.impl.trade;

import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.api.platform.trade.model.base.AuthOrderEntity;
import com.ink.trade.api.platform.trade.model.in.AuthOrderQueryInput;
import com.ink.trade.api.platform.trade.service.IAuthOrderService;
import com.ink.trade.core.manager.IAuthOrderManager;
import com.ink.trade.core.po.AuthOrder;
import com.ink.trade.core.query.AuthOrderQuery;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月27日 下午3:58:21
 */
@Service("authOrderService")
public class AuthOrderServiceImpl implements IAuthOrderService {

	YinkerLogger log = YinkerLogger.getLogger(AuthOrderServiceImpl.class);

	@Autowired
	private IAuthOrderManager authOrderManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月27日 下午3:58:21
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<AuthOrderEntity>> findPage(AuthOrderQueryInput input) {
		CommonResult<Page<AuthOrderEntity>> ret = new CommonResult<>();
		AuthOrderQuery query = BeanCopyConverter.converterClass(input, AuthOrderQuery.class);
		Page<AuthOrder> page = null;
		try {
			page = authOrderManager.findPage(query);
		} catch (Exception e) {
			log.error("AuthOrderServiceImpl.pageQuery.ex: "+e);
			return new CommonResult<Page<AuthOrderEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception:"+e.getMessage(), new Page<AuthOrderEntity>());
		}
		List<AuthOrderEntity> converterClass = (List<AuthOrderEntity>) BeanCopyConverter.converterClass(page.getResult(), AuthOrderEntity.class);
		Page<AuthOrderEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

}
