
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.trade.controller.basic;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.platform.api.model.SecUser;
import com.ink.trade.api.platform.basic.model.base.MchBankEntity;
import com.ink.trade.api.platform.basic.model.in.MchBankQueryInput;
import com.ink.trade.api.platform.basic.service.IMchBankService;
import com.ink.trade.api.platform.common.CommonResult;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("/trade/MchBank")
@Controller
public class MchBankController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/trade/MchBank/list";
	protected static final String CREATE_JSP = "/trade/MchBank/create";
	protected static final String EDIT_JSP = "/trade/MchBank/edit";
	protected static final String SHOW_JSP = "/trade/MchBank/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IMchBankService mchBankService;
	
	@RequestMapping(value="/list")
	@SuppressWarnings("unchecked")
	public ModelAndView list() {
		
		MchBankQueryInput query = newQuery(MchBankQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<MchBankEntity>> ret = mchBankService.list(query);
		
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
	public String save(MchBankEntity mchBank) {
		SecUser secUser = this.getLoginUser();
		if(secUser!=null){
			mchBank.setOperator(secUser.getLoginName());
		}
		mchBank.setCreateTime(new Date());
		mchBank.setLastupdateTime(new Date());
		mchBankService.save(mchBank);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(MchBankEntity mchBank) {
		CommonResult<MchBankEntity> ret = mchBankService.getById(mchBank.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(MchBankEntity mchBank) {
		CommonResult<MchBankEntity> ret = mchBankService.getById(mchBank.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(MchBankEntity mchBank) {
		SecUser secUser = this.getLoginUser();
		if(secUser!=null){
			mchBank.setOperator(secUser.getLoginName());
		}
		mchBank.setLastupdateTime(new Date());
		mchBankService.update(mchBank);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				mchBankService.deleteById(Integer.valueOf(id));
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
	public SecUser getLoginUser(){
		HttpSession loginSession = super.getLoginSession();
		SecUser secUser = (SecUser) loginSession.getAttribute("user");
		return secUser;
	}
}
