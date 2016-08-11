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
import com.ink.user.api.model.in.UnbundCardInput;
import com.ink.user.api.model.out.UnbundCardOutput;
import com.ink.user.api.service.IUnbundCardService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.IReqLogManager;
import com.ink.user.util.DateUtils;

/**
 * @Description: 个人账户银行卡解绑
 * @author wanghao
 * @date 2016年5月24日 下午5:21:21
 */
public class UnbundCardServiceImpl extends AbstractAccServiceImpl<UnbundCardOutput, UnbundCardInput> implements IUnbundCardService{
	@Autowired
	private IReqLogManager reqLogManager;
	@Autowired
	private CardBusinessHandle cardBusinessHandle;
	private static YinkerLogger logger = YinkerLogger.getLogger(UnbundCardServiceImpl.class);
	
	@Override
	public UnbundCardOutput exec(UnbundCardInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_UC, 
				"进入个人账户银行卡解绑........UnbundCard#exec(dto:" + dto.toString() + ")");
		UnbundCardOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_UC, ret.toString());
		return ret;
	}
	public void onMessage(String msg) throws Exception {
		try{
			logger.info(UserLoggerCnst.USER_RECHARGE_MOUDLE, "MQ进入个人账户银行卡解绑，" + msg);
			if(msg == null){
				logger.error(UserLoggerCnst.USER_BINDCAD_MOUDLE, "进入个人账户银行卡解绑.....UnbundCard#exec()， msg 为空");
			}
			UnbundCardInput dto = JSON.parseObject(msg, UnbundCardInput.class);
			UnbundCardOutput ret = doExec(dto);
			logger.info(UserLoggerCnst.USER_RECHARGE_MOUDLE,
					UserLoggerCnst.USER_UC ,ret.toString());
		}catch(Exception e){
			logger.error(UserLoggerCnst.USER_BINDCAD_MOUDLE, "进入个人账户银行卡绑定.....UnbundCard#exec()， msg 解析出错");
		}
	}

	public UnbundCardOutput doExec(UnbundCardInput dto) throws Exception {
		UnbundCardOutput retDTO = new UnbundCardOutput();
		ReqLog reqLog = new ReqLog();
		try {
			String json = JSON.toJSONString(dto);
			//检查操作
			Map<String, Object> map = check(AtpTnsConstant.ACC_UC, json);
			insertTnsLog(null, reqLog, json, (TnsTxn) map.get("tnsTxn"));
			
			AccCust accCust = (AccCust) map.get("accCust");
			//解绑逻辑处理
			cardBusinessHandle.unbundleCard(accCust.getUid(), dto.getCardNo());

			String cardNo = dto.getCardNo();
			StringBuffer cardNo2 = new StringBuffer()
					.append(cardNo.substring(0, 4))
					.append("******")
					.append(cardNo.substring(cardNo.length() - 4,
							cardNo.length()));
			retDTO.setCardNo(cardNo2.toString());// 需要对卡号加密
			retDTO.setRetCode(RespCodeConstant.RespCode_000000);// 返回码
			retDTO.setRetMsg(RespCodeConstant.RespCode_000000Desc);// 返回描述
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_UC, 
					"个人账户银行卡解绑（ACC_UC）异常！"+e.getMessage(), e, dto.getOrdId());
			retDTO.setRetCode(e.getCode());
			retDTO.setRetMsg(e.getMessage());
		} catch (Exception e) {
			retDTO.setRetCode(RespCodeConstant.RespCode_200000);
			retDTO.setRetMsg(RespCodeConstant.RespCode_200000Desc);
			logger.error(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_UC, 
					"个人账户银行卡解绑（ACC_UC）异常！"+e.getMessage(), e, dto.getOrdId());
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
		UnbundCardInput dto = JSON.parseObject(json, UnbundCardInput.class);
		// 数据拷贝
		BeanCopier copier = BeanCopier.create(UnbundCardInput.class, ReqLog.class,
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
