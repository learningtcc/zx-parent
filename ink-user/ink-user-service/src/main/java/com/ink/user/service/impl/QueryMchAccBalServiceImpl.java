package com.ink.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.QueryMchAccBalInput;
import com.ink.user.api.model.out.QueryMchAccBalOutput;
import com.ink.user.api.service.IQueryMchAccBalService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.dao.IAccAccDao;
import com.ink.user.core.dao.IAccCustDao;
import com.ink.user.core.po.AccAcc;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.AccMch;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;

/**
 * 查询商户账户余额
 * @author yangchen
 * @date 2016年5月6日 上午10:11:45
 */
public class QueryMchAccBalServiceImpl extends AbstractAccServiceImpl<QueryMchAccBalOutput, QueryMchAccBalInput> implements IQueryMchAccBalService{
	
	@Autowired
	private IAccAccDao accAccDao;
	@Autowired
	private IAccCustDao accCustDao;
	private static YinkerLogger logger = YinkerLogger.getLogger(QueryMchAccBalServiceImpl.class);

	@Override
	public QueryMchAccBalOutput exec(QueryMchAccBalInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_TRANSFERS_MOUDLE,
				UserLoggerCnst.USER_QMAB,
				"进入商户账户查询接口.....QueryMchAccBal#exec()方法,dto=【" + dto.toString() + "】");
		QueryMchAccBalOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QMAB, 
				"ACC37020交易查询结果.....商户信息=" + ret.toString());
		return ret;
	}
	
	public QueryMchAccBalOutput doExec(QueryMchAccBalInput dto) throws Exception {
		// 公用同一个
		QueryMchAccBalOutput ret = new QueryMchAccBalOutput();
		try {
			String json = JSON.toJSONString(dto);
			// 检查商户是否存在
			Map<String, Object> map = check(AtpTnsConstant.ACC_QMAB, json);
			AccMch accMch = (AccMch) map.get("accMch");
			//每个商户拥有一个客户账号
			AccCust accCust = accCustDao.checkAccCust(
					accMch.getMchId(), accMch.getCustId());
			AccAcc accAcc = accAccDao.checkByPacIdAndSacType(accCust,
					dto.getMchAccountType());
			ret.setAmt(accAcc.getCurBal().toString());
			ret.setOrdId(dto.getOrdId());
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
			// 复制
			BeanCopier copier = BeanCopier.create(AccMch.class,
					QueryMchAccBalOutput.class, false);
			copier.copy(accMch, ret, null);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QMAB, 
					"商户账户余额查询交易结果查询出现异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QMAB, 
					"商户账户余额交易结果查询出现异常！"+e.getMessage(), e, dto.getOrdId());
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
	}

}
