/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.api.service.platform;

import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AsileBusinessQueryInput;
import com.ink.route.api.model.po.AsileBusiness;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IAsileBusinessService {
	 
	 	CommonResult<Page<AsileBusiness>> list(AsileBusinessQueryInput input);

		CommonResult<Object> save(AsileBusiness entity);

		CommonResult<Object> update(AsileBusiness entity);

		CommonResult<Object> deleteById(long id);

		CommonResult<AsileBusiness> getById(long id);
	
}
