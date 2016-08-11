/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月27日 下午3:56:30
 */
package com.ink.trade.api.platform.trade.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.api.platform.trade.model.base.AuthOrderEntity;
import com.ink.trade.api.platform.trade.model.in.AuthOrderQueryInput;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月27日 下午3:56:30
 */
public interface IAuthOrderService {
	
	public CommonResult<Page<AuthOrderEntity>> findPage(AuthOrderQueryInput input);

}
