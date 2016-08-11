/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.dao.IAccMchDao;
import com.ink.user.core.po.AccAcc;
import com.ink.user.core.service.redis.AccMchCacheService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.yinker.pats.global.key.enums.GlobalKey;
import com.yinker.pats.global.key.service.factory.GlobalKeyFactory;
import com.ink.user.core.dao.IAccAccDao;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.AccMch;
import com.ink.user.core.service.IAccCustManager;
import com.ink.user.core.service.IAccMchManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("accMchManager")
@Transactional
public class AccMchManagerImpl extends BaseManager<AccMch,Long> implements IAccMchManager{

	@Autowired
	private IAccMchDao accMchDao;
	@Autowired
	private IAccAccDao accAccDao;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private AccMchCacheService accMchCacheService;
	@Autowired
	private IAccCustManager accCustManager;
	@Autowired
	private GlobalKeyFactory globalKeyFactory;
	private YinkerLogger logger = YinkerLogger.getLogger(AccMchManagerImpl.class);

	public EntityDao<AccMch, Long> getEntityDao() {
		return this.accMchDao;
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
	public int saveMchAndAcc(AccMch accMch) throws Exception {
		accMch.setId(idCodeGenerator.getId());
		Date date = new Date();
		accMch.setCreateTime(date);
		accMch.setLastUpdateTime(date);
		accMch.setVersion(1);
		accMchDao.save(accMch);
		//保存商户账号成功,新增三个商户账户,一个客户账号
		create(accMch,"");
		create(accMch,"0011");
		create(accMch,"0012");
		try {
			AccCust accCust = initAccCust(accMch);
			accCustManager.save(accCust);
		} catch (Exception e) {
			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_NORMAL,
					"#新增商户添加客户异常!AccMch = "+accMch.getMchId());
		}
		return 0;
	}
	
	private AccCust initAccCust(AccMch accMch) throws Exception {
		Date date = new Date();
		AccCust accCust = new AccCust();
		accCust.setId(idCodeGenerator.getId());
		accCust.setCreateTime(date);
		accCust.setLastUpdateTime(date);
		accCust.setMchId(accMch.getMchId());
		accCust.setCustId(globalKeyFactory.getKeyService(GlobalKey.account)
				.getKeyNext().toString());
		accCust.setCustType(0);
		accCust.setIdType("");
		accCust.setIdNo("");
		accCust.setPacId(globalKeyFactory.getKeyService(GlobalKey.account)
				.getKeyNext());
		accCust.setCustName("");
		accCust.setSex("");
		accCust.setBirthday(date);
		accCust.setMblNo("");
		accCust.setEmail("");
		accCust.setZipcode("");
		accCust.setAddress("");
		accCust.setStatus(1);
		accCust.setDelFlag(0);
		return accCust;
		
	}


	private AccAcc create(AccMch accMch, String sacType) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		Date now = new Date();
		String nowStr = sdf.format(now);
		now = sdf.parse(nowStr);
		AccAcc accAcc = new AccAcc();
		BigDecimal initAmt = new BigDecimal(0.00).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		Integer initVersion = new Integer(1);
		accAcc.setId(Long.valueOf(idCodeGenerator.getId()));//IdWorker.getNextId());
		accAcc.setCreateTime(now);
		accAcc.setLastUpdateTime(now);
		String sacId = accMch.getMchId().toString() + sacType;
		accAcc.setSacId(sacId);
		accAcc.setPacId(accMch.getMchId());
		accAcc.setSacType(sacType);
		accAcc.setCur("CNY");
		accAcc.setLstBal(initAmt);
		accAcc.setCurBal(initAmt);
		accAcc.setTmpBal(initAmt);
		accAcc.setFrozenAmt(initAmt);
		accAcc.setOpenDate(now);
		accAcc.setStatus(1);
		accAcc.setDelFlag(0);
		accAcc.setVersion(initVersion);
		accAcc.setAccMac(DigestUtils.md5Hex(accAcc.getCurBal().toString().trim() + nowStr));
		logger.info("创建账户MD5，curBal : " + accAcc.getCurBal().toString()
					+ ", date : " + sdf.format(accAcc.getLastUpdateTime())
					+ ", md5 : " + DigestUtils.md5Hex(accAcc.getCurBal().toString().trim() + nowStr)
					+ ", accMac : " + accAcc.getAccMac());
		try {
			accAccDao.save(accAcc);
		} catch (Exception e) {
			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_NORMAL, 
					"#新增商户添加账户异常!AccMch = "+accMch.getMchId()+",账户类型为 = "+sacType);
		}
		return accAcc;
	}
	
//	@Transactional(readOnly=true)
//	public AccMch getByMchId(Long v) {
//		return accMchDao.getByMchId(v);
//	}	
	
	public int removeById(Long id) throws DataAccessException {
		AccMch accMch = accMchDao.getById(id);
		if(accMch!=null){
			accMchCacheService.removeAccMchCache(accMch.getMchId());
		}
		return accMchDao.deleteById(id);
	}

}
