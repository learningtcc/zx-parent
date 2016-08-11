
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.user.controller;

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.user.core.po.mongo.AccAccLog;
import com.ink.user.core.service.mongo.IAccAccLogService;
import com.ink.user.mongo.util.AccAccLogFilter;
import org.apache.commons.lang3.StringUtils;
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
 
@RequestMapping("user/AccAccLog")
@Controller
public class AccAccLogController extends BaseController {
	//forward paths
	protected static final String LIST_JSP= "/user/AccAccLog/list";
	protected static final String CREATE_JSP = "/user/AccAccLog/create";
	protected static final String EDIT_JSP = "/user/AccAccLog/edit";
	protected static final String SHOW_JSP = "/user/AccAccLog/show";
	//redirect paths,startWith: !
	@Autowired
	private DubboBaseService userDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(String pageNumber, AccAccLogFilter filter) throws Exception {
		List<AccAccLog> list = new ArrayList<>();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);

			list = (List<AccAccLog>) userDubboBaseService.executeDynamicMethod("accAccLogService","getAccAccLogs",filter);
			Page<AccAccLog> page = new Page(filter.getPageNumber(),filter.getPageSize(),((Long)userDubboBaseService.executeDynamicMethod("accAccLogService","getCount",filter)).intValue(),list);
			modelAndView.addObject("page", page);
		modelAndView.addObject("filter",filter);
		return modelAndView;
	}
	
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(String id) throws Exception {
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		if(StringUtils.isNotBlank(id)){
			AccAccLog model = (AccAccLog) userDubboBaseService.getById("accAccLogService",Long.valueOf(id));
			mav.addObject("model", model);
		}else{
			mav.addObject("model", new AccAccLog());
		}

		return mav;
	}
	
}
