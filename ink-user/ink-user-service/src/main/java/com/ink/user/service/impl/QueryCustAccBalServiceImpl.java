package com.ink.user.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.QueryCustAccBalInput;
import com.ink.user.api.model.out.QueryCustAccBalOutput;
import com.ink.user.api.service.IQueryCustAccBalService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.dao.IAccAccDao;
import com.ink.user.core.dao.IAccCustDao;
import com.ink.user.core.dao.IAccMchDao;
import com.ink.user.core.po.AccAcc;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.tns.ITnsTxnService;

/**
 * @Description: 个人账户余额查询
 * @author wanghao^_^
 * @date 2016年5月24日 下午5:20:52
 */
public class QueryCustAccBalServiceImpl implements IQueryCustAccBalService{
	
	@Autowired
	private IAccAccDao accAccDao;
	@Autowired
	private IAccMchDao accMchDao;
	@Autowired
	private ITnsTxnService tnsTxnService;
	@Autowired
	private IAccCustDao accCustDao;
	private static YinkerLogger logger = YinkerLogger.getLogger(QueryCustAccBalServiceImpl.class);
	
	@Override
	public QueryCustAccBalOutput exec(QueryCustAccBalInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QCAB, 
				"进入个人账户余额查询........QueryCustAccBal#exec(dto:" + dto.toString() + ")");
		QueryCustAccBalOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QCAB, ret.toString());
		return ret;
	}

	public QueryCustAccBalOutput doExec(QueryCustAccBalInput dto) throws Exception {
		QueryCustAccBalOutput ret = new QueryCustAccBalOutput();
		try {
			String txnCode = dto.getTxnCode();
			if (!txnCode.equals(AtpTnsConstant.ACC_QCAB)) {
				logger.error(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QCAB, 
						"报文类型格式不正确！", null);
				throw new AtpBusinessException(RespCodeConstant.RespCode_000002,
						RespCodeConstant.RespCode_000002Desc);
			}
			// ------根据交易代码获取交易信息----
			TnsTxn tnsTxn = tnsTxnService.checkTnsTxn(txnCode);
			logger.info(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QCAB, 
					"根据报文中的交易代码交易类型信息,tnsTxn=【" + tnsTxn.toString() + "】");
			// 1.判断用户是否存在
			AccCust accCust = accCustDao.checkAccCust(
					Long.parseLong(dto.getMchId()), dto.getCustId());
			// 2.判断商户是否存在
			accMchDao.checkAccMch(Long.parseLong(dto.getMchId()));

			//
			List<AccAcc> accList = accAccDao.selectListByPacIdAndSacType(
					accCust.getPacId(), dto.getAccountType());
			if(accList == null || accList.size() == 0){
				throw new AtpBusinessException(RespCodeConstant.RespCode_200000,
						RespCodeConstant.RespCode_200000Desc);
			}
			BigDecimal totalBal = new BigDecimal(0);
			JSONArray json = new JSONArray();
			for (AccAcc accAcc : accList) {
				totalBal = totalBal.add(accAcc.getCurBal());
				if(accAcc.getSacType() != null && !"".equals(accAcc.getSacType())){
					JSONObject jo = new JSONObject();
					jo.put("sacId", accAcc.getSacId());
					jo.put("accountType", accAcc.getSacType());
					jo.put("curBal", accAcc.getCurBal());
					json.add(jo);
				}
				ret.setAccountId(accAcc.getPacId().toString());
			}
			// 5.拼装返回报文
			String jsonObject = JSON.toJSONString(json);
			ret.setBalDetail(jsonObject);
			ret.setAccBal(totalBal.toString());
			ret.setTradeDate(dto.getTradeDate());
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QCAB, 
					"个人账户余额查询（ACC_QCAB）出现异常！"+e.getMessage(), e, null);
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_QUERY_MOUDLE,UserLoggerCnst.USER_QCAB, 
					"个人账户余额查询（ACC_QCAB）出现异常！"+e.getMessage(), e, null);
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg(RespCodeConstant.RespCode_200000Desc);
		}
		return ret;
	}
}
