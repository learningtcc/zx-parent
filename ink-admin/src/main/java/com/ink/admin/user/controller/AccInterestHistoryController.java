
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.user.controller;

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.user.core.po.mongo.AccInterestHistory;
import com.ink.user.core.service.mongo.IAccInterestHistoryService;
import com.ink.user.mongo.util.AccInterestHistoryFilter;
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
 
@RequestMapping("user/AccInterestHistory")
@Controller
public class AccInterestHistoryController extends BaseController {
	//forward paths
	protected static final String LIST_JSP= "/user/AccInterestHistory/list";
	protected static final String CREATE_JSP = "/user/AccInterestHistory/create";
	protected static final String EDIT_JSP = "/user/AccInterestHistory/edit";
	protected static final String SHOW_JSP = "/user/AccInterestHistory/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService userDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(String pageNumber, AccInterestHistoryFilter filter) throws Exception {
		List<AccInterestHistory> list = new ArrayList<>();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);

		list = (List<AccInterestHistory>) userDubboBaseService.executeDynamicMethod("accInterestHistoryService","getAccInterestHistorys",filter);
		Page<AccInterestHistory> page = new Page(filter.getPageNumber(),filter.getPageSize(),((Long)userDubboBaseService.executeDynamicMethod("accInterestHistoryService","getCount",filter)).intValue(),list);
		modelAndView.addObject("page", page);

		modelAndView.addObject("filter",filter);
		return modelAndView;
	}
	
}
