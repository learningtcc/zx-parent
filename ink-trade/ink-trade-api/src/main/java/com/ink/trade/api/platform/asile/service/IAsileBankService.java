/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:31:21
 */
package com.ink.trade.api.platform.asile.service;

import java.util.List;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.asile.model.base.AsileBankEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBankQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:31:21
 */
public interface IAsileBankService {
	
	CommonResult<Page<AsileBankEntity>> list(AsileBankQueryInput input);

	CommonResult<Object> save(AsileBankEntity entity);

	CommonResult<Object> update(AsileBankEntity entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<AsileBankEntity> getById(long id);
	 void updateList(List<AsileBankEntity> records);
}
