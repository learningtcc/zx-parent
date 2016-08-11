
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.controller;

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.BasicBankQueryInput;
import com.ink.route.api.model.po.BasicBank;
import com.ink.route.api.service.platform.IBasicBankService;
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
 
@RequestMapping("BasicBank")
@Controller
public class BasicBankController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/BasicBank/list";
	protected static final String CREATE_JSP = "/BasicBank/create";
	protected static final String EDIT_JSP = "/BasicBank/edit";
	protected static final String SHOW_JSP = "/BasicBank/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IBasicBankService basicBankService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		BasicBankQueryInput query = newQuery(BasicBankQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<BasicBank>> ret = basicBankService.list(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		
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
	public String save(BasicBank basicBank) {
		String userName = this.getLoginUser();
		basicBank.setCreaterName(userName);
		basicBank.setUpdaterName(userName);
		basicBank.setCreateTime(new Date());
		basicBank.setUpdateTime(new Date());
		basicBankService.save(basicBank);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(BasicBank basicBank) {
		CommonResult<BasicBank> ret = basicBankService.getById(basicBank.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(BasicBank basicBank) {
		CommonResult<BasicBank> ret = basicBankService.getById(basicBank.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(BasicBank basicBank) {
		String userName = this.getLoginUser();
		basicBank.setUpdaterName(userName);
		basicBank.setUpdateTime(new Date());
		basicBankService.update(basicBank);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				basicBankService.deleteById(Long.valueOf(id));
			}
		}
		
		return writeAjaxResponse("1");
	}
	
	/**
	 * 
	 * @Description 获取登录用户
	 * @author xuguoqi
	 * @date 2016年7月20日 下午4:55:43
	 * @return
	 */
	public String getLoginUser(){

		return "admin";
	}
}
