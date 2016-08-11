package com.ink.admin.msgcenter.controller;
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.base.service.DubboBaseService;
import com.ink.msgcenter.core.po.SmsCode;
import com.ink.msgcenter.core.query.SmsCodeQuery;
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
 
@RequestMapping("msgcenter/SmsCode")
@Controller
public class SmsCodeController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "create_time desc"; 
	
	//forward paths
	protected static final String LIST_JSP= "/msgcenter/smsCode/list";
	protected static final String CREATE_JSP = "/msgcenter/smsCode/create";
	protected static final String EDIT_JSP = "/msgcenter/smsCode/edit";
	protected static final String SHOW_JSP = "/msgcenter/smsCode/show";
	//redirect paths,startWith: !
	
	@Autowired
	private DubboBaseService msgcenterDubboBaseService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		SmsCodeQuery query = newQuery(SmsCodeQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = null;
		try {
			page = msgcenterDubboBaseService.findPage("smsCodeManager",query);
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
	public String save(SmsCode smsCode) {
		try {
			msgcenterDubboBaseService.save("smsCodeManager",smsCode);
		} catch (Exception e) {
			e.printStackTrace();
			return writeAjaxResponse("0");
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(SmsCode smsCode) {
		SmsCode model = null;
		try {
			model = (SmsCode) msgcenterDubboBaseService.getById("smsCodeManager",smsCode.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(SmsCode smsCode) {
		SmsCode model = null;
		try {
			model = (SmsCode) msgcenterDubboBaseService.getById("smsCodeManager",smsCode.getId());
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
	public String update(SmsCode smsCode) {
		try {
			msgcenterDubboBaseService.update("smsCodeManager",smsCode);
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
				//smsCodeManager.removeById(Integer.valueOf(id));
			}
		}
		
		return writeAjaxResponse("1");
	}
}
