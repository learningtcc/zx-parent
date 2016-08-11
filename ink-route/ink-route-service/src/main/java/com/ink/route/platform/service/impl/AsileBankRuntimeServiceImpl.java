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
import com.ink.route.api.model.in.AsileBankRuntimeQueryInput;
import com.ink.route.api.model.po.AsileBankRuntime;
import com.ink.route.api.model.query.AsileBankRuntimeQuery;
import com.ink.route.api.service.platform.IAsileBankRuntimeService;
import com.ink.route.manager.IAsileBankRuntimeManager;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
@Service("asileBankRuntimeService")
public class AsileBankRuntimeServiceImpl implements IAsileBankRuntimeService {
	
	@Autowired
	private IAsileBankRuntimeManager asileBankRuntimeManager;

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param input
	 * @return  
	 */
	@Override
	public CommonResult<Page<AsileBankRuntime>> list(AsileBankRuntimeQueryInput input) {
		CommonResult<Page<AsileBankRuntime>> ret = new CommonResult<>();
		AsileBankRuntimeQuery query = BeanCopyConverter.converterClass(input, AsileBankRuntimeQuery.class);
		Page<AsileBankRuntime> page ;
		try {
			page = asileBankRuntimeManager.findPage(query);
		} catch (Exception e) {
			return new CommonResult<Page<AsileBankRuntime>>(-1, "database.query.exception", new Page<AsileBankRuntime>());
		}
		List<AsileBankRuntime> converterClass = (List<AsileBankRuntime>) BeanCopyConverter.converterClass(page.getResult(), AsileBankRuntime.class);
		Page<AsileBankRuntime> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
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
	public CommonResult<Object> save(AsileBankRuntime entity) {
		AsileBankRuntime asileBankRuntime = BeanCopyConverter.converterClass(entity, AsileBankRuntime.class);
		try {
			asileBankRuntimeManager.save(asileBankRuntime);
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
	public CommonResult<Object> update(AsileBankRuntime entity) {
		AsileBankRuntime asileBankRuntime = BeanCopyConverter.converterClass(entity, AsileBankRuntime.class);
		try {
			asileBankRuntimeManager.updateNotNull(asileBankRuntime);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.updateNotNull.exception"+e.getMessage(), null);
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
			asileBankRuntimeManager.removeById(id);
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
	public CommonResult<AsileBankRuntime> getById(long id) {
		AsileBankRuntime asileBankRuntime;
		try {
			asileBankRuntime = asileBankRuntimeManager.getById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		AsileBankRuntime AsileBankRuntime = BeanCopyConverter.converterClass(asileBankRuntime, AsileBankRuntime.class);
		CommonResult<AsileBankRuntime> ret = new CommonResult<>();
		ret.setBussinessObj(AsileBankRuntime);
		return ret;
	}


}
