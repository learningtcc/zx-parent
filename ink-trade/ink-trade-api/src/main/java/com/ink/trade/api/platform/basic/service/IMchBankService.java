/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:35:03
 */
package com.ink.trade.api.platform.basic.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.basic.model.base.MchBankEntity;
import com.ink.trade.api.platform.basic.model.in.MchBankQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:35:03
 */
public interface IMchBankService {
	
	CommonResult<Page<MchBankEntity>> list(MchBankQueryInput input);

	CommonResult<Object> save(MchBankEntity entity);

	CommonResult<Object> update(MchBankEntity entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<MchBankEntity> getById(long id);

}
