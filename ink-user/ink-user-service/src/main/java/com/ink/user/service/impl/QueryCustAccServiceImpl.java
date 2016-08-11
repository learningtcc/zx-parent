package com.ink.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.QueryCustAccInput;
import com.ink.user.api.model.out.QueryCustAccOutput;
import com.ink.user.api.service.IQueryCustAccService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.dao.IAccAccDao;
import com.ink.user.core.dao.IAccCustDao;
import com.ink.user.core.dao.IAccMchDao;
import com.ink.user.core.po.AccAcc;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.tns.ITnsTxnService;
import com.ink.user.util.DateUtils;

/**
 * @Description: 个人账户信息查询
 * @author wanghao
 * @date 2016年5月24日 下午5:20:36
 */
public class QueryCustAccServiceImpl implements IQueryCustAccService{
	@Autowired
	private IAccCustDao accCustDao;
	@Autowired
	private IAccMchDao accMchDao;
	@Autowired
	private ITnsTxnService tnsTxnService;
	@Autowired
	private IAccAccDao accAccDao;
	private static YinkerLogger logger = YinkerLogger.getLogger(QueryCustAccServiceImpl.class);
	
	@Override
	public QueryCustAccOutput exec(QueryCustAccInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QCA, 
				"进入个人账户信息查询........#exec(dto:" + dto.toString()
				+ ")");
		QueryCustAccOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_QCA, ret.toString());
		return ret;
	}

	public QueryCustAccOutput doExec(QueryCustAccInput dto) throws Exception {
		QueryCustAccOutput ret = new QueryCustAccOutput();
		try {
			String txnCode = dto.getTxnCode();
			if (!txnCode.equals(AtpTnsConstant.ACC_QCA)) {
				logger.error(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QCA, 
						"报文类型格式不正确！", null);
				throw new AtpBusinessException(RespCodeConstant.RespCode_000002,
						RespCodeConstant.RespCode_000002Desc);
			}
			// ------根据交易代码获取交易信息----
			TnsTxn tnsTxn = tnsTxnService.checkTnsTxn(txnCode);
			logger.info(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QCA, 
					"根据报文中的交易代码交易类型信息,tnsTxn=【" + tnsTxn.toString() + "】");
			// 判断商户是否存在
			accMchDao.checkAccMch(Long.parseLong(dto.getMchId()));
			// 个人账户信息查询
			AccCust accCust = accCustDao.checkAccCust(
					Long.parseLong(dto.getMchId()), dto.getCustId());
			// 查询账户信息
			AccAcc accAcc = accAccDao.checkByPacIdAndSacType(accCust,
					dto.getAccountType());
			ret.setAccountId(accAcc.getSacId() == null ? accAcc.getPacId()
					.toString() : accAcc.getSacId().toString());
			ret.setOpenDate(DateUtils.getDate(accAcc.getOpenDate()));
			ret.setCloseDate(DateUtils.getDate(accAcc.getCloseDate()));
			ret.setSacType(accAcc.getSacType());
			ret.setStatus(accAcc.getStatus().toString());
			ret.setTradeDate(dto.getTradeDate());
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_QCA, 
					"个人账户信息查询异常！"+e.getMessage(), e, null);
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_QCA, 
					"个人账户信息查询异常！"+e.getMessage(), e, null);
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg(RespCodeConstant.RespCode_200000Desc);
		} 
		
		return ret;
	}
}
