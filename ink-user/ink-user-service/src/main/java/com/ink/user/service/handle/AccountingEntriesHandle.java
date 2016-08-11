//package com.ink.user.service.handle;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.ink.pats.atp.api.dto.vo.InterestBean;
//import com.ink.pats.atp.common.constant.AtpTnsConstant;
//import com.ink.pats.atp.common.constant.RespCode;
//import com.ink.pats.atp.common.exception.AtpBusinessException;
//import com.ink.pats.atp.model.acc.AccAcc;
//import com.ink.pats.atp.model.acc.AccCust;
//import com.ink.pats.atp.model.acc.AccIac;
//import com.ink.pats.atp.model.acc.AccItem;
//import com.ink.pats.atp.model.acc.AccMch;
//import com.ink.pats.atp.model.acc.AccOrg;
//import com.ink.pats.atp.model.tns.TnsAce;
//import com.ink.pats.atp.model.tns.TnsAceGen;
//import com.ink.pats.atp.model.tns.TnsLog;
//import com.ink.pats.atp.service.api.acc.IAccAccService;
//import com.ink.pats.atp.service.api.acc.IAccIacService;
//import com.ink.pats.atp.service.api.acc.IAccItemService;
//import com.ink.pats.atp.service.api.tns.ITnsAceGenService;
//import com.ink.pats.atp.service.api.tns.ITnsAceService;
//import com.ink.pats.atp.service.redis.AccAccJobRedisService;
//import com.ink.pats.atp.service.redis.AccIacJobRedisService;
//import com.ink.pats.atp.service.timing.launcher.TimingJobLauncher;
//
//public class AccountingEntriesHandle extends AbstractBusinessHandle{
//
//	Logger logger = LoggerFactory.getLogger(AccountingEntriesHandle.class);
//
//	@Autowired
//	private IAccAccService accAccService;
//	@Autowired
//	private IAccIacService accIacService;
//	@Autowired
//	private IAccItemService accItemService;
//	@Autowired
//	private ITnsAceGenService tnsAceGenService;
//	@Autowired
//	private ITnsAceService tnsAceService;
//	@Autowired
//	private AccIacJobRedisService accIacJobRedisService;
//	@Autowired
//	private AccAccJobRedisService accAccJobRedisService;
//	@Autowired
//	private AmqpTemplate amqpTemplate;
//	@Autowired
//	private TimingJobLauncher timingJobLauncher;
//	public void accountingEntries(Map<String, Object> map) throws Exception {
//		AccCust accCust = (AccCust) map.get("accCust");
//		TnsLog tnsLog = (TnsLog) map.get("tnsLog");
//		String upItemId = map.get("upItemId").toString();
//		AccOrg accOrg = (AccOrg) map.get("accOrg");
//		AccAcc accAcc = (AccAcc) map.get("accAcc");
//		AccMch accMch = (AccMch) map.get("accMch");
//		// 检查上级科目是否存在
//		AccItem accItem = accItemService.selectAccItemByItemIdAndCur(upItemId,
//				AtpTnsConstant.CUR_CNY);
//		// 检查内部账户是否存在
//		AccIac accIac = accIacService.selectAccIacByUpIemId(accOrg,
//				AtpTnsConstant.CUR_CNY, upItemId, tnsLog.getAccDepositType());
//		if (accIac == null) {
//			accIac = accIacService.createAccIac(accOrg, AtpTnsConstant.CUR_CNY,
//					upItemId, null, tnsLog.getAccDepositType(),
//					accItem.getItemDir());
//		}
//		List<TnsAceGen> tnsAceGenList = tnsAceGenService
//				.getTnsAceGenByTxnCodeAndAceGroup(tnsLog.getTxnCode());
//		// 判断会计分录是否是成对出现
//		if (tnsAceGenList.size() % 2 != 0) {
//			throw new AtpBusinessException(RespCode.RespCode_000005,
//					RespCode.RespCode_000005Desc);
//		}
//		AccAcc accToMch = null;
//		if(accMch != null){
//			accToMch = accAccService.selectAccAccByPacIdAndSacTypeWithBLOBs(
//					Long.parseLong(accMch.getFiller1()), "0006");
//		}
//		
//		// 生成会计分录和资金划转
//		for (TnsAceGen tnsAceGen : tnsAceGenList) {
//			TnsAce tnsAce = tnsAceService.createTnsAceForIac(tnsAceGen, tnsLog,
//					accCust, accAcc, accIac, accOrg, accMch, accToMch);
//			updateFundsAboutAcc(tnsAce, accAcc, accToMch);// 更新商户账户信息
//			updateFundsAboutIac(tnsAce, Long.parseLong(accIac.getIacId()));
//		}
//
//	}
//
//	// 更新资金账户
//
//	private void updateFundsAboutAcc(TnsAce tnsAce, AccAcc accAcc,
//			AccAcc accToMch) throws Exception {
//		Long sacId = accAcc.getSacId() == null ? accAcc.getPacId() : Long.valueOf(accAcc
//				.getSacId());
//		if (tnsAce.getAccSubAmtId().equals(sacId)) {
//			tnsAce.setBal(accAcc.getCurBal());
//			tnsAce.setInFlg(AtpTnsConstant.IN_FLG_1);
//			tnsAceService.updateTnsAce(tnsAce);
//		}
//		if(accToMch != null){
//			Long mchSacId = accToMch.getSacId() == null ? accToMch.getPacId()
//					: Long.valueOf(accToMch.getSacId());
//			if (tnsAce.getAccSubAmtId().equals(mchSacId)) {
//				// 更新商户账户余额信息
//				accAccService.updateAccAcc(accToMch, tnsAce.getAmt());
//				tnsAce.setBal(accToMch.getCurBal());
//				// 交易代码
//				tnsAce.setInFlg(AtpTnsConstant.IN_FLG_1);
//				accAccJobRedisService.pushAccToRedisList(accToMch, tnsAce.getAmt(), tnsAce);
//				timingJobLauncher.increase(TimingJobLauncher.AccAccJobKey);
//			}
//		}
//	}
//
//	// 更新渠道账户
//	private void updateFundsAboutIac(TnsAce tnsAce, Long iacId)
//			throws Exception {
//		AccIac accIac = accIacService.selectAccIacByIacId(iacId);
//		if (tnsAce.getAccAmtId().equals(Long.valueOf(accIac.getIacId()))) {
//			// 更新渠道账户渠道金额，放到redis中，不用上面注释掉的代码了
//			accIacJobRedisService.pushIacToRedisList(accIac, tnsAce.getAmt(),
//					tnsAce);
//			timingJobLauncher.increase(TimingJobLauncher.AccIacJobKey);
//
//		}
//	}
//
////	private void saveMongo(TnsAce tnsAce) {
////		String tnsAceJson = JSON.toJSONString(tnsAce);
////		amqpTemplate.convertAndSend("tnsAceLog.queue", tnsAceJson);
////	}
//
//	public void doAccountTransfer(Map<String, Object> map) throws Exception {
//		TnsLog tnsLog = (TnsLog) map.get("tnsLog");
//		AccAcc accAcc = (AccAcc) map.get("accAcc");
//		AccAcc agaAcc = (AccAcc) map.get("agaAcc");
//		AccCust accCust = (AccCust) map.get("accCust");
//		AccCust agaCust = (AccCust) map.get("agaCust");
//
//		// 生成会计分录
//		List<TnsAceGen> tnsAceGenList = tnsAceGenService
//				.getTnsAceGenByTxnCodeAndAceGroup(tnsLog.getTxnCode());
//		// 根据会计分录进行资金划转
//		for (TnsAceGen tnsAceGen : tnsAceGenList) {
//			TnsAce tnsAce = tnsAceService.createTnsAceForAcc(tnsAceGen, tnsLog,
//					accCust, agaCust, accAcc, agaAcc);
//			if (tnsAce.getAccId() == accAcc.getId()) {
//				transferFundsAboutAcc(tnsAce, accAcc, agaAcc);
//			} else {
//				transferFundsAboutAcc(tnsAce, agaAcc, accAcc);
//			}
//		}
//	}
//
//	private void transferFundsAboutAcc(TnsAce tnsAce, AccAcc accAcc,
//			AccAcc agaAcc) throws Exception {
//		if (tnsAce.getRuntimeFlg() == AtpTnsConstant.RUNTIME_FLG_0
//				|| tnsAce.getInFlg() == AtpTnsConstant.IN_FLG_0) {
//			if ((tnsAce.getAccType() == AtpTnsConstant.ACC_TYPE_CUST || tnsAce
//					.getAccType() == AtpTnsConstant.ACC_TYPE_MCH)
//					&& (tnsAce.getAgaType() == AtpTnsConstant.ACC_TYPE_CUST || tnsAce
//							.getAgaType() == AtpTnsConstant.ACC_TYPE_MCH)
//					&& tnsAce.getAccSubAmtId().equals(Long.valueOf(accAcc.getSacId()))) {
//					tnsAce.setBal(accAcc.getCurBal());
//					// 交易代码
//					tnsAce.setInFlg(AtpTnsConstant.IN_FLG_1);
//					// 更新会计分录中的账户余额信息
//					tnsAceService.updateTnsAce(tnsAce);
//			} else if ((tnsAce.getAccType() == AtpTnsConstant.ACC_TYPE_CUST || tnsAce
//					.getAccType() == AtpTnsConstant.ACC_TYPE_MCH)
//					&& (tnsAce.getAgaType() == AtpTnsConstant.ACC_TYPE_CUST || tnsAce
//							.getAgaType() == AtpTnsConstant.ACC_TYPE_MCH)
//					&& tnsAce.getAccSubAmtId().equals(Long.valueOf(agaAcc.getSacId()))) {
//				
//					tnsAce.setBal(agaAcc.getCurBal());
//					// 交易代码
//					tnsAce.setInFlg(AtpTnsConstant.IN_FLG_1);
//					// 更新会计分录中的账户余额信息
//					tnsAceService.updateTnsAce(tnsAce);
//			} else {
//				logger.error("系统出现异常！");
//			}
//		}
//	}
//
//	public void redBagAccountingEntries(Map<String, Object> map)
//			throws Exception {
//		TnsLog tnsLog = (TnsLog) map.get("tnsLog");
//		AccAcc accAcc = (AccAcc) map.get("accAcc");
//		AccAcc agaAcc = (AccAcc) map.get("agaAcc");
//		AccCust accCust = (AccCust) map.get("accCust");
//		AccCust agaCust = (AccCust) map.get("agaCust");
//		AccOrg accOrg = (AccOrg) map.get("accOrg");
//		String upItemId = map.get("upItemId").toString();
//		String agaUpItemId = map.get("agaUpItemId").toString();
//
//		// 检查上级科目是否存在
//		AccItem accItem = accItemService.selectAccItemByItemIdAndCur(upItemId,
//				AtpTnsConstant.CUR_CNY);
//		// 检查内部账户是否存在
//		AccIac accIac = accIacService.selectAccIacByUpIemId(accOrg,
//				AtpTnsConstant.CUR_CNY, upItemId, tnsLog.getAccDepositType());
//		if (accIac == null) {
//			accIac = accIacService.createAccIac(accOrg, AtpTnsConstant.CUR_CNY,
//					upItemId, null, tnsLog.getAccDepositType(),
//					accItem.getItemDir());
//		}
//
//		// 检查上级科目是否存在
//		AccItem agaAccItem = accItemService.selectAccItemByItemIdAndCur(agaUpItemId,
//				AtpTnsConstant.CUR_CNY);
//		// 检查内部账户是否存在
//		AccIac agaAccIac = accIacService.selectAccIacByUpIemId(accOrg,
//				AtpTnsConstant.CUR_CNY, agaUpItemId, tnsLog.getAccDepositType());
//		if (agaAccIac == null) {
//			agaAccIac = accIacService.createAccIac(accOrg, AtpTnsConstant.CUR_CNY,
//					agaUpItemId, null, tnsLog.getAccDepositType(),
//					agaAccItem.getItemDir());
//		}
//
//		// 生成会计分录
//		List<TnsAceGen> tnsAceGenList = tnsAceGenService
//				.getTnsAceGenByTxnCodeAndAceGroup(tnsLog.getTxnCode());
//		// 根据会计分录进行资金划转
//		for (TnsAceGen tnsAceGen : tnsAceGenList) {
//			TnsAce tnsAce = tnsAceService.redBagcreateTnsAce(tnsAceGen, tnsLog,
//					accCust, agaCust, accAcc, agaAcc, accOrg, accIac, agaAccIac);
//			if (tnsAce.getAccSubAmtId().equals(Long.valueOf(accAcc.getSacId()))) {// 更新商户
//					// 更新商户账户余额信息
////				accAccService.updateAccAcc(accAcc, tnsAce.getAmt().negate());
//				tnsAce.setBal(accAcc.getCurBal());
//				// 交易代码
//				tnsAce.setInFlg(AtpTnsConstant.IN_FLG_1);
//				// 更新会计分录中的账户余额信息
////				tnsAceService.updateTnsAce(tnsAce);
//				accAccJobRedisService.pushAccToRedisList(accAcc, tnsAce.getAmt().negate(), tnsAce);
//				timingJobLauncher.increase(TimingJobLauncher.AccAccJobKey);
//
//			} else if (tnsAce.getAccSubAmtId().equals(Long.valueOf(agaAcc.getSacId()))) {// 更新客户的
//				tnsAce.setBal(agaAcc.getCurBal());
//				// 交易代码
//				tnsAce.setInFlg(AtpTnsConstant.IN_FLG_1);
//				// 更新会计分录中的账户余额信息
//				tnsAceService.updateTnsAce(tnsAce);
//			} else if (tnsAce.getAccSubAmtId().equals(Long.valueOf(accIac.getIacId()))) {// 更新渠道
//				AccIac iac = accIacService.selectAccIacByIacId(Long.parseLong(accIac.getIacId()));
//				if (tnsAce.getAccAmtId().equals(Long.valueOf(iac.getIacId()))) {
////					accIacService.updateAccIac(iac, tnsAce.getAmt());
//					tnsAce.setInFlg(AtpTnsConstant.IN_FLG_1);
//					tnsAce.setBal(iac.getCurBal());
////					tnsAceService.updateTnsAce(tnsAce);
//					
//					// 更新渠道账户渠道金额，放到redis中，不用上面注释掉的代码了
//					accIacJobRedisService.pushIacToRedisList(accIac, tnsAce.getAmt(),
//							tnsAce);
//					timingJobLauncher.increase(TimingJobLauncher.AccIacJobKey);
//
//				}
//			}else if (tnsAce.getAccSubAmtId().equals(Long.valueOf(agaAccIac.getIacId()))) {// 更新渠道
//				AccIac agaIac = accIacService.selectAccIacByIacId(Long.parseLong(agaAccIac.getIacId()));
//				if (tnsAce.getAccAmtId().equals(Long.valueOf(agaIac.getIacId()))) {
////					accIacService.updateAccIac(agaiac, tnsAce.getAmt());
//					tnsAce.setInFlg(AtpTnsConstant.IN_FLG_1);
//					tnsAce.setBal(agaIac.getCurBal());
////					tnsAceService.updateTnsAce(tnsAce);
//					
//					// 更新渠道账户渠道金额，放到redis中，不用上面注释掉的代码了
//					accIacJobRedisService.pushIacToRedisList(accIac, tnsAce.getAmt(),
//							tnsAce);
//					timingJobLauncher.increase(TimingJobLauncher.AccIacJobKey);
//
//				}
//			}
//		}
//	}
//
//	
//	public void interestAccountingEntries(Map<String, Object> map)
//			throws Exception {
//		InterestBean interest = (InterestBean) map.get("interest");
//		AccAcc accAcc = (AccAcc) map.get("accAcc");
//		AccCust accCust = (AccCust) map.get("accCust");
//		String upItemId = "64010301";
//		String txnCode = (String) map.get("txnCode");
//		String ordId = interest.getOrdId();
//		BigDecimal amt = new BigDecimal(interest.getAmt());
//		// 检查上级科目是否存在
//		accItemService.selectAccItemByItemIdAndCur(upItemId,
//				AtpTnsConstant.CUR_CNY);
//
//		// 生成会计分录
//		List<TnsAceGen> tnsAceGenList = tnsAceGenService
//				.getTnsAceGenByTxnCodeAndAceGroup(txnCode);
//		// 根据会计分录进行资金划转
//		for (TnsAceGen tnsAceGen : tnsAceGenList) {
//			tnsAceService.interestCreateTnsAce(tnsAceGen, amt, ordId,
//					accCust, accAcc);
//		}
//	}
//
//}
