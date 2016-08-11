/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:32:38
 */
package com.ink.trade.api.platform.asile.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.asile.model.base.AsileBankTempEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBankTempQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:32:38
 */
public interface IAsileBankTempService {

	CommonResult<Page<AsileBankTempEntity>> list(AsileBankTempQueryInput input);

	CommonResult<Object> save(AsileBankTempEntity entity);

	CommonResult<Object> update(AsileBankTempEntity entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<AsileBankTempEntity> getById(long id);

}
