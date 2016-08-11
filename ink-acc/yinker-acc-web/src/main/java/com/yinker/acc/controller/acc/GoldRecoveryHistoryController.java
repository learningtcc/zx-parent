
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
import com.yinker.user.core.po.mongo.GoldRecoveryHistory;
import com.yinker.user.core.service.mongo.IGoldRecoveryHistoryService;
import com.yinker.user.mongo.util.GoldRecoveryHistoryFilter;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("GoldRecoveryHistory")
@Controller
public class GoldRecoveryHistoryController extends BaseController {
	//forward paths
	protected static final String LIST_JSP= "/GoldRecoveryHistory/list";
	protected static final String CREATE_JSP = "/GoldRecoveryHistory/create";
	protected static final String EDIT_JSP = "/GoldRecoveryHistory/edit";
	protected static final String SHOW_JSP = "/GoldRecoveryHistory/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IGoldRecoveryHistoryService goldRecoveryHistoryService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(String pageNumber, GoldRecoveryHistoryFilter filter) {
		List<GoldRecoveryHistory> list = new ArrayList<>();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		try {
			list = goldRecoveryHistoryService.getGoldRecoveryHistorys(filter);
			Page<GoldRecoveryHistory> page = new Page(filter.getPageNumber(),filter.getPageSize(),((Long)goldRecoveryHistoryService.getCount(filter)).intValue(),list);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			modelAndView.addObject("page", new Page());
		}
		modelAndView.addObject("filter",filter);
		return modelAndView;
	}
	
}
