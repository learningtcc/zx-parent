package com.ink.user.service.limit;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.core.dao.IAccLimitDao;
import com.ink.user.core.dao.IAccSacTypeDao;
import com.ink.user.core.dao.IAccTypeMchLimitDao;
import com.ink.user.core.po.AccAcc;
import com.ink.user.core.po.AccLimit;
import com.ink.user.core.po.AccTypeMchLimit;
import com.ink.user.util.DateUtils;

@Service("accountLimitService")
public class AccountLimitServiceImpl implements IAccountLimitService {
	private static final Logger logger = LoggerFactory
			.getLogger(AccountLimitServiceImpl.class);
	@Autowired
	private IAccSacTypeDao accSacTypeDao;
	@Autowired
	private IAccLimitDao accLimitDao;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private IAccTypeMchLimitDao accTypeMchLimitDao;
//	private static final String sac_varecharge = AtpTnsConstant.ACC_30010 + "|";// 子账户-充值类的交易
//	private static final String sac_pay = "";// 子账户-消费交易
//	private static final String sac_in = "";// 子账户-转入交易
//	private static final String sac_out = "";// 子账户-转出交易
	private static final String sac_cash = AtpTnsConstant.ACC_AWF ;// 子账户-提现交易
//	private static final String pac_nopswd = "";// 主账户-免密支付
//	private static final String pac_in = AtpTnsConstant.ACC_30010 + "|";// 主账户-收入
//	private static final String pac_pay = AtpTnsConstant.ACC_30220 + "|"
//			+ AtpTnsConstant.ACC_30060;// 主账户-支出

	/**
	 * 检查主账户限额信息
	 * 
	 * @param accPac
	 * @param amt
	 * @param tnsTxn
	 * @throws Exception
	 */
//	public AccPac checkAccPacLimit(AccCust accCust, AccAcc accAcc,
//			BigDecimal amt, String txnCode) throws Exception {
//		logger.info("进入主账户控制检查方法，txnCode : {}, amt : {}, accAcc : {}", txnCode,
//				amt, accAcc);
//		AccPac accPac = accLimitCacheService.getAccPacByPacId(String.valueOf(accAcc.getPacId()));
//		if (accPac == null) {
//			accPac = accPacDao.getAccPacByPacId(String.valueOf(accAcc
//					.getPacId()));
//			if(accPac == null){
//				AccSacType accSacType = accSacTypeDao
//						.getAccSacTypeBySacType(AtpTnsConstant.PacAccType);
//				accPac = accPacDao.createAccPac(accCust, accAcc, accSacType);
//			}
//		}
//		if (pac_nopswd.indexOf(txnCode) >= 0) {
//			logger.info("主账户-免密支付校验");
//			// 金额校验
//			BigDecimal dayNopswdAmt = accPac.getDayNopswdAmt();
//			BigDecimal dayNopswdlmtAmt = accPac.getDayNopswdlmtAmt();
//			if (!checkAmtLimit(dayNopswdAmt, amt, dayNopswdlmtAmt)) {
//				logger.error("主账户-日免密支付校验，金额超过限额");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			// 笔数校验
//			Integer dayNopswdCnt = accPac.getDayNopswdCnt();
//			Integer dayNopswdlmtCnt = accPac.getDayNopswdlmtCnt();
//			if (!checkCntLimit(dayNopswdCnt, dayNopswdlmtCnt)) {// 限制次数必须大于0才校验
//				logger.error("主账户-日免密支付校验，笔数超过限额");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//		}
//		if (pac_in.indexOf(txnCode) >= 0) {
//			logger.info("主账户-收入校验");
//			// 金额校验
//			BigDecimal dayInAmt = accPac.getDayInAmt();
//			BigDecimal dayInlmtAmt = accPac.getDayInlmtAmt();
//			if (!checkAmtLimit(dayInAmt, amt, dayInlmtAmt)) {
//				logger.error("主账户-日收入校验，金额超过限额");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//
//			BigDecimal monInAmt = accPac.getMonPayAmt();
//			BigDecimal monInlmtAmt = accPac.getMonInlmtAmt();
//			if (!checkAmtLimit(monInAmt, amt, monInlmtAmt)) {
//				logger.error("主账户-月收入校验，金额超过限额");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			// 笔数校验
//			Integer dayInCnt = accPac.getDayPayCnt();
//			Integer dayInlmtCnt = accPac.getDayPaylmtCnt();
//			if (!checkCntLimit(dayInCnt, dayInlmtCnt)) {// 限制次数必须大于0才校验
//				logger.error("主账户-日收入校验，笔数超过限额");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//			Integer monInCnt = accPac.getMonPayCnt();
//			Integer monInlmtCnt = accPac.getMonPaylmtCnt();
//			if (!checkCntLimit(monInCnt, monInlmtCnt)) {// 限制次数必须大于0才校验
//				logger.error("主账户-月收入校验，笔数超过限额");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//		}
//		if (pac_pay.indexOf(txnCode) >= 0) {
//			logger.info("主账户-支出校验");
//			// 金额校验
//			BigDecimal dayPayAmt = accPac.getDayPayAmt();
//			BigDecimal dayPaylmtAmt = accPac.getDayPaylmtAmt();
//			if (!checkAmtLimit(dayPayAmt, amt, dayPaylmtAmt)) {
//				logger.error("主账户-日支出校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//
//			BigDecimal monPayAmt = accPac.getMonPayAmt();
//			BigDecimal monPaylmtAmt = accPac.getMonPaylmtAmt();
//			if (!checkAmtLimit(monPayAmt, amt, monPaylmtAmt)) {
//				logger.error("主账户-月支出校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			// 笔数校验
//			Integer dayPayCnt = accPac.getDayPayCnt();
//			Integer dayPaylmtCnt = accPac.getDayPaylmtCnt();
//			if (!checkCntLimit(dayPayCnt, dayPaylmtCnt)) {// 限制次数必须大于0才校验
//				logger.error("主账户-日支出校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//			Integer monPayCnt = accPac.getMonPayCnt();
//			Integer monPaylmtCnt = accPac.getMonPaylmtCnt();
//			if (!checkCntLimit(monPayCnt, monPaylmtCnt)) {// 限制次数必须大于0才校验
//				logger.error("主账户-月支出校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//		}
//		return accPac;
//	}

	/**
	 * 检查子账户限制
	 * 
	 * @param accCust
	 * @param accAcc
	 * @param amt
	 * @param txnCode
	 * @return
	 * @throws Exception
	 */
//	public AccSac checkAccSacLimit(AccCust accCust, AccAcc accAcc,
//			BigDecimal amt, String txnCode) throws Exception {
//		AccSac accSac = accLimitCacheService.getAccSacBySacId(String.valueOf(accAcc.getSacId()));
//		if (accSac == null) {
//			accSac = accSacDao.getAccSacBySacId(String.valueOf(accAcc
//					.getSacId()));
//			if(accSac == null){
//				AccSacType accSacType = accSacTypeDao
//						.getAccSacTypeBySacType(accAcc.getSacType());
//				accSac = accSacDao.createAccSac(accCust, accAcc, accSacType);
//			}
//		}
//		if (sac_cash.indexOf(txnCode) >= 0) {
//			logger.info("子账户-提现校验");
//			// 金额校验
//			BigDecimal dayCashAmt = accSac.getDayCashAmt();
//			BigDecimal dayCashlmtAmt = accSac.getDayCashlmtAmt();
//			if (!checkAmtLimit(dayCashAmt, amt, dayCashlmtAmt)) {
//				logger.error("子账户-日提现校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			BigDecimal monCashAmt = accSac.getMonCashAmt();
//			BigDecimal monCashlmtAmt = accSac.getMonCashlmtAmt();
//			if (!checkAmtLimit(monCashAmt, amt, monCashlmtAmt)) {
//				logger.error("子账户-月提现校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			// 次数校验
//			Integer dayCashCnt = accSac.getDayCashCnt();
//			Integer dayCashlmtCnt = accSac.getDayCashlmtCnt();
//			if (!checkCntLimit(dayCashCnt, dayCashlmtCnt)) {// 限制次数必须大于0才校验
//				logger.error("子账户-日提现校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//			Integer monCashCnt = accSac.getMonCashCnt();
//			Integer monCashlmtCnt = accSac.getMonCashlmtCnt();
//			if (!checkCntLimit(monCashCnt, monCashlmtCnt)) {// 限制次数必须大于0才校验
//				logger.error("子账户-月提现校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//
//		}
//		if (sac_in.indexOf(txnCode) >= 0) {
//			logger.info("子账户-转入校验");
//			// 金额校验
//			BigDecimal dayInAmt = accSac.getDayInAmt();
//			BigDecimal dayInlmtAmt = accSac.getDayInlmtAmt();
//			if (!checkAmtLimit(dayInAmt, amt, dayInlmtAmt)) {
//				logger.info("子账户-日转入校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			BigDecimal monInAmt = accSac.getMonInAmt();
//			BigDecimal monInlmtAmt = accSac.getMonInlmtAmt();
//			if (!checkAmtLimit(monInAmt, amt, monInlmtAmt)) {
//				logger.info("子账户-月转入校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			// 次数校验
//			Integer dayInCnt = accSac.getDayInCnt();
//			Integer dayInlmtCnt = accSac.getDayInlmtCnt();
//			if (!checkCntLimit(dayInCnt, dayInlmtCnt)) {// 限制次数必须大于0才校验
//				logger.info("子账户-日转入校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//			Integer monInCnt = accSac.getMonInCnt();
//			Integer monInlmtCnt = accSac.getMonInlmtCnt();
//			if (!checkCntLimit(monInCnt, monInlmtCnt)) {// 限制次数必须大于0才校验
//				logger.info("子账户-月转入校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//		}
//		if (sac_out.indexOf(txnCode) >= 0) {
//			logger.info("子账户-转出校验");
//			// 金额校验
//			BigDecimal dayOutAmt = accSac.getDayOutAmt();
//			BigDecimal dayOutlmtAmt = accSac.getDayOutlmtAmt();
//			if (!checkAmtLimit(dayOutAmt, amt, dayOutlmtAmt)) {
//				logger.info("子账户-日转出校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			BigDecimal monOutAmt = accSac.getMonOutAmt();
//			BigDecimal monOutlmtAmt = accSac.getMonOutlmtAmt();
//			if (!checkAmtLimit(monOutAmt, amt, monOutlmtAmt)) {
//				logger.info("子账户-月转出校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			// 次数校验
//			Integer dayOutCnt = accSac.getDayOutCnt();
//			Integer dayOutlmtCnt = accSac.getDayOutlmtCnt();
//			if (!checkCntLimit(dayOutCnt, dayOutlmtCnt)) {// 限制次数必须大于0才校验
//				logger.info("子账户-日转出校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//			Integer monOutCnt = accSac.getMonOutCnt();
//			Integer monOutlmtCnt = accSac.getMonOutlmtCnt();
//			if (!checkCntLimit(monOutCnt, monOutlmtCnt)) {// 限制次数必须大于0才校验
//				logger.info("子账户-月转出校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//		}
//		if (sac_pay.indexOf(txnCode) >= 0) {
//			logger.info("子账户-消费校验");
//			// 金额校验
//			BigDecimal dayPayAmt = accSac.getDayPayAmt();
//			BigDecimal dayPaylmtAmt = accSac.getDayPaylmtAmt();
//			if (!checkAmtLimit(dayPayAmt, amt, dayPaylmtAmt)) {
//				logger.error("子账户-日消费校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			BigDecimal monPayAmt = accSac.getMonPayAmt();
//			BigDecimal monPaylmtAmt = accSac.getMonPaylmtAmt();
//			if (!checkAmtLimit(monPayAmt, amt, monPaylmtAmt)) {
//				logger.error("子账户-月消费校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			// 次数校验
//			Integer dayPayCnt = accSac.getDayPayCnt();
//			Integer dayPaylmtCnt = accSac.getDayPaylmtCnt();
//			if (!checkCntLimit(dayPayCnt, dayPaylmtCnt)) {// 限制次数必须大于0才校验
//				logger.error("子账户-日消费校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//			Integer monPayCnt = accSac.getMonPayCnt();
//			Integer monPaylmtCnt = accSac.getMonPaylmtCnt();
//			if (!checkCntLimit(monPayCnt, monPaylmtCnt)) {// 限制次数必须大于0才校验
//				logger.error("子账户-月消费校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//		}
//		if (sac_varecharge.indexOf(txnCode) >= 0) {
//			logger.info("子账户-充值校验");
//			// 金额校验
//			BigDecimal dayVarchargeAmt = accSac.getDayVarchargeAmt();
//			BigDecimal dayVarchargelmtAmt = accSac.getDayVarchargelmtAmt();
//			if (!checkAmtLimit(dayVarchargeAmt, amt, dayVarchargelmtAmt)) {
//				logger.error("子账户-日充值校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			BigDecimal monVarchargeAmt = accSac.getMonVarchargeAmt();
//			BigDecimal monVarchargelmtAmt = accSac.getMonVarchargelmtAmt();
//			if (!checkAmtLimit(monVarchargeAmt, amt, monVarchargelmtAmt)) {
//				logger.error("子账户-月充值校验，金额超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
//						RespCodeConstant.RespCode_5000001Desc);
//			}
//			// 次数校验
//			Integer dayVarchargeCnt = accSac.getDayVarchargeCnt();
//			Integer dayVarchargelmtCnt = accSac.getDayVarchargelmtCnt();
//			if (!checkCntLimit(dayVarchargeCnt, dayVarchargelmtCnt)) {// 限制次数必须大于0才校验
//				logger.error("子账户-日充值校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//			Integer monVarchargeCnt = accSac.getMonVarchargeCnt();
//			Integer monVarchargelmtCnt = accSac.getMonVarchargelmtCnt();
//			if (!checkCntLimit(monVarchargeCnt, monVarchargelmtCnt)) {// 限制次数必须大于0才校验
//				logger.error("子账户-月充值校验，笔数超过限制");
//				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
//						RespCodeConstant.RespCode_5000002Desc);
//			}
//		}
//		return accSac;
//	}

	/**
	 * 更新主账户限制
	 * 
	 * @param accPac
	 * @param accAcc
	 * @param amt
	 * @param txnCode
	 * @throws Exception
	 */
//	public void updateAccPacLimit(AccPac accPac, AccAcc accAcc,
//			BigDecimal amt, String txnCode) throws Exception {
//		if (pac_nopswd.indexOf(txnCode) >= 0) {
//			logger.info("更新主账户-免密支付");
//			BigDecimal dayNopswdAmt = accPac.getDayNopswdAmt();
//			accPac.setDayNopswdAmt(dayNopswdAmt.add(amt));
//			Integer dayNopswdCnt = accPac.getDayNopswdCnt();
//			accPac.setDayNopswdCnt(dayNopswdCnt + 1);
//		}
//		if (pac_in.indexOf(txnCode) >= 0) {
//			logger.info("更新主账户-收入");
//			BigDecimal dayInAmt = accPac.getDayInAmt();
//			dayInAmt = initAmt(dayInAmt);
//			accPac.setDayInAmt(dayInAmt.add(amt));
//
//			BigDecimal monInAmt = accPac.getMonPayAmt();
//			monInAmt = initAmt(monInAmt);
//			accPac.setMonInAmt(monInAmt.add(amt));
//
//			Integer dayInCnt = accPac.getDayPayCnt();
//			dayInCnt = initCnt(dayInCnt);
//			accPac.setDayInCnt(dayInCnt + 1);
//
//			Integer monInCnt = accPac.getMonPayCnt();
//			monInCnt = initCnt(monInCnt);
//			accPac.setMonInCnt(monInCnt + 1);
//		}
//		if (pac_pay.indexOf(txnCode) >= 0) {
//			logger.info("更新主账户-支出");
//			BigDecimal dayPayAmt = accPac.getDayPayAmt();
//			dayPayAmt = initAmt(dayPayAmt);
//			accPac.setDayPayAmt(dayPayAmt.add(amt));
//
//			BigDecimal monPayAmt = accPac.getMonPayAmt();
//			monPayAmt = initAmt(monPayAmt);
//			accPac.setMonPayAmt(monPayAmt.add(amt));
//
//			Integer dayPayCnt = accPac.getDayPayCnt();
//			dayPayCnt = initCnt(dayPayCnt);
//			accPac.setDayPayCnt(dayPayCnt + 1);
//
//			Integer monPayCnt = accPac.getMonPayCnt();
//			monPayCnt = initCnt(monPayCnt);
//			accPac.setMonPayCnt(monPayCnt + 1);
//		}
////		accPacDao.updateAccPac(accPac);
//		accLimitCacheService.setAccPac(accPac);
//	}

	/**
	 * 更新子账户限制
	 * 
	 * @param accSac
	 * @param accAcc
	 * @param amt
	 * @param txnCode
	 * @throws Exception
	 */
//	public void updateAccSacLimit(AccSac accSac, AccAcc accAcc,
//			BigDecimal amt, String txnCode) throws Exception {
//		if (sac_cash.indexOf(txnCode) >= 0) {
//			logger.info("更新子账户-提现");
//			BigDecimal dayCashAmt = accSac.getDayCashAmt();
//			dayCashAmt = initAmt(dayCashAmt);
//			accSac.setDayCashAmt(dayCashAmt.add(amt));
//
//			BigDecimal monCashAmt = accSac.getMonCashAmt();
//			monCashAmt = initAmt(monCashAmt);
//			accSac.setMonCashAmt(monCashAmt.add(amt));
//
//			Integer dayCashCnt = accSac.getDayCashCnt();
//			dayCashCnt = initCnt(dayCashCnt);
//			accSac.setDayCashCnt(dayCashCnt + 1);
//
//			Integer monCashCnt = accSac.getMonCashCnt();
//			monCashCnt = initCnt(monCashCnt);
//			accSac.setMonCashCnt(monCashCnt + 1);
//		}
//		if (sac_in.indexOf(txnCode) >= 0) {
//			logger.info("更新子账户-转入");
//			BigDecimal dayInAmt = accSac.getDayInAmt();
//			dayInAmt = initAmt(dayInAmt);
//			accSac.setDayInAmt(dayInAmt.add(amt));
//
//			BigDecimal monInAmt = accSac.getMonInAmt();
//			monInAmt = initAmt(monInAmt);
//			accSac.setMonInAmt(monInAmt.add(amt));
//
//			Integer dayInCnt = accSac.getDayInCnt();
//			dayInCnt = initCnt(dayInCnt);
//			accSac.setDayInCnt(dayInCnt + 1);
//
//			Integer monInCnt = accSac.getMonInCnt();
//			monInCnt = initCnt(monInCnt);
//			accSac.setMonInCnt(monInCnt + 1);
//		}
//		if (sac_out.indexOf(txnCode) >= 0) {
//			logger.info("更新子账户-转出");
//			BigDecimal dayOutAmt = accSac.getDayOutAmt();
//			dayOutAmt = initAmt(dayOutAmt);
//			accSac.setDayOutAmt(dayOutAmt.add(amt));
//
//			BigDecimal monOutAmt = accSac.getMonOutAmt();
//			monOutAmt = initAmt(monOutAmt);
//			accSac.setMonOutAmt(monOutAmt.add(amt));
//
//			Integer dayOutCnt = accSac.getDayOutCnt();
//			dayOutCnt = initCnt(dayOutCnt);
//			accSac.setDayOutCnt(dayOutCnt + 1);
//
//			Integer monOutCnt = accSac.getMonOutCnt();
//			monOutCnt = initCnt(monOutCnt);
//			accSac.setMonOutCnt(monOutCnt + 1);
//		}
//		if (sac_pay.indexOf(txnCode) >= 0) {
//			logger.info("更新子账户-消费");
//			BigDecimal dayPayAmt = accSac.getDayPayAmt();
//			dayPayAmt = initAmt(dayPayAmt);
//			accSac.setDayPayAmt(dayPayAmt.add(amt));
//
//			BigDecimal monPayAmt = accSac.getMonPayAmt();
//			monPayAmt = initAmt(monPayAmt);
//			accSac.setMonPayAmt(monPayAmt.add(amt));
//
//			Integer dayPayCnt = accSac.getDayPayCnt();
//			dayPayCnt = initCnt(dayPayCnt);
//			accSac.setDayPayCnt(dayPayCnt + 1);
//
//			Integer monPayCnt = accSac.getMonPayCnt();
//			monPayCnt = initCnt(monPayCnt);
//			accSac.setMonPayCnt(monPayCnt + 1);
//		}
//		if (sac_varecharge.indexOf(txnCode) >= 0) {
//			logger.info("更新子账户-充值");
//			BigDecimal dayVarchargeAmt = accSac.getDayVarchargeAmt();
//			dayVarchargeAmt = initAmt(dayVarchargeAmt);
//			accSac.setDayVarchargeAmt(dayVarchargeAmt.add(amt));
//
//			BigDecimal monVarchargeAmt = accSac.getMonVarchargeAmt();
//			monVarchargeAmt = initAmt(monVarchargeAmt);
//			accSac.setMonVarchargeAmt(monVarchargeAmt.add(amt));
//
//			Integer dayVarchargeCnt = accSac.getDayVarchargeCnt();
//			dayVarchargeCnt = initCnt(dayVarchargeCnt);
//			accSac.setDayVarchargeCnt(dayVarchargeCnt + 1);
//
//			Integer monVarchargeCnt = accSac.getMonVarchargeCnt();
//			monVarchargeCnt = initCnt(monVarchargeCnt);
//			accSac.setMonVarchargeCnt(monVarchargeCnt + 1);
//		}
////		accSacDao.updateAccSac(accSac);
//		accLimitCacheService.setAccSac(accSac);
//	}

	/**
	 * 检验金额
	 * 
	 * @param oriAmt
	 * @param amt
	 * @param limitAmt
	 * @return
	 */
	private boolean checkAmtLimit(BigDecimal oriAmt, BigDecimal amt,
			BigDecimal limitAmt) {
		logger.info("进入金额校验，oriAmt : {}, amt : {}, limitAmt : {}", oriAmt, amt,
				limitAmt);
		if (limitAmt != null && limitAmt.compareTo(BigDecimal.ZERO) > 0) {// 限制金额大于0才交易
			if(oriAmt == null){
				oriAmt = BigDecimal.ZERO;
			}
			if (oriAmt.add(amt).compareTo(limitAmt) == 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检验交易笔数
	 * 
	 * @param oriCnt
	 * @param limitCnt
	 * @return
	 */
	private boolean checkCntLimit(Integer oriCnt, Integer limitCnt) {
		if (limitCnt != null && limitCnt > 0) {// 限制次数必须大于0才校验
			if(oriCnt == null){
				oriCnt = 0;
			}
			if (oriCnt + 1 > limitCnt) {
				return false;
			}
		}
		return true;
	}

	private BigDecimal initAmt(BigDecimal amt) {
		if (amt == null) {
			return BigDecimal.ZERO;
		}
		return amt;
	}

	private int initCnt(Integer cnt) {
		if (cnt == null) {
			return 0;
		}
		return cnt;
	}

	@Override
	public AccLimit checkAccLimit( AccAcc accAcc,
			BigDecimal amt, String txnCode, Long mchId) {
		AccLimit accLimit = accLimitDao.selectBySacIdAndTradeDateForUpdate(accAcc.getSacId(), DateUtils.getCurrentDate());
		if(accLimit == null){
			accLimit = createAccLimit( accAcc, amt, txnCode, mchId);
			if(accLimit == null){
				logger.warn("商户未配置该交易的限制信息，mchId : " + mchId 
						+ ", sacType : " + accAcc.getSacType());
				return null;
			}
		}
		if (sac_cash.indexOf(txnCode) >= 0) {
			logger.info("子账户-提现校验");
			// 金额校验
			BigDecimal dayCashAmt = accLimit.getDayCashAmt();
			BigDecimal dayCashlmtAmt = accLimit.getDayCashlmtAmt();
			if (!checkAmtLimit(dayCashAmt, amt, dayCashlmtAmt)) {
				logger.error("子账户-日提现校验，金额超过限制");
				throw new AtpBusinessException(RespCodeConstant.RespCode_5000001,
						RespCodeConstant.RespCode_5000001Desc+ ", sacId : " + accAcc.getSacId()
						+ ", dayCashAmt : " + dayCashAmt
						+ ", dayCashlmtAmt : " + dayCashlmtAmt);
			}
			Integer dayInCnt = accLimit.getDayCashCnt();
			Integer dayInlmtCnt = accLimit.getDayCashlmtCnt();
			if (!checkCntLimit(dayInCnt, dayInlmtCnt)) {// 限制次数必须大于0才校验
				logger.info("子账户-日转入校验，笔数超过限制");
				throw new AtpBusinessException(RespCodeConstant.RespCode_5000002,
						RespCodeConstant.RespCode_5000002Desc
						+ ", sacId : " + accAcc.getSacId()
						+ ", dayInCnt : " + dayInCnt
						+ ", dayInlmtCnt : " + dayInlmtCnt);
			}	
		}
			
		return accLimit;
	}

	/**
	 * 创建账户控制信息
	 * @param accAcc
	 * @param amt
	 * @param txnCode
	 * @return
	 */
	private AccLimit createAccLimit( AccAcc accAcc,
			BigDecimal amt, String txnCode, Long mchId) {
		AccLimit accLimit = new AccLimit();
		AccTypeMchLimit defaultLimit = accTypeMchLimitDao.getByMchIdAndSacType(mchId, accAcc.getSacType());
		if(defaultLimit == null){
			return null;
		}
		accLimit.setDayCashlmtAmt(defaultLimit.getDayCashlmtAmt());
		accLimit.setDayCashlmtCnt(defaultLimit.getDayCashlmtCnt());
		
		accLimit.setId(Long.valueOf(idCodeGenerator.getId()));
		accLimit.setPacId(accAcc.getPacId());
		accLimit.setSacId(Long.valueOf(accAcc.getSacId()));
		accLimit.setSacType(accAcc.getSacType());
		accLimit.setTradeDate(DateUtils.getCurrentDate());
		accLimit.setVersion(0);
		try{
			accLimitDao.save(accLimit);
		}catch(Exception e){
			logger.warn("账户控制创建异常",e);
		}
		// 拦住异常，防止多个线程同时创建
		// 加行锁
		accLimit = accLimitDao.selectBySacIdAndTradeDateForUpdate(String.valueOf(accLimit.getSacId()), accLimit.getTradeDate());
		if(accLimit == null){
			throw new AtpBusinessException(RespCodeConstant.RespCode_5000005, RespCodeConstant.RespCode_5000005Desc
					+ "sacId : " + accAcc.getSacId()
					+ ", tradeDate : " + DateUtils.getCurrentDate());
		}
		return accLimit;
	}

	/**
	 * 更新账户限制信息
	 */
	@Override
	public void updateAccLimit(AccLimit accLimit, AccAcc accAcc, BigDecimal amt,
			String txnCode, Integer cnt) throws Exception {
		if(accLimit == null){
			logger.warn("限制信息为空");
			return ;
		}
		if (sac_cash.indexOf(txnCode) >= 0) {
			BigDecimal dayCashAmt = accLimit.getDayCashAmt();
			dayCashAmt = initAmt(dayCashAmt);
			accLimit.setDayCashAmt(dayCashAmt.add(amt));
			Integer dayCashCnt = accLimit.getDayCashCnt();
			dayCashCnt = initCnt(dayCashCnt);
			accLimit.setDayCashCnt(dayCashCnt + cnt);	
			accLimitDao.update(accLimit);
		}
	}
}
