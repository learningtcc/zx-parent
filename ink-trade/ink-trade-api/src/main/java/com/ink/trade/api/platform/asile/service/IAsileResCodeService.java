/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:34:12
 */
package com.ink.trade.api.platform.asile.service;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.asile.model.base.AsileResCodeEntity;
import com.ink.trade.api.platform.asile.model.in.AsileResCodeQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:34:12
 */
public interface IAsileResCodeService {

	CommonResult<Page<AsileResCodeEntity>> list(AsileResCodeQueryInput input);

	CommonResult<Object> save(AsileResCodeEntity entity);

	CommonResult<Object> update(AsileResCodeEntity entity);

	CommonResult<Object> deleteById(long id);

	CommonResult<AsileResCodeEntity> getById(long id);

}
