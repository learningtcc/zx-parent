package com.ink.admin.monitor.controller.service; /**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.monitor.core.po.MonitorService;
import com.ink.monitor.core.po.MonitorUserService;
import com.ink.monitor.core.query.MonitorServiceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("monitor/service/dict")
@Controller
public class MonitorServiceController extends BaseController {
	protected static final String DEFAULT_SORT_COLUMNS = null;

	//forward paths
	protected static final String LIST_JSP= "/monitor/service/dict/list";
	protected static final String CREATE_JSP = "/monitor/service/dict/create";
	protected static final String EDIT_JSP = "/monitor/service/dict/edit";
	protected static final String SHOW_JSP = "/monitor/service/dict/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService monitorDubboBaseService;

	@RequestMapping(value="/list")
	public ModelAndView list() {

		MonitorServiceQuery query = newQuery(MonitorServiceQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = monitorDubboBaseService.findPage("monitorServiceManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
	public String save(MonitorService monitorService) {
//		monitorService.setStatus("0");
		try {
			monitorDubboBaseService.save("monitorServiceManager",monitorService);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}

	@RequestMapping(value="/show")
	public ModelAndView show(MonitorService monitorService) {
		MonitorService model = null;
		try {
			model = (MonitorService) monitorDubboBaseService.getById("monitorServiceManager",monitorService.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping(value="/edit")
	public ModelAndView edit(MonitorService monitorService) {
		MonitorService model = null;
		try {
			model = (MonitorService) monitorDubboBaseService.getById("monitorServiceManager",monitorService.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping(value="/update")
	@ResponseBody
	public String update(MonitorService monitorService) {
		try {
			monitorDubboBaseService.update("monitorServiceManager",monitorService);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){

				MonitorService monitorService = null;
				try {
					monitorService = (MonitorService) monitorDubboBaseService.getById("monitorServiceManager",Integer.valueOf(id));
					//删除服务监控
					MonitorUserService monitorUserService = new MonitorUserService();
					monitorUserService.setServiceId(monitorService.getId());
					monitorDubboBaseService.executeDynamicMethod("monitorUserServiceManager","deleteMonitorUserService",monitorUserService);
					monitorDubboBaseService.executeDynamicMethod("monitorServiceManager","removeById",Integer.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return writeAjaxResponse("1");
	}

}
