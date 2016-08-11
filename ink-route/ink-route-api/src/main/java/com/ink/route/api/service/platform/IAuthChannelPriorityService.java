/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.api.service.platform;

import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AuthChannelPriorityQueryInput;
import com.ink.route.api.model.po.AuthChannelPriority;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IAuthChannelPriorityService {
	 CommonResult<Page<AuthChannelPriority>> list(AuthChannelPriorityQueryInput input);

		CommonResult<Object> save(AuthChannelPriority entity);

		CommonResult<Object> update(AuthChannelPriority entity);

		CommonResult<Object> deleteById(long id);

		CommonResult<AuthChannelPriority> getById(long id);
	
}
