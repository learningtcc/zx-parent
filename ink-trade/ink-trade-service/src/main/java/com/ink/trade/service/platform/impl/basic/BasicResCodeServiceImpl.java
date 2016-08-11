/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月8日 上午10:46:45
 */
package com.ink.trade.service.platform.impl.basic;

import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.basic.core.manager.IBasicResCodeManager;
import com.ink.basic.core.po.BasicResCode;
import com.ink.basic.core.query.BasicResCodeQuery;
import com.ink.trade.api.platform.basic.model.base.BasicResCodeEntity;
import com.ink.trade.api.platform.basic.model.in.BasicResCodeQueryInput;
import com.ink.trade.api.platform.basic.service.IBasicResCodeService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月8日 上午10:46:45
 */
@Service("basicResCodeService")
public class BasicResCodeServiceImpl implements IBasicResCodeService {

	YinkerLogger log = YinkerLogger.getLogger(BasicResCodeServiceImpl.class);

	@Autowired
	private IBasicResCodeManager basicResCodeManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:46:45
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<BasicResCodeEntity>> list(BasicResCodeQueryInput input) {
		CommonResult<Page<BasicResCodeEntity>> ret = new CommonResult<>();
		BasicResCodeQuery query = BeanCopyConverter.converterClass(input, BasicResCodeQuery.class);
		Page<BasicResCode> page ;
		try {
			page = basicResCodeManager.findPage(query);
		} catch (Exception e) {
			log.error("BasicResCodeServiceImpl.pageQuery.ex: "+e);
			return new CommonResult<Page<BasicResCodeEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception: "+e.getMessage(), new Page<BasicResCodeEntity>());
		}
		List<BasicResCodeEntity> converterClass = (List<BasicResCodeEntity>) BeanCopyConverter.converterClass(page.getResult(), BasicResCodeEntity.class);
		Page<BasicResCodeEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:46:45
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> save(BasicResCodeEntity entity) {
		BasicResCode basicResCode = BeanCopyConverter.converterClass(entity, BasicResCode.class);
		try {
			basicResCodeManager.updateNotNull(basicResCode);
		} catch (Exception e) {
			log.error("BasicResCodeServiceImpl.insert.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.insert.exception: "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:46:45
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> update(BasicResCodeEntity entity) {
		BasicResCode basicResCode = BeanCopyConverter.converterClass(entity, BasicResCode.class);
		try {
			basicResCodeManager.updateNotNull(basicResCode);
		} catch (Exception e) {
			log.error("BasicResCodeServiceImpl.updateNotNull.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception: "+e.getMessage(), null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:46:45
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			basicResCodeManager.removeById(id);
		} catch (Exception e) {
			log.error("BasicResCodeServiceImpl.deleteById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.deleteById.exception: "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:46:45
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<BasicResCodeEntity> getById(long id) {
		BasicResCode basicResCode;
		try {
			basicResCode = basicResCodeManager.getById(id);
		} catch (Exception e) {
			log.error("BasicResCodeServiceImpl.getById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.getById.exception: "+e.getMessage(), null);
		}
		BasicResCodeEntity basicResCodeEntity = BeanCopyConverter.converterClass(basicResCode, BasicResCodeEntity.class);
		CommonResult<BasicResCodeEntity> ret = new CommonResult<>();
		ret.setBussinessObj(basicResCodeEntity);
		return ret;
	}

}
