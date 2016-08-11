package com.ink.admin.monitor.controller.rule;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.monitor.core.po.MonitorInfoRule;
import com.ink.monitor.core.query.MonitorInfoRuleQuery;
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
 
@RequestMapping("monitor/rule/info")
@Controller
public class MonitorInfoRuleController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/monitor/rule/info/list";
	protected static final String CREATE_JSP = "/monitor/rule/info/create";
	protected static final String EDIT_JSP = "/monitor/rule/info/edit";
	protected static final String SHOW_JSP = "/monitor/rule/info/show";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService monitorDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {

		Page page = null;
		try {
			MonitorInfoRuleQuery query = newQuery(MonitorInfoRuleQuery.class,DEFAULT_SORT_COLUMNS);
			query.setStatus("0");
			page = monitorDubboBaseService.findPage("monitorInfoRuleManager",query);
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
	public String save(MonitorInfoRule monitorInfoRule) {


		try {
			MonitorInfoRuleQuery infoRuleQuery = new MonitorInfoRuleQuery();
			infoRuleQuery.setSysCode(monitorInfoRule.getSysCode());
			infoRuleQuery.setModuleCode(monitorInfoRule.getModuleCode());
			infoRuleQuery.setInfoCode(monitorInfoRule.getInfoCode());
			infoRuleQuery.setQueryType("0");
			List list = null;
			list = monitorDubboBaseService.find("monitorInfoRuleManager",infoRuleQuery);
			if (!(null == list || list.isEmpty())){//判断是否重复
				return writeAjaxResponse("0");
			}
			monitorInfoRule.setStatus("0");
			monitorDubboBaseService.executeDynamicMethod("monitorInfoRuleManager","saveInfo",monitorInfoRule);
		} catch (Exception e) {
			e.printStackTrace();
			monitorInfoRule.setStatus("0");
		}

		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(MonitorInfoRule monitorInfoRule) {
		MonitorInfoRule model = null;
		try {
			model = (MonitorInfoRule) monitorDubboBaseService.getById("monitorInfoRuleManager",monitorInfoRule.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(MonitorInfoRule monitorInfoRule) {
		MonitorInfoRule model = null;
		try {
			model = (MonitorInfoRule) monitorDubboBaseService.getById("monitorInfoRuleManager",monitorInfoRule.getId());
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
	public String update(MonitorInfoRule monitorInfoRule) {
		try {
			monitorDubboBaseService.update("monitorInfoRuleManager",monitorInfoRule);
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
					monitorDubboBaseService.executeDynamicMethod("monitorInfoRuleManager","deleteInfo",Integer.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
}
