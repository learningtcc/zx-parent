/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年5月20日 下午7:20:24
 */
package com.ink.trade.service.check;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.exception.orderfail.UserMerchantIllegalException;
import com.ink.user.api.model.in.CheckCustInput;
import com.ink.user.api.model.out.CheckCustOutput;
import com.ink.user.api.service.ICheckCustService;

/**
 * @Description 用户合法性检查
 * @author xuguoqi
 * @date 2016年5月20日 下午7:20:24
 */
public class UserLegalCheck extends TradeCheck{
	
	@Autowired
	private ICheckCustService checkCustService;//用户状态检查

	/**
	 * @Description 检查
	 * @author xuguoqi
	 * @date 2016年5月20日 下午7:20:52
	 * @param order  
	 */
	@Override
	public void operateCheck(Order order) {
		this.isUserLegal(order);
		if (this.getOpt() != null) {
			this.getOpt().operateCheck(order);
	    }
		
	}
	
	/**
     * 
     * @Description 用户合法性验证
     * @author xuguoqi
     * @date 2016年5月19日 下午7:03:47
     * @param order
     */
    private void isUserLegal(Order order){
    	CheckCustInput input = new CheckCustInput();
    	input.setTxnCode("CC");
    	input.setMchId(order.getMerchantId());
    	input.setCustId(order.getUserId());
    	input.setOrdId(order.getOrderId());
    	input.setTradeDate(DateUtil.formatToYYYYMMDDMMHHSSStr(new Date()));
    	CheckCustOutput ret;
    	try {
    		ret = checkCustService.exec(input);
		} catch (Exception e) {
			throw new TradeException(TradeRespConstant.CHECK_ACC_DUBBO_EX, TradeRespConstant.CHECK_ACC_DUBBO_EX_MSG);
		}
    	if(!TradeRespConstant.TRADE_SUCCESS.equals(ret.getRetCode())){
    		throw new UserMerchantIllegalException(ret.getRetCode(), ret.getRetMsg());
    	}
    	
    }
    

}
