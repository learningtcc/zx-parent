
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.controller;

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
import com.ink.trade.api.platform.asile.model.base.AsileResCodeEntity;
import com.ink.trade.api.platform.asile.model.in.AsileResCodeQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileResCodeService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("AsileResCode")
@Controller
public class AsileResCodeController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/AsileResCode/list";
	protected static final String CREATE_JSP = "/AsileResCode/create";
	protected static final String EDIT_JSP = "/AsileResCode/edit";
	protected static final String SHOW_JSP = "/AsileResCode/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAsileResCodeService asileResCodeService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AsileResCodeQueryInput query = newQuery(AsileResCodeQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<AsileResCodeEntity>> ret = asileResCodeService.list(query);
		
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
	public String save(AsileResCodeEntity asileResCode) {
		String userName = this.getLoginUser();
		asileResCode.setCreaterName(userName);
		asileResCode.setUpdaterName(userName);
		asileResCode.setCreateTime(new Date());
		asileResCode.setUpdateTime(new Date());
		asileResCodeService.save(asileResCode);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AsileResCodeEntity asileResCode) {
		CommonResult<AsileResCodeEntity> ret = asileResCodeService.getById(asileResCode.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AsileResCodeEntity asileResCode) {
		CommonResult<AsileResCodeEntity> ret = asileResCodeService.getById(asileResCode.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AsileResCodeEntity asileResCode) {
		String userName = this.getLoginUser();
		asileResCode.setUpdaterName(userName);
		asileResCode.setUpdateTime(new Date());
		asileResCodeService.update(asileResCode);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				asileResCodeService.deleteById(Long.valueOf(id));
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
