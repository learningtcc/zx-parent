/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月8日 上午10:44:07
 */
package com.ink.trade.service.platform.impl.basic;

import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.basic.core.manager.IBasicBankManager;
import com.ink.basic.core.po.BasicBank;
import com.ink.basic.core.query.BasicBankQuery;
import com.ink.trade.api.platform.basic.model.base.BasicBankEntity;
import com.ink.trade.api.platform.basic.model.in.BasicBankQueryInput;
import com.ink.trade.api.platform.basic.service.IBasicBankService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月8日 上午10:44:07
 */
@Service("basicBankService")
public class BasicBankServiceImpl implements IBasicBankService{

	YinkerLogger log = YinkerLogger.getLogger(BasicBankServiceImpl.class);

	@Autowired
	private IBasicBankManager basicBankManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:44:29
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<BasicBankEntity>> list(BasicBankQueryInput input) {
		CommonResult<Page<BasicBankEntity>> ret = new CommonResult<>();
		BasicBankQuery query = BeanCopyConverter.converterClass(input, BasicBankQuery.class);
		Page<BasicBank> page ;
		try {
			page = basicBankManager.findPage(query);
		} catch (Exception e) {
			log.error("BasicBankServiceImpl.pageQuery.ex: "+e);
			return new CommonResult<Page<BasicBankEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception: "+e.getMessage(), new Page<BasicBankEntity>());
		}
		List<BasicBankEntity> converterClass = (List<BasicBankEntity>) BeanCopyConverter.converterClass(page.getResult(), BasicBankEntity.class);
		Page<BasicBankEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:44:29
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> save(BasicBankEntity entity) {
		BasicBank basicBank = BeanCopyConverter.converterClass(entity, BasicBank.class);
		try {
			basicBankManager.save(basicBank);
		} catch (Exception e) {
			log.error("BasicBankServiceImpl.insert.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.insert.exception: "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:44:29
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> update(BasicBankEntity entity) {
		BasicBank basicBank = BeanCopyConverter.converterClass(entity, BasicBank.class);
		try {
			basicBankManager.updateNotNull(basicBank);
		} catch (Exception e) {
			log.error("BasicBankServiceImpl.updateNotNull.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception: "+e.getMessage(), null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description TODO
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
			log.error("BasicBankServiceImpl.deleteById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.deleteById.exception: "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:44:29
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<BasicBankEntity> getById(long id) {
		BasicBank basicBank;
		try {
			basicBank = basicBankManager.getById(id);
		} catch (Exception e) {
			log.error("BasicBankServiceImpl.getById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.getById.exception:"+e.getMessage(), null);
		}
		BasicBankEntity basicBankEntity = BeanCopyConverter.converterClass(basicBank, BasicBankEntity.class);
		CommonResult<BasicBankEntity> ret = new CommonResult<>();
		ret.setBussinessObj(basicBankEntity);
		return ret;
	}

	@Override
	public List<BasicBankEntity> getAll() {
		List<BasicBank> basicBankList=basicBankManager.find(new BasicBankQuery());
		List<BasicBankEntity> basicBankEntity=(List<BasicBankEntity>)BeanCopyConverter.converterClass(basicBankList, BasicBankEntity.class);
		return basicBankEntity;
	}

}
