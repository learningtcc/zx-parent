package com.ink.trade.controller.recharge;

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
import com.ink.trade.api.enums.CardType;
import com.ink.trade.api.enums.IdType;
import com.ink.trade.api.enums.TradeCurrency;
import com.ink.trade.api.model.in.PrecollenctionInput;
import com.ink.trade.api.model.out.PrecollectionOutput;
import com.ink.trade.api.service.IPrecollectionService;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.exception.TradeException;

/**
 * 充值Controller
 * Created by huohb on 2016/5/9.
 */
@Controller
@RequestMapping("recharge")
public class RechargeController {
	
    @Autowired
    private IPrecollectionService iRechargeService;// 充值服务
    
    private YinkerLogger logger=YinkerLogger.getLogger(RechargeController.class);
    @RequestMapping(value = "/recharge")
    @ResponseBody
    public PrecollectionOutput recharge(@ModelAttribute PrecollenctionInput input){
        logger.info("dddddddddddddddddddd");
        PrecollectionOutput output = null;
        try {
            String validateMsg = ValidateUtil.validate(input);
            // 参数校验
            if(validateMsg != null && !"".equals(validateMsg)){
                throw new TradeException(TradeRespConstant.TRADE_ERROR_0001,validateMsg);
            }
            // 设置币种、证件类型和卡类型
            input.setCurrency(TradeCurrency.CNY);
            input.setIdType(IdType.IdentificationCard);
            input.setCardType(CardType.DebitCard);
            output = iRechargeService.recharge(input);
        }catch(TradeException e){
            output = new PrecollectionOutput();
            output.setReponseCode(e.getCode());
            output.setReponseMsg(e.getMessage());
        }catch (Exception e) {
            output = new PrecollectionOutput();
            output.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            output.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }
        return output;
    }
    @RequestMapping("getid")
    @ResponseBody
    public String getId() throws IOException{
//        File f=new File("d:/cmbc/id.txt");
//        f.createNewFile();
//        FileOutputStream fileOutputStream = new FileOutputStream(f,true);
//        PrintStream printStream = new PrintStream(fileOutputStream);
//        System.setOut(printStream);
//        System.out.println("start"+System.currentTimeMillis());
//        System.out.println(IdWorker.getNextId());
//        System.out.println("end"+System.currentTimeMillis());
        return "aa";
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

