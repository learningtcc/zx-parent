package com.ink.admin.monitor.controller.rule;
/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.monitor.core.po.MonitorModuleRule;
import com.ink.monitor.core.query.MonitorModuleRuleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("monitor/rule/module")
@Controller
public class MonitorModuleRuleController extends BaseController {
	protected static final String DEFAULT_SORT_COLUMNS = null;
	
	//forward paths
	protected static final String LIST_JSP= "/monitor/rule/module/list";
	protected static final String CREATE_JSP = "/monitor/rule/module/create";
	protected static final String EDIT_JSP = "/monitor/rule/module/edit";
	protected static final String SHOW_JSP = "/monitor/rule/module/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService monitorDubboBaseService;

	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception {
		MonitorModuleRuleQuery query = newQuery(MonitorModuleRuleQuery.class,DEFAULT_SORT_COLUMNS);
		query.setStatus("0");
		Page page = monitorDubboBaseService.findPage("monitorModuleRuleManager",query);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);

		return modelAndView;
	}

	@RequestMapping(value="/create")
	public ModelAndView create() {
		return new ModelAndView(CREATE_JSP);
	}

	@RequestMapping(value="/save")
	@ResponseBody
	public String save(MonitorModuleRule monitorModuleRule) {

		try {
			MonitorModuleRuleQuery moduleRuleQuery = new MonitorModuleRuleQuery();
			moduleRuleQuery.setModuleCode(monitorModuleRule.getModuleCode());
			moduleRuleQuery.setSysCode(monitorModuleRule.getSysCode());
			moduleRuleQuery.setQueryType("0");
			List list = null;
			list = monitorDubboBaseService.find("monitorModuleRuleManager",moduleRuleQuery);
			if (!(null == list || list.isEmpty())){//判断是否重复
				return writeAjaxResponse("0");
			}
			monitorModuleRule.setStatus("0");
			monitorDubboBaseService.executeDynamicMethod("monitorModuleRuleManager","saveInfo",monitorModuleRule);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return writeAjaxResponse("1");
	}

	@RequestMapping(value="/show")
	public ModelAndView show(MonitorModuleRule monitorModuleRule) {

		MonitorModuleRule model = null;
		try {
			model = (MonitorModuleRule) monitorDubboBaseService.getById("monitorModuleRuleManager",monitorModuleRule.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping(value="/edit")
	public ModelAndView edit(MonitorModuleRule monitorModuleRule) {
		MonitorModuleRule model = null;
		try {
			model = (MonitorModuleRule) monitorDubboBaseService.getById("monitorModuleRuleManager",monitorModuleRule.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping(value="/update")
	@ResponseBody
	public String update(MonitorModuleRule monitorModuleRule) {
		try {
			monitorDubboBaseService.update("monitorModuleRuleManager",monitorModuleRule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeAjaxResponse("1");
	}


	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				try {
					monitorDubboBaseService.executeDynamicMethod("monitorModuleRuleManager","deleteInfo",Integer.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return writeAjaxResponse("1");
	}
}
