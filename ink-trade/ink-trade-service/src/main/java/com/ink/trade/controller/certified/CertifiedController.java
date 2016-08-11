package com.ink.trade.controller.certified;

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

import com.ink.trade.api.model.in.CertifiedPayInput;
import com.ink.trade.api.model.in.CertifiedPayValidCodeInput;
import com.ink.trade.api.model.in.PayDirectInput;
import com.ink.trade.api.model.out.CertifiedPayOutput;
import com.ink.trade.api.model.out.CertifiedPayValidCodeOutput;
import com.ink.trade.api.model.out.PayDirectOutput;
import com.ink.trade.api.service.ICertifiedPayDirectService;
import com.ink.trade.api.service.ICertifiedPayService;
import com.ink.trade.api.service.ICertifiedPayValidCodeService;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.exception.TradeException;

@Controller
@RequestMapping("certified")
public class CertifiedController {
    @Autowired
    private ICertifiedPayDirectService certifiedPayDirectService;
    @Autowired
    private ICertifiedPayValidCodeService certifiedPayValidCodeService; 
    @Autowired
    private ICertifiedPayService certifiedPayService;
    
    
    @RequestMapping("drictpay")
    @ResponseBody
    public PayDirectOutput certifiedDrictPay(@ModelAttribute PayDirectInput input ) {
        PayDirectOutput output=null;
        try {
            String validateMsg = ValidateUtil.validate(input);
            // 参数校验
            if(validateMsg != null && !"".equals(validateMsg)){
                throw new TradeException(TradeRespConstant.TRADE_ERROR_0001,validateMsg);
            }
         output= certifiedPayDirectService.pay(input);
        }catch(TradeException e){
            output = new PayDirectOutput();
            output.setReponseCode(e.getCode());
            output.setReponseMsg(e.getMessage());
        }catch (Exception e) {
            output = new PayDirectOutput();
            output.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            output.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }
        return output;
    }
    
    @RequestMapping("auth")
    @ResponseBody
    public CertifiedPayValidCodeOutput certifiedAuth(@ModelAttribute CertifiedPayValidCodeInput input ) {
        CertifiedPayValidCodeOutput output=null;
        try {
            String validateMsg = ValidateUtil.validate(input);
            // 参数校验
            if(validateMsg != null && !"".equals(validateMsg)){
                throw new TradeException(TradeRespConstant.TRADE_ERROR_0001,validateMsg);
            }
         output= certifiedPayValidCodeService.validCode(input);
        }catch(TradeException e){
            output = new CertifiedPayValidCodeOutput();
            output.setReponseCode(e.getCode());
            output.setReponseMsg(e.getMessage());
        }catch (Exception e) {
            output = new CertifiedPayValidCodeOutput();
            output.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            output.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }
        return output;
    }
    
    @RequestMapping("pay")
    @ResponseBody
    public CertifiedPayOutput certifiedPay(@ModelAttribute CertifiedPayInput input ) {
        CertifiedPayOutput output=null;
        try {
            String validateMsg = ValidateUtil.validate(input);
            // 参数校验
            if(validateMsg != null && !"".equals(validateMsg)){
                throw new TradeException(TradeRespConstant.TRADE_ERROR_0001,validateMsg);
            }
         output= certifiedPayService.pay(input);
        }catch(TradeException e){
            output = new CertifiedPayOutput();
            output.setReponseCode(e.getCode());
            output.setReponseMsg(e.getMessage());
        }catch (Exception e) {
            output = new CertifiedPayOutput();
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
