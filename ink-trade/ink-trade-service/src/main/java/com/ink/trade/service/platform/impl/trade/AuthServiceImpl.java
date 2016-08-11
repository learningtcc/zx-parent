/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月27日 下午2:11:55
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
import com.ink.trade.api.platform.trade.model.base.AuthEntity;
import com.ink.trade.api.platform.trade.model.in.AuthQueryInput;
import com.ink.trade.api.platform.trade.service.IAuthService;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.query.AuthQuery;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月27日 下午2:11:55
 */
@Service("authService")
public class AuthServiceImpl implements IAuthService {

	YinkerLogger log = YinkerLogger.getLogger(AuthServiceImpl.class);

	@Autowired
	private IAuthManager authManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月27日 下午2:11:55
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<AuthEntity>> findPage(AuthQueryInput input) {
		CommonResult<Page<AuthEntity>> ret = new CommonResult<>();
		AuthQuery query = BeanCopyConverter.converterClass(input, AuthQuery.class);
		Page<Auth> page = null;
		try {
			page = authManager.findPage(query);
		} catch (Exception e) {
			log.error("AuthServiceImpl.pageQuery.ex: "+e.getMessage());
			return new CommonResult<Page<AuthEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception:"+e.getMessage(), new Page<AuthEntity>());
		}
		List<AuthEntity> converterClass = (List<AuthEntity>) BeanCopyConverter.converterClass(page.getResult(), AuthEntity.class);
		Page<AuthEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

}
