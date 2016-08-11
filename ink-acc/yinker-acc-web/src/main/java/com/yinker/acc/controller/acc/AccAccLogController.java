
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
import com.yinker.user.core.po.AccAcc;
import com.yinker.user.core.po.mongo.AccAccLog;
import com.yinker.user.core.service.mongo.IAccAccLogService;
import com.yinker.user.mongo.util.AccAccLogFilter;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AccAccLog")
@Controller
public class AccAccLogController extends BaseController {
	//forward paths
	protected static final String LIST_JSP= "/AccAccLog/list";
	protected static final String CREATE_JSP = "/AccAccLog/create";
	protected static final String EDIT_JSP = "/AccAccLog/edit";
	protected static final String SHOW_JSP = "/AccAccLog/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAccAccLogService accAccLogService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(String pageNumber, AccAccLogFilter filter) {
		List<AccAccLog> list = new ArrayList<>();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		try {
			list = accAccLogService.getAccAccLogs(filter);
			Page<AccAccLog> page = new Page(filter.getPageNumber(),filter.getPageSize(),((Long)accAccLogService.getCount(filter)).intValue(),list);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			modelAndView.addObject("page", new Page());
		}
		modelAndView.addObject("filter",filter);
		return modelAndView;
	}
	
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(String id) {
		AccAccLog model = accAccLogService.getById(Long.valueOf(id));
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
}
