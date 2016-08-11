/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月27日 下午1:52:37
 */
package com.ink.trade.api.platform.trade.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.api.platform.trade.model.base.AuthEntity;
import com.ink.trade.api.platform.trade.model.in.AuthQueryInput;

/**
 * @Description 鉴权信息表
 * @author xuguoqi
 * @date 2016年7月27日 下午1:52:37
 */
public interface IAuthService {
	
	/**
	 * 
	 * @Description 分页条件查询
	 * @author xuguoqi
	 * @date 2016年7月27日 下午1:53:31
	 * @return
	 */
	public CommonResult<Page<AuthEntity>> findPage(AuthQueryInput input);

}
