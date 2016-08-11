package com.ink.user.service.handle;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.ModifyUserBaseInfoInput;
import com.ink.user.api.model.in.ModifyUserIdInfoInput;
import com.ink.user.api.model.in.ModifyUserMobileInfoInput;
import com.ink.user.api.model.in.OpenAccountInput;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.dao.IAccAccDao;
import com.ink.user.core.dao.IAccCustDao;
import com.ink.user.core.dao.IAccCustProofDao;
import com.ink.user.core.po.AccAcc;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.AccCustProof;
import com.ink.user.core.po.ReqLog;

/**
 * 账户相关业务实现
 * @author yangchen
 * @date 2016年3月1日 下午5:35:34
 */
@Service("accountBusinessHandle")
public class AccountBusinessHandle extends AbstractBusinessHandle{
	
	private static YinkerLogger logger = YinkerLogger.getLogger(AccountBusinessHandle.class);
	@Autowired
	private IAccCustDao accCustDao;
	@Autowired
	private IAccAccDao accAccDao;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private IAccCustProofDao accCustProofDao;
	
	/**
	 * 开户
	 * @param reqLog
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AccCust openAccount(ReqLog reqLog, OpenAccountInput dto) throws Exception{
		// 记录用户信息
		AccCust accCust = accCustDao.insertAccCust(reqLog, dto);
		// 创建账户主账号
		accAccDao.createAccAcc(accCust, "");
		// 创建定期账户
		accAccDao.createAccAcc(accCust, AtpTnsConstant.RegularAccSubType);
		// 创建余额账户
		accAccDao.createAccAcc(accCust, AtpTnsConstant.BalAccSubType);
		// 创建活期账户
		accAccDao.createAccAcc(accCust, AtpTnsConstant.CurrentAccSubType);
		// 创建体验金账户
		accAccDao.createAccAcc(accCust, AtpTnsConstant.GoldRecoveryAccSubType);
		return accCust;
	}
	
	/**
	 * 更新客户的证件信息
	 * @param accCust
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public AccCust changeCustIdInfo(AccCust accCust, ModifyUserIdInfoInput dto) throws Exception{
		if(changeCustIdOriInfoCheck(accCust, dto)){
			AccCustProof accCustProof = initAccCustProof(accCust, dto.getIdType(), dto.getIdNo(), dto.getMblNo());

			accCust.setIdNo(dto.getIdNo());
			accCust.setIdType(dto.getIdType());
			accCustDao.updateAccCust(accCust);
			// 记录修改记录
			accCustProofDao.insertAccCustProof(accCustProof, dto.getTxnCode());
		}else{
			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"个人证件信息修改，原证件信息或手机号不匹配");
			throw new AtpBusinessException(RespCodeConstant.RespCode_5000003, RespCodeConstant.RespCode_5000003Desc);
		}
		return accCust;
	}
	
	/**
	 * 更新客户基本信息
	 * @param accCust
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public AccCust changeCustBaseInfo(AccCust accCust, ModifyUserBaseInfoInput dto) throws Exception{
		if(checkOriInfo(accCust.getIdType(), accCust.getIdNo(), accCust.getMblNo(),
				dto.getIdType(), dto.getIdNo(), dto.getMblNo())){

			BeanCopier dtoCopier = BeanCopier.create(ModifyUserBaseInfoInput.class,
					AccCust.class, false);
			dtoCopier.copy(dto, accCust, null);
			accCustDao.updateAccCust(accCust);
		}
		return accCust;
	}
	
	private AccCustProof initAccCustProof(AccCust accCust, String idType, String idNo, String mblNo) {
		AccCustProof accCustProof = new AccCustProof();
		accCustProof.setId(Long.valueOf(idCodeGenerator.getId()));
		accCustProof.setMblNo(mblNo);
		accCustProof.setBeforeMblNo(accCust.getMblNo());
		accCustProof.setBeforeIdNo(accCust.getMblNo());
		accCustProof.setBeforeIdType(accCust.getIdType());
		Date now = new Date();
		accCustProof.setCreateTime(now);
		accCustProof.setCustId(accCust.getCustId());
		accCustProof.setIdNo(idNo);
		accCustProof.setIdType(idType);
		accCustProof.setLastUpdateTime(now);
		accCustProof.setUid(accCust.getUid());
		return accCustProof;
	}

	/**
	 * 更改客户手机号
	 * @param accCust
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public AccCust changeCustMblNoInfo(AccCust accCust, ModifyUserMobileInfoInput dto) throws Exception{
		
		if(changeCustMobNoOriInfoCheck(accCust, dto)){
			AccCustProof accCustProof = initAccCustProof(accCust, dto.getIdType(), dto.getIdNo(), dto.getMblNo());

			accCust.setMblNo(dto.getMblNo());
			accCustDao.updateAccCust(accCust);
			
			accCustProofDao.insertAccCustProof(accCustProof, dto.getTxnCode());
		}else{
			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"个人手机号修改，证件信息或原手机号不匹配");
			throw new AtpBusinessException(RespCodeConstant.RespCode_5000003, RespCodeConstant.RespCode_5000003Desc);
		}
		return accCust;
	}

	/**
	 * 检查原始信息是否匹配
	 * 
	 * @param accCust
	 * @param dto
	 * @return
	 */
	protected boolean changeCustIdOriInfoCheck(AccCust accCust, ModifyUserIdInfoInput dto) {
		return checkOriInfo(accCust.getIdType(), accCust.getIdNo(),
				accCust.getMblNo(), dto.getOriIdType(), dto.getOriIdNo(),
				dto.getMblNo());
	}
	
	/**
	 * 更改客户手机号的原资料校验
	 * 
	 * @param accCust
	 * @param dto
	 * @return
	 */
	protected boolean changeCustMobNoOriInfoCheck(AccCust accCust,
			ModifyUserMobileInfoInput dto) {
		return checkOriInfo(accCust.getIdType(), accCust.getIdNo(),
				accCust.getMblNo(), dto.getIdType(), dto.getIdNo(),
				dto.getOriMblNo());
	}
	
	/**
	 * 检查账户，如果不存在就开户
	 * @param accCust
	 * @param accountType
	 * @param upItemId
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 60)
	public AccAcc createAccIfNotExist(AccCust accCust, String accountType, String upItemId) throws Exception{
		AccAcc accAcc = accAccDao.selectAccAccByPacIdAndSacTypeWithBLOBs(accCust.getPacId(),
				accountType);
		// 账户不存在开户
		if(accAcc == null){
			try{
				accAcc = accAccDao.createAccAcc(accCust,accountType);
			}catch(Exception e){
				logger.info(UserLoggerCnst.USER_RECHARGE_MOUDLE,"开户异常，可能是账户已经存在，子账户号：" + accCust.getPacId() + accountType + e.getMessage());
			}
			accAcc = accAccDao.selectAccAccByPacIdAndSacTypeWithBLOBs(accCust.getPacId(),
					accountType);
		}
		return accAcc;
	}
}
