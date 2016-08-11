/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.api.service.platform;

import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AsileBusinessSupportQueryInput;
import com.ink.route.api.model.po.AsileBusinessSupport;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IAsileBusinessSupportService {
	 	CommonResult<Page<AsileBusinessSupport>> list(AsileBusinessSupportQueryInput input);

		CommonResult<Object> save(AsileBusinessSupport entity);

		CommonResult<Object> update(AsileBusinessSupport entity);

		CommonResult<Object> deleteById(long id);

		CommonResult<AsileBusinessSupport> getById(long id);
	
}
