/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.api.platform.asile.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.asile.model.base.AsileBusinessSupportEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBusinessSupportQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IAsileBusinessSupportService {
	 	CommonResult<Page<AsileBusinessSupportEntity>> list(AsileBusinessSupportQueryInput input);

		CommonResult<Object> save(AsileBusinessSupportEntity entity);

		CommonResult<Object> update(AsileBusinessSupportEntity entity);

		CommonResult<Object> deleteById(long id);

		CommonResult<AsileBusinessSupportEntity> getById(long id);
	
}
