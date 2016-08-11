/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:02:24
 */
package com.ink.trade.service.platform.impl.asile;

import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileBusinessManager;
import com.ink.asile.core.po.AsileBusiness;
import com.ink.asile.core.query.AsileBusinessQuery;
import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.asile.model.base.AsileBusinessEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBusinessQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileBusinessService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:02:24
 */
@Service("asileBusinessService")
public class AsileBusinessServiceImpl implements IAsileBusinessService {

	YinkerLogger log = YinkerLogger.getLogger(AsileBusinessServiceImpl.class);

	@Autowired
	private IAsileBusinessManager asileBusinessManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:02:24
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<AsileBusinessEntity>> list(AsileBusinessQueryInput input) {
		CommonResult<Page<AsileBusinessEntity>> ret = new CommonResult<>();
		AsileBusinessQuery query = BeanCopyConverter.converterClass(input, AsileBusinessQuery.class);
		Page<AsileBusiness> page ;
		try {
			page = asileBusinessManager.findPage(query);
		} catch (Exception e) {
			log.error("AsileBusinessServiceImpl.pageQuery.ex: "+e);
			return new CommonResult<Page<AsileBusinessEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception: "+e.getMessage(), new Page<AsileBusinessEntity>());
		}
		List<AsileBusinessEntity> converterClass = (List<AsileBusinessEntity>) BeanCopyConverter.converterClass(page.getResult(), AsileBusinessEntity.class);
		Page<AsileBusinessEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:02:24
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> save(AsileBusinessEntity entity) {
		AsileBusiness model = BeanCopyConverter.converterClass(entity, AsileBusiness.class);
		try {
			asileBusinessManager.save(model);
		} catch (Exception e) {
			log.error("AsileBusinessServiceImpl.insert.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.insert.exception :"+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:02:24
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> update(AsileBusinessEntity entity) {
		AsileBusiness model = BeanCopyConverter.converterClass(entity, AsileBusiness.class);
		try {
			asileBusinessManager.updateNotNull(model);
		} catch (Exception e) {
			log.error("AsileBusinessServiceImpl.updateNotNull.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception:"+e.getMessage(), null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:02:24
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			asileBusinessManager.removeById(id);
		} catch (Exception e) {
			log.error("AsileBusinessServiceImpl.deleteById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.deleteById.exception:"+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:02:24
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<AsileBusinessEntity> getById(long id) {
		AsileBusiness model;
		try {
			model = asileBusinessManager.getById(id);
		} catch (Exception e) {
			log.error("AsileBusinessServiceImpl.deleteById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.deleteById.exception"+e.getMessage(), null);
		}
		AsileBusinessEntity entity = BeanCopyConverter.converterClass(model, AsileBusinessEntity.class);
		CommonResult<AsileBusinessEntity> ret = new CommonResult<>();
		ret.setBussinessObj(entity);
		return ret;
	}

}
