
/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.user.controller;

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.user.core.po.mongo.GoldRecoveryHistory;
import com.ink.user.core.service.mongo.IGoldRecoveryHistoryService;
import com.ink.user.mongo.util.GoldRecoveryHistoryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("user/GoldRecoveryHistory")
@Controller
public class GoldRecoveryHistoryController extends BaseController {
    //forward paths
    protected static final String LIST_JSP = "/user/GoldRecoveryHistory/list";
    protected static final String CREATE_JSP = "/user/GoldRecoveryHistory/create";
    protected static final String EDIT_JSP = "/user/GoldRecoveryHistory/edit";
    protected static final String SHOW_JSP = "/user/GoldRecoveryHistory/show";
    //redirect paths,startWith: !

    @Autowired
    private DubboBaseService userDubboBaseService;

    @RequestMapping(value = "/list")
    public ModelAndView list(String pageNumber, GoldRecoveryHistoryFilter filter) {
        List<GoldRecoveryHistory> list = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LIST_JSP);
        try {
            list = (List<GoldRecoveryHistory>) userDubboBaseService.executeDynamicMethod("goldRecoveryHistoryService", "getGoldRecoveryHistorys", filter);
            Page<GoldRecoveryHistory> page = new Page(filter.getPageNumber(), filter.getPageSize(), (Integer) userDubboBaseService.executeDynamicMethod("goldRecoveryHistoryService", "getCount", filter), list);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            modelAndView.addObject("page", new Page());
        }
        modelAndView.addObject("filter", filter);
        return modelAndView;
    }

}
