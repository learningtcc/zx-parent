/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:32:09
 */
package com.ink.trade.api.platform.asile.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.asile.model.base.AsileBankRuntimeEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBankRuntimeQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:32:09
 */
public interface IAsileBankRuntimeService {
	CommonResult<Page<AsileBankRuntimeEntity>> list(AsileBankRuntimeQueryInput input);

	CommonResult<Object> save(AsileBankRuntimeEntity entity);

	CommonResult<Object> update(AsileBankRuntimeEntity entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<AsileBankRuntimeEntity> getById(long id);

}
