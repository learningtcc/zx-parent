package com.ink.admin.msgcenter.controller;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.msgcenter.core.po.SmsTemplateLog;
import com.ink.msgcenter.core.query.SmsTemplateLogQuery;
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
 
@RequestMapping("msgcenter/SmsTemplateLog")
@Controller
public class SmsTemplateLogController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "operate_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/msgcenter/template/smsTemplateLog/list";
	protected static final String CREATE_JSP = "/msgcenter/template/smsTemplateLog/create";
	protected static final String EDIT_JSP = "/msgcenter/template/smsTemplateLog/edit";
	protected static final String SHOW_JSP = "/msgcenter/template/smsTemplateLog/show";
	protected static final String UPDATE_LOG_JSP= "/msgcenter/template/smsTemplate/updateLog";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService msgcenterDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		SmsTemplateLogQuery query = newQuery(SmsTemplateLogQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = msgcenterDubboBaseService.findPage("smsTemplateLogManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/updateLog")
	public ModelAndView updateLog() {
		
		SmsTemplateLogQuery query = newQuery(SmsTemplateLogQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = msgcenterDubboBaseService.findPage("smsTemplateLogManager",query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(UPDATE_LOG_JSP);
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
	public String save(SmsTemplateLog smsTemplateLog) {
		try {
			msgcenterDubboBaseService.save("smsTemplateLogManager",smsTemplateLog);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(SmsTemplateLog smsTemplateLog) {
		SmsTemplateLog model = null;
		try {
			model = (SmsTemplateLog) msgcenterDubboBaseService.getById("smsTemplateLogManager",smsTemplateLog.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(SmsTemplateLog smsTemplateLog) {
		SmsTemplateLog model = null;
		try {
			model = (SmsTemplateLog) msgcenterDubboBaseService.getById("smsTemplateLogManager",smsTemplateLog.getId());
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
	public String update(SmsTemplateLog smsTemplateLog) {
		try {
			msgcenterDubboBaseService.update("smsTemplateLogManager",smsTemplateLog);
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
					msgcenterDubboBaseService.removeById("smsTemplateLogManager",Long.valueOf(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return writeAjaxResponse("1");
	}
}
