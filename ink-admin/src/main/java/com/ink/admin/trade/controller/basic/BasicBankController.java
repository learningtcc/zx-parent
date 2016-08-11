
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.trade.controller.basic;

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
import com.ink.trade.api.platform.basic.model.base.BasicBankEntity;
import com.ink.trade.api.platform.basic.model.in.BasicBankQueryInput;
import com.ink.trade.api.platform.basic.service.IBasicBankService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("/trade/BasicBank")
@Controller
public class BasicBankController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/trade/BasicBank/list";
	protected static final String CREATE_JSP = "/trade/BasicBank/create";
	protected static final String EDIT_JSP = "/trade/BasicBank/edit";
	protected static final String SHOW_JSP = "/trade/BasicBank/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IBasicBankService basicBankService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		BasicBankQueryInput query = newQuery(BasicBankQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<BasicBankEntity>> ret = basicBankService.list(query);
		
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
	public String save(BasicBankEntity basicBank) {
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
	public ModelAndView show(BasicBankEntity basicBank) {
		CommonResult<BasicBankEntity> ret = basicBankService.getById(basicBank.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(BasicBankEntity basicBank) {
		CommonResult<BasicBankEntity> ret = basicBankService.getById(basicBank.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(BasicBankEntity basicBank) {
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
		HttpSession loginSession = super.getLoginSession();
		String userName = "";
		Assertion assertion = loginSession != null ? (Assertion) loginSession.getAttribute("ink_sso_user") : null;
		if(assertion!= null){
			userName = assertion.getPrincipal().getName();
		}
		return userName;
	}
}
