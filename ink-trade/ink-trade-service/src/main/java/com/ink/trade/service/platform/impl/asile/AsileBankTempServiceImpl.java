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

import com.ink.asile.core.manager.IAsileBankTempManager;
import com.ink.asile.core.po.AsileBankTemp;
import com.ink.asile.core.query.AsileBankTempQuery;
import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.asile.model.base.AsileBankTempEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBankTempQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileBankTempService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
@Service("asileBankTempService")
public class AsileBankTempServiceImpl implements IAsileBankTempService {

	YinkerLogger log = YinkerLogger.getLogger(AsileBankTempServiceImpl.class);

	@Autowired
	private IAsileBankTempManager asileBankTempManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<AsileBankTempEntity>> list(AsileBankTempQueryInput input) {
		CommonResult<Page<AsileBankTempEntity>> ret = new CommonResult<>();
		AsileBankTempQuery query = BeanCopyConverter.converterClass(input, AsileBankTempQuery.class);
		Page<AsileBankTemp> page ;
		try {
			page = asileBankTempManager.findPage(query);
		} catch (Exception e) {
			log.error("AsileBankTempServiceImpl.list.exception:"+e);
			return new CommonResult<Page<AsileBankTempEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception:"+e.getMessage(), new Page<AsileBankTempEntity>());
		}
		List<AsileBankTempEntity> converterClass = (List<AsileBankTempEntity>) BeanCopyConverter.converterClass(page.getResult(), AsileBankTempEntity.class);
		Page<AsileBankTempEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
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
	public CommonResult<Object> save(AsileBankTempEntity entity) {
		AsileBankTemp asileBankTemp = BeanCopyConverter.converterClass(entity, AsileBankTemp.class);
		try {
			asileBankTempManager.save(asileBankTemp);
		} catch (Exception e) {
			log.error("AsileBankTempServiceImpl.insert.exception:"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.insert.exception:"+e.getMessage(), null);
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
	public CommonResult<Object> update(AsileBankTempEntity entity) {
		AsileBankTemp asileBankTemp = BeanCopyConverter.converterClass(entity, AsileBankTemp.class);
		try {
			asileBankTempManager.updateNotNull(asileBankTemp);
		} catch (Exception e) {
			log.error("AsileBankTempServiceImpl.updateNotNull.exception:"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception:"+e.getMessage(), null);
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
			asileBankTempManager.removeById(id);
		} catch (Exception e) {
			log.error("AsileBankTempServiceImpl.deleteById:"+id+" and exception:"+e);
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
	public CommonResult<AsileBankTempEntity> getById(long id) {
		AsileBankTemp asileBankTemp;
		try {
			asileBankTemp = asileBankTempManager.getById(id);
		} catch (Exception e) {
			log.error("AsileBankTempServiceImpl.getById:"+id+" and exception:"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.getById.exception: "+e.getMessage(), null);
		}
		AsileBankTempEntity asileBankTempEntity = BeanCopyConverter.converterClass(asileBankTemp, AsileBankTempEntity.class);
		CommonResult<AsileBankTempEntity> ret = new CommonResult<>();
		ret.setBussinessObj(asileBankTempEntity);
		return ret;
	}


}
