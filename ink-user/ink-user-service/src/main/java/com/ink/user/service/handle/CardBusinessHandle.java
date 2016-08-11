package com.ink.user.service.handle;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.BindCardInput;
import com.ink.user.core.dao.IAccCardDao;
import com.ink.user.core.dao.IAccCardProofDao;
import com.ink.user.core.dao.IAccCustDao;
import com.ink.user.core.po.AccCard;
import com.ink.user.core.po.AccCust;

/**
 * 卡相关业务实现
 * @author yangchen
 * @date 2016年3月1日 下午5:35:48
 */
@Service("cardBusinessHandle")
public class CardBusinessHandle extends AbstractBusinessHandle{
	
	@Autowired
	private IAccCardDao accCardDao;
	@Autowired
	private IAccCustDao accCustDao;
	@Autowired
	private IAccCardProofDao accCardProofDao;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	//个人银行卡绑卡操作
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 60)
	public AccCard bindCard(AccCust accCust, BindCardInput dto) throws Exception {
		changeCard(accCust);
		AccCard accCard = accCardDao.insertAccCard(dto, accCust);
		// 更新客户信息
		accCust.setCustName(dto.getCustName());
		accCust.setIdNo(dto.getIdNo());
		accCust.setIdType(dto.getIdType());
		accCust.setLastUpdateTime(new Date());
		accCustDao.updateAccCust(accCust);
		return accCard;
	}
	
	// 如果该客户用卡信息，则认为是换卡操作
	private void changeCard(AccCust accCust) throws AtpBusinessException {
		// 一个客户只能绑定一张卡
		AccCard card = accCardDao.checkBindCard(accCust.getUid(),
				AtpTnsConstant.ACC_CARD_STATUS_1);
		if (card != null) {
			// 进行换卡操作//换卡之前先把原来的卡解绑然后在添加一条新记录
			accCardDao.updateAccCardStatus(accCust.getUid(),
					card.getCardNo(),
					AtpTnsConstant.ACC_CARD_STATUS_2.toString(),
					card.getVersion());// 状态9：注销
		}
	}
	
	/**
	 * 解绑
	 * @param uid
	 * @param cardNo
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class, timeout = 60)
	public void unbundleCard(Long uid, String cardNo) {
		// 3.判断该商户下卡号是否存在
		AccCard accCard = accCardDao.checkAccCardIsExist(uid, cardNo);
		if (accCard == null) {
			throw new AtpBusinessException(RespCodeConstant.RespCode_400008,
					RespCodeConstant.RespCode_400008Desc);
		}
		// 4.解绑操作
		accCardDao.updateAccCardStatus(uid, cardNo,
				AtpTnsConstant.ACC_CARD_STATUS_2.toString(),
				accCard.getVersion());// 状态9：注销
	}
	
	/**
	 * 更换卡的证件信息
	 * @param accCust
	 * @param dto
	 */
//	public AccCard changeCardIdInfo(AccCust accCust, ACC38010Input dto){
//		// 判断该商户下卡号是否存在
//		Long uid = Long.valueOf(accCust.getUid());
//		String cardNo = dto.getCardNo();
//		AccCard accCard = accCardDao.checkAccCardIsExist(uid, cardNo);
//		if (accCard == null) {
//			throw new AtpBusinessException(RespCode.RespCode_400008,
//					RespCode.RespCode_400008Desc);
//		}
//		if(changeCardIdOriInfo(accCard, dto)){
//			
//			AccCardProof accCardProof = initAccCardProof(accCard, dto.getIdType(), dto.getIdNo(), dto.getMblNo());
//			accCard.setIdNo(dto.getIdNo());
//			accCard.setIdType(dto.getIdType());
//			accCard.setVersion(accCard.getVersion() + 1);
//			accCardDao.updateAccCard(accCard);
//			// 记录修改记录
//			accCardProofDao.insertAccCardProof(accCardProof, dto.getTxnCode());
//			return accCard;
//		}else{
//			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"个人证件信息修改，原证件信息或手机号不匹配");
//			throw new AtpBusinessException(RespCode.RespCode_5000003, RespCode.RespCode_5000003Desc);
//		}
//	}
	
//	private AccCardProof initAccCardProof(AccCard accCard, String idType, String idNo, String mblNo) {
//		AccCardProof accCardProof = new AccCardProof();
//		accCardProof.setId(Long.valueOf(idCodeGenerator.getId()));
//		accCardProof.setBankMblNo(mblNo);
//		accCardProof.setBeforeBankMblNo(accCard.getBankMblNo());
//		accCardProof.setBeforeIdNo(accCard.getIdNo());
//		accCardProof.setBeforeIdType(accCard.getIdType());
//		accCardProof.setBindCardId(accCard.getBindCardId());
//		Date now = new Date();
//		accCardProof.setCreateTime(now);
//		accCardProof.setCardNo(accCard.getCardNo());
//		accCardProof.setCustId(Long.valueOf(accCard.getCustId()));
//		accCardProof.setIdNo(idNo);
//		accCardProof.setIdType(idType);
//		accCardProof.setLastUpdateTime(now);
//		return accCardProof;
//	}

//	public AccCard changeCardMblNoInfo(AccCust accCust, ACC38020Input dto){
//		Long uid = Long.valueOf(accCust.getUid());
//		String cardNo = dto.getCardNo();
//		AccCard accCard = accCardDao.checkAccCardIsExist(uid, cardNo);
//		if (accCard == null) {
//			throw new AtpBusinessException(RespCode.RespCode_400008,
//					RespCode.RespCode_400008Desc);
//		}
//		if(changeCardMblNoOriInfoCheck(accCard, dto)){
//			AccCardProof accCardProof = initAccCardProof(accCard, dto.getIdType(), dto.getIdNo(), dto.getMblNo());
//
//			accCard.setBankMblNo(dto.getMblNo());
//			accCard.setVersion(accCard.getVersion() + 1);
//			accCardDao.updateAccCard(accCard);
//			
//			accCardProofDao.insertAccCardProof(accCardProof, dto.getTxnCode());
//			return accCard;
//		}else{
//			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"个人证件信息修改，原证件信息或手机号不匹配");
//			throw new AtpBusinessException(RespCode.RespCode_5000003, RespCode.RespCode_5000003Desc);
//		}
//	}
	
	/**
	 * 检查原始信息是否匹配
	 * 
	 * @param accCard
	 * @param dto
	 * @return
	 */
//	protected boolean changeCardIdOriInfo(AccCard accCard, ACC38010Input dto) {
//		return checkOriInfo(accCard.getIdType(), accCard.getIdNo(),
//				accCard.getBankMblNo(), dto.getOriIdType(), dto.getOriIdNo(),
//				dto.getMblNo());
//	}

	/**
	 * 更改卡手机号的原资料校验
	 * 
	 * @param accCard
	 * @param dto
	 * @return
	 */
//	private boolean changeCardMblNoOriInfoCheck(AccCard accCard,
//			ACC38020Input dto) {
//		return checkOriInfo(accCard.getIdType(), accCard.getIdNo(),
//				accCard.getBankMblNo(), dto.getIdType(), dto.getIdNo(),
//				dto.getOriMblNo());
//	}	
}
