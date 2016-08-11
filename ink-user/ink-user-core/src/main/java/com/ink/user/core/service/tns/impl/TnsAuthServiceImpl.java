package com.ink.user.core.service.tns.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ink.user.core.po.TnsAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.dao.ITnsAuthDao;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.service.tns.ITnsAuthService;

@Service("tnsAuthService")
public class TnsAuthServiceImpl implements ITnsAuthService {

	private static final YinkerLogger logger = YinkerLogger.getLogger(TnsAuthServiceImpl.class);
	@Autowired
	private ITnsAuthDao tnsAuthDao;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
	@Override
	public void insertTnsAuth(TnsLog tnsLog, AccCust accCust) throws Exception {
		TnsAuth tnsAuth = new TnsAuth();
		tnsAuth.setId(Long.valueOf(idCodeGenerator.getId()));
		Date now = new Date();
		tnsAuth.setCreateTime(now);
		tnsAuth.setLastUpdateTime(now);
		tnsAuth.setTnsLogId(Long.parseLong(tnsLog.getId()));
		tnsAuth.setTxnCode(tnsLog.getTxnCode());
		tnsAuth.setTxnName(tnsLog.getTxnName());
		tnsAuth.setAccDate(new Date());// 记账日期
		tnsAuth.setCustId(tnsLog.getCustId());
		tnsAuth.setCustName(accCust.getCustName());
		tnsAuth.setCustType(accCust.getCustType());
		tnsAuth.setSacId(tnsLog.getAccSubType());
		tnsAuth.setCur("CNY");
		tnsAuth.setAuthAmt(tnsLog.getAmt().add(tnsLog.getCustFee()));
		tnsAuth.setAuthBal(tnsLog.getAmt().add(tnsLog.getCustFee()));
		tnsAuth.setRevFlg("N");
		tnsAuth.setAuthStatus(0);
		tnsAuth.setMemo(tnsLog.getMemo());
		tnsAuth.setDelFlag(0);
		tnsAuth.setVersion(1);
		tnsAuth.setFiller1(String.valueOf(tnsLog.getMchId()));
		tnsAuthDao.save(tnsAuth);
	}

	@Override
	public TnsAuth checkTnsAuth(Long tnsLogId) throws Exception {
		TnsAuth tnsAuth = tnsAuthDao.selectByTnsLogId(tnsLogId);
		if (null == tnsAuth) {
			throw new AtpBusinessException(RespCodeConstant.RespCode_300011,
					RespCodeConstant.RespCode_300011Desc);
		}
		if (!AtpTnsConstant.REF_FLG_N.equals(tnsAuth.getRevFlg())) {
			throw new AtpBusinessException(RespCodeConstant.RespCode_300012,
					RespCodeConstant.RespCode_300012Desc);
		}
		if(tnsAuth.getAuthStatus() == 1){
			throw new AtpBusinessException(RespCodeConstant.RespCode_300014,
					RespCodeConstant.RespCode_300014Desc);
		}
		return tnsAuth;
	}

	@Override
	public void updateTnsAuth(BigDecimal bal, long id, int version)
			throws Exception {
		TnsAuth tnsAuth = new TnsAuth();
		tnsAuth.setAuthBal(bal);
		// 可授权余额为0时，则授权状态为：1-授权完成
		if (bal.compareTo(BigDecimal.ZERO) == 0) {
			tnsAuth.setAuthStatus(1);
		}
		tnsAuth.setTnsLogId(id);
		tnsAuth.setVersion(version);
		int i = tnsAuthDao.updateByTnsLogId(tnsAuth);
		if (i < 0) {
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"更新授权交易表信息失败！tnsAuth=【" + tnsAuth.toString() + "】");
			throw new AtpBusinessException(RespCodeConstant.ErrorCode_00005,RespCodeConstant.ErrorCode_00005Desc);
		}
	}

	@Override
	public int updateTnsAuthByrevFlg(long id, int version) throws Exception {
		TnsAuth tnsAuth = new TnsAuth();
		tnsAuth.setTnsLogId(id);
		tnsAuth.setVersion(version);
		tnsAuth.setRevFlg("Y");
		return tnsAuthDao.updateByTnsLogId(tnsAuth);
	}

	@Override
	public List<TnsAuth> getUnfrozenTnsAuth(Date startTime, Date endTime) {
		return tnsAuthDao.getUnfrozenTnsAuth(startTime, endTime);
	}
}
