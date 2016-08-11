package com.ink.user.service.handle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.ink.user.service.limit.IAccountLimitService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.GoldGrantBean;
import com.ink.user.api.model.GoldRecoveryBean;
import com.ink.user.api.model.InterestBean;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.dao.IAccAccDao;
import com.ink.user.core.dao.IAccCustDao;
import com.ink.user.core.dao.IAccMchDao;
import com.ink.user.core.dao.IAccMchProofDao;
import com.ink.user.core.dao.IAccProofDao;
import com.ink.user.core.dao.IAccSacTypeDao;
import com.ink.user.core.dao.mongo.AccInterestHistoryMapper;
import com.ink.user.core.dao.mongo.GoldGrantHistoryMapper;
import com.ink.user.core.dao.mongo.GoldRecoveryHistoryMapper;
import com.ink.user.core.po.AccAcc;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.AccLimit;
import com.ink.user.core.po.AccMch;
import com.ink.user.core.po.AccMchProof;
import com.ink.user.core.po.AccSacType;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.po.mongo.AccInterestHistory;
import com.ink.user.core.po.mongo.GoldGrantHistory;
import com.ink.user.core.po.mongo.GoldRecoveryHistory;
import com.ink.user.core.service.redis.AccSacTypeCacheService;

/**
 * 资金相关业务处理
 * 
 * @author yangchen
 * @date 2016年3月1日 下午5:48:33
 */
@Service("fundBusinessHandle")
public class FundBusinessHandle extends AbstractBusinessHandle {

	private static YinkerLogger logger = YinkerLogger
			.getLogger(FundBusinessHandle.class);
	@Autowired
	private IAccAccDao accAccDao;
	@Autowired
	private IAccountLimitService accountLimitService;
	@Autowired
	private IAccSacTypeDao accSacTypeDao;
	@Autowired
	private AccSacTypeCacheService accSacTypeCacheService;
	@Autowired
	private IAccProofDao accProofDao;
	@Autowired
	private AmqpTemplate amqpTemplate;
	@Autowired
	private IAccMchProofDao accMchProofDao;
	@Autowired
	private IAccMchDao accMchDao;
	@Autowired
	private IAccCustDao accCustDao;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private AccountBusinessHandle accountBusinessHandle;
	@Autowired
	private AccInterestHistoryMapper accInterestHistoryDao;
	@Autowired
	private GoldRecoveryHistoryMapper goldRecoveryHistoryDao;
	@Autowired
	private GoldGrantHistoryMapper goldGrantHistoryDao;
	private static final String recharge = AtpTnsConstant.ACC_AR + "|";

	private static final String freezes = AtpTnsConstant.ACC_AWF + "|";

	private static final String unfrozen = AtpTnsConstant.ACC_AWA + "|"
			+ AtpTnsConstant.ACC_AWC + "|" ;

	/**
	 * 充值AR
	 * @param map
	 * @throws Exception
     */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
	public void fundAccount(Map<String, Object> map) throws Exception {
		AccCust accCust = (AccCust) map.get("accCust");
		TnsLog tnsLog = (TnsLog) map.get("tnsLog");
		String subType = (String) map.get("subType");
		BigDecimal sumAmt = tnsLog.getAmt().add(tnsLog.getCustFee());
		// 检查子账户类型
		checkSacType(subType);

		// 检查资金账号
		AccAcc accAcc = accAccDao.checkByPacIdAndSacType(accCust, subType);
		// 检查账户控制
		AccLimit accLimit = accountLimitService.checkAccLimit(accAcc, sumAmt,
				tnsLog.getTxnCode(), accCust.getMchId());

		// 判断金额正负（根据交易代码判断，之前是从会计分录配置信息判断）
		if (unfrozen.indexOf(tnsLog.getTxnCode()) >= 0) {
			sumAmt = sumAmt.negate();
		}

		// 更新账户金额
		updateAccAmtForRecharge(accAcc, sumAmt);

		// 更新账户控制表
		if (recharge.indexOf(tnsLog.getTxnCode()) >= 0) {
			accountLimitService.updateAccLimit(accLimit, accAcc, sumAmt.abs(),
					tnsLog.getTxnCode(), 1);
		}
		// 记录资金凭证
		accProofDao.insertAccProof(accAcc, tnsLog.getTxnCode());

		// 活期增加借款人账户金额
		if (tnsLog.getTxnCode().equals(AtpTnsConstant.ACC_AR) && subType.equals(AtpTnsConstant.CustFeeAccSubType)) {
			saveAccMchProof(accAcc, tnsLog, AtpTnsConstant.DIR_DEBIT, AtpTnsConstant.MchBorrowerAccSubType);
		} else if (tnsLog.getTxnCode().equals(AtpTnsConstant.ACC_AWA) && subType.equals(AtpTnsConstant.CustFeeAccSubType)) {// 活期提现减少借款人账户余额
			saveAccMchProof(accAcc, tnsLog, AtpTnsConstant.DIR_CREDIT,  AtpTnsConstant.MchBorrowerAccSubType);
		}
	}

	/**
	 * 检查子账户类型
	 * 
	 * @param subType
	 */
	private void checkSacType(String subType) {
		AccSacType sacType = accSacTypeCacheService.getAccSacTypeCache(subType);
//		if ( sacType== null) {
//			AccSacType sacType = accSacTypeDao.getAccSacTypeBySacType(subType);
			if (sacType == null) {
				throw new AtpBusinessException(RespCodeConstant.RespCode_400013,
						RespCodeConstant.RespCode_400013Desc + ",sacType : " + subType);
			} 
//			else {
//				accSacTypeCacheService.setAccSacTypeCache(sacType);
//			}
//		}
	}

	// public void redBagAccount(Map<String, Object> map) throws Exception{
	// AccCust accCust = (AccCust) map.get("accCust");
	// TnsLog tnsLog = (TnsLog) map.get("tnsLog");
	// String upItemId = map.get("upItemId").toString();
	// BigDecimal amt = tnsLog.getAmt();
	// AccCust agaCust = (AccCust) map.get("agaCust");
	// // 检查目标账户资金账号
	// AccAcc accAcc = accAccDao.selectByPacIdAndSacType(accCust,
	// tnsLog.getAccSubType());
	// if (accAcc == null) {
	// throw new AtpBusinessException(RespCode.ErrorCode_00004,
	// RespCode.ErrorCode_00004Desc);
	// }
	//
	// AccAcc agaAcc = createAccIfNotExist(agaCust, tnsLog.getAgaSubType(),
	// upItemId);
	//
	// // 发放红包不检查账户控制
	//
	// // 更新账户金额
	// updateAccAmt(agaAcc, amt);
	//
	// // 在会计分录中，会批量更新商户金额，所以在这里不更新
	//
	// // 记录资金凭证
	// accProofDao.insertAccProof(agaAcc, tnsLog.getTxnCode());
	// // map.put("agaAcc", agaAcc);
	// accProofDao.insertAccProof(accAcc, tnsLog.getTxnCode());
	// // map.put("accAcc", accAcc);
	// // amqpTemplate.convertAndSend("redBagAccounting.key", map);
	// }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
	public void transferAccount(Map<String, Object> map) throws Exception {
		AccCust accCust = (AccCust) map.get("accCust");
		AccCust agaCust = (AccCust) map.get("agaCust");
		TnsLog tnsLog = (TnsLog) map.get("tnsLog");
		// 检查转出账户
		AccAcc accAcc = accAccDao.checkByPacIdAndSacType(accCust,
				tnsLog.getAccSubType());
		// 检查转入账户
		AccAcc agaAcc = accAccDao.checkByPacIdAndSacType(agaCust,
				tnsLog.getAgaSubType());

		// 更新转出账户金额
		updateAccAmtForRecharge(accAcc, tnsLog.getAmt().negate());
		// 更新转入账户金额
		updateAccAmtForRecharge(agaAcc, tnsLog.getAmt());

		map.put("agaAcc", agaAcc);
		map.put("accAcc", accAcc);
		map.put("agaCust", agaCust);
		// 记录资金凭证
		accProofDao.insertAccProof(accAcc, tnsLog.getTxnCode());
		// 记录资金凭证
		accProofDao.insertAccProof(agaAcc, tnsLog.getTxnCode());

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
	public void mchTransferAccount(Map<String, Object> map) throws Exception {
		//客户账户
		AccCust accCust = (AccCust) map.get("accCust");
		TnsLog tnsLog = (TnsLog) map.get("tnsLog");
		// 检查商户账户acc_acc
		createAccMchIfNotExist(tnsLog);

		AccAcc accAcc = null;
		if (AtpTnsConstant.DIR_CREDIT.equals(tnsLog.getDir())) {
			// 客户减 商户加
			// 检查客户账户并更新余额(减)
			accAcc = accAccDao.checkByPacIdAndSacType(accCust,
					tnsLog.getAgaSubType());
			updateAccAmtForRecharge(accAcc, tnsLog.getAmt().negate());
			// 记录凭证并更新商户金额(加)
			saveAccMchProof(accAcc, tnsLog, AtpTnsConstant.DIR_CREDIT,
					tnsLog.getAccSubType());
		} else if (AtpTnsConstant.DIR_DEBIT.equals(tnsLog.getDir())) {
			// 客户加 商户减
			//检查商户账户并更新余额(减)
			accAcc = accAccDao.checkByPacIdAndSacType(accCust,
					tnsLog.getAgaSubType());
			updateAccAmtForRecharge(accAcc, tnsLog.getAmt());
			//记录凭证并更新客户金额(加)
			saveAccMchProof(accAcc, tnsLog, AtpTnsConstant.DIR_DEBIT,
					tnsLog.getAccSubType());
		}
		// 记录资金凭证
		accProofDao.insertAccProof(accAcc, tnsLog.getTxnCode());
	}

	private void saveAccMchProof(AccAcc accAcc, TnsLog tnsLog, String dir,
			String type) throws Exception {
		AccMchProof accMchProof = new AccMchProof();
		BigDecimal amt = tnsLog.getAmt();
		accMchProof.setAmt(amt);
		Date date = new Date();
		accMchProof.setCreateTime(date);
		accMchProof.setDir(dir);
		accMchProof.setId(Long.valueOf(idCodeGenerator.getId()));
		accMchProof.setLastUpdateTime(date);
		accMchProof.setMchId(tnsLog.getMchId());
		accMchProof.setPacId(accAcc.getPacId());
		accMchProof.setSacId(Long.valueOf(accAcc.getSacId()));
		accMchProof.setSacType(type);
		accMchProof.setTxnCode(tnsLog.getTxnCode());
		accMchProofDao.save(accMchProof);
		if(AtpTnsConstant.DIR_DEBIT.equals(tnsLog.getDir())){
			amt = amt.negate();
		}
		// 更新商户账户金额
		int i = accAccDao.updateMchAccount(amt, tnsLog.getMchId(), type);
		if(i == 0){
			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "","商户账户余额更新失败", tnsLog.getOrdId());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
	public void freezeThaw(Map<String, Object> maps) throws Exception {
		TnsLog oriTnsLog = (TnsLog) maps.get("oriTnsLog");
		AccCust accCust = (AccCust) maps.get("accCust");
		String subType = (String) maps.get("subType");
		String code = (String) maps.get("code");
		BigDecimal amt = oriTnsLog.getAmt();
		BigDecimal custFee = oriTnsLog.getCustFee();

		AccAcc accAcc = accAccDao.checkByPacIdAndSacType(accCust, subType);

		// 提现申请时，如果手续费 > 0，则冻结金额要加上手续费
		// 冲正时，如果手续费 > 0，则解冻金额要加上手续费
		if (custFee != null && custFee.compareTo(BigDecimal.ZERO) == 1) {
			amt = amt.add(custFee);
		}
		BigDecimal balAmt = new BigDecimal(0);
		if (freezes.indexOf(code) >= 0) {
			// 冻结时,判断冻结金额是否大于余额
			balAmt = accAcc.getCurBal().subtract(amt);
		} else if (unfrozen.indexOf(code) >= 0) {
			// 解冻时,判断解冻金额是否大于冻结金额
			balAmt = accAcc.getFrozenAmt().subtract(amt);
		} else {
			throw new AtpBusinessException(RespCodeConstant.RespCode_200000,
					RespCodeConstant.RespCode_200000Desc + "，该交易不应该涉及冻结解冻操作，txnCode : "
							+ code + ", sacId : " + accAcc.getSacId());
		}
		if (balAmt.compareTo(BigDecimal.ZERO) == -1) {
			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,
					"账户[" + accAcc.getSacId() + "]余额不可为负！");
			throw new AtpBusinessException(RespCodeConstant.RespCode_300005,
					RespCodeConstant.RespCode_300005Desc + ", sacId : "
							+ accAcc.getSacId() + "交易后的金额balAmt : " + balAmt);
		}

		freezeOrUnfrozen(accAcc, code, amt, oriTnsLog);
	}

	private void freezeOrUnfrozen(AccAcc accAcc, String code, BigDecimal amt,
			TnsLog oriTnsLog) throws Exception {
		if (freezes.indexOf(code) >= 0) {
			AccLimit accLimit = accountLimitService.checkAccLimit(accAcc, amt,
					oriTnsLog.getTxnCode(), oriTnsLog.getMchId());
			// 更新余额
			accAccDao.updateForFrozenByPrimaryKeyWithBLOBs(accAcc, amt);
			accountLimitService.updateAccLimit(accLimit, accAcc, amt,
					oriTnsLog.getTxnCode(), 1);

		} else if (unfrozen.indexOf(code) >= 0) {
			AccLimit accLimit = accountLimitService.checkAccLimit(accAcc, amt,
					oriTnsLog.getTxnCode(), oriTnsLog.getMchId());
			// 更新余额
			accAccDao
					.updateForFrozenByPrimaryKeyWithBLOBs(accAcc, amt.negate());
			accountLimitService.updateAccLimit(accLimit, accAcc, amt.negate(),
					oriTnsLog.getTxnCode(), -1);
		}

	}

	/**
	 * 回收体验金
	 * 
	 * @param map
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
	public void experienceGoldRecovery(Map<String, Object> map)
			throws Exception {
		GoldRecoveryBean goldRecovery = (GoldRecoveryBean) map
				.get("goldRecovery");
		AccCust accCust = (AccCust) map.get("accCust");
		AccAcc accAcc = accAccDao.checkByPacIdAndSacType(accCust,
				goldRecovery.getAccountType());
		// 更新账户和记录资金凭证
		updateAccAndProof(accAcc,
				new BigDecimal(goldRecovery.getAmt()).negate(),
				((TnsTxn) map.get("tnsTxn")).getTxnCode());
		map.put("accAcc", accAcc);
	}

	/**
	 * 发放体验金
	 * 
	 * @param map
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
	public void experienceGoldGrant(Map<String, Object> map) throws Exception {
		AccCust accCust = (AccCust) map.get("accCust");
		TnsLog tnsLog = (TnsLog) map.get("tnsLog");
		String upItemId = map.get("upItemId").toString();
		String subType = (String) map.get("subType");
		// 检查资金账号
		AccAcc accAcc = accAccDao.checkByPacIdAndSacType(accCust, subType);

		if (accAcc == null) {
			accAcc = accAccDao.createAccAcc(accCust, tnsLog.getAccSubType());
		}

		// 更新账户和资金凭证
		updateAccAndProof(accAcc, tnsLog.getAmt(), tnsLog.getTxnCode());
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
	public void interest(Map<String, Object> map) throws Exception {
		InterestBean interest = (InterestBean) map.get("interest");
		AccCust accCust = (AccCust) map.get("accCust");
		AccAcc accAcc = accAccDao.checkByPacIdAndSacType(accCust,
				interest.getAccountType());
		// 更新账户和资金凭证
		updateAccAndProof(accAcc, new BigDecimal(interest.getAmt()),
				String.valueOf(map.get("txnCode")));
		// 利息账户增加金额
		AccAcc interestAcc = accAccDao.checkByPacIdAndSacType(accCust,
				interest.getAccountType() + "01");
		updateAccAndProof(interestAcc, new BigDecimal(interest.getAmt()),
				String.valueOf(map.get("txnCode")));
	}

	/**
	 * @Description: 检查商户资金账户
	 * @param tnsLog
	 * @return
	 * @throws Exception
	 */
	private AccAcc createAccMchIfNotExist(TnsLog tnsLog) throws Exception {
		AccMch accMch = accMchDao.findByMchId(tnsLog.getMchId());
		AccCust agaCust = accCustDao.selectAccCustByCustIdAndMchId(
				tnsLog.getMchId(), accMch.getCustId());
		if (agaCust == null) {
			throw new AtpBusinessException(RespCodeConstant.RespCode_400020,
					RespCodeConstant.RespCode_400020Desc + ", mchId : "
							+ tnsLog.getMchId());
		}
		AccAcc agaAcc = accAccDao.checkByPacIdAndSacType(agaCust,
				tnsLog.getAccSubType());
		if (agaAcc == null) {
			throw new AtpBusinessException(RespCodeConstant.RespCode_400019,
					RespCodeConstant.RespCode_400019Desc + ", mchId : "
							+ tnsLog.getMchId());
		}
		return agaAcc;
	}

	/**
	 * 更新账户余额和资金凭证
	 * 
	 * @param accAcc
	 * @param amt
	 * @param txnCode
	 * @throws Exception
	 */
	private void updateAccAndProof(AccAcc accAcc, BigDecimal amt, String txnCode)
			throws Exception {
		// 更新账户金额
		updateAccAmtForRecharge(accAcc, amt);
		// 更新资金凭证
		accProofDao.insertAccProof(accAcc, txnCode);
	}

	/**
	 * 更新账户金额，如果更新失败则抛异常
	 * 
	 * @param accAcc
	 * @param amt
	 * @param tnsLog
	 * @throws Exception
	 */
	private void updateAccAmtForRecharge(AccAcc accAcc, BigDecimal amt)
			throws Exception {
		// 更新账户金额
		int count = accAccDao.updateAccAcc(accAcc, amt);
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "更新资金表"+count);
		if (count == 0) {
			String agaJson = JSON.toJSONString(accAcc);
			amqpTemplate.convertAndSend("accLog.queue", agaJson);
			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "资金帐户表失败！accAcc=【"
					+ accAcc.toString() + "】");
			throw new AtpBusinessException(RespCodeConstant.ErrorCode_00001,
					RespCodeConstant.ErrorCode_00001Desc + ", sacId : "
							+ accAcc.getSacId() + ", amt : " + amt);
		}
	}

	/**
	 * 提现受理
	 * 
	 * @param maps 参数集合
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
	public void withdrawAccepted(Map<String, Object> maps) throws Exception {
		TnsLog oriTnsLog = (TnsLog) maps.get("oriTnsLog");
		AccCust accCust = (AccCust) maps.get("accCust");
		String subType = (String) maps.get("subType");
		String code = (String) maps.get("code");
		BigDecimal amt = oriTnsLog.getAmt();
		TnsLog tnsLog = (TnsLog) maps.get("tnsLog");
		BigDecimal custFee = oriTnsLog.getCustFee();

		AccAcc accAcc = accAccDao.checkByPacIdAndSacType(accCust, subType);

		// 提现申请时，如果手续费 > 0，则冻结金额要加上手续费
		// 冲正时，如果手续费 > 0，则解冻金额要加上手续费
		if (custFee != null && custFee.compareTo(BigDecimal.ZERO) == 1) {
			amt = amt.add(custFee);
		}
		BigDecimal balAmt = new BigDecimal(0);
		if (unfrozen.indexOf(code) >= 0) {
			// 解冻时,判断解冻金额是否大于冻结金额
			balAmt = accAcc.getFrozenAmt().subtract(amt);
		} else {
			throw new AtpBusinessException(RespCodeConstant.RespCode_200000,
					RespCodeConstant.RespCode_200000Desc + "，该交易不应该涉及冻结解冻操作，txnCode : "
							+ code + ", sacId : " + accAcc.getSacId());
		}
		if (balAmt.compareTo(BigDecimal.ZERO) == -1) {
			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,
					"账户[" + accAcc.getSacId() + "]余额不可为负！");
			throw new AtpBusinessException(RespCodeConstant.RespCode_300005,
					RespCodeConstant.RespCode_300005Desc + ", sacId : "
							+ accAcc.getSacId() + "交易后的金额balAmt : " + balAmt);
		}

		updateAccAmtForWithdrawAccept(accAcc, amt);
		// 记录资金凭证更新商户余额
		accProofDao.insertAccProof(accAcc, tnsLog.getTxnCode());

		// 活期增加借款人账户金额
		if (tnsLog.getTxnCode().equals(AtpTnsConstant.ACC_AR ) && subType.equals("0001")) {
			saveAccMchProof(accAcc, tnsLog, AtpTnsConstant.DIR_DEBIT, "0011");
		} else if (tnsLog.getTxnCode().equals(AtpTnsConstant.ACC_AWA) && subType.equals("0001")) {// 活期提现减少借款人账户余额
			saveAccMchProof(accAcc, tnsLog, AtpTnsConstant.DIR_CREDIT, "0011");
		}

	}

	private void updateAccAmtForWithdrawAccept(AccAcc accAcc, BigDecimal amt)
			throws Exception {
		// 更新账户金额
		int count = accAccDao.updateForWithdrawAcceptByPrimaryKeyWithBLOBs(
				accAcc, amt);
		if (count == 0) {
			String agaJson = JSON.toJSONString(accAcc);
			amqpTemplate.convertAndSend("accLog.queue", agaJson);
			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "资金帐户表失败！accAcc=【"
					+ accAcc.toString() + "】");
			throw new AtpBusinessException(RespCodeConstant.ErrorCode_00001,
					RespCodeConstant.ErrorCode_00001Desc + ", sacId : "
							+ accAcc.getSacId() + ", amt : " + amt);
		}
	}

	/**
	 * @date 2016年5月5日 下午2:28:52
	 * @Description: 体验金发放历史记录的mongo
	 * @author wanghao
	 * @param map
	 * @param goldGrant
	 * @param txnCode
	 */
	public void saveGoldGrantHistory(Map<String, Object> map,
			GoldGrantBean goldGrant, String txnCode) {
		GoldGrantHistory goldGrantHistory = new GoldGrantHistory();

		goldGrantHistory.setCreateTime(goldGrant.getTradeDate());
		goldGrantHistory.setCustId(goldGrant.getCustId());
		goldGrantHistory.setRecoveryAmt(goldGrant.getAmt());
		goldGrantHistory.setMchId((String) map.get("mchId"));
		goldGrantHistory.setTxnCode(txnCode);
		goldGrantHistory.setRetCode((String) map.get("retCode"));
		goldGrantHistory.setRetMsg((String) map.get("retMsg"));
		goldGrantHistory.setState((String) map.get("state"));
		AccAcc accAcc = (AccAcc) map.get("accAcc");
		if (null != accAcc) {
			goldGrantHistory.setPacId(accAcc.getPacId().toString());
			goldGrantHistory.setCurAmt(accAcc.getCurBal().toString());
			goldGrantHistory.setLasAmt(accAcc.getLstBal().toString());
		}
		goldGrantHistoryDao.save(goldGrantHistory);
	}

	/**
	 * 体验金回收历史记录的mongo
	 * 
	 * @param map
	 * @param goldRecovery
	 * @param txnCode
	 */
	public void saveGoldRecoveryHistory(Map<String, Object> map,
			GoldRecoveryBean goldRecovery, String txnCode) {
		GoldRecoveryHistory goldRecoveryHistory = new GoldRecoveryHistory();

		goldRecoveryHistory.setCreateTime(goldRecovery.getTradeDate());
		goldRecoveryHistory.setCustId(goldRecovery.getCustId());
		goldRecoveryHistory.setRecoveryAmt(goldRecovery.getAmt());
		goldRecoveryHistory.setMchId((String) map.get("mchId"));
		goldRecoveryHistory.setTxnCode(txnCode);
		goldRecoveryHistory.setRetCode((String) map.get("retCode"));
		goldRecoveryHistory.setRetMsg((String) map.get("retMsg"));
		goldRecoveryHistory.setState((String) map.get("state"));
		AccAcc accAcc = (AccAcc) map.get("accAcc");
		if (null != accAcc) {
			goldRecoveryHistory.setPacId(accAcc.getPacId().toString());
			goldRecoveryHistory.setCurAmt(accAcc.getCurBal().toString());
			goldRecoveryHistory.setLasAmt(accAcc.getLstBal().toString());
		}
		goldRecoveryHistoryDao.save(goldRecoveryHistory);
	}

	/**
	 * 计息历史记到mongo
	 * 
	 * @param map
	 * @param interest
	 * @param txnCode
	 */
	public void saveAccInterestHistory(Map<String, Object> map,
			InterestBean interest, String txnCode) {
		AccInterestHistory accInterestHistory = new AccInterestHistory();

		accInterestHistory.setCreateTime(interest.getTradeDate());
		accInterestHistory.setCustId(interest.getCustId());
		accInterestHistory.setInterestAmt(interest.getAmt());
		accInterestHistory.setMchId((String) map.get("mchId"));
		accInterestHistory.setSacType(interest.getAccountType());
		accInterestHistory.setTxnCode(txnCode);
		accInterestHistory.setRetCode((String) map.get("retCode"));
		accInterestHistory.setRetMsg((String) map.get("retMsg"));
		accInterestHistory.setState((String) map.get("state"));
		AccAcc accAcc = (AccAcc) map.get("accAcc");
		if (null != accAcc) {
			accInterestHistory.setPacId(accAcc.getPacId().toString());
			accInterestHistory.setCurAmt(accAcc.getCurBal().toString());
			accInterestHistory.setLasAmt(accAcc.getLstBal().toString());
		}
		accInterestHistoryDao.save(accInterestHistory);
	}
}
