/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.api.platform.asile.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.asile.model.base.AuthChannelPriorityEntity;
import com.ink.trade.api.platform.asile.model.in.AuthChannelPriorityQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IAuthChannelPriorityService {
	 CommonResult<Page<AuthChannelPriorityEntity>> list(AuthChannelPriorityQueryInput input);

		CommonResult<Object> save(AuthChannelPriorityEntity entity);

		CommonResult<Object> update(AuthChannelPriorityEntity entity);

		CommonResult<Object> deleteById(long id);

		CommonResult<AuthChannelPriorityEntity> getById(long id);
	
}
