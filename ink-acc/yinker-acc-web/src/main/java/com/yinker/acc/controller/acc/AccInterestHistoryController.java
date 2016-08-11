
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
import com.yinker.user.core.po.mongo.AccInterestHistory;
import com.yinker.user.core.service.mongo.IAccInterestHistoryService;
import com.yinker.user.mongo.util.AccInterestHistoryFilter;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AccInterestHistory")
@Controller
public class AccInterestHistoryController extends BaseController {
	//forward paths
	protected static final String LIST_JSP= "/AccInterestHistory/list";
	protected static final String CREATE_JSP = "/AccInterestHistory/create";
	protected static final String EDIT_JSP = "/AccInterestHistory/edit";
	protected static final String SHOW_JSP = "/AccInterestHistory/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAccInterestHistoryService accInterestHistoryService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(String pageNumber, AccInterestHistoryFilter filter) {
		List<AccInterestHistory> list = new ArrayList<>();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		try {
			list = accInterestHistoryService.getAccInterestHistorys(filter);
			Page<AccInterestHistory> page = new Page(filter.getPageNumber(),filter.getPageSize(),((Long)accInterestHistoryService.getCount(filter)).intValue(),list);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			modelAndView.addObject("page", new Page());
		}
		modelAndView.addObject("filter",filter);
		return modelAndView;
	}
	
}
