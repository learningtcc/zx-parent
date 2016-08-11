/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.api.platform.asile.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.asile.model.base.AsileBusinessEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBusinessQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IAsileBusinessService {
	 
	 	CommonResult<Page<AsileBusinessEntity>> list(AsileBusinessQueryInput input);

		CommonResult<Object> save(AsileBusinessEntity entity);

		CommonResult<Object> update(AsileBusinessEntity entity);

		CommonResult<Object> deleteById(long id);

		CommonResult<AsileBusinessEntity> getById(long id);
	
}
