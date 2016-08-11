/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
package com.ink.route.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AsileBankQueryInput;
import com.ink.route.api.model.po.AsileBank;
import com.ink.route.api.model.query.AsileBankQuery;
import com.ink.route.api.service.platform.IAsileBankService;
import com.ink.route.manager.IAsileBankManager;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
@Service("asileBankService")
public class AsileBankServiceImpl implements IAsileBankService {
	
	@Autowired
	private IAsileBankManager asileBankManager;


	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			asileBankManager.removeById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<AsileBank> getById(long id) {
		AsileBank asileBank;
		try {
			asileBank = asileBankManager.getById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		AsileBank AsileBank = BeanCopyConverter.converterClass(asileBank, AsileBank.class);
		CommonResult<AsileBank> ret = new CommonResult<>();
		ret.setBussinessObj(AsileBank);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午6:48:51
	 * @param input
	 * @return  
	 */
	@Override
	public CommonResult<Page<AsileBank>> list(AsileBankQueryInput input) {
		CommonResult<Page<AsileBank>> ret = new CommonResult<>();
		AsileBankQuery query = BeanCopyConverter.converterClass(input, AsileBankQuery.class);
		Page<AsileBank> page ;
		try {
			page = asileBankManager.findPage(query);
		} catch (Exception e) {
			return new CommonResult<Page<AsileBank>>(-1, "database.query.exception", new Page<AsileBank>());
		}
		List<AsileBank> converterClass = (List<AsileBank>) BeanCopyConverter.converterClass(page.getResult(), AsileBank.class);
		Page<AsileBank> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午6:48:51
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> save(AsileBank entity) {
		AsileBank asileBank = BeanCopyConverter.converterClass(entity, AsileBank.class);
		try {
			asileBankManager.save(asileBank);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午6:48:51
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> update(AsileBank entity) {
		AsileBank asileBank = BeanCopyConverter.converterClass(entity, AsileBank.class);
		try {
			asileBankManager.updateNotNull(asileBank);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.updateNotNull.exception", null);
		}
		return  new CommonResult<>();
	}

	@Override
	public void updateList(List<AsileBank> records) {
		List<AsileBank> asileBanks=new ArrayList<AsileBank>();
		for(AsileBank entity:records){
			AsileBank asileBank = BeanCopyConverter.converterClass(entity, AsileBank.class);
			asileBanks.add(asileBank);
		}
		asileBankManager.updateList(asileBanks);
	}
	
	

}
