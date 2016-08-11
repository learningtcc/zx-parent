/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:04:17
 */
package com.ink.route.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AuthChannelPriorityQueryInput;
import com.ink.route.api.model.po.AuthChannelPriority;
import com.ink.route.api.model.query.AuthChannelPriorityQuery;
import com.ink.route.api.service.platform.IAuthChannelPriorityService;
import com.ink.route.manager.IAuthChannelPriorityManager;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:04:17
 */
@Service("authChannelPriorityService")
public class AuthChannelPriorityServiceImpl implements IAuthChannelPriorityService {
	
	@Autowired
	private IAuthChannelPriorityManager authChannelPriorityManager;

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:17
	 * @param input
	 * @return  
	 */
	@Override
	public CommonResult<Page<AuthChannelPriority>> list(AuthChannelPriorityQueryInput input) {
		CommonResult<Page<AuthChannelPriority>> ret = new CommonResult<>();
		AuthChannelPriorityQuery query = BeanCopyConverter.converterClass(input, AuthChannelPriorityQuery.class);
		Page<AuthChannelPriority> page ;
		try {
			page = authChannelPriorityManager.findPage(query);
		} catch (Exception e) {
			return new CommonResult<Page<AuthChannelPriority>>(-1, "database.query.exception", new Page<AuthChannelPriority>());
		}
		List<AuthChannelPriority> converterClass = (List<AuthChannelPriority>) BeanCopyConverter.converterClass(page.getResult(), AuthChannelPriority.class);
		Page<AuthChannelPriority> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:17
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> save(AuthChannelPriority entity) {
		AuthChannelPriority model = BeanCopyConverter.converterClass(entity, AuthChannelPriority.class);
		try {
			authChannelPriorityManager.save(model);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:17
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> update(AuthChannelPriority entity) {
		AuthChannelPriority model = BeanCopyConverter.converterClass(entity, AuthChannelPriority.class);
		try {
			authChannelPriorityManager.updateNotNull(model);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:17
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			authChannelPriorityManager.removeById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:17
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<AuthChannelPriority> getById(long id) {
		AuthChannelPriority model;
		try {
			model = authChannelPriorityManager.getById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		AuthChannelPriority entity = BeanCopyConverter.converterClass(model, AuthChannelPriority.class);
		CommonResult<AuthChannelPriority> ret = new CommonResult<>();
		ret.setBussinessObj(entity);
		return ret;
	}

}
