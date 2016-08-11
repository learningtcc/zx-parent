package com.ink.trade.controller.bindcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ink.trade.api.model.in.BindCardQueryInput;
import com.ink.trade.api.model.out.BindCardQueryOutput;
import com.ink.trade.api.service.IBindCardQueryService;

/**
 * 绑卡查询Controller
 * Created by huohb on 2016/7/7.
 */
@Controller
@RequestMapping("bindcard")
public class BindCardQueryController {

    @Autowired
    private IBindCardQueryService bindCardQueryService;

    @ResponseBody
    @RequestMapping("query")
    public BindCardQueryOutput query(@ModelAttribute BindCardQueryInput input){
        return bindCardQueryService.bindCardQuery(input);
    }
}
