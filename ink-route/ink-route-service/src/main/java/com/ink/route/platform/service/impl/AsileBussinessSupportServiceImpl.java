/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:03:19
 */
package com.ink.route.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AsileBusinessSupportQueryInput;
import com.ink.route.api.model.po.AsileBusinessSupport;
import com.ink.route.api.model.query.AsileBusinessSupportQuery;
import com.ink.route.api.service.platform.IAsileBusinessSupportService;
import com.ink.route.manager.IAsileBusinessSupportManager;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:03:19
 */
@Service("asileBusinessSupportService")
public class AsileBussinessSupportServiceImpl implements IAsileBusinessSupportService {
	
	@Autowired
	private IAsileBusinessSupportManager asileBusinessSupportManager;

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:03:19
	 * @param input
	 * @return  
	 */
	@Override
	public CommonResult<Page<AsileBusinessSupport>> list(AsileBusinessSupportQueryInput input) {
		CommonResult<Page<AsileBusinessSupport>> ret = new CommonResult<>();
		AsileBusinessSupportQuery query = BeanCopyConverter.converterClass(input, AsileBusinessSupportQuery.class);
		Page<AsileBusinessSupport> page ;
		try {
			page = asileBusinessSupportManager.findPage(query);
		} catch (Exception e) {
			return new CommonResult<Page<AsileBusinessSupport>>(-1, "database.query.exception", new Page<AsileBusinessSupport>());
		}
		List<AsileBusinessSupport> converterClass = (List<AsileBusinessSupport>) BeanCopyConverter.converterClass(page.getResult(), AsileBusinessSupport.class);
		Page<AsileBusinessSupport> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:03:19
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> save(AsileBusinessSupport entity) {
		AsileBusinessSupport model = BeanCopyConverter.converterClass(entity, AsileBusinessSupport.class);
		try {
			asileBusinessSupportManager.save(model);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:03:19
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> update(AsileBusinessSupport entity) {
		AsileBusinessSupport model = BeanCopyConverter.converterClass(entity, AsileBusinessSupport.class);
		try {
			asileBusinessSupportManager.updateNotNull(model);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:03:19
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			asileBusinessSupportManager.removeById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:03:19
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<AsileBusinessSupport> getById(long id) {
		AsileBusinessSupport model;
		try {
			model = asileBusinessSupportManager.getById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		AsileBusinessSupport entity = BeanCopyConverter.converterClass(model, AsileBusinessSupport.class);
		CommonResult<AsileBusinessSupport> ret = new CommonResult<>();
		ret.setBussinessObj(entity);
		return ret;
	}

}
