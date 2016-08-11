package com.ink.admin.monitor.controller.rule;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.monitor.core.po.MonitorUserRule;
import com.ink.monitor.core.query.MonitorUserRuleQuery;
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
 
@RequestMapping("monitor/rule/userule")
@Controller
public class MonitorUserRuleController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/monitor/rule/userule/list";
	protected static final String CREATE_JSP = "/monitor/rule/userule/create";
	protected static final String EDIT_JSP = "/monitor/rule/userule/edit";
	protected static final String SHOW_JSP = "/monitor/rule/userule/show";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService monitorDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		MonitorUserRuleQuery query = newQuery(MonitorUserRuleQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = monitorDubboBaseService.findPage("monitorUserRuleManager",query);
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
	public String save(MonitorUserRule monitorUserRule) {
		try {
			monitorDubboBaseService.executeDynamicMethod("monitorUserRuleManager","saveMonitorUserRule",monitorUserRule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(MonitorUserRule monitorUserRule) {
		MonitorUserRule model = null;
		try {
			model = (MonitorUserRule) monitorDubboBaseService.getById("monitorUserRuleManager",monitorUserRule.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(MonitorUserRule monitorUserRule) {
		MonitorUserRule model = null;
		try {
			model = (MonitorUserRule) monitorDubboBaseService.getById("monitorUserRuleManager",monitorUserRule.getId());
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
	public String update(MonitorUserRule monitorUserRule) {
		try {
			monitorDubboBaseService.update("monitorUserRuleManager",monitorUserRule);
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
					monitorDubboBaseService.removeById("monitorUserRuleManager",Integer.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}

	/**进入选择监控业务功能页面*/
	@RequestMapping(value="/choiceMonitorRuleInfo")
	public ModelAndView choiceMonitorRuleInfo(String userId,String fullName) {
		ModelAndView mav = new ModelAndView("/monitor/rule/userule/choiceMonitorInfo");
		mav.addObject("userId",userId);
		mav.addObject("fullName",fullName);
		return mav;
	}

	/**查询监控业务对象——树形结构输出*/
	@RequestMapping(value="/findMonitorInfoTree")
	@ResponseBody
	public Object findMonitorInfoTree(MonitorUserRule monitorUserRule) {

		List monitorInfoList = new ArrayList();

		try{
			monitorInfoList = (List) monitorDubboBaseService.executeDynamicMethod("monitorUserRuleManager","findMonitorInfoTree",monitorUserRule);
		}catch (Exception e){
			e.printStackTrace();
		}

		return monitorInfoList;
	}
}
