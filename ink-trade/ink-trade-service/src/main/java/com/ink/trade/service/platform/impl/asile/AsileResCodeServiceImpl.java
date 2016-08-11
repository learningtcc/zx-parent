/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description 
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
package com.ink.trade.service.platform.impl.asile;

import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.po.AsileResCode;
import com.ink.asile.core.query.AsileResCodeQuery;
import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.asile.model.base.AsileResCodeEntity;
import com.ink.trade.api.platform.asile.model.in.AsileResCodeQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileResCodeService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
@Service("asileResCodeService")
public class AsileResCodeServiceImpl implements IAsileResCodeService {

	YinkerLogger log = YinkerLogger.getLogger(AsileResCodeServiceImpl.class);

	@Autowired
	private IAsileResCodeManager asileResCodeManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<AsileResCodeEntity>> list(AsileResCodeQueryInput input) {
		CommonResult<Page<AsileResCodeEntity>> ret = new CommonResult<>();
		AsileResCodeQuery query = BeanCopyConverter.converterClass(input, AsileResCodeQuery.class);
		Page<AsileResCode> page ;
		try {
			page = asileResCodeManager.findPage(query);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.pageQuery.ex: "+e);
			return new CommonResult<Page<AsileResCodeEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.query.exception: "+e.getMessage(), new Page<AsileResCodeEntity>());
		}
		List<AsileResCodeEntity> converterClass = (List<AsileResCodeEntity>) BeanCopyConverter.converterClass(page.getResult(), AsileResCodeEntity.class);
		Page<AsileResCodeEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);

		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> save(AsileResCodeEntity entity) {
		AsileResCode asileResCode = BeanCopyConverter.converterClass(entity, AsileResCode.class);
		try {
			asileResCodeManager.save(asileResCode);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.save.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.save.exception: "+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> update(AsileResCodeEntity entity) {
		AsileResCode asileResCode = BeanCopyConverter.converterClass(entity, AsileResCode.class);
		try {
			asileResCodeManager.updateNotNull(asileResCode);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.updateNotNull.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception: "+e.getMessage(), null);
		}
		return  new CommonResult<>();
	}

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
			asileResCodeManager.removeById(id);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.deleteById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.deleteById.exception: "+e.getMessage(), null);
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
	public CommonResult<AsileResCodeEntity> getById(long id) {
		AsileResCode asileResCode;
		try {
			asileResCode = asileResCodeManager.getById(id);
		} catch (Exception e) {
			log.error("AsileResCodeServiceImpl.getById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.getById.exception: "+e.getMessage(), null);
		}
		AsileResCodeEntity asileInfoEntity = BeanCopyConverter.converterClass(asileResCode, AsileResCodeEntity.class);
		CommonResult<AsileResCodeEntity> ret = new CommonResult<>();
		ret.setBussinessObj(asileInfoEntity);
		return ret;
	}



}
