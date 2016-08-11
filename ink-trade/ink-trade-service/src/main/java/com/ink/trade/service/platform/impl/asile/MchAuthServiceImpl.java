/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:04:54
 */
package com.ink.trade.service.platform.impl.asile;

import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.asile.model.base.MchAuthEntity;
import com.ink.trade.api.platform.asile.model.in.MchAuthQueryInput;
import com.ink.trade.api.platform.asile.service.IMchAuthService;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.core.manager.IMchAuthManager;
import com.ink.trade.core.po.MchAuth;
import com.ink.trade.core.query.MchAuthQuery;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:04:54
 */
@Service("mchAuthService")
public class MchAuthServiceImpl implements IMchAuthService {

	YinkerLogger log = YinkerLogger.getLogger(MchAuthServiceImpl.class);

	@Autowired
	private IMchAuthManager IMchAuthManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:54
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<MchAuthEntity>> list(MchAuthQueryInput input) {
		CommonResult<Page<MchAuthEntity>> ret = new CommonResult<>();
		MchAuthQuery query = BeanCopyConverter.converterClass(input, MchAuthQuery.class);
		Page<MchAuth> page ;
		try {
			page = IMchAuthManager.findPage(query);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.pageQuery.ex: "+e);
			return new CommonResult<Page<MchAuthEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception: "+e.getMessage(), new Page<MchAuthEntity>());
		}
		List<MchAuthEntity> converterClass = (List<MchAuthEntity>) BeanCopyConverter.converterClass(page.getResult(), MchAuthEntity.class);
		Page<MchAuthEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:54
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> save(MchAuthEntity entity) {
		MchAuth model = BeanCopyConverter.converterClass(entity, MchAuth.class);
		try {
			IMchAuthManager.save(model);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.save.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.save.exception "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:54
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> update(MchAuthEntity entity) {
		MchAuth model = BeanCopyConverter.converterClass(entity, MchAuth.class);
		try {
			IMchAuthManager.updateNotNull(model);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.updateNotNull.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception "+e.getMessage(), null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:54
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			IMchAuthManager.removeById(id);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.deleteById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.deleteById.exception "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:04:54
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<MchAuthEntity> getById(long id) {
		MchAuth model;
		try {
			model = IMchAuthManager.getById(id);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.getById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.getById.exception "+e.getMessage(), null);
		}
		MchAuthEntity entity = BeanCopyConverter.converterClass(model, MchAuthEntity.class);
		CommonResult<MchAuthEntity> ret = new CommonResult<>();
		ret.setBussinessObj(entity);
		return ret;
	}

}
