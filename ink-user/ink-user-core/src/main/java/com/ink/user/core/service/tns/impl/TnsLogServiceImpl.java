package com.ink.user.core.service.tns.impl;

import com.ink.user.core.dao.ITnsLogDao;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.service.tns.ITnsLogService;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service("tnsLogService")
public class TnsLogServiceImpl implements ITnsLogService {
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private ITnsLogDao tnsLogDao;

	@Override
	public TnsLog insertTnsLog(TnsLog tnsLog) {
		tnsLog.setId(idCodeGenerator.getId());
		tnsLog.setOrdTime(new Date());
		tnsLog.setDelFlag(0);
		tnsLog.setVersion(1);
		tnsLog.setCreateTime(new Date());
		tnsLogDao.insertSelective(tnsLog);
		return tnsLog;
	}

	@Override
	public TnsLog findTnsLogByOrdId(Long mchId, String ordId, Date ordDate,
			String txnCode) throws Exception {
		return tnsLogDao.findTnsLogbyOrdId(ordId, mchId, ordDate, txnCode);
	}

	

	@Override
	public int updateTnsLog(TnsLog tnsLog) throws Exception {
		tnsLog.setLastUpdateTime(new Date());
		return tnsLogDao.updateByPrimaryKeySelective(tnsLog);
	}

	@Override
	public TnsLog findTnsLogByOriOrdId(Long mchId, String oriOrdId,
			Date oriOrdDate, String oriTxnCode) throws Exception {
		TnsLog tnsLog = tnsLogDao.findTnsLogbyOriOrdId(oriOrdId, mchId,
				oriOrdDate, oriTxnCode);
		if (null == tnsLog) {
			throw new AtpBusinessException(RespCodeConstant.RespCode_300030,
					RespCodeConstant.RespCode_300030Desc);
		}
		return tnsLog;
	}

	@Override
	public TnsLog findTnsLogById(Long id) throws Exception {
		return tnsLogDao.selectByPrimaryKey(id);
	}


	@Override
	public TnsLog checkTnsLog(String ordId,String txnCode, String mchId) {
		return tnsLogDao.selectTnsLogByOrdId(ordId,txnCode, mchId);
	}

	@Override
	public TnsLog findTnsLogByAccUnfrozen(String ordId,
			BigDecimal amt, BigDecimal custFee, Date ordDate) {
		return tnsLogDao.findTnsLogByAccUnfrozen(ordId,amt,custFee,ordDate);
	}

	@Override
	public TnsLog getTnsLogbyId(String id) {
		return tnsLogDao.getTnsLogbyId(id);
	}
	
}
