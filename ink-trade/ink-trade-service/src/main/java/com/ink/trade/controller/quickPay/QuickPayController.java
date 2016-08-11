package com.ink.trade.controller.quickPay;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.TradeQuickAuthInput;
import com.ink.trade.api.model.in.TradeQuickPayInput;
import com.ink.trade.api.model.out.TradeQuickAuthOutput;
import com.ink.trade.api.model.out.TradeQuickPayOutput;
import com.ink.trade.api.service.ITradeQuickAuthService;
import com.ink.trade.api.service.ITradeQuickPayService;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.exception.TradeException;

/**
 * 
 * @Description 快捷支付接口
 * @author xuguoqi
 * @date 2016年6月23日 下午5:53:14
 */
@Controller
@RequestMapping("quick")
public class QuickPayController {
	@Autowired
	private ITradeQuickAuthService tradeQuickAuthService;// 快捷鉴权发送短信验证码
	
	@Autowired
	private ITradeQuickPayService tradeQuickPayService;//支付请求接口
	
	private YinkerLogger logger=YinkerLogger.getLogger(QuickPayController.class);
    @RequestMapping(value = "/auth")
    @ResponseBody
    public TradeQuickAuthOutput quickAuth(@ModelAttribute TradeQuickAuthInput input){
        logger.info("快捷支付发送短信验证码开始");
        TradeQuickAuthOutput output = null;
        try {
//            String validateMsg = ValidateUtil.validate(input);
//            // 参数校验
//            if(validateMsg != null && !"".equals(validateMsg)){
//                throw new TradeException(TradeRespConstant.TRADE_ERROR_0001,validateMsg);
//            }
            // 设置币种、证件类型和卡类型
            input.setPayType(PayType.QUICKPAY.getValue());
            input.setTradeCode(TradeType.RECHARGE.getCode());
            output = tradeQuickAuthService.quickAuth(input);
        }catch(TradeException e){
            output = new TradeQuickAuthOutput();
            output.setReponseCode(e.getCode());
            output.setReponseMsg(e.getMessage());
        }catch (Exception e) {
            output = new TradeQuickAuthOutput();
            output.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            output.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }
        return output;
    }
    @RequestMapping("/pay")
    @ResponseBody
    public TradeQuickPayOutput pay(TradeQuickPayInput input) throws IOException{
        logger.info("快捷支付开始");
    	TradeQuickPayOutput output = null;
        try {
            String validateMsg = ValidateUtil.validate(input);
            // 参数校验
            if(validateMsg != null && !"".equals(validateMsg)){
                throw new TradeException(TradeRespConstant.TRADE_ERROR_0001,validateMsg);
            }
            // 设置币种、证件类型和卡类型
            input.setPayType(PayType.QUICKPAY.getValue());
            input.setTradeCode(TradeType.RECHARGE.getCode());
            output = tradeQuickPayService.quickPay(input);
        }catch(TradeException e){
            output = new TradeQuickPayOutput();
            output.setReponseCode(e.getCode());
            output.setReponseMsg(e.getMessage());
        }catch (Exception e) {
            output = new TradeQuickPayOutput();
            output.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            output.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }
        return output;
    }
}
class ValidateUtil{

    public static String validate(Object obj){
        StringBuffer buffer = new StringBuffer();//用于存储验证后的错误信息

        Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<Object>> constraintViolations = validator
                .validate(obj);//验证某个对象,，其实也可以只验证其中的某一个属性的

        Iterator<ConstraintViolation<Object>> iter = constraintViolations
                .iterator();
        while (iter.hasNext()) {
            String message = iter.next().getMessage();
            buffer.append(message);
        }
        return buffer.toString();
    }
}

