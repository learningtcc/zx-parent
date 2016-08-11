/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年5月6日 下午1:57:24
 */
package com.ink.trade.controller.sign;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ink.trade.api.enums.CardType;
import com.ink.trade.api.enums.IdType;
import com.ink.trade.api.model.in.SignApplyInput;
import com.ink.trade.api.model.in.SignConfirmInput;
import com.ink.trade.api.model.out.SignApplyOutput;
import com.ink.trade.api.model.out.SignConfirmOutput;
import com.ink.trade.api.service.ISignApplyService;
import com.ink.trade.api.service.ISignConfirmService;
import com.ink.trade.core.cnst.TradeRespConstant;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年5月6日 下午1:57:24
 */
@Controller
@RequestMapping("sign")
public class SignController {
	
	@Autowired
	private ISignApplyService signApplyService;//
	@Autowired
	private ISignConfirmService signConfirmService;
	
	@RequestMapping(value="apply")
	@ResponseBody
	public SignApplyOutput signApply(@ModelAttribute SignApplyInput signApplyInput){
		SignApplyOutput out = null;
		signApplyInput.setCardType(CardType.DebitCard);
        signApplyInput.setIdType(IdType.IdentificationCard);
        if(!this.signApplyCheck(signApplyInput)){
        	out = new SignApplyOutput();
        	out.setReponseCode("参数不可为空");
        	out.setReponseMsg("参数不可为空");
        	return out;
        }
		try {
			out = signApplyService.signApply(signApplyInput);
		} catch (Exception e) {
			out = new SignApplyOutput();
			out.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
			out.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
		}
		return out;
	}
	
	@RequestMapping("confirm")
	@ResponseBody
	public SignConfirmOutput signConfirm(@ModelAttribute SignConfirmInput signConfirmInput ,String idType){
		SignConfirmOutput out =null;
		if(!this.signConfirmCheck(signConfirmInput)){
        	out = new SignConfirmOutput();
        	out.setReponseCode("参数不可为空");
        	out.setReponseMsg("参数不可为空");
        	return out;
		}
		try {
			out = signConfirmService.signConfirm(signConfirmInput);
		} catch (Exception e) {
			out = new SignConfirmOutput();
			out.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
			out.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
		}
		return out;
	}
	
	private boolean signApplyCheck(SignApplyInput signApplyInput){
		if(null==signApplyInput){
			return false;
		}
		if(StringUtils.isBlank(signApplyInput.getCardNo()) || StringUtils.isBlank(signApplyInput.getRealName())  
				|| StringUtils.isBlank(signApplyInput.getIdNo()) || StringUtils.isBlank(signApplyInput.getMerchantId()) 
				|| StringUtils.isBlank(signApplyInput.getUserId()) || StringUtils.isBlank(signApplyInput.getPhoneNo())
				|| StringUtils.isBlank(signApplyInput.getSignOrderId()) || StringUtils.isBlank(signApplyInput.getTradeCode()) 
				 ){
			return false;
		}
        
		return true;
	}
	
	public boolean signConfirmCheck(SignConfirmInput signConfirmInput){
		if(null==signConfirmInput){
			return false;
		}

		if(StringUtils.isBlank(signConfirmInput.getMerchantId()) 
				|| StringUtils.isBlank(signConfirmInput.getUserId()) 
				|| StringUtils.isBlank(signConfirmInput.getSignOrderId()) || StringUtils.isBlank(signConfirmInput.getTradeCode()) 
				|| StringUtils.isBlank(signConfirmInput.getValidMessage())){
			return false;
		}
		return true;
		
	}

}
