/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月8日 上午10:44:07
 */
package com.ink.route.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.BasicBankQueryInput;
import com.ink.route.api.model.po.BasicBank;
import com.ink.route.api.model.query.BasicBankQuery;
import com.ink.route.api.service.platform.IBasicBankService;
import com.ink.route.manager.IBasicBankManager;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月8日 上午10:44:07
 */
@Service("basicBankService")
public class BasicBankServiceImpl implements IBasicBankService{
	
	@Autowired
	private IBasicBankManager basicBankManager;

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:44:29
	 * @param input
	 * @return  
	 */
	@Override
	public CommonResult<Page<BasicBank>> list(BasicBankQueryInput input) {
		CommonResult<Page<BasicBank>> ret = new CommonResult<>();
		BasicBankQuery query = BeanCopyConverter.converterClass(input, BasicBankQuery.class);
		Page<BasicBank> page ;
		try {
			page = basicBankManager.findPage(query);
		} catch (Exception e) {
			return new CommonResult<Page<BasicBank>>(-1, "database.query.exception", new Page<BasicBank>());
		}
		List<BasicBank> converterClass = (List<BasicBank>) BeanCopyConverter.converterClass(page.getResult(), BasicBank.class);
		Page<BasicBank> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:44:29
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> save(BasicBank entity) {
		BasicBank basicBank = BeanCopyConverter.converterClass(entity, BasicBank.class);
		try {
			basicBankManager.save(basicBank);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:44:29
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> update(BasicBank entity) {
		BasicBank basicBank = BeanCopyConverter.converterClass(entity, BasicBank.class);
		try {
			basicBankManager.updateNotNull(basicBank);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:44:29
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			basicBankManager.removeById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:44:29
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<BasicBank> getById(long id) {
		BasicBank basicBank;
		try {
			basicBank = basicBankManager.getById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		BasicBank BasicBank = BeanCopyConverter.converterClass(basicBank, BasicBank.class);
		CommonResult<BasicBank> ret = new CommonResult<>();
		ret.setBussinessObj(BasicBank);
		return ret;
	}

	@Override
	public List<BasicBank> getAll() {
		List<BasicBank> basicBankList=basicBankManager.find(new BasicBankQuery());
		List<BasicBank> BasicBank=(List<BasicBank>)BeanCopyConverter.converterClass(basicBankList, BasicBank.class);
		return BasicBank;
	}

}
