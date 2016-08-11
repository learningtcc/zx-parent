
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.admin.trade.controller.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ink.base.BaseController;
import com.ink.base.model.TagDataInfo;
import com.ink.base.page.Page;
import com.ink.trade.api.enums.AuthEnum;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.api.platform.trade.model.base.AuthEntity;
import com.ink.trade.api.platform.trade.model.in.AuthQueryInput;
import com.ink.trade.api.platform.trade.service.IAuthService;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("/trade/Auth")
@Controller
public class AuthController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/trade/Auth/list";
	protected static final String CREATE_JSP = "/trade/Auth/create";
	protected static final String EDIT_JSP = "/trade/Auth/edit";
	protected static final String SHOW_JSP = "/trade/Auth/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAuthService authService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list")
	public ModelAndView list() {
		
		AuthQueryInput query = newQuery(AuthQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<AuthEntity>> ret = authService.findPage(query);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", ret.getBussinessObj());
		modelAndView.addObject("statusData", this.getStatusData());
		
		return modelAndView;
	}
	
	private TagDataInfo getStatusData(){
		TagDataInfo statusData = new TagDataInfo();
		statusData.setJsonArray(AuthEnum.getAuthEnum());
		return statusData;
		
	}
	
//	/** 进入新增页面*/
//	@RequestMapping(value="/create")
//	public ModelAndView create() {
//		return new ModelAndView(CREATE_JSP);
//	}
//	
//	/** 保存新增对象 */
//	@RequestMapping(value="/save")
//	@ResponseBody
//	public String save(Auth auth) {
//		authManager.save(auth);
//		return writeAjaxResponse("1");
//	}
//	
//	/** 查看对象*/
//	@RequestMapping(value="/show")
//	public ModelAndView show(Auth auth) {
//		Auth model = authManager.getById(auth.getId());
//		ModelAndView mav = new ModelAndView(SHOW_JSP);
//		mav.addObject("model", model);
//		return mav;
//	}
//	
//	/**进入更新页面*/
//	@RequestMapping(value="/edit")
//	public ModelAndView edit(Auth auth) {
//		Auth model = authManager.getById(auth.getId());
//		ModelAndView mav = new ModelAndView(EDIT_JSP);
//		mav.addObject("model", model);
//		return mav;
//	}
//	
//	/**保存更新对象*/
//	@RequestMapping(value="/update")
//	@ResponseBody
//	public String update(Auth auth) {
//		authManager.update(auth);
//		return writeAjaxResponse("1");
//	}
//	
//	
//	/**删除对象*/
//	@RequestMapping(value="/delete")
//	@ResponseBody
//	public String delete(String items) {
//		if(items!=null){
//			String[] idArray = items.split(",");
//			for(String id: idArray){
//				authManager.removeById(Integer.valueOf(id));
//			}
//		}
//		
//		return writeAjaxResponse("1");
//	}
}
