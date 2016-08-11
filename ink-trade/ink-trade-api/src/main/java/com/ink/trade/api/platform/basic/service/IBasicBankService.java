/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:35:32
 */
package com.ink.trade.api.platform.basic.service;

import java.util.List;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.basic.model.base.BasicBankEntity;
import com.ink.trade.api.platform.basic.model.in.BasicBankQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:35:32
 */
public interface IBasicBankService {
	
	CommonResult<Page<BasicBankEntity>> list(BasicBankQueryInput input);

	CommonResult<Object> save(BasicBankEntity entity);

	CommonResult<Object> update(BasicBankEntity entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<BasicBankEntity> getById(long id);
    List<BasicBankEntity> getAll();
}
