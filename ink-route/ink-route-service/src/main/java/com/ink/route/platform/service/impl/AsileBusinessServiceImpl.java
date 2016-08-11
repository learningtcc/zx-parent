/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:02:24
 */
package com.ink.route.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AsileBusinessQueryInput;
import com.ink.route.api.model.po.AsileBusiness;
import com.ink.route.api.model.query.AsileBusinessQuery;
import com.ink.route.api.service.platform.IAsileBusinessService;
import com.ink.route.manager.IAsileBusinessManager;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:02:24
 */
@Service("asileBusinessService")
public class AsileBusinessServiceImpl implements IAsileBusinessService {
	
	@Autowired
	private IAsileBusinessManager asileBusinessManager;

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:02:24
	 * @param input
	 * @return  
	 */
	@Override
	public CommonResult<Page<AsileBusiness>> list(AsileBusinessQueryInput input) {
		CommonResult<Page<AsileBusiness>> ret = new CommonResult<>();
		AsileBusinessQuery query = BeanCopyConverter.converterClass(input, AsileBusinessQuery.class);
		Page<AsileBusiness> page ;
		try {
			page = asileBusinessManager.findPage(query);
		} catch (Exception e) {
			return new CommonResult<Page<AsileBusiness>>(-1, "database.query.exception", new Page<AsileBusiness>());
		}
		List<AsileBusiness> converterClass = (List<AsileBusiness>) BeanCopyConverter.converterClass(page.getResult(), AsileBusiness.class);
		Page<AsileBusiness> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:02:24
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> save(AsileBusiness entity) {
		AsileBusiness model = BeanCopyConverter.converterClass(entity, AsileBusiness.class);
		try {
			asileBusinessManager.save(model);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:02:24
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> update(AsileBusiness entity) {
		AsileBusiness model = BeanCopyConverter.converterClass(entity, AsileBusiness.class);
		try {
			asileBusinessManager.updateNotNull(model);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:02:24
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			asileBusinessManager.removeById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:02:24
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<AsileBusiness> getById(long id) {
		AsileBusiness model;
		try {
			model = asileBusinessManager.getById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		AsileBusiness entity = BeanCopyConverter.converterClass(model, AsileBusiness.class);
		CommonResult<AsileBusiness> ret = new CommonResult<>();
		ret.setBussinessObj(entity);
		return ret;
	}

}
