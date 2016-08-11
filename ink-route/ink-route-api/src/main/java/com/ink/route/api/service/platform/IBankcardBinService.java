/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:35:03
 */
package com.ink.route.api.service.platform;

import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.BankcardBinQueryInput;
import com.ink.route.api.model.po.BankcardBin;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:35:03
 */
public interface IBankcardBinService {
	
	CommonResult<Page<BankcardBin>> list(BankcardBinQueryInput input);

	CommonResult<Object> save(BankcardBin entity);

	CommonResult<Object> update(BankcardBin entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<BankcardBin> getById(long id);

}
