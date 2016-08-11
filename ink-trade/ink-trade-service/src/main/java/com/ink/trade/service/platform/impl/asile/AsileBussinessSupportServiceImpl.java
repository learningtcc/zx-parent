/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:03:19
 */
package com.ink.trade.service.platform.impl.asile;

import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileBusinessSupportManager;
import com.ink.asile.core.po.AsileBusinessSupport;
import com.ink.asile.core.query.AsileBusinessSupportQuery;
import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.asile.model.base.AsileBusinessSupportEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBusinessSupportQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileBusinessSupportService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月11日 下午3:03:19
 */
@Service("asileBusinessSupportService")
public class AsileBussinessSupportServiceImpl implements IAsileBusinessSupportService {

	YinkerLogger log = YinkerLogger.getLogger(AsileBussinessSupportServiceImpl.class);

	@Autowired
	private IAsileBusinessSupportManager asileBusinessSupportManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:03:19
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<AsileBusinessSupportEntity>> list(AsileBusinessSupportQueryInput input) {
		CommonResult<Page<AsileBusinessSupportEntity>> ret = new CommonResult<>();
		AsileBusinessSupportQuery query = BeanCopyConverter.converterClass(input, AsileBusinessSupportQuery.class);
		Page<AsileBusinessSupport> page ;
		try {
			page = asileBusinessSupportManager.findPage(query);
		} catch (Exception e) {
			log.error("AsileBussinessSupportServiceImpl.pageQuery.ex :"+e);
			return new CommonResult<Page<AsileBusinessSupportEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.query.exception: "+e.getMessage(), new Page<AsileBusinessSupportEntity>());
		}
		List<AsileBusinessSupportEntity> converterClass = (List<AsileBusinessSupportEntity>) BeanCopyConverter.converterClass(page.getResult(), AsileBusinessSupportEntity.class);
		Page<AsileBusinessSupportEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:03:19
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> save(AsileBusinessSupportEntity entity) {
		AsileBusinessSupport model = BeanCopyConverter.converterClass(entity, AsileBusinessSupport.class);
		try {
			asileBusinessSupportManager.save(model);
		} catch (Exception e) {
			log.error("AsileBussinessSupportServiceImpl.insert.ex :"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.insert.exception: "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:03:19
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> update(AsileBusinessSupportEntity entity) {
		AsileBusinessSupport model = BeanCopyConverter.converterClass(entity, AsileBusinessSupport.class);
		try {
			asileBusinessSupportManager.updateNotNull(model);
		} catch (Exception e) {
			log.error("AsileBussinessSupportServiceImpl.updateNotNull.ex :"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception: "+e.getMessage(), null);
		}
		return  new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:03:19
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<Object> deleteById(long id) {
		try {
			asileBusinessSupportManager.removeById(id);
		} catch (Exception e) {
			log.error("AsileBussinessSupportServiceImpl.removeById.ex :"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.removeById.exception: "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月11日 下午3:03:19
	 * @param id
	 * @return
	 */
	@Override
	public CommonResult<AsileBusinessSupportEntity> getById(long id) {
		AsileBusinessSupport model;
		try {
			model = asileBusinessSupportManager.getById(id);
		} catch (Exception e) {
			log.error("AsileBussinessSupportServiceImpl.getById.ex :"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.getById.exception: "+e.getMessage(), null);
		}
		AsileBusinessSupportEntity entity = BeanCopyConverter.converterClass(model, AsileBusinessSupportEntity.class);
		CommonResult<AsileBusinessSupportEntity> ret = new CommonResult<>();
		ret.setBussinessObj(entity);
		return ret;
	}

}
