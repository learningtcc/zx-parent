
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.trade.controller.asile;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;
import com.ink.base.model.TagDataInfo;
import com.ink.base.page.Page;
import com.ink.trade.api.platform.asile.model.base.AsileInfoEntity;
import com.ink.trade.api.platform.asile.model.base.AuthChannelPriorityEntity;
import com.ink.trade.api.platform.asile.model.in.AuthChannelPriorityQueryInput;
import com.ink.trade.api.platform.asile.service.IAsileInfoService;
import com.ink.trade.api.platform.asile.service.IAuthChannelPriorityService;
import com.ink.trade.api.platform.common.CommonResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("/trade/AuthChannelPriority")
@Controller
public class AuthChannelPriorityController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/trade/AuthChannelPriority/list";
	protected static final String CREATE_JSP = "/trade/AuthChannelPriority/create";
	protected static final String EDIT_JSP = "/trade/AuthChannelPriority/edit";
	protected static final String SHOW_JSP = "/trade/AuthChannelPriority/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAuthChannelPriorityService authChannelPriorityService;
	
	@Autowired
	private IAsileInfoService asileInfoService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AuthChannelPriorityQueryInput query = newQuery(AuthChannelPriorityQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<AuthChannelPriorityEntity>> ret = authChannelPriorityService.list(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		modelAndView.addObject("asileData", this.getAllAsileInfo());
		
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView(CREATE_JSP);
		modelAndView.addObject("asileData", this.getAllAsileInfo());
		return modelAndView;
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(AuthChannelPriorityEntity authChannelPriority) {
		String userName = this.getLoginUser();
		authChannelPriority.setOperator(userName);
		authChannelPriority.setCreateTime(new Date());
		authChannelPriority.setLastUpdateTime(new Date());
		authChannelPriorityService.save(authChannelPriority);
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AuthChannelPriorityEntity authChannelPriority) {
		CommonResult<AuthChannelPriorityEntity> ret = authChannelPriorityService.getById(authChannelPriority.getId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		mav.addObject("asileData", this.getAllAsileInfo());
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AuthChannelPriorityEntity authChannelPriority) {
		CommonResult<AuthChannelPriorityEntity> ret = authChannelPriorityService.getById(authChannelPriority.getId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", ret.getBussinessObj());
		mav.addObject("asileData", this.getAllAsileInfo());
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AuthChannelPriorityEntity authChannelPriority) {
		String userName = this.getLoginUser();
		authChannelPriority.setOperator(userName);
		authChannelPriority.setLastUpdateTime(new Date());
		authChannelPriorityService.update(authChannelPriority);
		return writeAjaxResponse("1");
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(String items) {
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
				authChannelPriorityService.deleteById(Integer.valueOf(id));
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
	/**
	 * 
	 * @Description 获取渠道信息集合并封装标签信息类中
	 * @author xuguoqi
	 * @date 2016年7月22日 上午10:49:40
	 * @return
	 */
	private TagDataInfo getAllAsileInfo(){
		TagDataInfo AsileInfoData = new TagDataInfo();
		List<AsileInfoEntity> list = asileInfoService.getAll();
		AsileInfoData.setJsonArray(list);
		return AsileInfoData;
		
	}
}
