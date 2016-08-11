package com.ink.user.service.impl;

import java.util.Map;

import org.springframework.cglib.beans.BeanCopier;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.QueryMchInfoInput;
import com.ink.user.api.model.out.QueryMchInfoOutput;
import com.ink.user.api.service.IQueryMchInfoService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.AccMch;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;

/**
 * 商户状态检查
 * @author yangchen
 * @date 2016年4月19日 下午3:21:20
 */
public class QueryMchInfoServiceImpl extends AbstractAccServiceImpl<QueryMchInfoOutput, QueryMchInfoInput> implements IQueryMchInfoService {
	private static YinkerLogger logger = YinkerLogger.getLogger(QueryMchInfoServiceImpl.class);

	@Override
	public QueryMchInfoOutput exec(QueryMchInfoInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QMI, 
				"QueryMchInfo商户信息查询接口.....#exec()方法,dto=【"
				+ dto.toString() + "】");
		QueryMchInfoOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QMI, 
				"QueryMchInfo商户信息查询结果.....商户信息=" + ret.toString());
		return ret;
	}
	
	public QueryMchInfoOutput doExec(QueryMchInfoInput dto) throws Exception {
		QueryMchInfoOutput ret = new QueryMchInfoOutput();
		try {
			String json = JSON.toJSONString(dto);
			// 检查商户是否存在
			Map<String, Object> map = check(AtpTnsConstant.ACC_QMI, json);
			AccMch accMch = (AccMch) map.get("accMch");
			ret.setOrdId(dto.getOrdId());
			ret.setTxnCode(dto.getTxnCode());
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
			// 复制
			BeanCopier copier = BeanCopier.create(AccMch.class,
					QueryMchInfoOutput.class, false);
			copier.copy(accMch, ret, null);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QMI, 
					"QueryMchInfo商户信息查询接口出现异常！"+e.getMessage(), e, null);
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QMI, 
					"QueryMchInfo商户信息查询接口出现异常！"+e.getMessage(), e, null);
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg(RespCodeConstant.RespCode_200000Desc);
		} finally{
			ret.setTradeDate(dto.getTradeDate());
		}
		return ret;
	}

	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception {
		// TODO Auto-generated method stub

	}
}
