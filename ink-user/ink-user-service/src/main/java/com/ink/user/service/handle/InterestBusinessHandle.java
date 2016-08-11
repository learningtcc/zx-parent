package com.ink.user.service.handle;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.InterestBean;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.dao.IAccCustDao;
import com.ink.user.core.po.AccCust;

/**
 * 计息业务处理器
 * @author yangchen
 * @date 2016年5月12日 下午2:53:58
 */
public class InterestBusinessHandle extends AbstractBusinessHandle{
	private static YinkerLogger logger = YinkerLogger.getLogger(InterestBusinessHandle.class);
	@Autowired
	public IAccCustDao accCustDao;
	@Autowired
	private FundBusinessHandle fundBusinessHandle;
	@Autowired
	private AccountBusinessHandle accountBusinessHandle;

	public void doInterest(String json) throws ParseException{
		InterestBean interest = JSON.parse(json, InterestBean.class);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			logger.info(UserLoggerCnst.USER_INTEREST_MOUDLE,UserLoggerCnst.USER_AI, 
					"活期计息处理，interest="+ interest);
			String sacType = interest.getAccountType();
			if(!sacType.equals("0001") 
					&& !sacType.equals("0004")
					&& !sacType.equals("0002")){
				throw new AtpBusinessException(RespCodeConstant.RespCode_400014, RespCodeConstant.RespCode_400014Desc);
			}
			map = interestCheck(interest);
			map.put("txnCode", AtpTnsConstant.ACC_AI);
			AccCust accCust = (AccCust) map.get("accCust");
			accountBusinessHandle.createAccIfNotExist(accCust, interest.getAccountType(), "");
			// 判断利息账户是否存在，不存在增加，账户类型后面+01为对应的利息账户
			accountBusinessHandle.createAccIfNotExist(accCust, interest.getAccountType() + "01", "");
			// 增加账户金额
			fundBusinessHandle.interest(map);
			map.put("retCode", RespCodeConstant.RespCode_000000);
			map.put("retMsg", RespCodeConstant.RespCode_000000Desc);
			map.put("state", "0");
			logger.info(UserLoggerCnst.USER_INTEREST_MOUDLE,UserLoggerCnst.USER_AI, 
					"计息处理成功，interest="+interest.toString());
		} catch(AtpBusinessException e){
			logger.error(UserLoggerCnst.USER_INTEREST_MOUDLE,UserLoggerCnst.USER_AI, 
					"计息处理异常，InterestBean=" + interest.toString(),e,null);
			map.put("retCode", e.getCode());
			map.put("retMsg", e.getMessage());
			map.put("state", "1");

		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_INTEREST_MOUDLE,UserLoggerCnst.USER_AI, 
					"计息处理异常，InterestBean=" + interest.toString(),e,null);
			map.put("retCode", RespCodeConstant.RespCode_200000);
			map.put("retMsg", RespCodeConstant.RespCode_200000Desc);
			map.put("state", "1");

		}finally{
			map.put("mchId", interest.getMchId());
			fundBusinessHandle.saveAccInterestHistory(map ,interest, AtpTnsConstant.ACC_AI);
		}
	}
	
	private Map<String, Object> interestCheck(InterestBean interest) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("interest", interest);
		AccCust accCust = accCustDao.checkAccCust(Long.valueOf(interest.getMchId()), interest.getCustId());
		map.put("accCust", accCust);
		return map;
	}
	
}
