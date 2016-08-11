package com.ink.user.controller;

import java.util.ArrayList;
import java.util.List;

import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.model.in.*;
import com.ink.user.api.model.out.OpenAccountOutput;
import com.ink.user.api.service.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.InterestBean;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.util.DateUtils;

/**
 * 测试用controller
 * @author yangchen
 * @date 2016年5月13日 下午6:00:24
 */
@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
    IdCodeGenerator idCodeGenerator;
	@Autowired
	private final ThreadLocal<IAccRechargeService> accRechargeService = new ThreadLocal<>();
	@Autowired
	private IAccWithdrawFreezeService accWithdrawFreezeService;
	@Autowired
	private IAccWithdrawAcceptService accWithdrawAcceptService;
	@Autowired
	private IAccTransferService accTransferService;
	@Autowired
	private IAccInterestService accInterestService;
	@Autowired
	private IExperienceGoldGrantService experienceGoldGrantService;
	@Autowired
	private IMchAcountTransferService mchAcountTransferService;
	@Autowired
	private IOpenAccountService openAccountService;
	
	@Autowired
	private static YinkerLogger logger = YinkerLogger.getLogger(TestController.class);

	/**
	 * 充值
	 * @param ordId
	 * @param custId
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/accRecharge")
	@ResponseBody
	public String accRecharge(String ordId, String custId) throws Exception{

		String txnCode = "AR";//银行卡购买活期
		String mchId="1000000000002";
		String tradeDate = DateUtils.getDateTime();
		String amt = "0.01";
		String custFee = "0.00";
		String sacType="0001";
		String cardNo="622880199437863789";
		String channelId="3000201595439683960";
		
		AccRechargeInput dto = new AccRechargeInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setOrdId(ordId);
		dto.setTradeDate(tradeDate);
		dto.setAmt(amt);
		dto.setCustFee(custFee);
		dto.setAccountType(sacType);
		dto.setCardNo(cardNo);
		dto.setChannelId(channelId);
		dto.setMac(DigestUtils.md5Hex("com.ink.trade!user"+ordId));
		accRechargeService.get().exec(dto);
		
		return "success";
	}

	/**
	 * 提现冻结
	 * @param ordId
	 * @param custId
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/accWithdrawFreeze")
	@ResponseBody
	public String accWithdrawFreeze(String ordId, String custId) throws Exception{
		String txnCode = "AWF";
		String mchId="1000000000002";
		String tradeDate = DateUtils.getDateTime();
		String amt = "0.01";
		String custFee = "0.00";
		String sacType="0001";
		String cardNo="622880199437863789";
		String channelId="3000201595439683960";
		
		AccWithdrawFreezeInput dto = new AccWithdrawFreezeInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setOrdId(ordId);
		dto.setTradeDate(tradeDate);
		dto.setAmt(amt);
		dto.setCustFee(custFee);
		dto.setAccountType(sacType);
		dto.setCardNo(cardNo);
		dto.setChannelId(channelId);
		dto.setMac(DigestUtils.md5Hex("com.ink.trade!user"+ordId));
		
		Long start = System.currentTimeMillis();
		accWithdrawFreezeService.exec(dto);
		Long end = System.currentTimeMillis();
		logger.debug("-------测试冻结时间为:"+String.valueOf(end-start)+"---------");
		return "success";
	}


	/**
	 * 提现受理
	 * @param oriOrdId
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/accWithdrawAccept")
	@ResponseBody
	public String accWithdrawAccept(String oriOrdId) throws Exception{
		String txnCode = "AWA";
		String mchId="1000000000002";
		Long start2 = System.currentTimeMillis();
		String ordId = idCodeGenerator.getId();
		Long end2 = System.currentTimeMillis();
		logger.debug("-------测试ID生成时间为:"+String.valueOf(end2-start2)+"---------");
		String tradeDate = DateUtils.getDateTime();
		String oritradeDate= "20160513154307";
		String channelId="3000201529439671604";
		String amt = "0.01";
		String custFee = "0.00";
		String oriTxnCode="AWF";
		
		AccWithdrawAcceptInput dto = new AccWithdrawAcceptInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setOrdId(ordId);
		dto.setOriTradeDate(oritradeDate);
		dto.setOriOrdId(oriOrdId);
		dto.setTradeDate(tradeDate);
		dto.setChannelId(channelId);
		dto.setAmt(amt);
		dto.setOriTxnCode(oriTxnCode);
		dto.setCustFee(custFee);
		dto.setMac(DigestUtils.md5Hex("com.ink.trade!user"+ordId));
		
		Long start = System.currentTimeMillis();
		accWithdrawAcceptService.exec(dto);
		Long end = System.currentTimeMillis();
		logger.debug("-------测试受理时间为:"+String.valueOf(end-start)+"---------");
		return "success";
	}

	/**
	 * 提现
	 * @param custId
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/withdraw")
	@ResponseBody
	public String withdraw(String custId) throws Exception{
		Long start = System.currentTimeMillis();
		String ordId = getAcc30210OrdId(custId);
		accWithdrawAccept(ordId);
		Long end = System.currentTimeMillis();
		logger.debug("-------测试提现时间为:"+String.valueOf(end-start)+"---------");
		return "success";
	}
	
	public String getAcc30210OrdId(String custId) throws Exception{
		
		String txnCode = "AWF";
		String mchId="1000000000002";
		String tradeDate = DateUtils.getDateTime();
		String amt = "0.01";
		String custFee = "0.00";
		String sacType="0001";
		String cardNo="622880199437863789";
		String channelId="3000201595439683960";
		
		AccWithdrawFreezeInput dto = new AccWithdrawFreezeInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		String ordId = idCodeGenerator.getId();
		dto.setOrdId(ordId);
		dto.setTradeDate(tradeDate);
		dto.setAmt(amt);
		dto.setCustFee(custFee);
		dto.setAccountType(sacType);
		dto.setCardNo(cardNo);
		dto.setChannelId(channelId);
		dto.setMac(DigestUtils.md5Hex("com.ink.trade!user"+ordId));
		
		Long start = System.currentTimeMillis();
		accWithdrawFreezeService.exec(dto);
		Long end = System.currentTimeMillis();
		logger.debug("-------测试冻结时间为:"+String.valueOf(end-start)+"---------");
		return ordId;
	}
	
	/**
	 * @Description: 转账
	 * @param custId
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("/accTransfer")
	@ResponseBody
	public String accTransfer(String custId) throws Exception{
		String txnCode = "AT";
		String mchId="1000000000002";
		String ordId = idCodeGenerator.getId();
		String tradeDate = DateUtils.getDateTime();
		String amt = "0.01";
		String custFee = "0.00";
		String sacType="0001";
		String agaSacType="0003";
		
		AccTransferInput dto = new AccTransferInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setOrdId(ordId);
		dto.setTradeDate(tradeDate);
		dto.setAmt(amt);
		dto.setAgaSacType(agaSacType);
		dto.setCustFee(custFee);
		dto.setAccountType(sacType);
		
		RetOutput ret = accTransferService.exec(dto);
		System.out.println(ret.toString());
		return "success";
	}
	
	/**
	 * @Description: 活期计息
	 * @param custId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/accInterest")
	@ResponseBody
	public String accInterest(String custId) throws Exception{
		String txnCode = "AI";
		String ordId = idCodeGenerator.getId();
		String tradeDate = DateUtils.getDateTime();
		String mchId="1000000000002";

		AccInterestInput dto = new AccInterestInput();
		dto.setTxnCode(txnCode);
		dto.setId(ordId);
		dto.setTradeDate(tradeDate);
		dto.setMchId(mchId);
		List<InterestBean> list = get30420List(custId) ;
		dto.setList(list);
		String ret = accInterestService.exec(dto).toString();
		System.out.println(ret);
		return "success";
	}
	
	private List<InterestBean> get30420List(String custId) {
		List<InterestBean> list = new ArrayList<InterestBean>();
		InterestBean interest = new InterestBean();
		interest.setAccountType("0001");
		interest.setAmt("0.01");
		interest.setCustId(custId);
		String ordId = idCodeGenerator.getId();
		interest.setOrdId(ordId);
		String tradeDate = DateUtils.getDateTime();
		interest.setTradeDate(tradeDate);
		interest.setUid("");
		list.add(interest);
		return list;
	}

	/**
	 * 体验金发放
	 * @param custId
	 * @throws Exception
     */
	@RequestMapping("/experienceGoldGrant")
	@ResponseBody
	public void ExperienceGoldGrant(String custId) throws Exception {
		String txnCode = "EGG";
		String mchId="1000000000002";
		String tradeDate = DateUtils.getDateTime();
		String amt = "0.01";
		String sacType="0004";
		String ordId = idCodeGenerator.getId();

		ExperienceGoldGrantInput dto = new ExperienceGoldGrantInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setTradeDate(tradeDate);
		dto.setAmt(amt);
		dto.setAccountType(sacType);
		dto.setOrdId(ordId);
		RetOutput ret = experienceGoldGrantService.exec(dto);
		System.out.println(ret.toString());
	}

	/**
	 * 内部转账
	 * @param custId
	 * @throws Exception
     */
	@RequestMapping("/mchAcountTransfer")
	@ResponseBody
	public void MchAcountTransfer(String custId) throws Exception {
		MchAcountTransferInput dto = new MchAcountTransferInput();
		dto.setTxnCode("MAT");
		dto.setOrdId(idCodeGenerator.getId());
		dto.setTradeDate(DateUtils.getDateTime());
		dto.setAmt("0.01");

		dto.setCustId(custId);
		dto.setCustAccountType("0001");
		dto.setMchId("1000000000002");
		dto.setMchAccountType("0011");
		dto.setDir(AtpTnsConstant.DIR_CREDIT);
		RetOutput ret = mchAcountTransferService.exec(dto);
		System.out.println(ret.toString());
	}

	@RequestMapping("/openAccount")
	@ResponseBody
	public void openAccount(String custId) throws Exception {
		String txnCode = "OA";//个人开户
		String mchId="1000000000002";
		String tradeDate = DateUtils.getDateTime();
		String custName = "whtest";
		String custType = "0";
		String idType = "01";
		String idNo="13012519890327656";
		String mblNo="18513441234";
		String ordId = idCodeGenerator.getId();
		OpenAccountInput dto = new OpenAccountInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setTradeDate(tradeDate);
		dto.setCustName(custName);
		dto.setIdType(idType);
		dto.setIdNo(idNo);
		dto.setMblNo(mblNo);
		dto.setOrdId(ordId);
		dto.setCustType(custType);
		OpenAccountOutput ret= openAccountService.exec(dto);
		System.out.println(ret.toString());
	}
	
}
