package com.ink.admin.monitor.controller.log;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.monitor.core.po.MongoLog;
import com.ink.monitor.core.query.MongoLogQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("monitor/log/mongo")
@Controller
public class MonitorMongoLogController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/monitor/log/mongo/list";
	protected static final String CREATE_JSP = "/monitor/log/mongo/create";
	protected static final String EDIT_JSP = "/monitor/log/mongo/edit";
	protected static final String SHOW_JSP = "/monitor/log/mongo/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService monitorDubboBaseService;
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		MongoLogQuery query = newQuery(MongoLogQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			if (StringUtils.isBlank(query.getEndLogTime())){
				query.setEndLogTime(DateUtil.formatToYYYYMMDDMMHHSS(new Date()));
				getRequest().setAttribute("endLogTime",query.getEndLogTime());
			}
			if (StringUtils.isBlank(query.getStartLogTime())){
				Date endDate = DateUtil.getBeforeDay(DateUtil.formatToyyyyMMddHHmmss(query.getEndLogTime()),5);
				query.setStartLogTime(DateUtil.formatToYYYYMMDDMMHHSS(endDate));
				getRequest().setAttribute("startLogTime",query.getStartLogTime());
			}
			page = (Page) monitorDubboBaseService.executeDynamicMethod("mongoLogServiceManager","findSystemLogInfo",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	

	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(MongoLogQuery mongoLogQuery) {
		MongoLog model = null;
		try {
			model = (MongoLog) monitorDubboBaseService.executeDynamicMethod("mongoLogServiceManager","getById",mongoLogQuery.getSource(),mongoLogQuery.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	

}
