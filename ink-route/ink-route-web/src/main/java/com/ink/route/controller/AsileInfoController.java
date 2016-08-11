
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.controller;

import com.ink.base.BaseController;
import com.ink.base.page.Page;
import com.ink.route.api.common.CommonResult;
import com.ink.route.api.model.in.AsileInfoQueryInput;
import com.ink.route.api.model.po.AsileInfo;
import com.ink.route.api.service.platform.IAsileInfoService;
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
 
@RequestMapping("AsileInfo")
@Controller
public class AsileInfoController extends BaseController {
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "/AsileInfo/list";
	protected static final String CREATE_JSP = "/AsileInfo/create";
	protected static final String EDIT_JSP = "/AsileInfo/edit";
	protected static final String SHOW_JSP = "/AsileInfo/show";
	//redirect paths,startWith: !
	
	@Autowired
	private IAsileInfoService asileInfoService;
	
	@RequestMapping(value="/list")
	public ModelAndView list() {
		@SuppressWarnings("unchecked")
		AsileInfoQueryInput query = newQuery(AsileInfoQueryInput.class,DEFAULT_SORT_COLUMNS);
		CommonResult<Page<AsileInfo>> ret = asileInfoService.list(query);
		if(0!=ret.getCode()){
			throw new RuntimeException(ret.getMessage());
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("page", ret.getBussinessObj());
		modelAndView.setViewName(LIST_JSP);
		
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		ModelAndView model=new  ModelAndView();
		model.setViewName(CREATE_JSP);
		return model;
	}
	
	/** 保存新增对象 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(AsileInfo asileInfo) {
		String userName = this.getLoginUser();
		asileInfo.setCreaterName(userName);
		asileInfo.setUpdaterName(userName);
		asileInfo.setCreateTime(new Date());
		asileInfo.setUpdateTime(new Date());
		CommonResult<Object> ret = asileInfoService.save(asileInfo);
		if(0!=ret.getCode()){
			return writeAjaxResponse(ret.getMessage());
		}
		return writeAjaxResponse("1");
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(AsileInfo asileInfo) {
		CommonResult<AsileInfo> ret = asileInfoService.getById(asileInfo.getId());
		if(0!=ret.getCode()){
			throw new RuntimeException(ret.getMessage());
		}
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(AsileInfo asileInfo) {
		CommonResult<AsileInfo> ret = asileInfoService.getById(asileInfo.getId());
		if(0!=ret.getCode()){
			throw new RuntimeException(ret.getMessage());
		}
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", ret.getBussinessObj());
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(AsileInfo asileInfo) {
		String userName = this.getLoginUser();
		asileInfo.setUpdaterName(userName);
		asileInfo.setUpdateTime(new Date());
		CommonResult<Object> ret = asileInfoService.update(asileInfo);
		if(0!=ret.getCode()){
			return writeAjaxResponse(ret.getMessage());
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
				asileInfoService.deleteById(Long.valueOf(id));
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
