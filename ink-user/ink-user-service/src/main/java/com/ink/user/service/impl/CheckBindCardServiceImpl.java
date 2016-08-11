package com.ink.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.CheckBindCardInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.ICheckBindCardService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.util.DateUtils;

/**
 * 绑卡检查接口
 * @author yangchen
 * @date 2016年4月21日 下午4:44:47
 */
public class CheckBindCardServiceImpl extends AbstractAccServiceImpl<RetOutput, CheckBindCardInput> implements ICheckBindCardService{
	private static YinkerLogger logger = YinkerLogger.getLogger(CheckBindCardServiceImpl.class);
	
	@Override
	public RetOutput exec(CheckBindCardInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_CBC, 
				"进入个人账户银行卡绑定前置检测........CheckBindCard#exec(tnsLog:" + dto.toString() + ")");
		RetOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_CBC, ret.toString());
		return ret;
	}
	
	public RetOutput doExec(CheckBindCardInput dto) throws Exception {
		RetOutput retDTO = new RetOutput();
		try {
			String json = JSON.toJSONString(dto);
			// 检查信息
			check(AtpTnsConstant.ACC_CBC, json);
			//
			retDTO.setRetCode(RespCodeConstant.RespCode_000000);
			retDTO.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_CBC, 
					"个人账户（ACC_CBC）异常！"+e.getMessage(), e, dto.getOrdId());
			retDTO.setRetCode(e.getCode());
			retDTO.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_BINDCAD_MOUDLE,UserLoggerCnst.USER_CBC, 
					"个人账户（ACC_CBC）异常！"+e.getMessage(), e, dto.getOrdId());
			retDTO.setRetCode(RespCodeConstant.RespCode_200000);
			retDTO.setRetMsg(RespCodeConstant.RespCode_200000Desc);
		} finally {
			// 更新流水和报文信息
			retDTO.setTradeDate(DateUtils.getDateTime());
		}
		return retDTO;
	}

	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception {
	}

}
