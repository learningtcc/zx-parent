/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:04:17
 */
package com.ink.trade.service.platform.impl.asile;

import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAuthChannelPriorityManager;
import com.ink.asile.core.po.AuthChannelPriority;
import com.ink.asile.core.query.AuthChannelPriorityQuery;
import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.asile.model.base.AuthChannelPriorityEntity;
import com.ink.trade.api.platform.asile.model.in.AuthChannelPriorityQueryInput;
import com.ink.trade.api.platform.asile.service.IAuthChannelPriorityService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:04:17
 */
@Service("authChannelPriorityService")
public class AuthChannelPriorityServiceImpl implements IAuthChannelPriorityService {

	YinkerLogger log = YinkerLogger.getLogger(AuthChannelPriorityServiceImpl.class);

	@Autowired
	private IAuthChannelPriorityManager authChannelPriorityManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:17
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<AuthChannelPriorityEntity>> list(AuthChannelPriorityQueryInput input) {
		CommonResult<Page<AuthChannelPriorityEntity>> ret = new CommonResult<>();
		AuthChannelPriorityQuery query = BeanCopyConverter.converterClass(input, AuthChannelPriorityQuery.class);
		Page<AuthChannelPriority> page ;
		try {
			page = authChannelPriorityManager.findPage(query);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.pageQuery.ex: "+e);
			return new CommonResult<Page<AuthChannelPriorityEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception: "+e.getMessage(), new Page<AuthChannelPriorityEntity>());
		}
		List<AuthChannelPriorityEntity> converterClass = (List<AuthChannelPriorityEntity>) BeanCopyConverter.converterClass(page.getResult(), AuthChannelPriorityEntity.class);
		Page<AuthChannelPriorityEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:17
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> save(AuthChannelPriorityEntity entity) {
		AuthChannelPriority model = BeanCopyConverter.converterClass(entity, AuthChannelPriority.class);
		try {
			authChannelPriorityManager.save(model);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.save.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.save.exception: "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:17
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> update(AuthChannelPriorityEntity entity) {
		AuthChannelPriority model = BeanCopyConverter.converterClass(entity, AuthChannelPriority.class);
		try {
			authChannelPriorityManager.updateNotNull(model);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.updateNotNull.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception: "+e.getMessage(), null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description TODO
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
			log.error("AsileResCodeServiceImpl.removeById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.removeById.exception: "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:17
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<AuthChannelPriorityEntity> getById(long id) {
		AuthChannelPriority model;
		try {
			model = authChannelPriorityManager.getById(id);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.getById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.getById.exception: "+e.getMessage(), null);
		}
		AuthChannelPriorityEntity entity = BeanCopyConverter.converterClass(model, AuthChannelPriorityEntity.class);
		CommonResult<AuthChannelPriorityEntity> ret = new CommonResult<>();
		ret.setBussinessObj(entity);
		return ret;
	}

}
