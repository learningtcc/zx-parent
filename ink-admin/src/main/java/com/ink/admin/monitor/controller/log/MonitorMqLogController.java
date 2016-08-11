package com.ink.admin.monitor.controller.log;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.monitor.core.po.MonitorMqLog;
import com.ink.monitor.core.query.MonitorMqLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("monitor/log/rabbit")
@Controller
public class MonitorMqLogController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/monitor/log/rabbit/list";
	protected static final String CREATE_JSP = "/monitor/log/rabbit/create";
	protected static final String EDIT_JSP = "/monitor/log/rabbit/edit";
	protected static final String SHOW_JSP = "/monitor/log/rabbit/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService monitorDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		MonitorMqLogQuery query = newQuery(MonitorMqLogQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = monitorDubboBaseService.findPage("monitorMqLogManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}

	/**
	 * 再次发送MQ
	 * @param id
	 * @return
     */
	@RequestMapping(value="/sendMq")
	@ResponseBody
	public String sendMq(String id){
		String result = "0";
		try{
			MonitorMqLog monitorMqLog = (MonitorMqLog) monitorDubboBaseService.getById("monitorMqLogManager",Long.parseLong(id));

			result = (String) monitorDubboBaseService.executeDynamicMethod("monitorMqLogManager","sendMq",monitorMqLog);

			monitorMqLog = new MonitorMqLog();
			monitorMqLog.setId(Long.valueOf(id));
			monitorMqLog.setLogStatus("1");
			monitorMqLog.setFixedTime(new Date());

			monitorDubboBaseService.executeDynamicMethod("monitorMqLogManager","updateNotNull",monitorMqLog);
		}catch (Exception e){
			result= "0";
		}

		return writeAjaxResponse(result);
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(MonitorMqLog monitorMqLog) {
		MonitorMqLog model = null;
		try {
			model = (MonitorMqLog) monitorDubboBaseService.getById("monitorMqLogManager",monitorMqLog.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(MonitorMqLog monitorMqLog) {
		MonitorMqLog model = null;
		try {
			model = (MonitorMqLog) monitorDubboBaseService.getById("monitorMqLogManager",monitorMqLog.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(MonitorMqLog monitorMqLog) {
		try {
			monitorDubboBaseService.update("monitorMqLogManager",monitorMqLog);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				try {
					monitorDubboBaseService.removeById("monitorMqLogManager",Long.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
}
