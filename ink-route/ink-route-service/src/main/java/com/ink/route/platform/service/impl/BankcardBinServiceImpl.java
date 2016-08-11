/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月8日 上午10:45:22
 */
package com.ink.route.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.BankcardBinQueryInput;
import com.ink.route.api.model.po.BankcardBin;
import com.ink.route.api.model.query.BankcardBinQuery;
import com.ink.route.api.service.platform.IBankcardBinService;
import com.ink.route.manager.IBankcardBinManager;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月8日 上午10:45:22
 */
@Service("bankcardBinService")
public class BankcardBinServiceImpl implements IBankcardBinService {
	
	@Autowired
	private IBankcardBinManager bankcardBinManager;

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:45:22
	 * @param input
	 * @return  
	 */
	@Override
	public CommonResult<Page<BankcardBin>> list(BankcardBinQueryInput input) {
		CommonResult<Page<BankcardBin>> ret = new CommonResult<>();
		BankcardBinQuery query = BeanCopyConverter.converterClass(input, BankcardBinQuery.class);
		Page<BankcardBin> page ;
		try {
			page = bankcardBinManager.findPage(query);
		} catch (Exception e) {
			return new CommonResult<Page<BankcardBin>>(-1, "database.query.exception", new Page<BankcardBin>());
		}
		List<BankcardBin> converterClass = (List<BankcardBin>) BeanCopyConverter.converterClass(page.getResult(), BankcardBin.class);
		Page<BankcardBin> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:45:22
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> save(BankcardBin entity) {
		BankcardBin bankcardBin = BeanCopyConverter.converterClass(entity, BankcardBin.class);
		try {
			bankcardBinManager.updateNotNull(bankcardBin);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:45:22
	 * @param entity
	 * @return  
	 */
	@Override
	public CommonResult<Object> update(BankcardBin entity) {
		BankcardBin bankcardBin = BeanCopyConverter.converterClass(entity, BankcardBin.class);
		try {
			bankcardBinManager.update(bankcardBin);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:45:22
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			bankcardBinManager.removeById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:45:22
	 * @param id
	 * @return  
	 */
	@Override
	public CommonResult<BankcardBin> getById(long id) {
		BankcardBin bankcardBin;
		try {
			bankcardBin = bankcardBinManager.getById(id);
		} catch (Exception e) {
			return new CommonResult<>(-1, "database.query.exception", null);
		}
		BankcardBin BankcardBin = BeanCopyConverter.converterClass(bankcardBin, BankcardBin.class);
		CommonResult<BankcardBin> ret = new CommonResult<>();
		ret.setBussinessObj(BankcardBin);
		return ret;
	}

}
