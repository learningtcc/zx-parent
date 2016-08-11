package com.ink.admin.monitor.controller.service;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.monitor.core.po.MonitorUserService;
import com.ink.monitor.core.query.MonitorUserServiceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("monitor/service/userService")
@Controller
public class MonitorUserServiceController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/monitor/service/userService/list";
	protected static final String CREATE_JSP = "/monitor/service/userService/create";
	protected static final String EDIT_JSP = "/monitor/service/userService/edit";
	protected static final String SHOW_JSP = "/monitor/service/userService/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService monitorDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		MonitorUserServiceQuery query = newQuery(MonitorUserServiceQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = monitorDubboBaseService.findPage("monitorUserServiceManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		return new ModelAndView(CREATE_JSP);
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(MonitorUserService monitorUserService) {
//		monitorUserServiceManager.save(monitorUserService);
		try {
			monitorDubboBaseService.executeDynamicMethod("monitorUserServiceManager","saveMonitorUserService",monitorUserService);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(MonitorUserService monitorUserService) {
		MonitorUserService model = null;
		try {
			model = (MonitorUserService) monitorDubboBaseService.getById("monitorUserServiceManager",monitorUserService.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(MonitorUserService monitorUserService) {
		MonitorUserService model = null;
		try {
			model = (MonitorUserService) monitorDubboBaseService.getById("monitorUserServiceManager",monitorUserService.getId());
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
	public String update(MonitorUserService monitorUserService) {
		try {
			monitorDubboBaseService.update("monitorUserServiceManager",monitorUserService);
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
					monitorDubboBaseService.removeById("monitorUserServiceManager",Integer.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}

	/**进入选择监控业务功能页面*/
	@RequestMapping(value="/choiceMonitorServiceInfo")
	public ModelAndView choiceMonitorServiceInfo(String userId,String fullName) {
		ModelAndView mav = new ModelAndView("/monitor/service/userService/choiceMonitorServiceInfo");
		mav.addObject("userId",userId);
		mav.addObject("fullName",fullName);
		return mav;
	}

	/**查询监控业务对象——树形结构输出*/
	@RequestMapping(value="/findMonitorServiceTree")
	@ResponseBody
	public Object findMonitorServiceTree(MonitorUserService monitorUserService) {

		List monitorInfoList = new ArrayList();

		try{
			monitorInfoList = (List) monitorDubboBaseService.executeDynamicMethod("monitorUserServiceManager","findMonitorServiceTree",monitorUserService);
		}catch (Exception e){
			e.printStackTrace();
		}

		return monitorInfoList;
	}
}
