/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:33:17
 */
package com.ink.trade.api.platform.asile.service;

import java.util.List;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.asile.model.base.AsileInfoEntity;
import com.ink.trade.api.platform.asile.model.in.AsileInfoQueryInput;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:33:17
 */
public interface IAsileInfoService {
	
	
	CommonResult<Page<AsileInfoEntity>> list(AsileInfoQueryInput input);
	
	CommonResult<Object> save(AsileInfoEntity entity);
	
	CommonResult<Object> update(AsileInfoEntity entity);
	
	CommonResult<Object> deleteById(long id);
	
	CommonResult<AsileInfoEntity> getById(long id);
	List<AsileInfoEntity>  getAll();
}
