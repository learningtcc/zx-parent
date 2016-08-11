package com.ink.admin.msgcenter.controller;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.msgcenter.core.po.SmsMongoLog;
import com.ink.msgcenter.core.query.SmsMongoLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("msgcenter/SmsMongo")
@Controller
public class SmsMongoLogController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	//forward paths
	protected static final String LIST_JSP= "/msgcenter/log/smsMongo/list";
	protected static final String SHOW_JSP = "/msgcenter/log/smsMongo/show";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService msgcenterDubboBaseService;
	/**
	 * 查询列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list() {
		SmsMongoLogQuery query = newQuery(SmsMongoLogQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = (Page) msgcenterDubboBaseService.executeDynamicMethod("smsMongoLogManager","findSmsMongoLogInfo",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/show")
	public ModelAndView show(SmsMongoLogQuery mongoLogQuery) {
		SmsMongoLog model = null;
		try {
			model = (SmsMongoLog) msgcenterDubboBaseService.executeDynamicMethod("smsMongoLogManager","getById",
					mongoLogQuery.getMerctCode(),mongoLogQuery.getId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
}
