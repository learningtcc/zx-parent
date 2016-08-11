package com.ink.trade.service.check;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.exception.orderfail.UserMerchantIllegalException;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.query.AuthQuery;
import com.ink.user.api.model.in.CheckCustInput;
import com.ink.user.api.model.out.CheckCustOutput;
import com.ink.user.api.service.ICheckCustService;
/**
 * 
 *<pre>
 *<b>类描述:</b>(签约检查)
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月18日 下午7:37:58
 *</pre>
 */
public class SignCardCheck extends TradeCheck {
	
	@Autowired
	private ICheckCustService checkCustService;//用户状态检查
    
    @Autowired
    private IAuthManager authManager;//绑卡关系映射
    
    /**
     * 
     * @Description 其他检查
     * @author xuguoqi
     * @date 2016年4月26日 下午5:38:10
     * @param order
     */
    @Override
    public void operateCheck(Order order)  {
        //账户绑卡验证
//    	this.isBingCardCheck(order);
    	//用户合法性校验
    	this.isUserLegal(order);
        this.isCardSign(order.getCardNo(), order.getUserId(), order.getMerchantId());
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
    
    /**
     * 
     * @Description 绑卡检查接口[调用账户系统接口]
     * @author xuguoqi
     * @date 2016年4月21日 下午6:23:26
     * @param order
     */
//    private void isBingCardCheck(Order order){
//    	ACC38120Input input = new ACC38120Input();
//    	input.setTxnCode("ACC38120");
//    	input.setMchId(order.getMerchantId());
//    	input.setCustId(order.getUserId());
//    	input.setOrdId(order.getOrderId());
//    	input.setTradeDate(String.valueOf(new Date()));
//    	input.setCustName(order.getCustName());
//    	input.setIdType(order.getIdType());//证件类型
//    	input.setIdNo(order.getIdNo());//证件号
//    	input.setCardType(order.getCardType());//银行卡类型
//    	input.setCardNo(order.getCardNo());//银行卡号
//    	input.setBankMblNo(order.getBankMblNo());//银行预留手机号
//    	input.setDepositType("APT0000001");
//    	RetOutput ret = null;
//    	try {
//			ret = acc38120Service.exec(input);
//		} catch (Exception e) {
//			throw new TradeException(TradeRespConstant.CHECK_ACC_DUBBO_EX, TradeRespConstant.CHECK_ACC_DUBBO_EX_MSG);
//		}
//    	if(!TradeRespConstant.Trade_SUCCESS.equals(ret.getRetCode())){
//    		throw new TradeException(ret.getRetCode(), ret.getRetMsg());
//    	}
//    }
//    
    /**
     * 
     * @Description 该信息是否已经签约
     * @author xuguoqi
     * @date 2016年4月26日 下午5:29:54
     * @param cardNo
     * @param userId
     * @param mchId
     */
    private void isCardSign(String cardNo, String userId, String mchId) {
        AuthQuery query = new AuthQuery();
        query.setCardNo(cardNo);
        query.setUserId(userId);
        query.setMchId(mchId);
        query.setPayType(PayType.ALL.getValue());
        query.setMasterMark(true);// 查主库
        List<Auth> authList = authManager.find(query);
        if(CollectionUtils.isNotEmpty(authList)){//如果该卡已经签约
            throw new TradeException(TradeRespConstant.TRADE_SIGN_0001,TradeRespConstant.TRADE_SIGN_0001_MSG);
        }
        
    }

}
