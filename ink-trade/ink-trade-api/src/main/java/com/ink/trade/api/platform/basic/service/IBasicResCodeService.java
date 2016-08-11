/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:36:09
 */
package com.ink.trade.api.platform.basic.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.basic.model.base.BasicResCodeEntity;
import com.ink.trade.api.platform.basic.model.in.BasicResCodeQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:36:09
 */
public interface IBasicResCodeService {
	
	CommonResult<Page<BasicResCodeEntity>> list(BasicResCodeQueryInput input);

	CommonResult<Object> save(BasicResCodeEntity entity);

	CommonResult<Object> update(BasicResCodeEntity entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<BasicResCodeEntity> getById(long id);

}
