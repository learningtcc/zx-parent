package com.ink.trade.service.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ink.trade.api.model.in.PrepaidInput;
import com.ink.trade.api.model.out.PrepaidOutput;
import com.ink.trade.api.service.IPrepaidService;
import com.ink.trade.core.cnst.TradeRespConstant;

@Controller
@RequestMapping("withdraw")
public class WithdrawController {

    @Autowired
    private IPrepaidService withdrawService;

    @RequestMapping(value = "withdraw")
    @ResponseBody
    public PrepaidOutput testWithdraw(@ModelAttribute PrepaidInput withdrawInput) {
        PrepaidOutput withdrawOutput = null;
        if (!this.checkWithdrawInput(withdrawInput)) {
            withdrawOutput = new PrepaidOutput();
            withdrawOutput.setReponseCode("参数不可为空");
            withdrawOutput.setReponseMsg("参数不可为空");
            return withdrawOutput;
        }
        try {
            withdrawOutput = withdrawService.withdraw(withdrawInput);
        } catch (Exception e) {
            e.printStackTrace();
            withdrawOutput.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            withdrawOutput.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }

        return withdrawOutput;
    }

    private boolean checkWithdrawInput(PrepaidInput withdrawInput) {

        //
        if (withdrawInput == null || StringUtils.isBlank(withdrawInput.getAccountType())
                        || StringUtils.isBlank(withdrawInput.getCardNo())) {
            return false;
        }
        return true;

    }

}
