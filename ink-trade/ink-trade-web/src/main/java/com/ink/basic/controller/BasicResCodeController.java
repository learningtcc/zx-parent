
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.basic.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.trade.api.platform.basic.model.base.BasicResCodeEntity;
import com.ink.trade.api.platform.basic.model.in.BasicResCodeQueryInput;
import com.ink.trade.api.platform.basic.service.IBasicResCodeService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("BasicResCode")
@Controller
public class BasicResCodeController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/BasicResCode/list";
	protected static final String CREATE_JSP = "/BasicResCode/create";
	protected static final String EDIT_JSP = "/BasicResCode/edit";
	protected static final String SHOW_JSP = "/BasicResCode/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IBasicResCodeService basicResCodeService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		BasicResCodeQueryInput query = newQuery(BasicResCodeQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<BasicResCodeEntity>> ret = basicResCodeService.list(query);
		
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
	public String save(BasicResCodeEntity basicResCode) {
		String userName = this.getLoginUser();
		basicResCode.setCreaterName(userName);
		basicResCode.setUpdaterName(userName);
		basicResCode.setCreateTime(new Date());
		basicResCode.setUpdateTime(new Date());
		basicResCodeService.save(basicResCode);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(BasicResCodeEntity basicResCode) {
		CommonResult<BasicResCodeEntity> ret = basicResCodeService.getById(basicResCode.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(BasicResCodeEntity basicResCode) {
		String userName = this.getLoginUser();
		basicResCode.setUpdaterName(userName);
		basicResCode.setUpdateTime(new Date());
		CommonResult<BasicResCodeEntity> ret = basicResCodeService.getById(basicResCode.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(BasicResCodeEntity basicResCode) {
		basicResCodeService.update(basicResCode);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				basicResCodeService.deleteById(Long.valueOf(id));
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
		HttpSession loginSession = super.getLoginSession();
		String userName = "";
		Assertion assertion = loginSession != null ? (Assertion) loginSession.getAttribute("ink_sso_user") : null;
		if(assertion!= null){
			userName = assertion.getPrincipal().getName();
		}
		return userName;
	}
}
