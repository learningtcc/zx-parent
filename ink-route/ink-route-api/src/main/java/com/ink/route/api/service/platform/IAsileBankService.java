/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:31:21
 */
package com.ink.route.api.service.platform;

import java.util.List;

import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AsileBankQueryInput;
import com.ink.route.api.model.po.AsileBank;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:31:21
 */
public interface IAsileBankService {
	
	CommonResult<Page<AsileBank>> list(AsileBankQueryInput input);

	CommonResult<Object> save(AsileBank entity);

	CommonResult<Object> update(AsileBank entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<AsileBank> getById(long id);
	 void updateList(List<AsileBank> records);
}
