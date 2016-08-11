
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.user.controller;

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.user.core.po.mongo.GoldGrantHistory;
import com.ink.user.core.service.mongo.IGoldGrantHistoryService;
import com.ink.user.mongo.util.GoldGrantHistoryFilter;
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
 
@RequestMapping("user/GoldGrantHistory")
@Controller
public class GoldGrantHistoryController extends BaseController {
	//forward paths
	protected static final String LIST_JSP= "/user/GoldGrantHistory/list";
	protected static final String CREATE_JSP = "/user/GoldGrantHistory/create";
	protected static final String EDIT_JSP = "/user/GoldGrantHistory/edit";
	protected static final String SHOW_JSP = "/user/GoldGrantHistory/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService userDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(String pageNumber, GoldGrantHistoryFilter filter) {
		List<GoldGrantHistory> list = new ArrayList<>();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		try {
			list = (List<GoldGrantHistory>) userDubboBaseService.executeDynamicMethod("goldGrantHistoryService", "getGoldGrantHistorys", filter);
			Page<GoldGrantHistory> page = new Page(filter.getPageNumber(),filter.getPageSize(),(Integer) userDubboBaseService.executeDynamicMethod("goldGrantHistoryService", "getCount", filter),list);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			modelAndView.addObject("page", new Page());
		}
		modelAndView.addObject("filter",filter);
		return modelAndView;
	}

}
