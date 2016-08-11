/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:33:17
 */
package com.ink.route.api.service.platform;

import java.util.List;

import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AsileInfoQueryInput;
import com.ink.route.api.model.po.AsileInfo;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:33:17
 */
public interface IAsileInfoService {
	
	
	CommonResult<Page<AsileInfo>> list(AsileInfoQueryInput input);
	
	CommonResult<Object> save(AsileInfo entity);
	
	CommonResult<Object> update(AsileInfo entity);
	
	CommonResult<Object> deleteById(long id);
	
	CommonResult<AsileInfo> getById(long id);
	List<AsileInfo>  getAll();
}
