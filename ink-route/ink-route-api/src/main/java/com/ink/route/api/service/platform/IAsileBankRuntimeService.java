/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:32:09
 */
package com.ink.route.api.service.platform;

import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AsileBankRuntimeQueryInput;
import com.ink.route.api.model.po.AsileBankRuntime;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:32:09
 */
public interface IAsileBankRuntimeService {
	CommonResult<Page<AsileBankRuntime>> list(AsileBankRuntimeQueryInput input);

	CommonResult<Object> save(AsileBankRuntime entity);

	CommonResult<Object> update(AsileBankRuntime entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<AsileBankRuntime> getById(long id);

}
