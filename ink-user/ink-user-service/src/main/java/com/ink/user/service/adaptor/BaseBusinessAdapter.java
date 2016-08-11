package com.ink.user.service.adaptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.core.dao.mongo.AccInterestHistoryMapper;
import com.ink.user.core.dao.mongo.GoldGrantHistoryMapper;
import com.ink.user.core.dao.mongo.GoldRecoveryHistoryMapper;
import com.ink.user.service.handle.AccountBusinessHandle;
import com.ink.user.service.handle.CardBusinessHandle;
import com.ink.user.service.handle.FundBusinessHandle;

@Service("baseBusinessAdapter")
public class BaseBusinessAdapter{

	@Autowired
	private CardBusinessHandle cardBusinessHandle;
	@Autowired
	private AccountBusinessHandle accountBusinessHandle;
	@Autowired
	private FundBusinessHandle fundBusinessHandle;
	@Autowired
	private AccInterestHistoryMapper accInterestHistoryDao;
	@Autowired
	private GoldRecoveryHistoryMapper goldRecoveryHistoryDao;
	@Autowired
	private GoldGrantHistoryMapper goldGrantHistoryDao;
	/**
	 * 个人开户操作
	 * @param reqLog
	 * @param dto
	 * @return
	 * @throws Exception
	 */
//	public AccCust openAccount(ReqLog reqLog, ACC38040Input dto) throws Exception{
//		return accountBusinessHandle.openAccount(reqLog, dto);
//	}
	
	/**
	 * 个人银行卡绑卡操作
	 * @param accCust
	 * @param dto
	 * @return
	 * @throws Exception
	 */
//	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public AccCard bindCard(AccCust accCust, ACC38070Input dto) throws Exception {
//		return cardBusinessHandle.bindCard(accCust, dto);
//	}

	/**
	 * 个人银行卡解绑操作
	 * @param uid
	 * @param cardNo
	 */
//	public void unbundlCard(Long uid, String cardNo) {
//		cardBusinessHandle.unbundleCard(uid, cardNo);
//	}

	/**
	 * 账户充值类操作，如余额充值、银行卡购买活期、银行卡购买定期
	 * @param map
	 * @throws Exception
	 */
//	public void fundAccount(Map<String, Object> map) throws Exception {
//		fundBusinessHandle.fundAccount(map);
//	}

	/**
	 * 红包发放
	 * @param map
	 * @throws Exception
	 */
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 60)
//	public void redBagAccount(Map<String, Object> map) throws Exception{
//		fundBusinessHandle.redBagAccount(map);
//	}
	
//	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
//	public  void withdrawAccepted(Map<String, Object> maps) throws Exception{
//		fundBusinessHandle.withdrawAccepted(maps);
////		freezeThaw(maps);
////		fundAccount(maps);
//	}
	
	
	/** 资金冻结和解冻操作 **/
//	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 60)
//	public void freezeThaw(Map<String, Object> maps) throws Exception {
//		fundBusinessHandle.freezeThaw(maps);
//	}

	/**
	 * 转账
	 * @throws Exception
	 */
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 60)
//	public void transferAccount(Map<String, Object> map) throws Exception {
//		fundBusinessHandle.transferAccount(map);
//	}
	
	/**
	 * 商户转账
	 * @throws Exception
	 */
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 60)
//	public void mchTransferAccount(Map<String, Object> map) throws Exception {
//		fundBusinessHandle.mchTransferAccount(map);
//	}

	/**
	 * 计息
	 * 
	 * @param interest
	 * @param tnsLog
	 * @throws Exception
	 */
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 60)
//	public void interest(Map<String, Object> map) throws Exception {
//		fundBusinessHandle.interest(map);
//	}
	
	/**
	 * 体验金回收
	 * @param interest
	 * @param tnsLog
	 * @throws Exception
	 */
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 60)
//	public void experienceGoldRecovery(Map<String, Object> map) throws Exception {
//		fundBusinessHandle.experienceGoldRecovery(map);
//	}
	/**
	 * 体验金发放
	 * @param map
	 * @throws Exception
	 */
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 60)
//	public void experientceGoldGrant(Map<String, Object> map) throws Exception {
//		fundBusinessHandle.experienceGoldGrant(map);
//	}
	
	/**
	 * 更换证件号
	 * @param map
	 * @throws Exception 
	 */
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 60)
//	public void changeId(Map<String, Object> map, ACC38010Input dto) throws Exception{
//		AccCust accCust = (AccCust)map.get("accCust");
//		// 1、只更新客户资料，2、只更新银行卡，3、客户资料和银行卡都更新
//		boolean flag = false;
//		if(dto.getScope().equals(AtpTnsConstant.SCOPE_1) || dto.getScope().equals(AtpTnsConstant.SCOPE_3)){
//			flag = true;
//			accountBusinessHandle.changeCustIdInfo( accCust, dto);
//		}
//		if(dto.getScope().equals(AtpTnsConstant.SCOPE_2) || dto.getScope().equals(AtpTnsConstant.SCOPE_3)){
//			flag = true;
//			cardBusinessHandle.changeCardIdInfo(accCust, dto);
//		}
//		if(!flag){
//			logger.error("个人证件修改，修改作用域有误，scope : {}", dto.getScope());
//			throw new AtpBusinessException(RespCode.RespCode_5000004, RespCode.RespCode_5000004Desc);
//		}
//	}
	
	/**
	 * 更换手机号
	 * @param map
	 * @param dto
	 * @throws Exception 
	 */
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 60)
//	public void changeMblNo(Map<String, Object> map, ACC38020Input dto) throws Exception{
//		AccCust accCust = (AccCust)map.get("accCust");
//		boolean flag = false;
//		// 1、只更新客户资料，2、只更新银行卡，3、客户资料和银行卡都更新
//		if(dto.getScope().equals(AtpTnsConstant.SCOPE_1) || dto.getScope().equals(AtpTnsConstant.SCOPE_3)){
//			flag = true;
//			accountBusinessHandle.changeCustMblNoInfo( accCust, dto);
//		}
//		if(dto.getScope().equals(AtpTnsConstant.SCOPE_2) || dto.getScope().equals(AtpTnsConstant.SCOPE_3)){
//			flag = true;
//			cardBusinessHandle.changeCardMblNoInfo(accCust, dto);
//		}
//		if(!flag){
//			logger.error("个人手机号修改，修改作用域有误，scope : {}", dto.getScope());
//			throw new AtpBusinessException(RespCode.RespCode_5000004, RespCode.RespCode_5000004Desc);
//		}
//	}
	
	
//	public AccAcc createAccIfNotExist(AccCust accCust, String accountType, String upItemId) throws Exception{
//		return accountBusinessHandle.createAccIfNotExist(accCust, accountType, upItemId);
//	}
}
