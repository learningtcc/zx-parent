/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.api.platform.asile.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.asile.model.base.MchAuthEntity;
import com.ink.trade.api.platform.asile.model.in.MchAuthQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IMchAuthService {
	 CommonResult<Page<MchAuthEntity>> list(MchAuthQueryInput input);

		CommonResult<Object> save(MchAuthEntity entity);

		CommonResult<Object> update(MchAuthEntity entity);

		CommonResult<Object> deleteById(long id);

		CommonResult<MchAuthEntity> getById(long id);
	
}
