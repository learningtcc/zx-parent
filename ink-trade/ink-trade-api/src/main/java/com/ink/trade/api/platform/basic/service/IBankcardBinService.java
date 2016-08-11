/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:35:03
 */
package com.ink.trade.api.platform.basic.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.basic.model.base.BankcardBinEntity;
import com.ink.trade.api.platform.basic.model.in.BankcardBinQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:35:03
 */
public interface IBankcardBinService {
	
	CommonResult<Page<BankcardBinEntity>> list(BankcardBinQueryInput input);

	CommonResult<Object> save(BankcardBinEntity entity);

	CommonResult<Object> update(BankcardBinEntity entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<BankcardBinEntity> getById(long id);

}
