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

import com.ink.asile.core.manager.IAsileBankRuntimeManager;
import com.ink.asile.core.po.AsileBankRuntime;
import com.ink.asile.core.query.AsileBankRuntimeQuery;
import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.asile.model.base.AsileBankRuntimeEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBankRuntimeQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileBankRuntimeService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
@Service("asileBankRuntimeService")
public class AsileBankRuntimeServiceImpl implements IAsileBankRuntimeService {

	YinkerLogger log = YinkerLogger.getLogger(AsileBankRuntimeServiceImpl.class);

	@Autowired
	private IAsileBankRuntimeManager asileBankRuntimeManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<AsileBankRuntimeEntity>> list(AsileBankRuntimeQueryInput input) {
		CommonResult<Page<AsileBankRuntimeEntity>> ret = new CommonResult<>();
		AsileBankRuntimeQuery query = BeanCopyConverter.converterClass(input, AsileBankRuntimeQuery.class);
		Page<AsileBankRuntime> page ;
		try {
			page = asileBankRuntimeManager.findPage(query);
		} catch (Exception e) {
			log.error("AsileBankRuntimeServiceImpl 分页查询异常"+e);
			return new CommonResult<Page<AsileBankRuntimeEntity>>(-1, "database.query.exception"+e.getMessage(), new Page<AsileBankRuntimeEntity>());
		}
		List<AsileBankRuntimeEntity> converterClass = (List<AsileBankRuntimeEntity>) BeanCopyConverter.converterClass(page.getResult(), AsileBankRuntimeEntity.class);
		Page<AsileBankRuntimeEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
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
	public CommonResult<Object> save(AsileBankRuntimeEntity entity) {
		AsileBankRuntime asileBankRuntime = BeanCopyConverter.converterClass(entity, AsileBankRuntime.class);
		try {
			asileBankRuntimeManager.save(asileBankRuntime);
		} catch (Exception e) {
			log.error("AsileBankRuntimeServiceImpl insert异常"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.insert.exception"+e.getMessage(), null);
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
	public CommonResult<Object> update(AsileBankRuntimeEntity entity) {
		AsileBankRuntime asileBankRuntime = BeanCopyConverter.converterClass(entity, AsileBankRuntime.class);
		try {
			asileBankRuntimeManager.updateNotNull(asileBankRuntime);
		} catch (Exception e) {
			log.error("AsileBankRuntimeServiceImpl updateNotNull异常"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception"+e.getMessage(), null);
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
			asileBankRuntimeManager.removeById(id);
		} catch (Exception e) {
			log.error("AsileBankRuntimeServiceImpl 删除异常,主键为:"+id+"msg : "+e.getMessage());
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.delete.exception"+e.getMessage(), null);
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
	public CommonResult<AsileBankRuntimeEntity> getById(long id) {
		AsileBankRuntime asileBankRuntime;
		try {
			asileBankRuntime = asileBankRuntimeManager.getById(id);
		} catch (Exception e) {
			log.error("AsileBankRuntimeServiceImpl 主键查询异常,主键为:"+id+"msg : "+e.getMessage());
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.findById.exception : "+e.getMessage(), null);
		}
		AsileBankRuntimeEntity asileBankRuntimeEntity = BeanCopyConverter.converterClass(asileBankRuntime, AsileBankRuntimeEntity.class);
		CommonResult<AsileBankRuntimeEntity> ret = new CommonResult<>();
		ret.setBussinessObj(asileBankRuntimeEntity);
		return ret;
	}


}
