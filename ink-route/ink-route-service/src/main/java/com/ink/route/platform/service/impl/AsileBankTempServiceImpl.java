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
import com.ink.route.api.model.in.AsileBankTempQueryInput;
import com.ink.route.api.model.po.AsileBankTemp;
import com.ink.route.api.model.query.AsileBankTempQuery;
import com.ink.route.api.service.platform.IAsileBankTempService;
import com.ink.route.manager.IAsileBankTempManager;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
@Service("asileBankTempService")
public class AsileBankTempServiceImpl implements IAsileBankTempService {
	
	@Autowired
	private IAsileBankTempManager asileBankTempManager;

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param input
	 * @return  
	 */
	@Override
	public CommonResult<Page<AsileBankTemp>> list(AsileBankTempQueryInput input) {
		CommonResult<Page<AsileBankTemp>> ret = new CommonResult<>();
		AsileBankTempQuery query = BeanCopyConverter.converterClass(input, AsileBankTempQuery.class);
		Page<AsileBankTemp> page ;
		try {
			page = asileBankTempManager.findPage(query);
		} catch (Exception e) {
			return new CommonResult<Page<AsileBankTemp>>(-1, "database.query.exception", new Page<AsileBankTemp>());
		}
		List<AsileBankTemp> converterClass = (List<AsileBankTemp>) BeanCopyConverter.converterClass(page.getResult(), AsileBankTemp.class);
		Page<AsileBankTemp> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);	
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
	public CommonResult<Object> save(AsileBankTemp entity) {
		AsileBankTemp asileBankTemp = BeanCopyConverter.converterClass(entity, AsileBankTemp.class);
		try {
			asileBankTempManager.save(asileBankTemp);
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
	public CommonResult<Object> update(AsileBankTemp entity) {
		AsileBankTemp asileBankTemp = BeanCopyConverter.converterClass(entity, AsileBankTemp.class);
		try {
			asileBankTempManager.updateNotNull(asileBankTemp);
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
			asileBankTempManager.removeById(id);
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
	public CommonResult<AsileBankTemp> getById(long id) {
		AsileBankTemp asileBankTemp;
		try {
			asileBankTemp = asileBankTempManager.getById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		AsileBankTemp AsileBankTemp = BeanCopyConverter.converterClass(asileBankTemp, AsileBankTemp.class);
		CommonResult<AsileBankTemp> ret = new CommonResult<>();
		ret.setBussinessObj(AsileBankTemp);
		return ret;
	}
	
	

}
