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
import com.ink.basic.core.manager.IBankcardBinManager;
import com.ink.basic.core.po.BankcardBin;
import com.ink.basic.core.query.BankcardBinQuery;
import com.ink.trade.api.platform.basic.model.base.BankcardBinEntity;
import com.ink.trade.api.platform.basic.model.in.BankcardBinQueryInput;
import com.ink.trade.api.platform.basic.service.IBankcardBinService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月8日 上午10:45:22
 */
@Service("bankcardBinService")
public class BankcardBinServiceImpl implements IBankcardBinService {

	YinkerLogger log = YinkerLogger.getLogger(BankcardBinServiceImpl.class);

	@Autowired
	private IBankcardBinManager bankcardBinManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月8日 上午10:45:22
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<BankcardBinEntity>> list(BankcardBinQueryInput input) {
		CommonResult<Page<BankcardBinEntity>> ret = new CommonResult<>();
		BankcardBinQuery query = BeanCopyConverter.converterClass(input, BankcardBinQuery.class);
		Page<BankcardBin> page ;
		try {
			page = bankcardBinManager.findPage(query);
		} catch (Exception e) {
			log.error("BankcardBinServiceImpl.pageQuery.ex: "+e);
			return new CommonResult<Page<BankcardBinEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception:"+e.getMessage(), new Page<BankcardBinEntity>());
		}
		List<BankcardBinEntity> converterClass = (List<BankcardBinEntity>) BeanCopyConverter.converterClass(page.getResult(), BankcardBinEntity.class);
		Page<BankcardBinEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
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
	public CommonResult<Object> save(BankcardBinEntity entity) {
		BankcardBin bankcardBin = BeanCopyConverter.converterClass(entity, BankcardBin.class);
		try {
			bankcardBinManager.updateNotNull(bankcardBin);
		} catch (Exception e) {
			log.error("BankcardBinServiceImpl.insert.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.insert.exception", null);
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
	public CommonResult<Object> update(BankcardBinEntity entity) {
		BankcardBin bankcardBin = BeanCopyConverter.converterClass(entity, BankcardBin.class);
		try {
			bankcardBinManager.updateNotNull(bankcardBin);
		} catch (Exception e) {
			log.error("BankcardBinServiceImpl.updateNotNull.ex: "+e);
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
			bankcardBinManager.removeById(id);
		} catch (Exception e) {
			log.error("BankcardBinServiceImpl.removeById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.removeById.exception: "+e.getMessage(), null);
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
	public CommonResult<BankcardBinEntity> getById(long id) {
		BankcardBin bankcardBin;
		try {
			bankcardBin = bankcardBinManager.getById(id);
		} catch (Exception e) {
			log.error("BankcardBinServiceImpl.getById.ex: "+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.getById.exception: "+e.getMessage(), null);
		}
		BankcardBinEntity bankcardBinEntity = BeanCopyConverter.converterClass(bankcardBin, BankcardBinEntity.class);
		CommonResult<BankcardBinEntity> ret = new CommonResult<>();
		ret.setBussinessObj(bankcardBinEntity);
		return ret;
	}

}
