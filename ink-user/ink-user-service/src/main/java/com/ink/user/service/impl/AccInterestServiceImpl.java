package com.ink.user.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.InterestBean;
import com.ink.user.api.model.in.AccInterestInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccInterestService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.redis.AccMchCacheService;
import com.ink.user.core.service.tns.ITnsTxnService;

/**
 * @Description: 活期计息批量接口
 * @author wanghao^_^
 * @date 2016年5月24日 下午5:18:44
 */
public class AccInterestServiceImpl extends AbstractAccServiceImpl<RetOutput, AccInterestInput> implements IAccInterestService{

	@Autowired
	private ITnsTxnService tnsTxnService;
	@Autowired
	private AccMchCacheService accMchCacheService;
	@Autowired
	private AmqpTemplate amqpTemplate;
	private static YinkerLogger logger = YinkerLogger.getLogger(AccInterestServiceImpl.class);
	
	@Override
	public RetOutput exec(AccInterestInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_INTEREST_MOUDLE,UserLoggerCnst.USER_AI, 
				"进入计息批量接口.....AccInterest#exec()方法dto="+dto.toString());
		RetOutput ret = doExec(dto);
		return ret;
	}
	
	public RetOutput doExec(AccInterestInput dto) throws Exception {
		RetOutput ret = new RetOutput();
		try {
			String txnCode = dto.getTxnCode();
			if (!txnCode.equals(AtpTnsConstant.ACC_AI)) {
				logger.error(UserLoggerCnst.USER_INTEREST_MOUDLE,UserLoggerCnst.USER_AI, 
						"报文类型格式不正确！",null);
				throw new AtpBusinessException(RespCodeConstant.RespCode_000002,
						RespCodeConstant.RespCode_000002Desc);
			}
			// 根据交易代码获取交易信息
			tnsTxnService.checkTnsTxn(dto.getTxnCode());
			accMchCacheService.getAccMchCacheByMchId(Long.valueOf(dto.getMchId()));
			// 记录txnLog流水信息
//			insertLnsLog(tnsLog, JSON.toJSONString(dto), tnsTxn);

			// 启动异步线程处理
//			callAsynProcess(dto);
			putMq(dto);
			// 返回结果
			
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_INTEREST_MOUDLE,UserLoggerCnst.USER_AI, 
					"AccInterest计息批量接口出现异常！", e, dto.getId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_INTEREST_MOUDLE,UserLoggerCnst.USER_AI, 
					"AccInterest计息批量接口！", e, dto.getId());
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg("AccInterest计息批量接口" + RespCodeConstant.RespCode_200000Desc);
		} finally {
			ret.setOrdId(dto.getId());
			ret.setTradeDate(dto.getTradeDate());
		}
		return ret;
	}
	
	private void putMq(AccInterestInput dto) {
		for(InterestBean interest : dto.getList()){
			interest.setMchId(dto.getMchId());
			amqpTemplate.convertAndSend("AccInterest.key", JSON.toJSONString(interest));
		}
	}
	
	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception {
	}

}
