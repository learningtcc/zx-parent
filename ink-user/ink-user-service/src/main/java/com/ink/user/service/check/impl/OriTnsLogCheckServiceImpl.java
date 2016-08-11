package com.ink.user.service.check.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.service.tns.ITnsLogService;
import com.ink.user.service.check.api.Checker;
import com.ink.user.service.check.bean.Constants;
import com.ink.user.util.DateUtils;

/**
 * 原订单流水查询校验
 * @author yangchen
 * @date 2016年2月24日 下午5:24:11
 */
@Service("oriTnsLogCheckService")
public class OriTnsLogCheckServiceImpl extends AbstractCheck implements Checker {
	@Autowired
	private ITnsLogService tnsLogService;
	@Override
	public Map<String, Object> check(String txnCode,
			Map<String, String> dtoMap, Map<String, Object> map)
			throws Exception {
		String mchId = dtoMap.get("mchId");
		String oriOrdId = dtoMap.get("oriOrdId");
		String oriTradeDate = dtoMap.get("oriTradeDate");
		String oriTxnCode = dtoMap.get("oriTxnCode");
		TnsLog oriTnsLog = tnsLogService.findTnsLogByOrdId(Long.valueOf(mchId), oriOrdId,
				DateUtils.strToDateLong(oriTradeDate), oriTxnCode);
		if (null == oriTnsLog) {
			logger.error("原订单流水未找到，mchId : {}, oriOrdId : {}, oriTradeDate : {}, oriTxnCode : {}",
					mchId, oriOrdId, oriTradeDate, oriTxnCode);
			throw new AtpBusinessException(RespCodeConstant.RespCode_300030,
					RespCodeConstant.RespCode_300030Desc
					+ "原订单流水未找到，mchId : " + mchId 
					+ ", oriOrdId : " + oriOrdId );
		}
		// 原订单金额是否与交易金额一致
		String amt = dtoMap.get("amt");
		if(amt != null){// 提现申请撤回时，没有amt字段
			if (new BigDecimal(amt).compareTo(oriTnsLog.getAmt()) != 0) {
				logger.error("与原订单交易金额不一致");
				throw new AtpBusinessException(RespCodeConstant.RespCode_200027,
						RespCodeConstant.RespCode_200027Desc);
			}
		}
		// 原订单手续费信息是否一致
		String custFee = dtoMap.get("custFee");
		if(custFee != null){
			if (new BigDecimal(custFee).compareTo(oriTnsLog.getCustFee()) != 0) {
				logger.error("与原订单手续费不一致");
				throw new AtpBusinessException(RespCodeConstant.RespCode_200028,
						RespCodeConstant.RespCode_200028Desc);
			}
		}
		dtoMap.put("custId", oriTnsLog.getCustId());
		return putMap(map, Constants.OriTnsLog, oriTnsLog);
	}

}
