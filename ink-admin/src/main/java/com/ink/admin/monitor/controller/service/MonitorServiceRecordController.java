package com.ink.admin.monitor.controller.service; /**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.monitor.core.po.MonitorServiceRecord;
import com.ink.monitor.core.query.MonitorServiceRecordQuery;
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
 
@RequestMapping("monitor/service/record")
@Controller
public class MonitorServiceRecordController extends BaseController {
	protected static final String DEFAULT_SORT_COLUMNS = "  operateTime desc,firstErrorTime desc ";
	
	//forward paths
	protected static final String LIST_JSP= "/monitor/service/record/list";
	protected static final String CREATE_JSP = "/monitor/service/record/create";
	protected static final String EDIT_JSP = "/monitor/service/record/edit";
	protected static final String SHOW_JSP = "/monitor/service/record/show";
	//redirect paths,startWith: !

	@Autowired
	private DubboBaseService monitorDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		MonitorServiceRecordQuery query = newQuery(MonitorServiceRecordQuery.class,DEFAULT_SORT_COLUMNS);
//		if (null == query.getStatus()){
//			query.setStatus("0");
//		}
		Page page = null;
		try {
			page = monitorDubboBaseService.findPage("monitorServiceRecordManager",query);
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
	public String save(MonitorServiceRecord monitorServiceRecord) {
		try {
			monitorDubboBaseService.save("monitorServiceRecordManager",monitorServiceRecord);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	@RequestMapping(value="/show")
	public ModelAndView show(MonitorServiceRecord monitorServiceRecord) {
		MonitorServiceRecord model = null;
		try {
			model = (MonitorServiceRecord) monitorDubboBaseService.getById("monitorServiceRecordManager",monitorServiceRecord.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(MonitorServiceRecord monitorServiceRecord) {
		MonitorServiceRecord model = null;
		try {
			model = (MonitorServiceRecord) monitorDubboBaseService.getById("monitorServiceRecordManager",monitorServiceRecord.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(MonitorServiceRecord monitorServiceRecord) {
		try {
			monitorDubboBaseService.update("monitorServiceRecordManager",monitorServiceRecord);
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
				try {
					monitorDubboBaseService.removeById("monitorServiceRecordManager",Integer.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
}
