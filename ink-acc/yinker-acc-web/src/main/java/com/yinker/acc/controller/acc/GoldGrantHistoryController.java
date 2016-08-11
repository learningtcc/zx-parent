
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.acc.controller.acc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinker.base.BaseController;
import com.yinker.base.page.Page;
import com.yinker.user.core.po.mongo.GoldGrantHistory;
import com.yinker.user.core.service.mongo.IGoldGrantHistoryService;
import com.yinker.user.mongo.util.GoldGrantHistoryFilter;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("GoldGrantHistory")
@Controller
public class GoldGrantHistoryController extends BaseController {
	//forward paths
	protected static final String LIST_JSP= "/GoldGrantHistory/list";
	protected static final String CREATE_JSP = "/GoldGrantHistory/create";
	protected static final String EDIT_JSP = "/GoldGrantHistory/edit";
	protected static final String SHOW_JSP = "/GoldGrantHistory/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IGoldGrantHistoryService goldGrantHistoryService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(String pageNumber, GoldGrantHistoryFilter filter) {
		List<GoldGrantHistory> list = new ArrayList<>();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		try {
			list = goldGrantHistoryService.getGoldGrantHistorys(filter);
			Page<GoldGrantHistory> page = new Page(filter.getPageNumber(),filter.getPageSize(),((Long)goldGrantHistoryService.getCount(filter)).intValue(),list);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			modelAndView.addObject("page", new Page());
		}
		modelAndView.addObject("filter",filter);
		return modelAndView;
	}

}
