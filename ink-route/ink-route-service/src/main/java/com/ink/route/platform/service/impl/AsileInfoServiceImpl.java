/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
package com.ink.route.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AsileInfoQueryInput;
import com.ink.route.api.model.po.AsileInfo;
import com.ink.route.api.model.query.AsileInfoQuery;
import com.ink.route.api.service.platform.IAsileInfoService;
import com.ink.route.manager.IAsileInfoManager;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
@Service("asileInfoService")
public class AsileInfoServiceImpl implements IAsileInfoService {
	
	@Autowired
	private IAsileInfoManager asileInfoManager;

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param input
	 * @return  
	 */
	@Override
	public CommonResult<Page<AsileInfo>> list(AsileInfoQueryInput input) {
		CommonResult<Page<AsileInfo>> ret = new CommonResult<>();
		Page<AsileInfo> page ;
		try {
			AsileInfoQuery query = BeanCopyConverter.converterClass(input, AsileInfoQuery.class);
			page = asileInfoManager.findPage(query);
		} catch (Exception e) {
			return new CommonResult<Page<AsileInfo>>(-1, "database.query.exception", new Page<AsileInfo>());
		}
		List<AsileInfo> converterClass = (List<AsileInfo>) BeanCopyConverter.converterClass(page.getResult(), AsileInfo.class);
		Page<AsileInfo> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> save(AsileInfo entity) {
		AsileInfo asileInfo = BeanCopyConverter.converterClass(entity, AsileInfo.class);
		try {
			asileInfoManager.save(asileInfo);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> update(AsileInfo entity) {
		AsileInfo asileInfo = BeanCopyConverter.converterClass(entity, AsileInfo.class);
		try {
			asileInfoManager.updateNotNull(asileInfo);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			asileInfoManager.removeById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<AsileInfo> getById(long id) {
		AsileInfo asileInfo;
		try {
			asileInfo = asileInfoManager.getById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		AsileInfo AsileInfo = BeanCopyConverter.converterClass(asileInfo, AsileInfo.class);
		CommonResult<AsileInfo> ret = new CommonResult<>();
		ret.setBussinessObj(AsileInfo);
		return ret;
	}

	@Override
	public List<AsileInfo> getAll() {
		AsileInfoQuery query=new AsileInfoQuery();
		query.setAsileStatus("1");
		return (List<AsileInfo>)BeanCopyConverter.converterClass(asileInfoManager.find(query),AsileInfo.class);
		
	}
	
	

}
