package com.ink.user.service.impl;

import java.util.Map;

import com.ink.user.service.handle.CardBusinessHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.BindCardInput;
import com.ink.user.api.model.out.BindCardOutput;
import com.ink.user.api.service.IBindCardService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.AccCard;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.IReqLogManager;
import com.ink.user.util.DateUtils;

/**
 * @Description: 个人账户银行卡绑定
 * @author wanghao
 * @date 2016年5月24日 下午5:21:07
 */
public class BindCardServiceImpl extends AbstractAccServiceImpl<BindCardOutput, BindCardInput> implements IBindCardService{
	@Autowired
	private IReqLogManager reqLogManager;
	@Autowired
	private CardBusinessHandle cardBusinessHandle;
	private static YinkerLogger logger = YinkerLogger.getLogger(BindCardServiceImpl.class);
	
	@Override
	public BindCardOutput exec(BindCardInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_BC, 
				"进入个人账户银行卡绑定........BindCard#exec(tnsLog:" + dto.toString() + ")");
		BindCardOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_BC, ret.toString());
		return ret;
	}
	public void onMessage(String msg) throws Exception {
		try{
			logger.info(UserLoggerCnst.USER_BINDCAD_MOUDLE, "MQ同步绑卡数据参数，" + msg);
			if(msg == null){
				logger.error(UserLoggerCnst.USER_BINDCAD_MOUDLE, "进入个人账户银行卡绑定.....ACC38070#exec()， msg 为空");
			}
			BindCardInput dto = JSON.parseObject(msg, BindCardInput.class);
			BindCardOutput ret = doExec(dto);
			logger.info(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_BC, ret.toString());
		}catch(Exception e){
			logger.error(UserLoggerCnst.USER_BINDCAD_MOUDLE, "进入个人账户银行卡绑定.....BindCard#exec()， msg 解析出错");
		}
	}

	public BindCardOutput doExec(BindCardInput dto) throws Exception {
		BindCardOutput retDTO = new BindCardOutput();
		ReqLog reqLog = new ReqLog();
		try {
			String json = JSON.toJSONString(dto);
			//检查信息
			Map<String, Object> map = check(AtpTnsConstant.ACC_BC, json);
			insertTnsLog(null, reqLog, json, (TnsTxn) map.get("tnsTxn"));
			
			AccCust accCust = (AccCust) map.get("accCust");
			AccCard accCard = cardBusinessHandle.bindCard(accCust, dto);
			String cardNo = accCard.getCardNo();
			StringBuffer cardNo2 = new StringBuffer()
					.append(cardNo.substring(0, 4))
					.append("******")
					.append(cardNo.substring(cardNo.length() - 4,
							cardNo.length()));
			retDTO.setCardNo(cardNo2.toString());// 需要对卡号加密
			retDTO.setBindCardId(accCard.getBindCardId().toString());
			retDTO.setRetCode(RespCodeConstant.RespCode_000000);
			retDTO.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_BC, 
					"个人账户银行卡绑定（ACC_BC）异常！"+e.getMessage(), e, dto.getOrdId());
			retDTO.setRetCode(e.getCode());
			retDTO.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_BC, 
					"个人账户银行卡绑定（ACC_BC）异常！"+e.getMessage(), e, dto.getOrdId());
			retDTO.setRetCode(RespCodeConstant.RespCode_200000);
			retDTO.setRetMsg(RespCodeConstant.RespCode_200000Desc);
		} finally {
			if (reqLog.getId() != null) {
				reqLog.setRetCode(retDTO.getRetCode());
				reqLog.setRetMsg(retDTO.getRetMsg());
				reqLogManager.update(reqLog);
				retDTO.setAccTnsId(reqLog.getId().toString());
			}
			// 更新流水和报文信息
			retDTO.setTradeDate(DateUtils.getDateTime());
		}
		return retDTO;
	}

	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception {
		BindCardInput dto = JSON.parseObject(json, BindCardInput.class);
		// 数据拷贝
		BeanCopier copier = BeanCopier.create(BindCardInput.class, ReqLog.class,
				false);
		copier.copy(dto, reqLog, null);
		BeanCopier tsnTxncopier = BeanCopier.create(TnsTxn.class, ReqLog.class,
				false);
		tsnTxncopier.copy(tnsTxn, reqLog, null);
		reqLog.setMchId(Long.parseLong(dto.getMchId()));
		reqLog.setOrdDate(DateUtils.strToDateLong(dto.getTradeDate()));
		reqLogManager.insert(reqLog);

	}
}
