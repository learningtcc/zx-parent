/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月8日 上午10:45:22
 */
package com.ink.trade.service.platform.impl.basic;

import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.basic.model.base.MchBankEntity;
import com.ink.trade.api.platform.basic.model.in.MchBankQueryInput;
import com.ink.trade.api.platform.basic.service.IMchBankService;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.core.manager.IMchBankManager;
import com.ink.trade.core.po.MchBank;
import com.ink.trade.core.query.MchBankQuery;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月8日 上午10:45:22
 */
@Service("mchBankService")
public class MchBankServiceImpl implements IMchBankService {

	YinkerLogger log = YinkerLogger.getLogger(MchBankServiceImpl.class);

	@Autowired
	private IMchBankManager MchBankManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:45:22
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<MchBankEntity>> list(MchBankQueryInput input) {
		CommonResult<Page<MchBankEntity>> ret = new CommonResult<>();
		MchBankQuery query = BeanCopyConverter.converterClass(input, MchBankQuery.class);
		Page<MchBank> page ;
		try {
			page = MchBankManager.findPage(query);
		} catch (Exception e) {
			log.error("MchBankServiceImpl.pageQuery.ex: "+e);
			return new CommonResult<Page<MchBankEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception", new Page<MchBankEntity>());
		}
		List<MchBankEntity> converterClass = (List<MchBankEntity>) BeanCopyConverter.converterClass(page.getResult(), MchBankEntity.class);
		Page<MchBankEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:45:22
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> save(MchBankEntity entity) {
		MchBank MchBank = BeanCopyConverter.converterClass(entity, MchBank.class);
		try {
			MchBankManager.save(MchBank);
		} catch (Exception e) {
			log.error("MchBankServiceImpl.insert.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.inset.exception: "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:45:22
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> update(MchBankEntity entity) {
		MchBank MchBank = BeanCopyConverter.converterClass(entity, MchBank.class);
		try {
			MchBankManager.updateNotNull(MchBank);
		} catch (Exception e) {
			log.error("MchBankServiceImpl.updateNotNull.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception: "+e.getMessage(), null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:45:22
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			MchBankManager.removeById(id);
		} catch (Exception e) {
			log.error("MchBankServiceImpl.updateNotNull.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception: "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:45:22
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<MchBankEntity> getById(long id) {
		MchBank MchBank;
		try {
			MchBank = MchBankManager.getById(id);
		} catch (Exception e) {
			log.error("MchBankServiceImpl.getById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.getById.exception: "+e.getMessage(), null);
		}
		MchBankEntity MchBankEntity = BeanCopyConverter.converterClass(MchBank, MchBankEntity.class);
		CommonResult<MchBankEntity> ret = new CommonResult<>();
		ret.setBussinessObj(MchBankEntity);
		return ret;
	}

}
