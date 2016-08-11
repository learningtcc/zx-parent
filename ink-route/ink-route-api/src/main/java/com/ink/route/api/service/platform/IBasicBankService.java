/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:35:32
 */
package com.ink.route.api.service.platform;

import java.util.List;

import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.BasicBankQueryInput;
import com.ink.route.api.model.po.BasicBank;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:35:32
 */
public interface IBasicBankService {
	
	CommonResult<Page<BasicBank>> list(BasicBankQueryInput input);

	CommonResult<Object> save(BasicBank entity);

	CommonResult<Object> update(BasicBank entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<BasicBank> getById(long id);
    List<BasicBank> getAll();
}
