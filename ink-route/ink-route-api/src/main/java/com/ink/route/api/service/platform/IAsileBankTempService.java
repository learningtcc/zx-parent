/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:32:38
 */
package com.ink.route.api.service.platform;

import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AsileBankTempQueryInput;
import com.ink.route.api.model.po.AsileBankTemp;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:32:38
 */
public interface IAsileBankTempService {

	CommonResult<Page<AsileBankTemp>> list(AsileBankTempQueryInput input);

	CommonResult<Object> save(AsileBankTemp entity);

	CommonResult<Object> update(AsileBankTemp entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<AsileBankTemp> getById(long id);

}
