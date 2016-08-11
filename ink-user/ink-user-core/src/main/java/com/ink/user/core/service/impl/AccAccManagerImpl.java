/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.service.impl;

import com.ink.user.core.po.AccAcc;
import com.ink.user.core.service.IAccAccManager;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;
import com.ink.user.core.dao.IAccAccDao;

import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("accAccManager")
@Transactional
public class AccAccManagerImpl extends BaseManager<AccAcc,Long> implements IAccAccManager {

	@Autowired
	private IAccAccDao accAccDao;
	private YinkerLogger logger = YinkerLogger.getLogger(AccAccManagerImpl.class);

	public EntityDao<AccAcc, Long> getEntityDao() {
		return this.accAccDao;
	}
	
	public Page<AccAcc> findCustPage(PageRequest query){
		return accAccDao.findCustPage(query);
	}
	
	public Page<AccAcc> findMchPage(PageRequest query){
		return accAccDao.findMchPage(query);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int initAccMac() {
		Date date = null;
		try {
			date = DateUtils.getStrToDate("2016-07-01 00:00:00");
			logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE, "导入数据信息初始化,初始化最后更新时间为 = "+date+",初始化AccMac");
			int i = accAccDao.initLastUpdateTime(date);
			int j = accAccDao.initAccMac(date);
			if( i ==1 && j == 1){
				logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE, "导入数据信息初始化成功!");
				return 1;
			}else{
				logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE, "导入数据信息初始化失败!");
			}
		} catch (Exception e) {
			logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE, "导入数据信息初始化异常!");
		}

		return 0;
	}


}
