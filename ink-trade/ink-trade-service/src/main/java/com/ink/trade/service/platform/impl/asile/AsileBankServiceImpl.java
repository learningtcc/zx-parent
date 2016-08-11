/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
package com.ink.trade.service.platform.impl.asile;

import java.util.ArrayList;
import java.util.List;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.constants.TradePlatformConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileBankManager;
import com.ink.asile.core.po.AsileBank;
import com.ink.asile.core.query.AsileBankQuery;
import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.asile.model.base.AsileBankEntity;
import com.ink.trade.api.platform.asile.model.in.AsileBankQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileBankService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
@Service("asileBankService")
public class AsileBankServiceImpl implements IAsileBankService {

	YinkerLogger log = YinkerLogger.getLogger(AsileBankServiceImpl.class);

	@Autowired
	private IAsileBankManager asileBankManager;


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
			asileBankManager.removeById(id);
		} catch (Exception e) {
			log.error("AsileBankServiceImpl.deleteById 异常:"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.delete.exception: "+e.getMessage(), null);
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
	public CommonResult<AsileBankEntity> getById(long id) {
		AsileBank asileBank;
		try {
			asileBank = asileBankManager.getById(id);
		} catch (Exception e) {
			log.error("AsileBankServiceImpl.getById 异常:"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.findById.exception:"+e.getMessage(), null);
		}
		AsileBankEntity asileBankEntity = BeanCopyConverter.converterClass(asileBank, AsileBankEntity.class);
		CommonResult<AsileBankEntity> ret = new CommonResult<>();
		ret.setBussinessObj(asileBankEntity);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午6:48:51
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<AsileBankEntity>> list(AsileBankQueryInput input) {
		CommonResult<Page<AsileBankEntity>> ret = new CommonResult<>();
		AsileBankQuery query = BeanCopyConverter.converterClass(input, AsileBankQuery.class);
		Page<AsileBank> page ;
		try {
			page = asileBankManager.findPage(query);
		} catch (Exception e) {
			log.error("AsileBankServiceImpl.pageQuery 异常:"+e);
			return new CommonResult<Page<AsileBankEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception:"+e.getMessage(), new Page<AsileBankEntity>());
		}
		List<AsileBankEntity> converterClass = (List<AsileBankEntity>) BeanCopyConverter.converterClass(page.getResult(), AsileBankEntity.class);
		Page<AsileBankEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
		ret.setBussinessObj(retPage);
		return ret;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午6:48:51
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> save(AsileBankEntity entity) {
		AsileBank asileBank = BeanCopyConverter.converterClass(entity, AsileBank.class);
		try {
			asileBankManager.save(asileBank);
		} catch (Exception e) {
			log.error("AsileBankServiceImpl.insert 异常:"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.insert.exception:"+e.getMessage(), null);
		}
		return new CommonResult<>();
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午6:48:51
	 * @param entity
	 * @return
	 */
	@Override
	public CommonResult<Object> update(AsileBankEntity entity) {
		AsileBank asileBank = BeanCopyConverter.converterClass(entity, AsileBank.class);
		try {
			asileBankManager.updateNotNull(asileBank);
		} catch (Exception e) {
			log.error("AsileBankServiceImpl.updateNotNull 异常:"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.updateNotNull.exception:"+e.getMessage(), null);
		}
		return  new CommonResult<>();
	}

	@Override
	public void updateList(List<AsileBankEntity> records) {
		List<AsileBank> asileBanks=new ArrayList<AsileBank>();
		for(AsileBankEntity entity:records){
			AsileBank asileBank = BeanCopyConverter.converterClass(entity, AsileBank.class);
			asileBanks.add(asileBank);
		}
		asileBankManager.updateList(asileBanks);
	}



}
