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

import com.ink.asile.core.manager.IAsileInfoManager;
import com.ink.asile.core.po.AsileInfo;
import com.ink.asile.core.query.AsileInfoQuery;
import com.ink.base.page.Page;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.trade.api.platform.asile.model.base.AsileInfoEntity;
import com.ink.trade.api.platform.asile.model.in.AsileInfoQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileInfoService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @Description 
 * @author xuguoqi
 * @date 2016年7月7日 上午11:41:32
 */
@Service("asileInfoService")
public class AsileInfoServiceImpl implements IAsileInfoService {

	YinkerLogger log = YinkerLogger.getLogger(AsileInfoServiceImpl.class);

	@Autowired
	private IAsileInfoManager asileInfoManager;

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月7日 下午3:45:12
	 * @param input
	 * @return
	 */
	@Override
	public CommonResult<Page<AsileInfoEntity>> list(AsileInfoQueryInput input) {
		CommonResult<Page<AsileInfoEntity>> ret = new CommonResult<>();
		Page<AsileInfo> page ;
		try {
			AsileInfoQuery query = BeanCopyConverter.converterClass(input, AsileInfoQuery.class);
			page = asileInfoManager.findPage(query);
		} catch (Exception e) {
			log.error("AsileBussinessSupportServiceImpl.pageQuery.ex :"+e);
			return new CommonResult<Page<AsileInfoEntity>>(TradePlatformConstants.DATABASE_EXCEPTION, "database.pageQuery.exception: "+e.getMessage(), new Page<AsileInfoEntity>());
		}
		List<AsileInfoEntity> converterClass = (List<AsileInfoEntity>) BeanCopyConverter.converterClass(page.getResult(), AsileInfoEntity.class);
		Page<AsileInfoEntity> retPage = new Page<>(page.getPageNumber(), page.getPageSize(), page.getTotalCount(), converterClass);
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
	public CommonResult<Object> save(AsileInfoEntity entity) {
		AsileInfo asileInfo = BeanCopyConverter.converterClass(entity, AsileInfo.class);
		try {
			asileInfoManager.save(asileInfo);
		} catch (Exception e) {
			log.error("AsileBussinessSupportServiceImpl.save.ex :"+e);
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
	public CommonResult<Object> update(AsileInfoEntity entity) {
		AsileInfo asileInfo = BeanCopyConverter.converterClass(entity, AsileInfo.class);
		try {
			asileInfoManager.updateNotNull(asileInfo);
		} catch (Exception e) {
			log.error("AsileBussinessSupportServiceImpl.updateNotNull.ex :"+e);
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
			asileInfoManager.removeById(id);
		} catch (Exception e) {
			log.error("AsileBussinessSupportServiceImpl.deleteById.ex :"+e);
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
	public CommonResult<AsileInfoEntity> getById(long id) {
		AsileInfo asileInfo;
		try {
			asileInfo = asileInfoManager.getById(id);
		} catch (Exception e) {
			log.error("AsileBussinessSupportServiceImpl.getById.ex :"+e);
			return new CommonResult<>(TradePlatformConstants.DATABASE_EXCEPTION, "database.getById.exception: "+e.getMessage(), null);
		}
		AsileInfoEntity asileInfoEntity = BeanCopyConverter.converterClass(asileInfo, AsileInfoEntity.class);
		CommonResult<AsileInfoEntity> ret = new CommonResult<>();
		ret.setBussinessObj(asileInfoEntity);
		return ret;
	}

	@Override
	public List<AsileInfoEntity> getAll() {
		AsileInfoQuery query=new AsileInfoQuery();
		query.setAsileStatus("1");
		return (List<AsileInfoEntity>)BeanCopyConverter.converterClass(asileInfoManager.find(query),AsileInfoEntity.class);

	}



}
