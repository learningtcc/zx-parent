package com.ink.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.QueryTransResultInput;
import com.ink.user.api.model.out.QueryTransResultOutput;
import com.ink.user.api.service.IQueryTransResultService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.dao.IAccMchDao;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.service.tns.ITnsLogService;

/**
 * @Description: 交易结果查询 
 * @author wanghao
 * @date 2016年5月24日 下午4:58:21
 */
public class QueryTransResultServiceImpl implements IQueryTransResultService{

	@Autowired
	private ITnsLogService tnsLogService;
	@Autowired
	private IAccMchDao accMchDao;
	private static YinkerLogger logger = YinkerLogger.getLogger(QueryTransResultServiceImpl.class);
	@Override
	public QueryTransResultOutput exec(QueryTransResultInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QTR, 
				"QueryTransResult交易查询接口.....QueryTransResult#exec()方法,dto=【"+dto.toString()+"】");
		QueryTransResultOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QTR,ret.toString());
		return ret;
	}
	
	public QueryTransResultOutput doExec(QueryTransResultInput dto) throws Exception{
		QueryTransResultOutput ret = new QueryTransResultOutput();
		try {
			
			//2.检查商户是否存在
			accMchDao.checkAccMch(Long.parseLong(dto.getMchId()));
			//3.根据原订单信息查询交易流水
			TnsLog tnsLog = tnsLogService.checkTnsLog(dto.getOriOrdId(),dto.getOriTxnCode(), dto.getMchId());
			if(tnsLog == null){
				throw new AtpBusinessException(RespCodeConstant.RespCode_300030,
						RespCodeConstant.RespCode_300030Desc);
			}
			//返回报文信息
			ret.setOrdId(dto.getOrdId());
			ret.setTxnCode(dto.getTxnCode());
			ret.setTradeDate(dto.getTradeDate());
			ret.setOriRetMsg(tnsLog.getRetMsg());
			ret.setOriRetCode(tnsLog.getRetCode());
			String tradeInfo = this.toJsonString(tnsLog);
			ret.setTradeInfo(tradeInfo);
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		}catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QTR,
					"QueryTransResult交易结果查询出现异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QTR, 
					"QueryTransResult交易结果查询出现异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg(RespCodeConstant.RespCode_200000Desc);
		} 
		
		return ret;
	}
	
	public String toJsonString(TnsLog tnsLog){
		JSONObject jo = new JSONObject();
		jo.put("accTnsId", tnsLog.getId());
		jo.put("mchId", tnsLog.getMchId());
		jo.put("custId", tnsLog.getCustId());
		jo.put("txnCode", tnsLog.getTxnCode());
		jo.put("txnName", tnsLog.getTxnName());
		jo.put("tradeDate", tnsLog.getOrdDate());
		jo.put("ordId", tnsLog.getOrdId());
		jo.put("accountType", tnsLog.getAccSubType());
		jo.put("agaSacType", tnsLog.getAgaSubType());
		jo.put("amt", tnsLog.getAmt());
		jo.put("custFee", tnsLog.getCustFee());
		jo.put("oriOrdId", tnsLog.getOriOrdId());
		jo.put("oriOrdDate", tnsLog.getOriOrdDate());
		jo.put("oriTxnCode", tnsLog.getOriTxnCode());
		jo.put("oriRetCode", tnsLog.getRetCode());
		jo.put("oriRetMsg", tnsLog.getRetMsg());
		String tradeInfo = JSON.toJSONString(jo);
		return tradeInfo;
	}

}
